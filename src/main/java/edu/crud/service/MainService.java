package edu.crud.service;

import edu.crud.constants.Repository;
import edu.crud.controller.CommonController;
import edu.crud.view.CommonView;
import edu.crud.view.LabelView;
import edu.crud.view.PostView;
import edu.crud.view.WriterView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import static edu.crud.constants.MenuActions.EDIT;
import static edu.crud.constants.Repository.*;

public class MainService {
    private final CommonView labelView;
    private final CommonView postView;
    private final CommonView writerView;
    private final Scanner scanner;
    static Map<Repository, File> storageFiles;

    public MainService(CommonView labelView, CommonView postView, CommonView writerView, Scanner scanner) {
        this.labelView = labelView;
        this.postView = postView;
        this.writerView = writerView;
        this.scanner = scanner;
    }

    public static MainService getInstance() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("project.properties"));
        File dir = new File(String.valueOf(properties.get("path.to.json.files")));
        System.out.println(dir.mkdirs() ? "Created new directory for storage: " + dir.getAbsolutePath() :
                "Directory already created for storage: " + dir.getAbsolutePath());
        storageFiles = Map.of(
                POST, new File(dir.getAbsolutePath() + "/posts.json"),
                WRITER, new File(dir.getAbsolutePath() + "/writers.json"),
                LABEL, new File(dir.getAbsolutePath() + "/labels.json"));
        storageFiles.values().forEach(file -> {
            if (!file.exists()) {
                try {
                    System.out.println(file.createNewFile() ? "Created new file for storage: " + file.getName() :
                            "File already created for storage: " + dir.getName());
                } catch (IOException e) {
                    System.out.println("Cannot create file for storage. Check your path in properties file");
                    throw new RuntimeException(e);
                }
            }
        });
        Scanner scanner = new Scanner(System.in);
        return new MainService(
                new LabelView(CommonController.getInstance(LABEL, storageFiles.get(LABEL))),
                new PostView(CommonController.getInstance(POST, storageFiles.get(POST))),
                new WriterView(CommonController.getInstance(WRITER, storageFiles.get(WRITER))),
                scanner);
    }

    public void run() {
        mainMenu(scanner);
    }

    private void mainMenu(Scanner scanner) {
        System.out.println("Please select what entity you want to work with:");
        System.out.println(WRITER.ordinal() + " " + WRITER + " menu");
        System.out.println(POST.ordinal() + " " + POST + " menu");
        System.out.println(LABEL.ordinal() + " " + LABEL + " menu");
        int selectedMenu;
        while (scanner.hasNext()) {
            try {
                selectedMenu = scanner.nextInt();
                if (selectedMenu < 0 || selectedMenu > Repository.values().length) {
                    throw new IllegalAccessException();
                }

                switch (Repository.values()[selectedMenu]) {
                    case UNDEFINED -> {
                        mainMenu(scanner);
                    }
                    case WRITER -> {
                        System.out.println(WRITER.name() + " selected");
                        writerView.printMenu(scanner);
                    }
                    case POST -> {
                        System.out.println(POST.name() + " selected");
                        postView.printMenu(scanner);
                    }
                    case LABEL -> {
                        System.out.println(EDIT.name() + " selected");
                        labelView.printMenu(scanner);
                    }
                }
            } catch (Exception e) {
                System.out.println("Only " + Arrays.stream(Repository.values()).map(Enum::ordinal).toList() + " numbers are allowed");
                scanner.next();
            }
        }
    }
}
