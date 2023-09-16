package edu.crud.model;

import edu.crud.constants.PostStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public record PostEntity(
        long id,
        String content,
        LocalDateTime created,
        LocalDateTime updated,
        List<LabelEntity> labels,
        PostStatus status
) implements Serializable {
    PostEntity withId(long id) {
        return new PostEntity(id, this.content, this.created, this.updated, this.labels, this.status);
    }

}
