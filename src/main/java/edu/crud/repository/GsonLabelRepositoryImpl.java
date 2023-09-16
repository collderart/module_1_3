package edu.crud.repository;

import com.google.gson.Gson;
import edu.crud.model.LabelEntity;

import javax.annotation.Nonnull;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private final String JSON_FILE;
    private final Gson gson;

    public GsonLabelRepositoryImpl(String jsonFile, Gson gson) {
        JSON_FILE = jsonFile;
        this.gson = gson;
    }

    @Override
    public LabelEntity getById(@Nonnull Long aLong) {
        return null;
    }

    @Override
    public List<LabelEntity> getAll() {
        return null;
    }

    @Override
    public LabelEntity save(@Nonnull LabelEntity labelEntity) {
        return null;
    }

    @Override
    public LabelEntity update(@Nonnull LabelEntity labelEntity) {
        return null;
    }

    @Override
    public void deleteById(@Nonnull Long aLong) {

    }
}
