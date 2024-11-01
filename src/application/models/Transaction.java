package application.models;

import java.time.LocalDate;

public class Transaction {
    private String account;
    private String transactionType;
    private LocalDate date;
    private String description;
    private double payment;
    private double deposit;
    
    public Transaction(String account, String transactionType, LocalDate date, String description, double payment, double deposit) {
        this.account = account;
        this.transactionType = transactionType;
        this.date = date;
        this.description = description;
        this.payment = payment;
        this.deposit = deposit;
    }
    
    public String getAccount() {
        return account;
    }
    
    public void setAccount(String account) {
        this.account = account;
    }
    
    public String getTransactionType() {
        return transactionType;
    }
    
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPayment() {
        return payment;
    }
    
    public void setPayment(double payment) {
        this.payment = payment;
    }
    
    public double getDeposit() {
        return deposit;
    }
    
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }
    
    // Unique ID
    public String getKey() {
        return account + "_" + date.toString() + "_" + description;
    }
    
    // CSV String -> Object
    public static Transaction fromCSV(String text) {
        String[] fields = text.split(",");
        return new Transaction(fields[0], fields[1], LocalDate.parse(fields[2]), fields[3], Double.parseDouble(fields[4]), Double.parseDouble(fields[5]));
    }
    
    // Object -> CSV String
    public String toString() {
        return String.join(",", account, transactionType, date.toString(), description, String.valueOf(payment), String.valueOf(deposit));
    }
}
