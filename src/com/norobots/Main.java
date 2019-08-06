package com.norobots;

import com.norobots.io.AccountsBalancePrinter;
import com.norobots.io.UserInputGetter;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        var userInputGetter = new UserInputGetter();
        var login = userInputGetter.getLogin();
        var password = userInputGetter.getPassword();

        var httpClient = HttpClientCreator.CreateHttpClient();

        var loginRequestSender = new LoginRequestSender();
        var loginResponse = loginRequestSender.SendLoginRequest(httpClient, login);

        var passwordRequestSender = new PasswordRequestSender();
        var passwordResponse = passwordRequestSender.SendPasswordRequest(httpClient, login, password,
                loginResponse.data.salt, loginResponse.data.mask, loginResponse.data.key);

        var accountsRequestSender = new AccountsRequestSender();
        var accountsResponse = accountsRequestSender.SendAccountsRequest(httpClient, passwordResponse.data.token);

        var accountsBalancePrinter = new AccountsBalancePrinter();
        accountsBalancePrinter.PrintAccountsBalance(accountsResponse);
    }
}
