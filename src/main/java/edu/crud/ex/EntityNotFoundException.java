package edu.crud.ex;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(long id) {
        super("ENTITY NOT FOUND WITH ID " + id);
    }
}
