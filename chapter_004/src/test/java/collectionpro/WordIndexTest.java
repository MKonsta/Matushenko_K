package collectionpro;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

public class WordIndexTest {

    @Test
    public void whenFindThreeWords() throws IOException {
        WordIndex wordIndex = new WordIndex("Raskaz.txt");
        Set<Integer> expect = new TreeSet<>();
        expect.add(8);
        expect.add(14);
        assertThat(wordIndex.getIndex4Word("rezjume"), is(expect));
    }

    @Test
    public void whenNoSoWord() throws IOException {
        WordIndex wordIndex = new WordIndex("Raskaz.txt");
        Set<Integer> expect = null;
        assertThat(wordIndex.getIndex4Word("Rezjume"), is(expect));
    }
}
