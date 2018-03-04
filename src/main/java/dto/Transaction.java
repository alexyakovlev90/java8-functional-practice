package dto;

import java.util.Date;

/**
 * Created by Alexander Yakovlev on 19/02/2018.
 */
public class Transaction {
//    uuid: String,  state: State (), sum: Long, created: Date
    private String uuid;
    private State state;
    private Long sum;
    private Date created;
    private Account account;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
