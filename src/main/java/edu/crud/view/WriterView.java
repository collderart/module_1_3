package edu.crud.view;

import edu.crud.controller.CommonController;
import edu.crud.model.WriterEntity;

import java.util.Scanner;

import static edu.crud.constants.MenuActions.*;

public class WriterView implements CommonView<WriterEntity> {
    private final CommonController writerController;
    public WriterView(CommonController writerController) {
        this.writerController = writerController;
    }

    @Override
    public void printMenu(Scanner scanner) throws Exception {
        System.out.println("Select action:");
        System.out.println(CREATE.ordinal() + " - to create Writer");
        System.out.println(GET.ordinal() + " - to get Writer");
        System.out.println(EDIT.ordinal() + " - to edit Writer");
        System.out.println(DELETE.ordinal() + " - to delete Writer");
        System.out.println(UNDEFINED.ordinal() + " - go back");
    }
}
