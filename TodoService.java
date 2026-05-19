public class TodoService {
    private int nextId = 1;

    public Todo createTodo(String title) {
        Todo todo = new Todo(nextId, title);
        nextId++;
        return todo;
    }
}
