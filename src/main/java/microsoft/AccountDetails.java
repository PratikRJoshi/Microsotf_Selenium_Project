package microsoft;

/**
 * Created by pratik.joshi on 10/29/17.
 */
public class AccountDetails {
    private String userName = null;
    private String password = null;

    public AccountDetails() {

    }

    public AccountDetails(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
