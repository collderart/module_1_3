package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.LabelEntity;
import edu.crud.model.PostEntity;
import edu.crud.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static edu.crud.util.RepoUtil.generateNextId;

public class PostControllerImpl implements PostController {
    private final PostRepository postRepository;

    public PostControllerImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostEntity createPost(String content, List<LabelEntity> labels) {
        Long nextId = generateNextId(postRepository.getAll());
        return postRepository.save(new PostEntity(nextId, content, LocalDateTime.now(), LocalDateTime.now(), labels, PostStatus.ACTIVE));
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
