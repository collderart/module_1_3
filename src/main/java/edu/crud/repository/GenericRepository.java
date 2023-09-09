package edu.crud.repository;

import java.util.List;
import javax.annotation.Nonnull;

public interface GenericRepository<T, ID> {
    T getById(@Nonnull ID id);
    List<T> getAll();
    T save(@Nonnull T t);
    T update(@Nonnull T t);
    void deleteById(@Nonnull ID id);
}
