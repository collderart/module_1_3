package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.PostEntity;
import edu.crud.model.WriterEntity;
import edu.crud.repository.GenericRepository;
import edu.crud.repository.WriterRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static edu.crud.util.RepoUtil.generateNextId;

public class WriterControllerImpl implements WriterController {
    private final WriterRepository writerRepository;

    public WriterControllerImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }

    @Override
    public WriterEntity createWriter(String firstName, String lastName, List<PostEntity> posts) {
        Long nextId = generateNextId(writerRepository.getAll());
        return writerRepository.save(new WriterEntity(nextId, firstName, lastName, posts, PostStatus.ACTIVE));
    }

    @Override
    public Optional<WriterEntity> findById(long id) {
        Map<Long, WriterEntity> map = writerRepository.getAllActive().stream().collect(Collectors.toMap(WriterEntity::id, Function.identity()));
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public GenericRepository<WriterEntity, Long> getRepository() {
        return writerRepository;
    }
}
