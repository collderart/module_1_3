package edu.crud.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RepoUtil {
    public static <T> List<T> getAllFromJson(String JSON_REPO, Gson gson, Class<T> type) {
        List<T> entities = null;
        try (FileReader reader = new FileReader(JSON_REPO)) {
            Type dtoListType = TypeToken.getParameterized(List.class, type).getType();
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

    public static <T> void writeToJson(List<T> list, String JSON_REPO, Gson gson) {
        try (Writer writer = new FileWriter(JSON_REPO, false)) {
            gson.toJson(list, writer);
        } catch (Exception e) {
            System.out.println("Cannot save writer entity " + e.getMessage());
        }
    }

    public static Long generateNextId(List<Long> labels) {
        return labels.stream().max(Long::compare).orElse(0L) + 1;
    }
}
