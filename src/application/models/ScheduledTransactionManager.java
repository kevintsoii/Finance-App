package application.models;

import java.util.HashMap;
import java.util.ArrayList;
import application.util.FileUtil;

public class ScheduledTransactionManager {
    private static String FILE_PATH = "./resources/data/scheduled.csv";
    private static ScheduledTransactionManager instance = new ScheduledTransactionManager();

    private HashMap<String, ScheduledTransaction> transactions;

    private ScheduledTransactionManager() {
        transactions = FileUtil.loadObjectsToMap(FILE_PATH, ScheduledTransaction::fromCSV, ScheduledTransaction::getKey);
    }

    public static ScheduledTransactionManager getInstance() {
        return instance;
    }
    
    public void save() {
        FileUtil.writeMapToFile(FILE_PATH, transactions);
    }
    
    public boolean addTransaction(ScheduledTransaction a) {
        // Prevent duplicate names
        if (transactions.containsKey(a.getKey())) {
            return false;
        }
        
        transactions.put(a.getKey(), a);
        save();
        return true;
    }
    
    public boolean editKey(String oldKey, String newKey) {
        // invalid keys
        if (!transactions.containsKey(oldKey) || transactions.containsKey(newKey)) {
            return false;
        }
        
        transactions.put(newKey, transactions.get(oldKey));
        transactions.remove(oldKey);        
        return true;
    }

    public ArrayList<ScheduledTransaction> getTransactions() {
        return new ArrayList<>(transactions.values());
    }
}
