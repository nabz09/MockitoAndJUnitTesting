package PowerMock;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.junit.JUnitRule;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingConstructorTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private ArrayList mockList;

    @InjectMocks
    private SystemUnderTest systemUnderTest;

    @Test
    public void testBadNames() throws Exception {
        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);
        when(mockList.size()).thenReturn(5);

        int size = systemUnderTest.methodUsingAnArrayListConstructor();

        Assert.assertEquals(5, size);
    }
}
