package PowerMock;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class InvokingPrivateMethodTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Dependency dependency;

    @InjectMocks
    private SystemUnderTest systemUnderTest;

    @Test
    public void testPrivateMethodCalled() throws Exception {
        List<Integer> nums = Arrays.asList(1,5,9);
        when(dependency.retrieveAllStats()).thenReturn(nums);

        long result = Whitebox.invokeMethod(systemUnderTest, "privateMethodUnderTest");

        Assert.assertEquals(15, result);
    }
}
