package edu.crud.view;

import edu.crud.model.PostEntity;

import java.util.List;

public class PostView implements CommonView<PostEntity> {
    @Override
    public void printEntity(PostEntity entity) {
        System.out.println(entity);
    }

    @Override
    public void printList(List<PostEntity> entities) {
        System.out.println(entities);
    }
}
