package com.my.lab.dao.exception;

public class EntityAlreadyExistsException extends DaoException {

    public EntityAlreadyExistsException() {
        super();
    }

    public EntityAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public EntityAlreadyExistsException(Builder builder) {
        super(builder);
    }

    public static class Builder extends DaoException.Builder {

        private final String messageId = "Entity with id=%d already exists";
        private final String messageNaturalId = "Entity with natural id=%s of type %s already exists";

        public Builder setEntityIdMessage(Long id) {
            message = String.format(messageId, id);
            return this;
        }

        public Builder setEntityNaturalIdMessage(String idType, String id) {
            message = String.format(messageNaturalId, id, idType);
            return this;
        }

        @Override
        public EntityAlreadyExistsException build() {
            return new EntityAlreadyExistsException(this);
        }
    }
}
