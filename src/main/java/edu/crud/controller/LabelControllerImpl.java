package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.LabelEntity;
import edu.crud.repository.LabelRepository;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static edu.crud.util.RepoUtil.generateNextId;

public class LabelControllerImpl implements LabelController {
    private final LabelRepository labelRepository;

    public LabelControllerImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public LabelEntity createLabel(@Nonnull String name) {
        Long nextId = generateNextId(labelRepository.getAll());
        return labelRepository.save(new LabelEntity(nextId, name, PostStatus.ACTIVE));
    }

    @Override
    public List<LabelEntity> getAll() {
        return labelRepository.getAll();
    }

    @Override
    public Optional<LabelEntity> findById(long id) {
        Map<Long, LabelEntity> map = labelRepository.getAll().stream().collect(Collectors.toMap(LabelEntity::id, Function.identity()));
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void update(LabelEntity entityToUpdate) {
        labelRepository.update(entityToUpdate);
    }

    @Override
    public void remove(long id) {
        labelRepository.deleteById(id);
    }
}
