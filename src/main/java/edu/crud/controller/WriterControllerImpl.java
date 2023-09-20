package edu.crud.controller;

import edu.crud.constants.MenuActions;
import edu.crud.constants.PostStatus;
import edu.crud.constants.Repository;
import edu.crud.model.WriterEntity;
import edu.crud.repository.WriterRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static edu.crud.constants.MenuActions.*;

public class WriterControllerImpl implements CommonController {
    private final WriterRepository writerRepository;

    public WriterControllerImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

//    public static CommonController getInstance(Scanner scanner) throws Exception {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream("project.properties"));
//        File dir = new File(String.valueOf(properties.get("path.to.json.files")));
//        System.out.println(dir.mkdirs() ? "Created new directory for storage: " + dir.getAbsolutePath() :
//                "Directory already created for storage: " + dir.getAbsolutePath());
//        Map<RepoType, File> storageFiles = Map.of(
//                POST, new File(dir.getAbsolutePath() + "/posts.json"),
//                WRITER, new File(dir.getAbsolutePath() + "/writers.json"),
//                LABEL, new File(dir.getAbsolutePath() + "/labels.json"));
//        storageFiles.values().forEach(file -> {
//            if (!file.exists()) {
//                try {
//                    System.out.println(file.createNewFile() ? "Created new file for storage: " + file.getName() :
//                            "File already created for storage: " + dir.getName());
//                } catch (IOException e) {
//                    System.out.println("Cannot create file for storage. Check your path in properties file");
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        return new WriterControllerImpl(scanner,
//                new GsonWriterRepositoryImpl(storageFiles.get(WRITER).getAbsolutePath(), new GsonBuilder().setPrettyPrinting().create()));
//    }

//    @Override
//    public void run() {
//        Scanner scanner = new Scanner(System.in);
//        mainMenu(scanner);
//    }

    private void mainMenu(Scanner scanner) {

        System.out.println("Please number:");
        System.out.println(CREATE.ordinal() + " - to create menu");
        System.out.println(GET.ordinal() + " - to get menu");
        System.out.println(EDIT.ordinal() + " - to edit menu");
        System.out.println(DELETE.ordinal() + " - to delete menu");
        int selectedMenu;
        while (scanner.hasNext()) {
            try {
                selectedMenu = scanner.nextInt();
                if (selectedMenu < 0 || selectedMenu > MenuActions.values().length) {
                    throw new IllegalAccessException();
                }

                switch (MenuActions.values()[selectedMenu]) {
                    case UNDEFINED -> {
                        mainMenu(scanner);
                    }
                    case CREATE -> {
                        System.out.println(CREATE.name() + " selected");
                        System.out.println("Choose what you want to create:");
                        System.out.println("1 - to create Writer");
                        System.out.println("2 - to create Post");
                        System.out.println("3 - to create Label");
                        int selectedEntityType = scanner.nextInt();
                        if (selectedEntityType < 0 || selectedEntityType > Repository.values().length) {
                            throw new IllegalAccessException();
                        }
                        createMenu(scanner, Repository.values()[selectedEntityType]);
                    }
                    case GET -> {
                        System.out.println(GET.name() + " selected");
                        System.out.println("Choose what you want to get:");
                        System.out.println("1 - to create Writer");
                        System.out.println("2 - to create Post");
                        System.out.println("3 - to create Label");
                        getMenu(scanner);
                    }
                    case EDIT -> {
                        System.out.println("Choose what you want to edit:");
                        System.out.println("1 - to create Writer");
                        System.out.println("2 - to create Post");
                        System.out.println("3 - to create Label");
                        System.out.println(EDIT.name() + " selected");
                        editMenu(scanner);
                    }
                    case DELETE -> {
                        System.out.println("Choose what you want to delete:");
                        System.out.println("1 - to create Writer");
                        System.out.println("2 - to create Post");
                        System.out.println("3 - to create Label");
                        System.out.println(DELETE.name() + " selected");
                        deleteMenu(scanner);
                    }
                }
            } catch (Exception e) {
                System.out.println("Only " + Arrays.stream(MenuActions.values()).map(Enum::ordinal).toList() + " numbers are allowed");
                scanner.next();
            }
        }
    }

    private void deleteMenu(Scanner scanner) {

    }

    private void editMenu(Scanner scanner) {

    }

    private void getMenu(Scanner scanner) {

    }

    private void createMenu(Scanner scanner, Repository value) {
        switch (value) {
            case UNDEFINED -> {
                mainMenu(scanner);
            }
            case WRITER -> {
                System.out.println("CREATE WRITER");
                System.out.print("First name: ");
                String fName = scanner.next();
                System.out.print("Last Name: ");
                String lName = scanner.next();

                WriterEntity saved = writerRepository.save(new WriterEntity(-1, fName, lName, new ArrayList<>(), PostStatus.ACTIVE));
                System.out.println("Successfully created WRITER: \n" + saved);

            }
            case POST -> {
                System.out.println("Write your post:");
                String postContent = scanner.nextLine();
                System.out.println("If you want you can add label to post:");
                String postLabel = scanner.nextLine();

//                postRepository.save(new PostEntity(-1, postContent, LocalDateTime.now(), LocalDateTime.now(), List.of(), PostStatus.UNDER_REVIEW));
            }
            case LABEL -> {
                System.out.println("You can create any label to use it for posts");
                String labelName = scanner.nextLine();

//                labelRepository.save(new LabelEntity(-1, labelName, PostStatus.ACTIVE));
            }
        }
    }
}
