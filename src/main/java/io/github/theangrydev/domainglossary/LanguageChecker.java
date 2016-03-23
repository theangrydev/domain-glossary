package io.github.theangrydev.domainglossary;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.GenericVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static org.apache.commons.lang3.StringUtils.splitByCharacterTypeCamelCase;

public class LanguageChecker extends GenericVisitorAdapter<List<Violation>, List<Violation>> {

    private final Glossary glossary;

    private ClassOrInterfaceDeclaration currentType;

    public LanguageChecker(Glossary glossary) {
        this.glossary = glossary;
    }

    public List<Violation> checkLanguage(CompilationUnit javaFile) {
        return javaFile.accept(this, new ArrayList<>());
    }

    @Override
    public List<Violation> visit(ClassOrInterfaceDeclaration classOrInterfaceType, List<Violation> violations) {
        currentType = classOrInterfaceType;
        return super.visit(classOrInterfaceType, violations);
    }

    @Override
    public List<Violation> visit(MethodDeclaration methodDeclaration, List<Violation> violations) {
        super.visit(methodDeclaration, violations);
        stream(splitByCharacterTypeCamelCase(methodDeclaration.getName()))
                .map(String::toLowerCase)
                .filter(this::wordIsNotAllowed)
                .map(word -> new Violation(word, currentType.getName() + "." + methodDeclaration.getName()))
                .forEach(violations::add);
        return violations;
    }

    private boolean wordIsNotAllowed(String word) {
        return !glossary.contains(word);
    }
}
