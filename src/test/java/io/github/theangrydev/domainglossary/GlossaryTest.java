package io.github.theangrydev.domainglossary;

import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import static java.util.Arrays.asList;

public class GlossaryTest implements WithAssertions {

    private Glossary glossary = new Glossary();

    @Test
    public void shouldNotContainAWordThatIsNotInTheGlossary() {
        assertThat(glossary.contains("badword")).describedAs("Glossary contains 'badword'").isFalse();
    }

    @Test
    public void shouldContainAWordThatIsInTheGlossary() {
        glossary.addWord("allowedword");

        assertThat(glossary.contains("allowedword")).describedAs("Glossary contains 'allowedword'").isTrue();
    }

    @Test
    public void shouldFindBadWords() {
        glossary.addWord("allowedword");

        assertThat(glossary.findBadWords(asList("allowedword", "badword"))).containsExactly("badword");
    }
}
