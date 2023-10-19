package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.LabelEntity;
import edu.crud.repository.LabelRepository;

import javax.annotation.Nonnull;
import java.util.*;
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
    public List<LabelEntity> findByNamesOrCreate(String... labelNames) {
        List<LabelEntity> collected = new ArrayList<>();
        List<LabelEntity> all = labelRepository.getAll();
        all.forEach(label -> {
            if (Arrays.asList(labelNames).contains(label.name())) {
                collected.add(label);
            }
        });
        Set<String> existingNames = collected.stream().map(LabelEntity::name).collect(Collectors.toSet());
        for (String name : labelNames) {
            if (!existingNames.contains(name)) {
                collected.add(createLabel(name));
            }
        }
        return collected;
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
