package sz.bank;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class AccountTest {

    @BeforeAll
    public static void before() {
        System.out.println("Start tests");
    }

    @Test
    void test1() {
        final String ACCOUNT_ID = "testAccount";
        Account a = null;
        boolean ok = true;
        try {
            a = new Account(ACCOUNT_ID);
        } catch (AccountException e) {
            ok = false;
        }
        if (!ok) {
            throw new RuntimeException("test1 failed (2)");
        }
        ok = false;
        try {
            a.deposit(-1);
        } catch (AccountException e) {
            ok = true;
        }
        if (!ok) {
            throw new RuntimeException("test1 failed (1)");
        }
        ok = false;
        try {
            a.withdraw(-5);
        } catch (AccountException e) {
            ok = true;
        }
        if (!ok) {
            throw new RuntimeException("test1 failed (2)");
        }
        ok = true;
        try {
            a.deposit(500);
        } catch (AccountException e) {
            ok = false;
        }
        if (!ok) {
            throw new RuntimeException("test1 failed (2)");
        }
        if (!a.verify())
            throw new RuntimeException("test1 failed (3)");
        if (a.getBalance() != 500) {
            throw new RuntimeException("test1 failed (4)");
        }
        ok = true;
        try {
            a.withdraw(100);
        } catch (AccountException e) {
            ok = false;
        }
        if (!ok) {
            throw new RuntimeException("test1 failed (2)");
        }
        if (!a.verify())
            throw new RuntimeException("test1 failed (5)");
        if (a.getBalance() != 400) {
            throw new RuntimeException("test1 failed (6)");
        }
        List<Account.Transaction> tl = a.getTransactionList();
        if (tl == null) {
            throw new RuntimeException("test1 failed (7)");
        }
        if (tl.size() != 2) {
            throw new RuntimeException("test1 failed (8)");
        }
        if (tl.get(0).getAmount() != 500) {
            throw new RuntimeException("test1 failed (9)");
        }
        if (tl.get(1).getAmount() != -100) {
            throw new RuntimeException("test1 failed (10)");
        }

        // verify that this is actually a copy of the list
        tl.add(new Account.Transaction(42));
        tl = a.getTransactionList();
        if (tl == null) {
            throw new RuntimeException("test1 failed (11)");
        }
        if (tl.size() != 2) {
            throw new RuntimeException("test1 failed (12)");
        }

        String id = a.getIdentifier();
        if ((id == null) || !id.equals(ACCOUNT_ID)) {
            throw new RuntimeException("test1 failed (13)");
        }

        ok = false;
        try {
            new Account(null);
        } catch (AccountException e) {
            ok = true;
        }
        if (!ok) {
            throw new RuntimeException("test1 failed (14)");
        }
        ok = false;
        try {
            new Account("");
        } catch (AccountException e) {
            ok = true;
        }
        if (!ok) {
            throw new RuntimeException("test1 failed (15)");
        }
        a.printStatement();

    }
}
