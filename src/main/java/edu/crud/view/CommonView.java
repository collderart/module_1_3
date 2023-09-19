package edu.crud.view;

import java.util.List;

public interface CommonView<T> {
    void printEntity(T entity);

    void printList(List<T> entities);
}
