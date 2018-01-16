package sz.bank;


public class AccountMain
{
    public static void main(String[] args) throws AccountException
    {
        Account a = new Account("0815");

        a.deposit(500);

        a.withdraw(100);

        a.printStatement();
    }
}

