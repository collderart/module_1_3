package edu.crud.controller;

import edu.crud.model.LabelEntity;
import edu.crud.model.PostEntity;
import edu.crud.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostControllerImpl implements PostController {
    private final PostRepository postRepository;

    public PostControllerImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostEntity createLabel(String content, List<LabelEntity> labels) {
        return null;
    }

    @Override
    public List<PostEntity> getAll() {
        return null;
    }

    @Override
    public Optional<PostEntity> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(PostEntity entityToUpdate) {

    }

    @Override
    public void remove(long id) {

    }
}
