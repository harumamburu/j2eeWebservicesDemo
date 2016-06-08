package com.my.lab.dao.exception;

public class EntityNotFoundException extends DaoException {

    private final String messageId = "Entity with id=%d not found";
    private final String messageNaturalId = "Entity with natural id=%s of type %s not found";

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public void setEntityIdMessage(Long id){
        message = String.format(messageId, id);
    }

    public void setEntityNaturalIdMessage(String idType, String id) {
        message = String.format(messageNaturalId, id, idType);
    }
}
