package application.models;

import java.util.HashMap;
import java.util.ArrayList;
import application.util.FileUtil;

public class TransactionManager {
    private static String FILE_PATH = "./resources/data/transactions.csv";
    private static TransactionManager instance = new TransactionManager();

    private HashMap<String, Transaction> transactions;

    private TransactionManager() {
        transactions = FileUtil.loadObjectsToMap(FILE_PATH, Transaction::fromCSV, Transaction::getKey);
    }

    public static TransactionManager getInstance() {
        return instance;
    }
    
    public void save() {
        FileUtil.writeMapToFile(FILE_PATH, transactions);
    }
    
    public boolean addTransaction(Transaction a) {
        // Prevent duplicate names
        if (transactions.containsKey(a.getKey())) {
            return false;
        }
        
        transactions.put(a.getKey(), a);
        save();
        return true;
    }

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(transactions.values());
    }
}
