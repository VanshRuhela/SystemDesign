package concurrency.code.meesho.spilitWise;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        BillSplittingApp app = new BillSplittingApp();

        User user1 = app.createUser("U1", "Alice", "alice@example.com", "1234567890");
        User user2 = app.createUser("U2", "Bob", "bob@example.com", "0987654321");
        User user3 = app.createUser("U3", "Charlie", "charlie@example.com", "1122334455");

        List<User> members = Arrays.asList(user1,user2,user3);
        Group group = app.createGroup("G1", "FlatMates", members);

        app.addExpense("G1", 300, user1, Arrays.asList(user1, user2, user3)); // Alice paid for the group
        app.addExpense("G1", 200, user2, Arrays.asList(user3, user2));        // Bob paid for Alice and Bob
        System.out.println("\nBefore simplify");
        app.getBalance("U1"); // Alice's balance
        app.getBalance("U2"); // Bob's balance
        app.getBalance("U3");

        app.simplifyDebts("G1");
        System.out.println("\nAfter simplify");
        app.getBalance("U1"); // Alice's balance
        app.getBalance("U2"); // Bob's balance
        app.getBalance("U3");

//        app.settleBalance("U1", "U3", 50); // Alice pays Bob 50

//        app.getBalance("U1"); // Alice's balance
//        app.getBalance("U2");
//        app.getBalance("U3");
    }
}
