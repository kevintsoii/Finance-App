package application.models;

import java.time.LocalDate;

public class Account {
    private String name;
    private LocalDate date;
    private double balance;
    
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
        return name;
    }
    
    // CSV String -> Object
    public static Account fromCSV(String text) {
        String[] fields = text.split(",");
        return new Account(fields[0], LocalDate.parse(fields[1]), Double.parseDouble(fields[2]));
    }
    
    // Object -> CSV String
    public String toString() {
        return name + "," + date.toString() + "," + balance;
    }
}
