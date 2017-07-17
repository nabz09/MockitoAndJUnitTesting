package PowerMock;

import sun.text.normalizer.Utility;

import java.util.ArrayList;
import java.util.List;

interface Dependency {
    List<Integer> retrieveAllStats();
}

public class SystemUnderTest {
    private Dependency dependency;

    public int methodUsingAnArrayListConstructor() {
        ArrayList list = new ArrayList();
        return list.size();
    }

    public int methodCallingStaticMethod() {
        List<Integer> stats = dependency.retrieveAllStats();
        long sum = 0;

        for(int stat: stats){
            sum += stat;
        }

        return UtilityClass.staticMethod(sum);
    }

    private long privateMethodUnderTest() {
        List<Integer> stats = dependency.retrieveAllStats();
        long sum = 0;
        for(int stat: stats) {
            sum += stat;
        }
        return sum;
    }
}

class UtilityClass {
    static int staticMethod(long sum) {
        throw new RuntimeException("Dont want to be calling this method");
    }
}