package edu.crud.repository.jsonrepository;

import com.google.gson.Gson;
import edu.crud.constants.PostStatus;
import edu.crud.ex.EntityNotFoundException;
import edu.crud.model.PostEntity;
import edu.crud.repository.PostRepository;
import edu.crud.util.LocalDateTimeAdapter;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.List;

import static edu.crud.util.RepoUtil.getAllFromJson;
import static edu.crud.util.RepoUtil.writeToJson;

public class GsonPostRepositoryImpl implements PostRepository {
    private final String JSON_REPO;
    private final Gson gson;

    public GsonPostRepositoryImpl(String jsonFile, Gson gson) {
        JSON_REPO = jsonFile;
        this.gson = gson.newBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    }

    private List<PostEntity> getAllPosts() {
        return getAllFromJson(JSON_REPO, gson, PostEntity.class);
    }

    @Override
    public PostEntity getById(@Nonnull Long id) {
        return getAllPosts().stream()
                .filter(entity -> entity.id() == id).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public List<PostEntity> getAll() {
        return getAllPosts().stream()
                .filter(entity -> entity.status() != PostStatus.DELETED)
                .toList();
    }

    @Override
    public PostEntity save(@Nonnull PostEntity postEntity) {
        List<PostEntity> existing = getAllPosts();
        existing.add(postEntity);
        writeToJson(existing, JSON_REPO, gson);
        return postEntity;
    }

    @Override
    public PostEntity update(@Nonnull PostEntity postEntity) {
        List<PostEntity> updated = getAllPosts()
                .stream().map(existing -> {
                    if (existing.id() == postEntity.id()) {
                        return postEntity;
                    }
                    return existing;
                }).toList();
        writeToJson(updated, JSON_REPO, gson);
        return postEntity;
    }

    @Override
    public void deleteById(@Nonnull Long id) {
        List<PostEntity> updated = getAllPosts()
                .stream().map(existing -> {
                    if (existing.id() == id) {
                        return new PostEntity(existing.id(), existing.content(), existing.created(), LocalDateTime.now(), existing.labels(), PostStatus.DELETED);
                    }
                    return existing;
                }).toList();

        writeToJson(updated, JSON_REPO, gson);
    }
}
