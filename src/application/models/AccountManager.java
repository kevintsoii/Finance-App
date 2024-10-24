package application.models;

import java.util.HashMap;
import application.util.FileUtil;

public class AccountManager {
    private static String FILE_PATH = "./resources/data/accounts.csv";
    private static AccountManager instance = new AccountManager();

    private HashMap<String, Account> accounts;

    private AccountManager() {
        accounts = FileUtil.loadObjectsToMap(FILE_PATH, Account::fromCSV, Account::getKey);
    }

    public static AccountManager getInstance() {
        return instance;
    }
    
    public void saveAccounts() {
        FileUtil.writeMapToFile(FILE_PATH, accounts);
    }
    
    public boolean addAccount(Account a) {
        // Prevent duplicate names
        if (accounts.containsKey(a.getName())) {
            return false;
        }
        
        accounts.put(a.getName(), a);
        saveAccounts();
        return true;
    }
}
