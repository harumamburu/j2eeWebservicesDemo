package com.my.lab.dao.exception;

public class EntityNotFoundException extends DaoException {

    public EntityNotFoundException() {
        super();
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(Builder builder) {
        super(builder);
    }

    public static class Builder extends DaoException.Builder {

        private final String messageId = "Entity with id=%d not found";
        private final String messageNaturalId = "Entity with natural id=%s of type %s not found";

        public Builder setEntityIdMessage(Long id){
            message = String.format(messageId, id);
            return this;
        }

        public Builder setEntityNaturalIdMessage(String idType, String id) {
            message = String.format(messageNaturalId, id, idType);
            return this;
        }

        @Override
        public DaoException build() {
            return new EntityNotFoundException(this);
        }
    }
}
