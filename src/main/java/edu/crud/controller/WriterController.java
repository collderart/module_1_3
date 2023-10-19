package edu.crud.controller;

import edu.crud.model.PostEntity;
import edu.crud.model.WriterEntity;

import java.util.List;

public interface WriterController extends CommonController<WriterEntity> {
    WriterEntity createWriter(String firstName, String lastName, List<PostEntity> posts);
}
