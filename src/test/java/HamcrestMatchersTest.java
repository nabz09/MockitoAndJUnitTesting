import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsCollectionContaining.hasItems;

public class HamcrestMatchersTest {
    @Test
    public void testArrayMethods() {
        List<Integer> scores = Arrays.asList(3,5,19,4);

        assertThat(scores, hasSize(4));
        assertThat(scores, hasItems(2,4,5));

        assertThat(scores, hasItems(5,3,4));
        assertThat(scores, everyItem(greaterThan(2)));
        assertThat(scores, everyItem(lessThan(22)));
    }

    @Test
    public void testStringMethods() {
        String a = "";
        String b = null;

        assertThat(a, isEmptyString());
        assertThat(b, isEmptyOrNullString());
    }

    @Test
    public void hashMapMethods() {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("first", "lol");
        hashMap.put("second", "bloppp");

        assertThat(hashMap, hasKey(any(String.class)));
    }
}
