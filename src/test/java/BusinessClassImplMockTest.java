import com.sun.xml.internal.bind.v2.TODO;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class BusinessClassImplMockTest {
    @Test
    public void testRetrievedTodosContainingStubStub() {
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");

        when(mockTodoService.retrieveTodo("args")).thenReturn(todos);

        BusinessClassImpl businessClassImpl = new BusinessClassImpl(mockTodoService);
        List<String> filteredTodos = businessClassImpl.retrieveTodosContainingSpring("args");
        Assert.assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testNoResultReturned() {
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("Learning to bop", "What are you writing", "dunno fam");

        when(mockTodoService.retrieveTodo("args")).thenReturn(todos);

        BusinessClassImpl businessClassImpl = new BusinessClassImpl(mockTodoService);
        List<String> filteredTodos = businessClassImpl.retrieveTodosContainingSpring("args");
        Assert.assertEquals(0, filteredTodos.size());
    }

    @Test
    public void testDeleteTodosMethodIsCalled_UsingBDD() {
        /////// Given /////////
        TodoService mockTodoService = mock(TodoService.class);
        List<String> todos = Arrays.asList("spring wuttt", "delete this", "and this", "and this");
        when(mockTodoService.retrieveTodo("user")).thenReturn(todos);

        BusinessClassImpl businessClassImpl = new BusinessClassImpl(mockTodoService);
        ///////////////////////

        ////// When ///////////
        businessClassImpl.deleteTodosNotRelatedToSpring("user");
        ///////////////////////

        ////// Then ///////////we're verifying that deleteTodo is called on "delete this" and "and this"
        verify(mockTodoService).deleteTodo("delete this");
        verify(mockTodoService, times(3)).deleteTodo(anyString());
        verify(mockTodoService, atLeast(1)).deleteTodo("and this");
        verify(mockTodoService, atMost(5)).deleteTodo("and this");
        verify(mockTodoService, never()).deleteTodo("spring wuttt");    // verify the method is never called with these args
        ///////////////////////
    }

    @Test
    public void testArgumentCapture() {
        //// Given //////////////
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        TodoService mockTodoService = mock(TodoService.class);

        List<String> allTodos = Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");
        when(mockTodoService.retrieveTodo("user")).thenReturn(allTodos);

        BusinessClassImpl businessClass = new BusinessClassImpl(mockTodoService);
        /////////////////////////

        ///// When //////////////
        businessClass.deleteTodosNotRelatedToSpring("user");
        /////////////////////////

        //// Then ///////////////
        verify(mockTodoService).deleteTodo(argumentCaptor.capture());
        Assert.assertEquals("Learn to dance", argumentCaptor.getValue());
        ////////////////////////
    }

    @Test
    public void testArgumentCaptureCalledMultiple() {
        //////Given ////////////////////
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        TodoService mockTodoService = mock(TodoService.class);

        List<String> todos = Arrays.asList("Learn spring MVC", "Learn salsa", "Learn to dance");
        when(mockTodoService.retrieveTodo("user")).thenReturn(todos);

        BusinessClassImpl businessClass = new BusinessClassImpl(mockTodoService);
        ////////////////////////////////

        ///// When ////////////////////
        businessClass.deleteTodosNotRelatedToSpring("user");
        //////////////////////////////

        /////// Then //////////////////
        verify(mockTodoService, atLeast(1)).deleteTodo(argumentCaptor.capture());
        Assert.assertEquals(2, argumentCaptor.getAllValues().size());
        ///////////////////////////////
    }
}
