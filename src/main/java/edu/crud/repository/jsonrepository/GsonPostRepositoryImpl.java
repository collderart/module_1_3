package edu.crud.repository.jsonrepository;

import com.google.gson.Gson;
import edu.crud.model.PostEntity;
import edu.crud.repository.PostRepository;

import javax.annotation.Nonnull;
import java.util.List;

public class GsonPostRepositoryImpl implements PostRepository {
    private final String JSON_REPO;
    private final Gson gson;

    public GsonPostRepositoryImpl(String jsonFile, Gson gson) {
        JSON_REPO = jsonFile;
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
