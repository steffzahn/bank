package sz.bank;


public class AccountException extends Exception
{

    public AccountException()
    {
        super();
    }
    public AccountException(String msg)
    {
        super(msg);
    }
    public AccountException(String msg, Throwable e)
    {
        super(msg, e);
    }
}

