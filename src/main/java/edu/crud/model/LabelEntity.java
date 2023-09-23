package edu.crud.model;

import edu.crud.constants.PostStatus;

import java.io.Serializable;

public record LabelEntity(
        long id,
        String name,
        PostStatus status
) implements Serializable {
    @Override
    public String toString() {
        return
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status;
    }
}
