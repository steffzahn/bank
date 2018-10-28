package sz.bank;


import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class Account
{

    public static class Transaction
    {
        private int amount;
        private long time;
        private boolean immutable;

        @SuppressWarnings("WeakerAccess")
        public Transaction(int amount)
        {
            this.amount = amount;
            this.time = System.currentTimeMillis();
            this.immutable = true;
        }
        private Transaction(int amount, boolean immutable)
        {
            this.amount = amount;
            this.time = System.currentTimeMillis();
            this.immutable = immutable;
        }

        @SuppressWarnings("WeakerAccess")
        public int getAmount()
        {
            return amount;
        }
        private int incrementAmount(int increment)
        {
            if( !immutable )
            {
                amount += increment;
            }
            return amount;
        }

        @SuppressWarnings("WeakerAccess")
        public long getTime()
        {
            return time;
        }
    }

    private List<Transaction> transactionList = new ArrayList<>();

    @SuppressWarnings("WeakerAccess")
    public static final int INITIAL_BALANCE = 0;
    private int balance = INITIAL_BALANCE;

    private String identifier;
    
    @SuppressWarnings("WeakerAccess")
    public Account(String identifier) throws AccountException
    {
        if( (identifier==null) || (identifier.length()==0))
            throw new AccountException("Account.Account(): identifier is missing or empty");
        this.identifier = identifier;
    }

    @SuppressWarnings("WeakerAccess")
    public synchronized String getIdentifier()
    {
        return this.identifier;
    }
    
    private synchronized void transaction(int amount)
    {
        this.transactionList.add( new Transaction(amount) );
        this.balance += amount;
    }

    @SuppressWarnings({"WeakerAccess", "BooleanMethodIsAlwaysInverted"})
    public boolean verify()
    {
        int verifiedBalance = INITIAL_BALANCE;
        for( Transaction t : this.transactionList )
        {
            verifiedBalance += t.getAmount();
        }
        return ( this.balance == verifiedBalance );
    }

    @SuppressWarnings("WeakerAccess")
    public void deposit(int amount) throws AccountException
    {
        if( amount <= 0 )
            throw new AccountException("Account.deposit(): amount is not positive");
        transaction(amount);
    }

    @SuppressWarnings("WeakerAccess")
    public void withdraw(int amount) throws AccountException
    {
        if( amount <= 0 )
            throw new AccountException("Account.withdraw(): amount is not positive");
        transaction(-amount);
    }

    @SuppressWarnings("WeakerAccess")
    public synchronized List<Transaction> getTransactionList()
    {
        return new ArrayList<>( this.transactionList );
    }

    @SuppressWarnings("WeakerAccess")
    public synchronized int getBalance() {
        return balance;
    }

    @SuppressWarnings("WeakerAccess")
    public void printStatement()
    {
        // Date         Amount   Balance
        // 24.12.2015   +500     500
        // 23.8.2015    -100     400
        List<Transaction> tl = this.getTransactionList();
        System.out.println(String.format("Balance for Account %s", this.getIdentifier() ) );
        System.out.println(String.format("%-10s   %-10s   %-10s","Date","Amount","Balance"));
        //noinspection ResultOfMethodCallIgnored
        tl.stream()
                .reduce( new Transaction(INITIAL_BALANCE, false),
                        (acc, t) ->
                            {
                                int amount= t.getAmount();
                                int currentBalance = acc.incrementAmount(amount);
                                String amountString = ( (amount>0) ? "+" : "") + Integer.toString(amount);
                                System.out.println(String.format("%1$td.%1$tm.%1$tY   %2$-10s   %3$-10d",t.getTime(),amountString,currentBalance));
                                return acc;
                            });
    }
}
