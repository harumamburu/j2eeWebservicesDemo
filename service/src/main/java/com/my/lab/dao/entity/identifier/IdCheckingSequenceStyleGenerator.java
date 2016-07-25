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

/**
 * An extension for {@link SequenceStyleGenerator} which allows mixed-type ID generation
 * (an ID won't be generated if it was provided by user)
 */
public class IdCheckingSequenceStyleGenerator extends SequenceStyleGenerator {

    /**
     * keeps user-provided IDs for different entities types
     */
    private static Map<Class<?>, List<Integer>> idsMap = new ConcurrentHashMap<>(2, 0.9f, 1);

    /**
     *Checks if a provided entity is an instance of {@link Identifiable} and if it has an id,
     * generates one if it's not the case
     * @param session see {@link SessionImplementor}
     * @param object an entity object
     * @return {@link Serializable} ID object, either generated or provided by user
     * @throws HibernateException
     */
    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        if (object instanceof Identifiable) {
            Identifiable identifiable = (Identifiable) object;
            Integer id = identifiable.getId();
            if (id != null) {
                // get an ids list for an entity's class and save a provided ID
                List<Integer> ids = idsMap.getOrDefault(identifiable.getClass(), new ArrayList<>());
                ids.add(id);
                idsMap.put(identifiable.getClass(), ids);
                return id;
            }
            // get the last generated ID value holder
            IntegralDataTypeHolder dtHolder = super.getOptimizer().getLastSourceValue();
            // just a null-check for the very first generation
            if (dtHolder != null) {
                // get the value of ID to be generated (the last generated + 1)
                Integer next = Integer.valueOf(dtHolder.makeValue().intValue() + 1);
                // check if such ID value was provided by user for this entity type
                List<Integer> ids = idsMap.get(identifiable.getClass());
                if (ids != null && ids.contains(next)) {
                    synchronized (this.getClass()) {
                        // remove it from the list (it won't be needed any more) if it was
                        // and blank-generate it (related sequence will be queried)
                        while (ids.contains(next)) {
                            ids.remove(next++);
                            super.generate(session, object);
                        }
                    }
                }
            }
        }
        return super.generate(session, object);
    }
}
