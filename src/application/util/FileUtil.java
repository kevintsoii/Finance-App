package application.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.function.Function;

public class FileUtil {
    public static <K, V> HashMap<K, V> loadObjectsToMap(String fileName, Function<String, V> createObject, Function<V, K> getKey) {
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
                if (line.split(",").length == 3) {
                    V object = createObject.apply(line);
                    K key = getKey.apply(object);
                    objects.put(key, object);
                }
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
    
    public static <K, V> void writeMapToFile(String fileName, HashMap<K, V> map) {
        BufferedWriter writer = null;
        
        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            for (V value: map.values()) {
                writer.write(value.toString());
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
