package application.models;

import java.util.HashMap;
import java.util.ArrayList;
import application.util.FileUtil;

public class AccountManager {
    private static String FILE_PATH = "./resources/data/accounts.csv";
    private static AccountManager instance = new AccountManager();

    private HashMap<String, Account> accounts;

    private AccountManager() {
        accounts = FileUtil.loadObjectsToMap(FILE_PATH, Account::new, Account::getKey);
    }

    public static AccountManager getInstance() {
        return instance;
    }
    
    public void save() {
        FileUtil.writeMapToFile(FILE_PATH, accounts);
    }
    
    public boolean addAccount(Account a) {
        // Prevent duplicate names
        if (accounts.containsKey(a.getName())) {
            return false;
        }
        
        accounts.put(a.getKey(), a);
        save();
        return true;
    }

    public ArrayList<Account> getAccounts() {
        return new ArrayList<>(accounts.values());
    }
}
