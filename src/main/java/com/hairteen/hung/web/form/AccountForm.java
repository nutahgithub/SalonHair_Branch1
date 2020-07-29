package com.hairteen.hung.web.form;

public class AccountForm {

    private String userName;
    private String password;
    private String passwordConfirm;
    private String emailUser;

    public AccountForm() {}

    public AccountForm(String userName, String password, String passwordConfirm, String emailUser) {
        super();
        this.userName = userName;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.emailUser = emailUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }
}
