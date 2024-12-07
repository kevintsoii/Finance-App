package application.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import application.models.FlatFileEntity;

public class FileUtil {
    public static <K, V extends FlatFileEntity> HashMap<K, V> loadObjectsToMap(String fileName, Supplier<V> createObject, Function<V, K> getKey) {
        // HashMap for O(1) search and common operations
        HashMap<K, V> objects = new HashMap<>();
        BufferedReader reader = null;
        
        try {
            // Create file if not exists
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            
            // Put each line into HashMap
            while ((line = reader.readLine()) != null) {
                V object = createObject.get();
                object.fromCSV(line);
                K key = getKey.apply(object);
                objects.put(key, object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return objects;
    }
    
    public static <K, V extends FlatFileEntity> void writeMapToFile(String fileName, HashMap<K, V> map) {
        BufferedWriter writer = null;
        
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (V value: map.values()) {
                writer.write(value.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
