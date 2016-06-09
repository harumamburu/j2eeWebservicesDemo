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
        private final String messageNaturalId = "Entity with natural id of a field {%s=%s} already exists";

        public Builder setEntityIdMessage(Long id) {
            message = String.format(messageId, id);
            return this;
        }

        public Builder setEntityNaturalIdMessage(String field, String value) {
            message = String.format(messageNaturalId, field, value);
            return this;
        }

        @Override
        public EntityAlreadyExistsException build() {
            return new EntityAlreadyExistsException(this);
        }
    }
}
