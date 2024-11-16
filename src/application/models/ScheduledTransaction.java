package application.models;

import java.time.LocalDate;

public class ScheduledTransaction {
    private String account;
    private String type;
    private LocalDate dueDate;
    private String description;
    private double payment;
    private double deposit;

    // constructor
    public ScheduledTransaction(String account, String type, LocalDate dueDate, String description, double payment, double deposit) {
        this.account = account;
        this.type = type;
        this.dueDate = dueDate;
        this.description = description;
        this.payment = payment;
        this.deposit = deposit;
    }

    // getters and setters
    public String getAccount() { return account; }
    public void setAccount(String account) { this.account = account; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPayment() { return payment; }
    public void setPayment(double payment) { this.payment = payment; }
    public double getDeposit() { return deposit; }
    public void setDeposit(double deposit) { this.deposit = deposit; }
}
