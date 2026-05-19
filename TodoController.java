public class TodoController {
    private RequestHandlerFactory requestHandlerFactory;

    public TodoController(RequestHandlerFactory requestHandlerFactory) {
        this.requestHandlerFactory = requestHandlerFactory;
    }

    public Response createTodo(Request request) {
        RequestHandler handlerChain = requestHandlerFactory.createTodoHandlerChain();
        return handlerChain.handle(request);
    }
}
