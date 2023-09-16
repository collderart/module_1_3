package edu.crud.controller;

import com.google.gson.GsonBuilder;
import edu.crud.constants.RepoType;
import edu.crud.repository.GsonLabelRepositoryImpl;
import edu.crud.repository.GsonPostRepositoryImpl;
import edu.crud.repository.GsonWriterRepositoryImpl;

import java.io.File;
import java.util.Scanner;

public interface CommonController {

    static CommonController getInstance(RepoType type, File fileForStorage, Scanner scanner) {
        switch (type) {
            case WRITER -> {
                return new WriterControllerImpl(scanner, new GsonWriterRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            case POST -> {
                return new PostControllerImpl(scanner, new GsonPostRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            case LABEL -> {
                return new LabelControllerImpl(scanner, new GsonLabelRepositoryImpl(fileForStorage.getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
            }
            default -> throw new IllegalArgumentException("Unknown report type");
        }
    }
}
