package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.LabelEntity;
import edu.crud.repository.LabelRepository;

import javax.annotation.Nonnull;
import java.util.List;

public class LabelControllerImpl implements LabelController {
    private final LabelRepository labelRepository;

    public LabelControllerImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public LabelEntity createLabel(@Nonnull String name) {
        List<LabelEntity> all = labelRepository.getAll();
        long lastId = all.isEmpty() ? 0 : all.get(all.size() - 1).id();
        return labelRepository.save(new LabelEntity(++lastId, name, PostStatus.ACTIVE));
    }
}
