package edu.crud.controller;

import com.google.gson.GsonBuilder;
import edu.crud.constants.Repository;
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

    List<T> getAll();

    Optional<T> findById(long id);

    void update(T entityToUpdate);

    void remove(long id);
}
