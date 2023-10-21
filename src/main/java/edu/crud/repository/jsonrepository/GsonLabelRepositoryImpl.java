package edu.crud.repository.jsonrepository;

import com.google.gson.Gson;
import edu.crud.constants.PostStatus;
import edu.crud.ex.EntityNotFoundException;
import edu.crud.model.LabelEntity;
import edu.crud.repository.LabelRepository;

import javax.annotation.Nonnull;
import java.util.List;

import static edu.crud.util.RepoUtil.getAllFromJson;
import static edu.crud.util.RepoUtil.writeToJson;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private final String JSON_REPO;
    private final Gson gson;

    public GsonLabelRepositoryImpl(String jsonFile, Gson gson) {
        JSON_REPO = jsonFile;
        this.gson = gson;
    }

    private List<LabelEntity> getAllLabels() {
        return getAllFromJson(JSON_REPO, gson, LabelEntity.class);
    }


    @Override
    public LabelEntity getById(@Nonnull Long id) {
        return getAllLabels().stream()
                .filter(entity -> entity.id() == id).findFirst()
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public List<LabelEntity> getAllActive() {
        return getAllLabels().stream()
                .filter(entity -> entity.status() != PostStatus.DELETED)
                .toList();
    }

    @Override
    public List<LabelEntity> getAll() {
        return getAllLabels();
    }

    @Override
    public LabelEntity save(@Nonnull LabelEntity labelEntity) {
        List<LabelEntity> existing = getAllLabels();
        existing.add(labelEntity);
        writeToJson(existing, JSON_REPO, gson);
        return labelEntity;
    }

    @Override
    public LabelEntity update(@Nonnull LabelEntity labelEntity) {
        List<LabelEntity> updated = getAllLabels()
                .stream().map(existing -> {
                    if (existing.id() == labelEntity.id()) {
                        return labelEntity;
                    }
                    return existing;
                }).toList();
        writeToJson(updated, JSON_REPO, gson);
        return labelEntity;
    }

    @Override
    public void deleteById(@Nonnull Long id) {
        List<LabelEntity> updated = getAllLabels()
                .stream().map(existing -> {
                    if (existing.id() == id) {
                        return new LabelEntity(existing.id(), existing.name(), PostStatus.DELETED);
                    }
                    return existing;
                }).toList();

        writeToJson(updated, JSON_REPO, gson);
    }
}
