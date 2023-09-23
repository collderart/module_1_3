package edu.crud.controller;

import edu.crud.model.LabelEntity;

import java.util.List;
import java.util.Optional;

public interface LabelController extends CommonController {
    LabelEntity createLabel(String name);

    List<LabelEntity> getAll();

    Optional<LabelEntity> findById(long id);
}
