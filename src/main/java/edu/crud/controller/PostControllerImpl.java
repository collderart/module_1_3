package edu.crud.controller;

import edu.crud.repository.PostRepository;

import java.util.Scanner;

public class PostControllerImpl implements CommonController {
    private final PostRepository postRepository;
    private final Scanner scanner;

    public PostControllerImpl(Scanner scanner, PostRepository postRepository) {
        this.scanner = scanner;
        this.postRepository = postRepository;
    }
}
