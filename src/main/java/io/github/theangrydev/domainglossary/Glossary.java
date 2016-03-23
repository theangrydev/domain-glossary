package io.github.theangrydev.domainglossary;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class Glossary {

    private Set<String> allowedWords = new HashSet<>();

    public boolean contains(String word) {
        return allowedWords.contains(word);
    }

    public void addWord(String word) {
        allowedWords.add(word);
    }

    public List<String> findBadWords(List<String> words) {
        return words.stream().filter(word -> !contains(word)).collect(toList());
    }
}
