package collectionpro;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TwoWordsTest {

    @Test
    public void whenTwoEqualWords() {
        TwoWords tw = new TwoWords();
        String a = "мама";
        String b = "аамм";
        assertThat(tw.comp(a, b), is(true));
    }

    @Test
    public void whenTwoNotEqualWords() {
        TwoWords tw = new TwoWords();
        String a = "wmobile";
        String b = "mobeil";
        assertThat(tw.comp(a, b), is(false));
    }
}
