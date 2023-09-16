package edu.crud.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.crud.model.WriterEntity;
import edu.crud.util.LocalDateTimeAdapter;

import javax.annotation.Nonnull;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final String JSON_FILE;
    private final Gson gson;

    public GsonWriterRepositoryImpl(String jsonFile, Gson gson) {
        JSON_FILE = jsonFile;
        this.gson = gson.newBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
    }


    @Override
    public WriterEntity getById(@Nonnull Long aLong) {
        try (FileInputStream fin = new FileInputStream(JSON_FILE)) {
//            fin.read()
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<WriterEntity> getAll() {
        List<WriterEntity> entities = null;
        try (FileReader reader = new FileReader(JSON_FILE)) {
            Type dtoListType = new TypeToken<List<WriterEntity>>() {
            }.getType();
            entities = gson.fromJson(reader, dtoListType);
            if (entities == null) {
                entities = new ArrayList<>();
            }
//            entities.add(writerEntity);
//            gson.toJson(writerEntity, writer);
        } catch (Exception e) {
            System.out.println("Cannot save writer entity ");
            System.out.println(e.getMessage());
        }
        return entities;
    }

    @Override
    public WriterEntity save(@Nonnull WriterEntity writerEntity) {
        try (FileReader reader = new FileReader(JSON_FILE); Writer writer = new FileWriter(JSON_FILE, true)) {
//            Type dtoListType = new TypeToken<List<WriterEntity>>() {
//            }.getType();
//            List<WriterEntity> entities = gson.fromJson(reader, dtoListType);
//            if (entities == null) {
//                entities = new ArrayList<>();
//            }
//            entities.add(writerEntity);
            gson.toJson(List.of(writerEntity), writer);
        } catch (Exception e) {
            System.out.println("Cannot save writer entity ");
            System.out.println(e.getMessage());
        }
        return writerEntity;
    }

    @Override
    public WriterEntity update(@Nonnull WriterEntity writerEntity) {
        return null;
    }

    @Override
    public void deleteById(@Nonnull Long aLong) {

    }
}
