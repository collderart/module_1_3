package edu.crud.repository.ex;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(long id) {
        super("Entity Not found with id " + id);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
