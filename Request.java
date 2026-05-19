public class Request {
    private String path;
    private String method;
    private String token;
    private String userRole;
    private String body;

    public Request(String path, String method, String token, String userRole, String body) {
        this.path = path;
        this.method = method;
        this.token = token;
        this.userRole = userRole;
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getToken() {
        return token;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getBody() {
        return body;
    }
}
