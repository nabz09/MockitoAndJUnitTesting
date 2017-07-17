import java.util.List;

public interface TodoService {
    public List<String> retrieveTodo(String user);
    public void deleteTodo(String user);
}