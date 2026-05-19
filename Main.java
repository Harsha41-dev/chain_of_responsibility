public class Main {
    public static void main(String[] args) {
        TokenService tokenService = new SimpleTokenService();
        TodoService todoService = new TodoService();

        RequestHandlerFactory factory = new RequestHandlerFactory(tokenService, todoService);
        TodoController todoController = new TodoController(factory);

        Request goodRequest = new Request("/todos", "POST", "valid-token", "ADMIN", "Learn SOLID LLD");
        Request badTokenRequest = new Request("/todos", "POST", "wrong-token", "ADMIN", "Learn LLD");
        Request badRoleRequest = new Request("/todos", "POST", "valid-token", "USER", "Learn LLD");
        Request emptyBodyRequest = new Request("/todos", "POST", "valid-token", "ADMIN", "");

        runExample("GOOD REQUEST", todoController, goodRequest);
        runExample("BAD TOKEN REQUEST", todoController, badTokenRequest);
        runExample("BAD ROLE REQUEST", todoController, badRoleRequest);
        runExample("EMPTY BODY REQUEST", todoController, emptyBodyRequest);
    }

    private static void runExample(String title, TodoController todoController, Request request) {
        System.out.println("\n--- " + title + " ---");

        Response response = todoController.createTodo(request);

        System.out.println("Final Response: " + response.getStatusCode() + " - " + response.getMessage());
    }
}
