package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Driver;
import models.Truck;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
//    private static Path path;
//    private static Path path = Paths.get("src/data/trucks.json");

//    public FileUtil(Path path) {
//        this.path = path;
//    }

    public static Truck[] readFile(Path path) {
        try {
            String str = Files.readString(path);
            return GSON.fromJson(str, Truck[].class);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return new Truck[3];
    }

    public static Driver[] readFileDr(Path path) {
        try {
            String str = Files.readString(path);
            return GSON.fromJson(str, Driver[].class);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
        return new Driver[3];
    }

    public static void writeFile(Truck[] trucks, Path path) {
        String newJson = GSON.toJson(trucks);
        byte[] bytes = newJson.getBytes();
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }
}
