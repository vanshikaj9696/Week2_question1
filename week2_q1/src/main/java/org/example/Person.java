package org.example;

@ClassDocumentation("This class is for Person")
public class Person {
    @MethodDocumentation("This method is person")
    public void walk(){
        System.out.println("person is walking");
    }
}

//public class UndocumentedClass {
//    @MethodDocumentation("Sample method with documentation")
//    public void documentedMethod() {
//        System.out.println("I am documented method from Undocumented class");
//
//    }
//
//    public void undocumentedMethod() {
//        System.out.println("I am Undocumented method from Undocumented class");
//
//    }
//}
