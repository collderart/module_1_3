package edu.crud.view;

import edu.crud.constants.MenuActions;
import edu.crud.constants.PostStatus;
import edu.crud.controller.LabelController;
import edu.crud.ex.InvalidParamException;
import edu.crud.model.LabelEntity;
import edu.crud.util.RepoUtil;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static edu.crud.constants.MenuActions.*;

public class LabelView implements CommonView<LabelEntity> {
    private final LabelController labelController;

    public LabelView(LabelController controller) {
        this.labelController = controller;
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
                    String labelName;
                    System.out.println(CREATE.name() + " selected");
                    System.out.print("Name of Label: ");
                    labelName = scanner.nextLine();
                    LabelEntity label = labelController.createLabel(labelName);
                    System.out.println("Label created\n" + label);
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
                        List<LabelEntity> all = labelController.getAll();
                        all.forEach(System.out::println);
                        System.out.println("TOTAL COUNT " + all.size());
                    } else {
                        Optional<LabelEntity> byId = labelController.findById(Integer.parseInt(param));
                        byId.ifPresentOrElse(System.out::println, () -> System.out.println("id " + param + " Not Found"));
                    }
                }
                case EDIT -> {
                    System.out.println(EDIT.name() + " selected");
                    System.out.print("enter id of entity to edit: ");
                    long id = scanner.nextInt();
                    if (labelController.findById(id).isEmpty()) {
                        System.out.println("ENTITY NOT FOUND");
                        return;
                    }
                    System.out.print("new label name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("new label status (Active or Under Review): ");
                    String status;
                    try {
                        status = scanner.next(Pattern.compile("Active|Under Review", Pattern.CASE_INSENSITIVE));
                    } catch (InputMismatchException e) {
                        throw new InvalidParamException("Active or Under Review");
                    }

                    labelController.update(new LabelEntity(id, name, PostStatus.valueOf(status.replace(" ", "_").toUpperCase())));
                    System.out.println("Label successfully updated");
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
                    labelController.remove(Long.parseLong(param));
                    System.out.println(param + " deleted");
                }
            }
        } while (selectedMenu != 0);
    }
}
