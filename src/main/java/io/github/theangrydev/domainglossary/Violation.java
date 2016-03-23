package io.github.theangrydev.domainglossary;

public class Violation {

    private final String word;
    private final String location;

    public Violation(String word, String location) {
        this.word = word;
        this.location = location;
    }

    @Override
    public String toString() {
        return word + "@" + location;
    }
}
