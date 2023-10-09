package edu.crud.view;

import edu.crud.constants.MenuActions;
import edu.crud.constants.PostStatus;
import edu.crud.controller.PostController;
import edu.crud.ex.InvalidParamException;
import edu.crud.model.PostEntity;
import edu.crud.util.RepoUtil;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static edu.crud.constants.MenuActions.*;

public class PostView implements CommonView<PostEntity> {
    private final PostController postController;

    public PostView(PostController controller) {
        this.postController = controller;
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
                    String postContent;
                    System.out.println(CREATE.name() + " selected");
                    System.out.print("Name of Label: ");
                    postContent = scanner.nextLine();
                    PostEntity label = postController.createPost(postContent, List.of());
                    System.out.println("Label created\n" + label);
                }
                case GET -> {
                    String param;
                    System.out.println(GET.name() + " selected");
                    System.out.println("To get all posts enter All");
                    System.out.println("To get post by id enter id of post");

                    try {
                        param = scanner.next(Pattern.compile("All|\\d*", Pattern.CASE_INSENSITIVE));
                    } catch (InputMismatchException e) {
                        throw new InvalidParamException("All or any number");
                    }

                    if ("all".equalsIgnoreCase(param)) {
                        List<PostEntity> all = postController.getAll();
                        all.forEach(System.out::println);
                    } else {
                        Optional<PostEntity> byId = postController.findById(Integer.parseInt(param));
                        byId.ifPresentOrElse(System.out::println, () -> System.out.println("id " + param + " Not Found"));
                    }
                }
                case EDIT -> {
                    System.out.println(EDIT.name() + " selected");
                    System.out.print("enter id of entity to edit: ");
                    long id = scanner.nextInt();

                    Optional<PostEntity> byId = postController.findById(id);
                    if (byId.isEmpty()) {
                        System.out.println("ENTITY NOT FOUND");
                        return;
                    }
                    System.out.print("new label content: ");
                    scanner.nextLine();
                    String content = scanner.nextLine();
                    System.out.print("new label status (Active or Under Review): ");
                    String status;
                    try {
                        status = scanner.next(Pattern.compile("Active|Under Review", Pattern.CASE_INSENSITIVE));
                    } catch (InputMismatchException e) {
                        throw new InvalidParamException("Active or Under Review");
                    }

                    postController.update(new PostEntity(id, content, byId.get().created(), LocalDateTime.now(), List.of(), PostStatus.valueOf(status.replace(" ", "_").toUpperCase())));
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
                    postController.remove(Long.parseLong(param));
                    System.out.println(param + " deleted");
                }
            }
        } while (selectedMenu != 0);
    }
}
