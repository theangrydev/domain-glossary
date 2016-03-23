package io.github.theangrydev.domainglossary;

import com.github.javaparser.ast.CompilationUnit;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;

import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class LanguageCheckerTest implements WithAssertions {

    @Test
    public void shouldCheckLanguageUsedAndReportViolations() {
        JavaFileParser javaFileParser = new JavaFileParser();
        List<CompilationUnit> javaFiles = javaFileParser.parseJavaFiles(Paths.get("./src/test/java"));

        Glossary glossary = new Glossary();
        LanguageChecker languageChecker = new LanguageChecker(glossary);

        List<String> violations = javaFiles.stream().map(languageChecker::checkLanguage).flatMap(List::stream).map(Violation::toString).collect(toList());

        assertThat(violations).contains("");
    }

}