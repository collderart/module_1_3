package edu.crud.repository.jsonrepository;

import com.google.gson.Gson;
import edu.crud.constants.PostStatus;
import edu.crud.ex.EntityNotFoundException;
import edu.crud.model.WriterEntity;
import edu.crud.repository.WriterRepository;
import edu.crud.util.LocalDateTimeAdapter;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

import static edu.crud.util.RepoUtil.getAllFromJson;
import static edu.crud.util.RepoUtil.writeToJson;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final String JSON_REPO;
    private final Gson gson;

    public GsonWriterRepositoryImpl(String jsonFile, Gson gson) {
        JSON_REPO = jsonFile;
        this.gson = gson.newBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    }

    private List<WriterEntity> getAllWriters() {
        return getAllFromJson(JSON_REPO, gson, WriterEntity.class);
    }

    @Override
    public WriterEntity getById(@Nonnull Long id) {
        return getAllWriters().stream()
                .filter(entity -> entity.id() == id).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public List<WriterEntity> getAllActive() {
        return getAllWriters().stream()
                .filter(entity -> entity.status() != PostStatus.DELETED)
                .toList();
    }

    @Override
    public List<WriterEntity> getAll() {
        return getAllWriters();
    }

    @Override
    public WriterEntity save(@Nonnull WriterEntity writerEntity) {
        List<WriterEntity> existing = getAllWriters();
        existing.add(writerEntity);
        writeToJson(existing, JSON_REPO, gson);
        return writerEntity;
    }

    @Override
    public WriterEntity update(@Nonnull WriterEntity writerEntity) {
        List<WriterEntity> updated = getAllWriters()
                .stream().map(existing -> {
                    if (existing.id() == writerEntity.id()) {
                        return writerEntity;
                    }
                    return existing;
                }).toList();
        writeToJson(updated, JSON_REPO, gson);
        return writerEntity;
    }

    @Override
    public void deleteById(@Nonnull Long id) {
        List<WriterEntity> updated = getAllWriters()
                .stream().map(existing -> {
                    if (existing.id() == id) {
                        return new WriterEntity(existing.id(), existing.firstName(), existing.lastName(), existing.posts(), PostStatus.DELETED);
                    }
                    return existing;
                }).toList();

        writeToJson(updated, JSON_REPO, gson);
    }
}
