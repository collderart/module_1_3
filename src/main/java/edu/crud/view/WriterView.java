package edu.crud.view;

import edu.crud.model.WriterEntity;

import java.util.List;

public class WriterView implements CommonView<WriterEntity> {
    @Override
    public void printEntity(WriterEntity entity) {
        System.out.println(entity);
    }

    @Override
    public void printList(List<WriterEntity> entities) {
        System.out.println(entities);
    }
}
