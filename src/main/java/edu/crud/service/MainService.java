package edu.crud.service;

import edu.crud.constants.RepoType;
import edu.crud.controller.CommonController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import static edu.crud.constants.MenuActions.*;
import static edu.crud.constants.RepoType.*;

public class MainService {
    private final CommonController writerController;
    private final CommonController postController;
    private final CommonController labelController;
    private final Scanner scanner;
    static Map<RepoType, File> storageFiles;

    private MainService(Scanner scanner, CommonController writerController, CommonController postController, CommonController labelController) {
        this.scanner = scanner;
        this.writerController = writerController;
        this.postController = postController;
        this.labelController = labelController;
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
                scanner,
                CommonController.getInstance(WRITER, storageFiles.get(WRITER), scanner),
                CommonController.getInstance(POST, storageFiles.get(POST), scanner),
                CommonController.getInstance(LABEL, storageFiles.get(LABEL), scanner));
    }

    public void run() {
        mainMenu(scanner);
    }

    private void mainMenu(Scanner scanner) {
//        System.out.println(new WriterEntity(-1, "fName", "lName",
//                List.of(new PostEntity(-1, "some content", LocalDateTime.now(), LocalDateTime.now(),
//                        List.of(new LabelEntity(-1, "LABEL", PostStatus.ACTIVE)), PostStatus.UNDER_REVIEW)),
//                PostStatus.ACTIVE));
        System.out.println("Please select what entity you want to work with:");
        System.out.println(CREATE.ordinal() + " - to create menu");
        System.out.println(GET.ordinal() + " - to get menu");
        System.out.println(EDIT.ordinal() + " - to edit menu");
        System.out.println(DELETE.ordinal() + " - to delete menu");
        int selectedMenu;
        while (scanner.hasNext()) {
            try {
                selectedMenu = scanner.nextInt();
                if (selectedMenu < 0 || selectedMenu > RepoType.values().length) {
                    throw new IllegalAccessException();
                }

                switch (RepoType.values()[selectedMenu]) {
                    case UNDEFINED -> {
                        mainMenu(scanner);
                    }
                    case WRITER -> {
                        System.out.println(WRITER.name() + " selected");

                    }
                    case POST -> {
                        System.out.println(GET.name() + " selected");
                        System.out.println("Choose what you want to get:");
                        System.out.println("1 - to create Writer");
                        System.out.println("2 - to create Post");
                        System.out.println("3 - to create Label");
//                        getMenu(scanner);
                    }
                    case LABEL -> {
                        System.out.println("Choose what you want to edit:");
                        System.out.println("1 - to create Writer");
                        System.out.println("2 - to create Post");
                        System.out.println("3 - to create Label");
                        System.out.println(EDIT.name() + " selected");
//                        editMenu(scanner);
                    }
                }
            } catch (Exception e) {
                System.out.println("Only " + Arrays.stream(RepoType.values()).map(Enum::ordinal).toList() + " numbers are allowed");
                scanner.next();
            }
        }
    }
}
