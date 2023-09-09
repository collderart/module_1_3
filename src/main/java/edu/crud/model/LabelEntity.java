package edu.crud.model;

import edu.crud.constants.PostStatus;

public record LabelEntity(
        long id,
        String name,
        PostStatus status
) {
}
