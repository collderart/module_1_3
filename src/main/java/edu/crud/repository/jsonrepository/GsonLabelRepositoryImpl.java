package edu.crud.repository.jsonrepository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.crud.model.LabelEntity;
import edu.crud.repository.LabelRepository;

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
    public LabelEntity getById(@Nonnull Long aLong) {
        return null;
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
        try (FileReader reader = new FileReader(JSON_REPO); Writer writer = new FileWriter(JSON_REPO, true)) {
            List<LabelEntity> entities = getAll();
            entities.add(labelEntity);
            gson.toJson(entities, writer);
        } catch (Exception e) {
            System.out.println("Cannot save writer entity " + e.getMessage());
        }
        return labelEntity;
    }

    @Override
    public LabelEntity update(@Nonnull LabelEntity labelEntity) {
        return null;
    }

    @Override
    public void deleteById(@Nonnull Long aLong) {

    }
}
