package pl.crc.gateway.auth.model;

public class ResponseAuth {
    private final String token;

    public ResponseAuth(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
