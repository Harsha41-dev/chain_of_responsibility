public abstract class BaseRequestHandler implements RequestHandler {
    private RequestHandler nextHandler;

    public BaseRequestHandler(RequestHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    protected Response callNext(Request request) {
        if (nextHandler == null) {
            return new Response(500, "No final handler found");
        }

        return nextHandler.handle(request);
    }
}
