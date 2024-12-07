package application.models;

public class AccountType implements FlatFileEntity {
    private String name;

    public AccountType() {
        this("");
    }
    
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
    public void fromCSV(String text) {
        setName(text);
    }
    
    // Object -> CSV String
    public String toCSV() {
        return name;
    }
}
