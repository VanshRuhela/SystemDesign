package concurrency.code.meesho.spilitWise;

import java.util.*;

public class BillSplittingApp {
    private Map<String , User> users;
    private Map<String , Group> groups;
    private Map<String, Map<String, Double>> balances; // to store balance between users

    public BillSplittingApp(){
        users = new HashMap<>();
        groups = new HashMap<>();
        balances = new HashMap<>();
    }

    // 1. Create User
    public User createUser(String userId, String userName, String email, String phoneNumber) {
        if(users.containsKey(userId)){
            System.out.println("Already exists");
            return users.get(userId);
        }else{
            User user = new User(userId, userName, email, phoneNumber);
            users.put(userId, user);
            return user;
        }
    }

    // Create a new Group
    public Group createGroup(String groupId, String groupName, List<User> users){
        if(groups.containsKey(groupId)){
            System.out.println("Already Present");
            return groups.get(groupId);
        }else{
            Group group = new Group(groupId, groupName, users);
            groups.put(groupId, group);
            return group;
        }
    }

    // add expense to group
    public void addExpense(String groupId , double amount, User paidBy, List<User> splitBetween){
        Group group = groups.get(groupId);
        if(groupId == null){
            System.out.println("No group exits with the group id");
        }
        else{
            Expense expense = new Expense(UUID.randomUUID().toString(), amount, paidBy, splitBetween);
            group.addExpense(expense);
            updateExpense(paidBy, splitBetween, amount);
        }
    }

    private void updateExpense(User paidBy , List<User> splitBetween, double amount){
        double splitAmt = amount / splitBetween.size();
        for(User user: splitBetween){
            if(user != paidBy){
                balances.putIfAbsent(paidBy.getUserId(), new HashMap<>());
                balances.get(paidBy.getUserId())
                        .put(user.getUserId(),
                                balances.get(paidBy.getUserId())
                                        .getOrDefault(user.getUserId(), 0.0) + splitAmt);

                balances.putIfAbsent(user.getUserId(), new HashMap<>());
                balances.get(user.getUserId())
                        .put(paidBy.getUserId(),
                                balances.get(user.getUserId())
                                        .getOrDefault(paidBy.getUserId(), 0.0) - splitAmt);
            }
        }
    }

    //get balance for a user
    public void getBalance(String userId){
        System.out.println("Balances for user " + users.get(userId).getUserName() + ":");
        for(var entry : balances.get(userId).entrySet()){
            if(entry.getValue() != 0){
                System.out.println(users.get(entry.getKey()).getUserName() + ": " + entry.getValue());
            }
        }
    }


    public void settleBalance(String paidBy , String paidTo , double amount){
        balances.get(paidBy)
                .put(paidTo,
                        balances.get(paidBy)
                        .getOrDefault(paidTo, 0.0) - amount);
        balances.get(paidTo)
                .put(paidBy,
                        balances.get(paidTo)
                                .getOrDefault(paidBy, 0.0) + amount);

        System.out.println("Settled paidBy" + paidBy +" to " +paidTo);
    }

    public void simplifyDebts(String groupId){
        System.out.println("Simplify Debts");
        Group group = groups.get(groupId);
        List<User> members = group.getMembers();
        Map<String , Double> totBalances = new HashMap<>();

        for(User user: members){
            double bal = 0;
            for(var entry : balances.get(user.getUserId()).entrySet()){
                bal += entry.getValue();
            }
            totBalances.put(user.getUserId(), bal);
        }

        List<Map.Entry<String, Double>> debtors = new ArrayList<>();
        List<Map.Entry<String, Double>> creditors = new ArrayList<>();

        for(var entry : totBalances.entrySet()){
            if(entry.getValue() < 0)
                debtors.add(entry);
            else
                creditors.add(entry);
        }
        System.out.println(Arrays.toString(debtors.toArray()));
        System.out.println(Arrays.toString(creditors.toArray()));

        // simplify Debt
        for(var debtor : debtors){
            String debtorId = debtor.getKey();
            double debtAmt = - debtor.getValue();

            for(var creditor : creditors){
                String creditorId = creditor.getKey();
                double credAmt = creditor.getValue();

                if(debtAmt == 0) break;
                double payment = Math.min(debtAmt, credAmt);
                settleBalance(debtorId, creditorId, payment);

                debtAmt -= payment;
                credAmt -= payment;
                creditor.setValue(credAmt);

                if(credAmt <= 0)
                    creditors.remove(creditor);
            }
        }
    }
}
