public class RequestHandlerFactory {
    private TokenService tokenService;
    private TodoService todoService;

    public RequestHandlerFactory(TokenService tokenService, TodoService todoService) {
        this.tokenService = tokenService;
        this.todoService = todoService;
    }

    public RequestHandler createTodoHandlerChain() {
        return new ValidateParamsHandler(
                new ValidateBodyHandler(
                        new AuthenticationHandler(
                                new AuthorizationHandler(
                                        new CreateTodoHandler(todoService)
                                ),
                                tokenService
                        )
                )
        );
    }
}
