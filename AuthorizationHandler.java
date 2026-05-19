public class AuthorizationHandler extends BaseRequestHandler {
    public AuthorizationHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    public Response handle(Request request) {
        System.out.println("4. Checking user permission");

        if (!"ADMIN".equals(request.getUserRole())) {
            return new Response(403, "User is not allowed to create todo");
        }

        return callNext(request);
    }
}
