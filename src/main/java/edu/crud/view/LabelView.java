package edu.crud.view;

import edu.crud.model.LabelEntity;

import java.util.List;

public class LabelView implements CommonView<LabelEntity> {
    @Override
    public void printEntity(LabelEntity entity) {
        System.out.println(entity);
    }

    @Override
    public void printList(List<LabelEntity> entities) {
        System.out.println(entities);
    }
}
