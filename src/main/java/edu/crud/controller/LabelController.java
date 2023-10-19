package edu.crud.controller;

import edu.crud.model.LabelEntity;

import java.util.List;

public interface LabelController extends CommonController<LabelEntity> {
    LabelEntity createLabel(String name);

    List<LabelEntity> findByNamesOrCreate(String... labelNames);
}
