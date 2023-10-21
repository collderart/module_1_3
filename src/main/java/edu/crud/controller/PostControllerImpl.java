package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.LabelEntity;
import edu.crud.model.PostEntity;
import edu.crud.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        return postRepository.getAll();
    }

    @Override
    public Optional<PostEntity> findById(long id) {
        Map<Long, PostEntity> map = postRepository.getAll().stream().collect(Collectors.toMap(PostEntity::id, Function.identity()));
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void update(PostEntity entityToUpdate) {
        postRepository.update(entityToUpdate);
    }

    @Override
    public void remove(long id) {
        postRepository.deleteById(id);
    }
}
