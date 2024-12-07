package application.models;

public class ScheduledTransaction implements FlatFileEntity {
    private String name;
    private String account;
    private String type;
    private String frequency;
    private int date;
    private double amount;

    public ScheduledTransaction() {
        this("", "", "", "", 0, 0.0);
    }

    // constructor
    public ScheduledTransaction(String name, String account, String type, String frequency, int date, double amount) {
        this.name = name;
        this.account = account;
        this.type = type;
        this.frequency = frequency;
        this.date = date;
        this.amount = amount;
    }

    // getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }
    public int getDate() { return date; }
    public void setDate(int date) { this.date = date; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    
    // Unique ID
    public String getKey() {
        return name;
    }
    
    // CSV String -> Object
    public void fromCSV(String text) {
        String[] fields = text.split(",");
        setName(fields[0]);
        setAccount(fields[1]);
        setType(fields[2]);
        setFrequency(fields[3]);
        setDate(Integer.parseInt(fields[4]));
        setAmount(Double.parseDouble(fields[5]));
    }
    
    // Object -> CSV String
    public String toCSV() {
        return String.join(",", name, account, type, frequency, String.valueOf(date), String.valueOf(amount));
    }
}
