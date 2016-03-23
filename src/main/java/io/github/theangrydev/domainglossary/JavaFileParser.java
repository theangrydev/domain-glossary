package io.github.theangrydev.domainglossary;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class JavaFileParser {

    public List<CompilationUnit> parseJavaFiles(Path path) {
        try {
            return Files.walk(path).filter(Files::isRegularFile).map(Path::toFile).map(this::parseJavaFile).collect(toList());
        } catch (IOException checkedException) {
            throw new RuntimeException(checkedException);
        }
    }

    private CompilationUnit parseJavaFile(File javaFile) {
        try {
            return JavaParser.parse(javaFile);
        } catch (ParseException | IOException checkedException) {
            throw new RuntimeException(checkedException);
        }
    }
}
