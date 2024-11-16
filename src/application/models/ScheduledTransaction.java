package application.models;

import java.time.LocalDate;

public class ScheduledTransaction {
    private String name;
    private String account;
    private String type;
    private String frequency;
    private int date;
    private double amount;

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
    public static ScheduledTransaction fromCSV(String text) {
        String[] fields = text.split(",");
        return new ScheduledTransaction(fields[0], fields[1], fields[2], fields[3], Integer.parseInt(fields[4]), Double.parseDouble(fields[5]));
    }
    
    // Object -> CSV String
    public String toString() {
        return String.join(",", name, account, type, frequency, String.valueOf(date), String.valueOf(amount));
    }
}
