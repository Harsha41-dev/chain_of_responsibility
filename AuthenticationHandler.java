public class AuthenticationHandler extends BaseRequestHandler {
    private TokenService tokenService;

    public AuthenticationHandler(RequestHandler nextHandler, TokenService tokenService) {
        super(nextHandler);
        this.tokenService = tokenService;
    }

    public Response handle(Request request) {
        System.out.println("3. Authenticating user");

        if (!tokenService.isValid(request.getToken())) {
            return new Response(401, "User is not authenticated");
        }

        return callNext(request);
    }
}
