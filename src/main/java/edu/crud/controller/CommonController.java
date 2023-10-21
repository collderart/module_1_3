package edu.crud.controller;

import com.google.gson.GsonBuilder;
import edu.crud.constants.Repository;
import edu.crud.repository.GenericRepository;
import edu.crud.repository.jsonrepository.GsonLabelRepositoryImpl;
import edu.crud.repository.jsonrepository.GsonPostRepositoryImpl;
import edu.crud.repository.jsonrepository.GsonWriterRepositoryImpl;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface CommonController<T> {

    static <K extends CommonController> K getInstance(Repository type, File fileForStorage) {
        switch (type) {
            case WRITER -> {
                return (K) new WriterControllerImpl(new GsonWriterRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            case POST -> {
                return (K) new PostControllerImpl(new GsonPostRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            case LABEL -> {
                return (K) new LabelControllerImpl(new GsonLabelRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            default -> throw new IllegalArgumentException("Unknown report type");
        }
    }

    default List<T> getAll() {
        return getRepository().getAllActive();
    }

    Optional<T> findById(long id);

    default void update(T entityToUpdate) {
        getRepository().update(entityToUpdate);
    }

    default void remove(long id) {
        getRepository().deleteById(id);
    }

    GenericRepository<T, Long> getRepository();
}
