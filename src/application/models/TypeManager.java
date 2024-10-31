package application.models;

import java.util.HashMap;
import java.util.ArrayList;
import application.util.FileUtil;

public class TypeManager {
    private static String FILE_PATH = "./resources/data/types.csv";
    private static TypeManager instance = new TypeManager();

    private HashMap<String, AccountType> types;

    private TypeManager() {
        types = FileUtil.loadObjectsToMap(FILE_PATH, AccountType::fromCSV, AccountType::getKey);
    }

    public static TypeManager getInstance() {
        return instance;
    }
    
    public void save() {
        FileUtil.writeMapToFile(FILE_PATH, types);
    }
    
    public boolean addType(AccountType t) {
        // Prevent duplicate names
        if (types.containsKey(t.getKey())) {
            return false;
        }
        
        types.put(t.getKey(), t);
        save();
        return true;
    }

    public ArrayList<AccountType> getTypes() {
        return new ArrayList<>(types.values());
    }
}
