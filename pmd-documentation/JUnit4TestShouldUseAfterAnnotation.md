
# JUnit4TestShouldUseAfterAnnotation

*Usage:* 
`pmd -d <source code folder> -R category/java/bestpractices.xml/JUnit4TestShouldUseAfterAnnotation -format <output format>`

*Description:*

In JUnit 3, the tearDown method was used to clean up all data entities required in running tests.
JUnit 4 skips the tearDown method and executes all methods annotated with @After after running each test.
JUnit 5 introduced @AfterEach and @AfterAll annotations to execute methods after each test or after all tests in the class, respectively.
        

*Example:*
```java


public class MyTest {
    public void tearDown() {
        bad();
    }
}
public class MyTest2 {
    @After public void tearDown() {
        good();
    }
}

        
```