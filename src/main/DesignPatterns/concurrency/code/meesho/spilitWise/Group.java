package concurrency.code.meesho.spilitWise;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String groupId;
    private String groupName;
    List<User> members;
    List<Expense> expenses;
    public Group(String groupId, String groupName, List<User> members) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.members = new ArrayList<>(members);
        this.expenses = new ArrayList<>();
    }


    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public List<User> getMembers() {
        return members;
    }
}
