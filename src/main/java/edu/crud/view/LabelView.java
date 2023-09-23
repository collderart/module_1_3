package edu.crud.view;

import edu.crud.constants.MenuActions;
import edu.crud.controller.LabelController;
import edu.crud.model.LabelEntity;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;

import static edu.crud.constants.MenuActions.*;

public class LabelView implements CommonView<LabelEntity> {
    private final LabelController labelController;

    public LabelView(LabelController controller) {
        this.labelController = controller;
    }

    @Override
    public void printMenu(Scanner scanner) throws Exception {
        int selectedMenu;
        do {
            System.out.println("Select action:");
            System.out.println(CREATE.ordinal() + " - to create Label");
            System.out.println(GET.ordinal() + " - to get Label");
            System.out.println(EDIT.ordinal() + " - to edit Label");
            System.out.println(DELETE.ordinal() + " - to delete Label");
            System.out.println(UNDEFINED.ordinal() + " - go back");
            selectedMenu = scanner.nextInt();
            if (selectedMenu < 0 || selectedMenu > MenuActions.values().length) {
                throw new IllegalAccessException();
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

                    param = scanner.next(Pattern.compile("All|\\d*", Pattern.CASE_INSENSITIVE));
                    if ("all".equalsIgnoreCase(param)) {
                        List<LabelEntity> all = labelController.getAll();
                        all.forEach(System.out::println);
                    } else {
                        Optional<LabelEntity> byId = labelController.findById(Integer.parseInt(param));
                        byId.ifPresentOrElse(System.out::println, () -> System.out.println("id " + param + " Not Found"));
                    }
                }
                case EDIT -> {
                    System.out.println(EDIT.name() + " selected");
                }
                case DELETE -> {
                    System.out.println(DELETE.name() + " selected");
                }
            }
        } while (selectedMenu != 0);
    }
}
