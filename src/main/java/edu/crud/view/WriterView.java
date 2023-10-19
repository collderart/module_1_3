package edu.crud.view;

import edu.crud.constants.MenuActions;
import edu.crud.constants.PostStatus;
import edu.crud.controller.LabelController;
import edu.crud.controller.PostController;
import edu.crud.controller.WriterController;
import edu.crud.ex.InvalidParamException;
import edu.crud.model.LabelEntity;
import edu.crud.model.WriterEntity;
import edu.crud.util.RepoUtil;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static edu.crud.constants.MenuActions.*;

public class WriterView implements CommonView<WriterEntity> {
    private final WriterController writerController;
    private final PostController postController;
    private final LabelController labelController;

    public WriterView(WriterController writerController, PostController post, LabelController label) {
        this.writerController = writerController;
        postController = post;
        labelController = label;
    }

    @Override
    public void printMenu(Scanner scanner) throws InvalidParamException {
        int selectedMenu;
        do {
            RepoUtil.printSubMenu();

            selectedMenu = scanner.nextInt();
            if (selectedMenu < 0 || selectedMenu > MenuActions.values().length) {
                throw new InvalidParamException(Arrays.stream(MenuActions.values()).map(Enum::ordinal).map(String::valueOf).collect(Collectors.joining(", ")));
            }
            scanner.nextLine();
            switch (MenuActions.values()[selectedMenu]) {

                case UNDEFINED -> {
                    System.out.println("Back to main menu...\n");
                    return;
                }
                case CREATE -> {
                    System.out.println("CREATE WRITER");
                    System.out.print("First name: ");
                    String fName = scanner.next();
                    System.out.print("Last Name: ");
                    String lName = scanner.next();
                    WriterEntity writer = writerController.createWriter(fName, lName, new ArrayList<>());
                    System.out.println("Label created\n" + writer);
                }
                case GET -> {
                    String param;
                    System.out.println(GET.name() + " selected");
                    System.out.println("To get all labels enter All");
                    System.out.println("To get label by id enter id of label");

                    try {
                        param = scanner.next(Pattern.compile("All|\\d*", Pattern.CASE_INSENSITIVE));
                    } catch (InputMismatchException e) {
                        throw new InvalidParamException("All or any number");
                    }

                    if ("all".equalsIgnoreCase(param)) {
                        List<WriterEntity> all = writerController.getAll();
                        all.forEach(System.out::println);
                        System.out.println("TOTAL COUNT " + all.size());
                    } else {
                        Optional<WriterEntity> byId = writerController.findById(Integer.parseInt(param));
                        byId.ifPresentOrElse(System.out::println, () -> System.out.println("id " + param + " Not Found"));
                    }
                }
                case EDIT -> {
                    System.out.println(EDIT.name() + " selected");
                    System.out.print("enter id of entity to edit: ");
                    long id = scanner.nextInt();
                    if (writerController.findById(id).isEmpty()) {
                        System.out.println("ENTITY NOT FOUND");
                        return;
                    }
                    System.out.print("new First name: ");
                    String fName = scanner.next();
                    System.out.print("new Last Name: ");
                    String lName = scanner.next();
                    System.out.print("new Labels (add names separated by commas): ");
                    scanner.nextLine();
                    String labelNames = scanner.nextLine();
                    List<LabelEntity> labels = labelController.findByNamesOrCreate(labelNames.split(","));
                    System.out.print("new label status (Active or Under Review): ");
                    String status;
                    try {
                        status = scanner.next(Pattern.compile("Active|Under Review", Pattern.CASE_INSENSITIVE));
                    } catch (InputMismatchException e) {
                        throw new InvalidParamException("Active or Under Review");
                    }

                    writerController.update(new WriterEntity(id, fName, lName, new ArrayList<>(), PostStatus.valueOf(status.replace(" ", "_").toUpperCase())));
                    System.out.println("Writer successfully updated");
                }
                case DELETE -> {
                    String param;
                    System.out.println(DELETE.name() + " selected");
                    System.out.print("Enter id to delete: ");
                    try {
                        param = scanner.next(Pattern.compile("\\d*"));
                    } catch (InputMismatchException e) {
                        throw new InvalidParamException("numbers");
                    }
                    writerController.remove(Long.parseLong(param));
                    System.out.println(param + " deleted");
                }
            }
        } while (selectedMenu != 0);
    }
}
