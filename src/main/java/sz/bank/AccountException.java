package sz.bank;


public class AccountException extends Exception
{
    @SuppressWarnings("WeakerAccess")
    public AccountException(String msg)
    {
        super(msg);
    }
    @SuppressWarnings("unused")
    public AccountException(String msg, Throwable e)
    {
        super(msg, e);
    }
}

