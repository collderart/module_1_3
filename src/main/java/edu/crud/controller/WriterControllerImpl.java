package edu.crud.controller;

import edu.crud.constants.PostStatus;
import edu.crud.model.PostEntity;
import edu.crud.model.WriterEntity;
import edu.crud.repository.WriterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.crud.util.RepoUtil.generateNextId;

public class WriterControllerImpl implements WriterController {
    private final WriterRepository writerRepository;

    public WriterControllerImpl(WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }


    @Override
    public WriterEntity createWriter(String firstName, String lastName, List<PostEntity> posts) {
        Long nextId = generateNextId(writerRepository.getAll());
        return writerRepository.save(new WriterEntity(nextId, firstName, lastName, new ArrayList<>(), PostStatus.ACTIVE));
    }

    @Override
    public List<WriterEntity> getAll() {
        return null;
    }

    @Override
    public Optional<WriterEntity> findById(long id) {
        return Optional.empty();
    }

    @Override
    public void update(WriterEntity entityToUpdate) {

    }

    @Override
    public void remove(long id) {

    }
}
