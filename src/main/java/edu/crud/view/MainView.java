package edu.crud.view;

import edu.crud.constants.Repository;
import edu.crud.controller.CommonController;
import edu.crud.controller.LabelController;
import edu.crud.controller.PostController;
import edu.crud.controller.WriterController;
import edu.crud.ex.EntityNotFoundException;
import edu.crud.ex.InvalidParamException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static edu.crud.constants.Repository.*;

public class MainView {
    private final CommonView labelView;
    private final CommonView postView;
    private final CommonView writerView;
    private final Scanner scanner;
    static Map<Repository, File> storageFiles;

    public MainView(CommonView labelView, CommonView postView, CommonView writerView, Scanner scanner) {
        this.labelView = labelView;
        this.postView = postView;
        this.writerView = writerView;
        this.scanner = scanner;
    }

    public static MainView getInstance() throws Exception {
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
        Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
        LabelController label = CommonController.getInstance(LABEL, storageFiles.get(LABEL));
        PostController post = CommonController.getInstance(POST, storageFiles.get(POST));
        WriterController writer = CommonController.getInstance(WRITER, storageFiles.get(WRITER));
        return new MainView(
                new LabelView(label),
                new PostView(post, label),
                new WriterView(writer, post, label),
                scanner);
    }

    public void run() {
        mainMenu(scanner);
    }

    private void mainMenu(Scanner scanner) {
        int selectedMenu = 0;
        do {
            System.out.println("Please select what entity you want to work with:");
            System.out.println(WRITER.ordinal() + " " + WRITER + " menu");
            System.out.println(POST.ordinal() + " " + POST + " menu");
            System.out.println(LABEL.ordinal() + " " + LABEL + " menu");
            try {
                selectedMenu = scanner.nextInt();
                if (selectedMenu < 0 || selectedMenu > Repository.values().length) {
                    throw new InvalidParamException(Arrays.stream(Repository.values()).map(Enum::ordinal).map(String::valueOf).collect(Collectors.joining(", ")));
                }
                scanner.nextLine();
                switch (Repository.values()[selectedMenu]) {
                    case UNDEFINED -> {
                        System.out.println("exit the program");
                        return;
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
                        System.out.println(LABEL.name() + " selected");
                        labelView.printMenu(scanner);
                    }
                }
            } catch (InputMismatchException e) {
                // nextLine to prevent recursion when exception thrown
                scanner.nextLine();
                System.out.println("Only " + Arrays.stream(Repository.values()).map(Enum::ordinal).toList() + " numbers are allowed");
            } catch (InvalidParamException | EntityNotFoundException e) {
                scanner.nextLine();
                System.out.println(e.getMessage());
            }
        } while (selectedMenu != 0);
    }
}
