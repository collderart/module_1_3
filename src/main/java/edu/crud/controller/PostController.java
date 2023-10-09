package edu.crud.controller;

import edu.crud.model.LabelEntity;
import edu.crud.model.PostEntity;

import java.util.List;
import java.util.Optional;

public interface PostController extends CommonController {
    PostEntity createPost(String content, List<LabelEntity> labels);

    List<PostEntity> getAll();

    Optional<PostEntity> findById(long id);

    void update(PostEntity entityToUpdate);

    void remove(long id);
}
