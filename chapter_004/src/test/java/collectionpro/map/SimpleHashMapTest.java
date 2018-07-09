package collectionpro.map;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    @Test
    public void whenAddThreeElements() {
        SimpleHashMap<String, Integer> sh = new SimpleHashMap<>();
        sh.insert("Boris", 16);
        sh.insert("Fedor", 32);
        sh.insert("Alex", 8);
        sh.insert("Alex", 51);
        assertThat(sh.contains("Boris"), is(true));
        assertThat(sh.contains("Fedor"), is(true));
        assertThat(sh.contains("Alex"), is(true));
        assertThat(sh.get("Alex"), is(8));
    }

    @Test
    public void whenGetTwoElements() {
        SimpleHashMap<String, Integer> sh = new SimpleHashMap<>();
        sh.insert("Boris", 16);
        sh.insert("Fedor", 32);
        assertThat(sh.get("Boris"), is(16));
        assertThat(sh.get("Fedor"), is(32));
    }

    @Test
    public void whenDeleteOneElement() {
        SimpleHashMap<String, Integer> sh = new SimpleHashMap<>();
        sh.insert("Boris", 16);
        sh.insert("Fedor", 32);
        sh.delete("Fedor");
        assertThat(sh.contains("Fedor"), is(false));
    }


}
