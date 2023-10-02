package edu.crud.view;

import edu.crud.controller.CommonController;
import edu.crud.ex.InvalidParamException;
import edu.crud.model.PostEntity;

import java.util.Scanner;

import static edu.crud.constants.MenuActions.*;

public class PostView implements CommonView<PostEntity> {
    private final CommonController postController;

    public PostView(CommonController controller) {
        this.postController = controller;
    }

    @Override
    public void printMenu(Scanner scanner) throws InvalidParamException {
        System.out.println("Select action:");
        System.out.println(CREATE.ordinal() + " - to create Post");
        System.out.println(GET.ordinal() + " - to get Post");
        System.out.println(EDIT.ordinal() + " - to edit Post");
        System.out.println(DELETE.ordinal() + " - to delete Post");
        System.out.println(UNDEFINED.ordinal() + " - go back");
    }
}
