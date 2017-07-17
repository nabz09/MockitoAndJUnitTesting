package PowerMock;

import com.sun.org.apache.xpath.internal.SourceTree;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class)
public class MockingStaticMethodTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private Dependency dependency;

    @InjectMocks
    private SystemUnderTest systemUnderTest;

    @Test
    public void test(){
        System.out.println("help");
    }

    @Test
    public void tests() {
        List<Integer> nums = Arrays.asList(1,5,9);
        when(dependency.retrieveAllStats()).thenReturn(nums);
        PowerMockito.mockStatic(UtilityClass.class);
        when(UtilityClass.staticMethod(anyInt())).thenReturn(150);

        int result = systemUnderTest.methodCallingStaticMethod();
        Assert.assertEquals(150, result);

        PowerMockito.verifyStatic();
        UtilityClass.staticMethod(15);
    }
}
