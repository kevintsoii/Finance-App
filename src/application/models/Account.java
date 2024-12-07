package application.models;

import java.time.LocalDate;

public class Account implements FlatFileEntity {
    private String name;
    private LocalDate date;
    private double balance;

    public Account() {
        this("", LocalDate.now(), 0.0);
    }
    
    public Account(String name, LocalDate date, double balance) {
        this.name = name;
        this.date = date;
        this.balance = balance;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    // Unique ID
    public String getKey() {
        return getName();
    }
    
    // CSV String -> Object
    public void fromCSV(String text) {
        String[] fields = text.split(",");
        setName(fields[0]);
        setDate(LocalDate.parse(fields[1]));
        setBalance(Double.parseDouble(fields[2]));
    }
    
    // Object -> CSV String
    public String toCSV() {
        return name + "," + date.toString() + "," + balance;
    }
}
