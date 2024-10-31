package application.models;

public class AccountType {
    private String name;
    
    public AccountType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    // Unique ID
    public String getKey() {
        return getName();
    }
    
    // CSV String -> Object
    public static AccountType fromCSV(String text) {
        return new AccountType(text);
    }
    
    // Object -> CSV String
    public String toString() {
        return name;
    }
}
