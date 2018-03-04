package other.chain_of_responsibility;

/**
 * Created by Alexander Yakovlev on 12/02/2018.
 */
public class Request {
    private final String data;

    public Request(String requestData) {
        this.data = requestData;
    }

    public String getData() {
        return data;
    }
}
