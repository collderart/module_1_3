package edu.crud.controller;

import edu.crud.model.LabelEntity;

public interface LabelController extends CommonController{
    LabelEntity createLabel(String name);
}
