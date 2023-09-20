package edu.crud.controller;

import com.google.gson.GsonBuilder;
import edu.crud.constants.Repository;
import edu.crud.repository.jsonrepository.GsonLabelRepositoryImpl;
import edu.crud.repository.jsonrepository.GsonPostRepositoryImpl;
import edu.crud.repository.jsonrepository.GsonWriterRepositoryImpl;

import java.io.File;

public interface CommonController {

    static <T extends CommonController> T getInstance(Repository type, File fileForStorage) {
        switch (type) {
            case WRITER -> {
                return (T) new WriterControllerImpl(new GsonWriterRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            case POST -> {
                return (T) new PostControllerImpl(new GsonPostRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            case LABEL -> {
                return (T) new LabelControllerImpl(new GsonLabelRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            default -> throw new IllegalArgumentException("Unknown report type");
        }
    }
}
