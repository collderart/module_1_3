package edu.crud.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import edu.crud.model.ModelEntity;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static edu.crud.constants.MenuActions.*;

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

    public static <T extends ModelEntity> Long generateNextId(List<T> labels) {
        return labels.stream().mapToLong(ModelEntity::id).max().orElse(0L) + 1;
    }

    public static void printSubMenu() {
        System.out.println("Select action:");
        System.out.println(CREATE.ordinal() + " - to create");
        System.out.println(GET.ordinal() + " - to get");
        System.out.println(EDIT.ordinal() + " - to edit");
        System.out.println(DELETE.ordinal() + " - to delete");
        System.out.println(UNDEFINED.ordinal() + " - go back");
    }
}
