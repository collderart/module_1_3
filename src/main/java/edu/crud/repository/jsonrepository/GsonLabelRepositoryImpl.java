package edu.crud.repository.jsonrepository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.crud.model.LabelEntity;
import edu.crud.repository.LabelRepository;
import edu.crud.repository.ex.EntityNotFoundException;

import javax.annotation.Nonnull;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private final String JSON_REPO;
    private final Gson gson;

    public GsonLabelRepositoryImpl(String jsonFile, Gson gson) {
        JSON_REPO = jsonFile;
        this.gson = gson;
    }

    @Override
    public LabelEntity getById(@Nonnull Long id) {
        return getAll().stream().filter(entity -> entity.id()==id).findFirst().orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Override
    public List<LabelEntity> getAll() {
        List<LabelEntity> entities = null;
        try (FileReader reader = new FileReader(JSON_REPO)) {
            Type dtoListType = new TypeToken<List<LabelEntity>>() {
            }.getType();
            entities = gson.fromJson(reader, dtoListType);
            if (entities == null) {
                entities = new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Cannot get all labels ");
            System.out.println(e.getMessage());
        }
        return entities;
    }

    @Override
    public LabelEntity save(@Nonnull LabelEntity labelEntity) {
        List<LabelEntity> entities = getAll();
        try (Writer writer = new FileWriter(JSON_REPO, false)) {
            entities.add(labelEntity);
            gson.toJson(entities, writer);
        } catch (Exception e) {
            System.out.println("Cannot save writer entity " + e.getMessage());
        }
        return labelEntity;
    }

    @Override
    public LabelEntity update(@Nonnull LabelEntity labelEntity) {
        List<LabelEntity> all = getAll();
        LabelEntity founded = all.stream().filter(entity -> entity.id() == labelEntity.id()).findFirst().orElseThrow(() -> new EntityNotFoundException(labelEntity.id()));
        founded = labelEntity;
        try (Writer writer = new FileWriter(JSON_REPO, false)) {
            gson.toJson(all, writer);
        } catch (Exception e) {
            System.out.println("Cannot save writer entity " + e.getMessage());
        }
        return labelEntity;
    }

    @Override
    public void deleteById(@Nonnull Long id) {
        List<LabelEntity> all = getAll();
        List<LabelEntity> filtered = all.stream().filter(entity -> entity.id() != id).toList();
        if (all.size() == filtered.size()) {
            throw new EntityNotFoundException(id);
        }
        try (Writer writer = new FileWriter(JSON_REPO, false)) {
            gson.toJson(filtered, writer);
        } catch (Exception e) {
            System.out.println("Cannot save writer entity " + e.getMessage());
        }

    }
}
