package com.my.lab.dao.entity.identifier;

import com.my.lab.core.Identifiable;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IntegralDataTypeHolder;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class IdCheckingSequenceStyleGenerator extends SequenceStyleGenerator {

    private static Map<Class<?>, List<Integer>> idsMap = new ConcurrentHashMap<>(8, 0.9f, 1);

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        if (object instanceof Identifiable) {
            Identifiable identifiable = (Identifiable) object;
            Integer id = identifiable.getId();
            if (id != null) {
                List<Integer> ids = idsMap.getOrDefault(identifiable.getClass(), new ArrayList<>());
                ids.add(id);
                idsMap.put(identifiable.getClass(), ids);
                return id;
            }
            IntegralDataTypeHolder dtHolder = super.getOptimizer().getLastSourceValue();
            if (dtHolder != null) {
                Integer previous = Integer.valueOf(dtHolder.makeValue().intValue());
                synchronized (this.getClass()) {
                    List<Integer> ids = idsMap.get(identifiable.getClass());
                    if (ids != null && ids.contains(++previous)) {
                        ids.remove(previous);
                        super.generate(session, object);
                    }
                }
            }
        }
        return super.generate(session, object);
    }
}
