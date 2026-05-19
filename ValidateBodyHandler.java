public class ValidateBodyHandler extends BaseRequestHandler {
    public ValidateBodyHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    public Response handle(Request request) {
        System.out.println("2. Validating request body");

        if (request.getBody() == null || request.getBody().isEmpty()) {
            return new Response(400, "Todo body is missing");
        }

        return callNext(request);
    }
}
