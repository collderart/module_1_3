package edu.crud.model;

import edu.crud.constants.PostStatus;

import java.io.Serializable;
import java.util.List;

public record WriterEntity(
        long id,
        String firstName,
        String lastName,
        List<PostEntity> posts,
        PostStatus status
) implements Serializable {
}
