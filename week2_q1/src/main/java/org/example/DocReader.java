package org.example;


import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DocReader {

    private static final List<String> inconsistencies = new ArrayList<>();


    public static void analyzeDocumentation(Class<?> clazz) {

        if(clazz.isAnnotationPresent(ClassDocumentation.class)) {
            ClassDocumentation classDoc = clazz.getAnnotation(ClassDocumentation.class);
            String classDescription = classDoc.value();
            System.out.println("Class: " + clazz.getSimpleName());
            System.out.println("Class Description: " + classDescription);
            if (classDescription.isEmpty()) {
                inconsistencies.add("Class '" + clazz.getSimpleName() + "' is missing documentation.");
            }
        } else {
            System.out.println("Class " + clazz.getSimpleName() + " is missing ClassDocumentation annotation.");
        }


        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(MethodDocumentation.class)) {
                MethodDocumentation methodDoc = method.getAnnotation(MethodDocumentation.class);
                String methodDescription = methodDoc.value();
                System.out.println("  Method: " + method.getName());
                System.out.println("  Method Description: " + methodDescription);
                if (methodDescription.isEmpty()) {
                    inconsistencies.add("Method '" + method.getName() + "' in class '" + clazz.getSimpleName() + "' is missing documentation.");
                }
            } else {
                System.out.println("  Method " + method.getName() + " is missing MethodDocumentation annotation.");
            }
        }
    }



    public static void main(String[] args) {

        analyzeDocumentation(Hello.class);
        analyzeDocumentation(Person.class);
        printInconsistencies();
        writeDocumentationToFile("result.txt");
    }
    private static void printInconsistencies() {
        System.out.println("Inconsistencies:");
        for (String inconsistency : inconsistencies) {
            System.out.println(" - " + inconsistency);
        }
    }
    private static void writeDocumentationToFile(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String documentation : inconsistencies) {
                writer.write(documentation + System.lineSeparator()); // Add line separator
            }
            System.out.println("Extracted documentation written to '" + fileName + "'.");
        } catch (IOException e) {
            System.err.println("Error writing documentation to file: " + e.getMessage());
        }
    }
}