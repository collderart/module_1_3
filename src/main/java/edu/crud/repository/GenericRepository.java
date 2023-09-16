package edu.crud.repository;

import javax.annotation.Nonnull;
import java.util.List;

public interface GenericRepository<T, ID> {
    T getById(@Nonnull ID id);
    List<T> getAll();
    T save(@Nonnull T t);
    T update(@Nonnull T t);
    void deleteById(@Nonnull ID id);
}
