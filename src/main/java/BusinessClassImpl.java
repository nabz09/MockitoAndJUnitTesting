import java.util.ArrayList;
import java.util.List;

//BusinessClassImpl SUT: System under test
//TodoService Dependency
public class BusinessClassImpl{
    private TodoService todoService;

    public BusinessClassImpl(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    public List<String> retrieveTodosContainingSpring(String user) {
        abc;
        List<String> filteredTodos = new ArrayList<String>();
        List<String> todos = todoService.retrieveTodo(user);
        for(String todo:todos) {
            if(todo.contains("spring")) {
                filteredTodos.add(todo);
            }
        }

        System.out.println(filteredTodos);
        return filteredTodos;
    }

    public void deleteTodosNotRelatedToSpring(String user) {
        List<String> todos = todoService.retrieveTodo(user);
        for(String todo:todos) {
            if(!todo.contains("spring")) {
                todoService.deleteTodo(todo);
            }
        }
    }
}
