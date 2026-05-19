public class CreateTodoHandler implements RequestHandler {
    private TodoService todoService;

    public CreateTodoHandler(TodoService todoService) {
        this.todoService = todoService;
    }

    public Response handle(Request request) {
        System.out.println("5. Creating todo");

        Todo todo = todoService.createTodo(request.getBody());
        return new Response(201, "Todo created with id " + todo.getId() + ": " + todo.getTitle());
    }
}
