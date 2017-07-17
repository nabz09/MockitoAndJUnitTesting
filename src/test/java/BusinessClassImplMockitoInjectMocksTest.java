import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BusinessClassImplMockitoInjectMocksTest {
    @Mock
    TodoService mockTodoService;

    @InjectMocks
    BusinessClassImpl businessClass;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void testRetrievedTodosContainingStub() {
        List<String> todos = Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");

        when(mockTodoService.retrieveTodo("args")).thenReturn(todos);

        List<String> filteredTodos = businessClass.retrieveTodosContainingSpring("args");
        Assert.assertEquals(2, filteredTodos.size());
    }

    @Test
    public void testNoResultReturned() {
        List<String> todos = Arrays.asList("Learning to bop", "What are you writing", "dunno fam");

        when(mockTodoService.retrieveTodo("args")).thenReturn(todos);

        List<String> filteredTodos = businessClass.retrieveTodosContainingSpring("args");
        Assert.assertEquals(0, filteredTodos.size());
    }

    @Test
    public void testDeleteTodosMethodIsCalled_UsingBDD() {
        /////// Given /////////
        List<String> todos = Arrays.asList("spring wuttt", "delete this", "and this", "and this");
        when(mockTodoService.retrieveTodo("user")).thenReturn(todos);
        ///////////////////////

        ////// When ///////////
        businessClass.deleteTodosNotRelatedToSpring("user");
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
        List<String> allTodos = Arrays.asList("Learn spring MVC", "Learn spring", "Learn to dance");
        when(mockTodoService.retrieveTodo("user")).thenReturn(allTodos);
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
        List<String> todos = Arrays.asList("Learn spring MVC", "Learn salsa", "Learn to dance");
        when(mockTodoService.retrieveTodo("user")).thenReturn(todos);
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
