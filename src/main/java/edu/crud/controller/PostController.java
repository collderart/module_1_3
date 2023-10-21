package edu.crud.controller;

import edu.crud.model.LabelEntity;
import edu.crud.model.PostEntity;

import java.util.List;
import java.util.Set;

public interface PostController extends CommonController<PostEntity> {
    PostEntity createPost(String content, List<LabelEntity> labels);

    List<PostEntity> findByIds(Set<Long> ids);
}
