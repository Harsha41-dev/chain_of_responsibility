public class SimpleTokenService implements TokenService {
    public boolean isValid(String token) {
        return "valid-token".equals(token);
    }
}
