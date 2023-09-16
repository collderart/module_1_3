package edu.crud.repository;

import com.google.gson.Gson;
import edu.crud.model.PostEntity;

import javax.annotation.Nonnull;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {
    private final String JSON_FILE;
    private final Gson gson;

    public GsonPostRepositoryImpl(String jsonFile, Gson gson) {
        JSON_FILE = jsonFile;
        this.gson = gson;
    }

    @Override
    public PostEntity getById(@Nonnull Long aLong) {
        return null;
    }

    @Override
    public List<PostEntity> getAll() {
        return null;
    }

    @Override
    public PostEntity save(@Nonnull PostEntity postEntity) {
        return null;
    }

    @Override
    public PostEntity update(@Nonnull PostEntity postEntity) {
        return null;
    }

    @Override
    public void deleteById(@Nonnull Long aLong) {

    }
}
