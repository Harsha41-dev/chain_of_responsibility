public class ValidateParamsHandler extends BaseRequestHandler {
    public ValidateParamsHandler(RequestHandler nextHandler) {
        super(nextHandler);
    }

    public Response handle(Request request) {
        System.out.println("1. Validating route params");

        if (isEmpty(request.getPath())) {
            return new Response(400, "Path is missing");
        }

        if (isEmpty(request.getMethod())) {
            return new Response(400, "HTTP method is missing");
        }

        if (!request.getPath().equals("/todos") || !request.getMethod().equals("POST")) {
            return new Response(404, "Route not found");
        }

        return callNext(request);
    }

    private boolean isEmpty(String value) {
        return value == null || value.isEmpty();
    }
}
