package sz.bank;


import java.util.ArrayList;
import java.util.List;

public class Account
{

    public static class Transaction
    {
        private int amount;
        private long time;

        public Transaction(int amount)
        {
            this.amount = amount;
            this.time = System.currentTimeMillis();
        }
        
        public int getAmount()
        {
            return amount;
        }
    }

    private List<Transaction> transactionList = new ArrayList<>();

    public static final int INITIAL_BALANCE = 0;
    private int balance = INITIAL_BALANCE;
    
    public Account()
    {
    }
    
    private void transaction(int amount)
    {
        this.transactionList.add( new Transaction(amount) );
    }

    public boolean verify()
    {
        int verifiedBalance = INITIAL_BALANCE;
        for( Transaction t : this.transactionList )
        {
            verifiedBalance += t.getAmount();
        }
        return ( this.balance == verifiedBalance );
    }

    public void deposit(int amount) throws AccountException
    {
        if( amount <= 0 )
            throw new AccountException("Account.deposit(): amount is not positive");
        transaction(amount);
    }

    public void withdraw(int amount) throws AccountException
    {
        if( amount <= 0 )
            throw new AccountException("Account.withdraw(): amount is not positive");
        transaction(-amount);
    }

    public void printStatement()
    {
        // Date         Amount   Balance
        // 24.12.2015   +500     500
        // 23.8.2015    -100     400
    }
}

