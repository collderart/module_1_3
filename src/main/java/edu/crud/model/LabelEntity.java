package edu.crud.model;

import edu.crud.constants.PostStatus;

public record LabelEntity(
        long id,
        String name,
        PostStatus status
) implements ModelEntity {
    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status;
    }
}
