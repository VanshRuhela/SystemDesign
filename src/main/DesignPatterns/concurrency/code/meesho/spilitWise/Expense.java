package concurrency.code.meesho.spilitWise;

import java.util.Date;
import java.util.List;

public class Expense {
    private String expenseId;
    private double amount;
    private User paidBy;
    private List<User> splitBetween;
    private Date dateOfExpense;

    public Expense(String expenseId, double amount, User paidBy, List<User> splitBetween) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.paidBy = paidBy;
        this.splitBetween = splitBetween;
        this.dateOfExpense = new Date();
    }

    //

    public String getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public List<User> getSplitBetween() {
        return splitBetween;
    }

    public void setSplitBetween(List<User> splitBetween) {
        this.splitBetween = splitBetween;
    }

    public Date getDateOfExpense() {
        return dateOfExpense;
    }

    public void setDateOfExpense(Date dateOfExpense) {
        this.dateOfExpense = dateOfExpense;
    }
}
