package edu.crud.view;

import edu.crud.constants.MenuActions;
import edu.crud.controller.LabelController;
import edu.crud.model.LabelEntity;

import java.util.Scanner;

import static edu.crud.constants.MenuActions.*;

public class LabelView implements CommonView<LabelEntity> {
    private final LabelController labelController;

    public LabelView(LabelController controller) {
        this.labelController = controller;
    }

    @Override
    public void printMenu(Scanner scanner) throws Exception {
        System.out.println("Select action:");
        System.out.println(CREATE.ordinal() + " - to create Label");
        System.out.println(GET.ordinal() + " - to get Label");
        System.out.println(EDIT.ordinal() + " - to edit Label");
        System.out.println(DELETE.ordinal() + " - to delete Label");
        System.out.println(UNDEFINED.ordinal() + " - go back");
        int selectedMenu;
        while (scanner.hasNext()) {
            selectedMenu = scanner.nextInt();
            if (selectedMenu < 0 || selectedMenu > MenuActions.values().length) {
                throw new IllegalAccessException();
            }

            switch (MenuActions.values()[selectedMenu]) {

                case UNDEFINED -> {
                }
                case CREATE -> {
                    String labelName;
                    System.out.println(CREATE.name() + " selected");
                    System.out.print("Name of Label: ");
                    labelName = scanner.nextLine();
                    LabelEntity label = labelController.createLabel(labelName);
                    System.out.println("Label created\n" + label);
                    scanner.next();
                }
                case GET -> {
                    System.out.println(GET.name() + " selected");
                }
                case EDIT -> {
                    System.out.println(EDIT.name() + " selected");
                }
                case DELETE -> {
                    System.out.println(DELETE.name() + " selected");
                }
            }
        }

    }
}
