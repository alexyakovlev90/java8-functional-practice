package dto;

/**
 * Created by Alexander Yakovlev on 04.03.2018.
 */
public class User {
    //User (login: String, account: Account)
    private String login;
    private Account account;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
