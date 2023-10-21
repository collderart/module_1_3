package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.LabelEntity;
import edu.crud.model.PostEntity;
import edu.crud.repository.GenericRepository;
import edu.crud.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.*;
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
        Long nextId = generateNextId(postRepository.getAllActive());
        return postRepository.save(new PostEntity(nextId, content, LocalDateTime.now(), LocalDateTime.now(), labels, PostStatus.ACTIVE));
    }

    @Override
    public Optional<PostEntity> findById(long id) {
        Map<Long, PostEntity> map = postRepository.getAllActive().stream().collect(Collectors.toMap(PostEntity::id, Function.identity()));
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public List<PostEntity> findByIds(Set<Long> ids) {
        List<PostEntity> result = new ArrayList<>();
        postRepository.getAllActive().forEach(entity -> {
            if (ids.contains(entity.id())) {
                result.add(entity);
            }
        });
        return result;
    }

    @Override
    public GenericRepository<PostEntity, Long> getRepository() {
        return postRepository;
    }
}
