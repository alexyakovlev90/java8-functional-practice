package dto;

import java.util.List;
import java.util.UUID;

/**
 * Created by Alexander Yakovlev on 19/02/2018.
 */
public class Account {
    private UUID guid;
    private String number;
    private Long balance;
    private List<Transaction> transactions;

    public UUID getGuid() {
        return guid;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
