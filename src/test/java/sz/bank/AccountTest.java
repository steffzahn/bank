package sz.bank;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class AccountTest extends TestCase{

    public AccountTest(String testName)
    {
        super( testName );
    }

    @Test
    public void test1() throws AccountException
    {
        Account a = new Account();

        boolean ok=false;
        try{
            a.deposit( -1);
        } catch (AccountException e)
        {
            ok = true;
        }
        if( !ok ) {
            throw new RuntimeException("test1 failed (1)");
        }
        a.deposit(500);
        if( !a.verify() )
            throw new RuntimeException("test1 failed (2)");
        if( a.getBalance() != 500 )
        {
            throw new RuntimeException("test1 failed (3)");
        }
        a.withdraw(100);
        if( !a.verify() )
            throw new RuntimeException("test1 failed (4)");
        if( a.getBalance() != 400 )
        {
            throw new RuntimeException("test1 failed (5)");
        }
        List<Account.Transaction> tl = a.getTransactionList();
        if( tl==null )
        {
            throw new RuntimeException("test1 failed (6)");
        }
        if( tl.size() !=2 )
        {
            throw new RuntimeException("test1 failed (7)");
        }
        if( tl.get(0).getAmount() != 500 )
        {
            throw new RuntimeException("test1 failed (8)");
        }
        if( tl.get(1).getAmount() != -100 )
        {
            throw new RuntimeException("test1 failed (9)");
        }

        // verify that this is actually a copy of the list
        tl.add(new Account.Transaction(42));
        tl = a.getTransactionList();
        if( tl==null )
        {
            throw new RuntimeException("test1 failed (10)");
        }
        if( tl.size() !=2 )
        {
            throw new RuntimeException("test1 failed (11)");
        }
    }
}
