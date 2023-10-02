package edu.crud.model;

import edu.crud.constants.PostStatus;

import java.time.LocalDateTime;
import java.util.List;

public record PostEntity(
        long id,
        String content,
        LocalDateTime created,
        LocalDateTime updated,
        List<LabelEntity> labels,
        PostStatus status
) implements ModelEntity {
    PostEntity withId(long id) {
        return new PostEntity(id, this.content, this.created, this.updated, this.labels, this.status);
    }

}
