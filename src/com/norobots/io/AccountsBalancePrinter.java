package com.norobots.io;

import com.norobots.pocos.AccountsResponse;

public class AccountsBalancePrinter {
    public void PrintAccountsBalance(AccountsResponse accountsResponse) {
        for (int i = 0; i < accountsResponse.data.accts.cur.accts.size(); i++) {
            var account = accountsResponse.data.accts.cur.accts.get(i);
            System.out.println(String.format("Account number: %s; Balance: %s", account.acct, account.plnbal));
        }
    }
}
