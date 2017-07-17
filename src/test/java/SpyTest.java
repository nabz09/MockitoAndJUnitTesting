import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class SpyTest {
    Logger LOGGER = LoggerFactory.getLogger(SpyTest.class);

    @Test
    public void testThatMockDoesntCallFunctionOfArrayList() {
        List arrayList = mock(ArrayList.class);
        Assert.assertEquals(0, arrayList.size());

        arrayList.add("element");

        Assert.assertEquals(0,arrayList.size());
    }

    @Test
    public void testThatSpyActuallyCallsAddMethod() {
        List arrayList = spy(ArrayList.class);
        Assert.assertEquals(0, arrayList.size());

        arrayList.add("element");

        Assert.assertEquals(1, arrayList.size());
    }
}
