import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    public void testListSizeWorks() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2);

        Assert.assertEquals(2, listMock.size());
    }

    @Test
    public void testListSizeWorksReturnsMultipleValues() {
        List listMock = mock(List.class);
        when(listMock.size()).thenReturn(2).thenReturn(3);

        Assert.assertEquals(2, listMock.size());
        Assert.assertEquals(3, listMock.size());
    }

    @Test
    public void testListGetWorks() {
        List listMock = mock(List.class);
        when(listMock.get(0)).thenReturn("Something");

        Assert.assertEquals("Something", listMock.get(0));
        Assert.assertNull(listMock.get(1));
    }

    @Test
    public void testListGetWorksWithArgumentMatcher() {
        List listMock = mock(List.class);
        // anyInt() is an argumentMatcher
        when(listMock.get(anyInt())).thenReturn("Something");

        Assert.assertEquals("Something", listMock.get(52));
    }

    @Test(expected=RuntimeException.class)
    public void testListThrowsException() {
        List listMock = mock(List.class);
        // anyInt() is an argumentMatcher
        when(listMock.get(anyInt())).thenThrow(new RuntimeException("Something"));

        listMock.get(52);
    }

    @Test
    public void testAny() {
        List listMock = mock(ArrayList.class);
        when(listMock.set(anyInt(), Matchers.anyObject())).thenReturn("Done");

        Assert.assertEquals("Done", listMock.set(4,new Date()));
    }
}
