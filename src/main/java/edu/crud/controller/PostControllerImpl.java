package edu.crud.controller;

import edu.crud.repository.PostRepository;

public class PostControllerImpl implements CommonController {
    private final PostRepository postRepository;

    public PostControllerImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
