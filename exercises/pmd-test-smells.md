# Detecting test smells with PMD

In folder [`pmd-documentation`](../pmd-documentation) you will find the documentation of a selection of PMD rules designed to catch test smells.
Identify which of the test smells discussed in classes are implemented by these rules.

Use one of the rules to detect a test smell in one of the following projects:

- [Apache Commons Collections](https://github.com/apache/commons-collections)
- [Apache Commons CLI](https://github.com/apache/commons-cli)
- [Apache Commons Math](https://github.com/apache/commons-math)
- [Apache Commons Lang](https://github.com/apache/commons-lang)

Discuss the test smell you found with the help of PMD and propose here an improvement.
Include the improved test code in this file.

## Answer

Règles PMD pour identifier les test smells :
- **DetachedTestCase** : Détecte les méthodes qui semblent être des tests mais ne sont pas annotées avec @Test. Cela peut entraîner une confusion sur les méthodes de test valides.
- **UnitTestAssertionsShouldIncludeMessage** : Détecte les assertions sans message explicite. Cela rend plus difficile le diagnostic des erreurs de test, augmentant le risque de "assertion roulette".
- **UnitTestContainsTooManyAsserts** : Identifie les tests avec trop d'assertions, ce qui nuit à leur lisibilité et leur maintenance. Ce smell est souvent lié à des tests trop complexes et difficiles à déboguer.
- **UnitTestShouldIncludeAssert** : Assure qu'au moins une assertion est présente dans chaque test. Cela résout des problèmes comme le Free Ride ou Piggyback, où les tests peuvent ne rien vérifier.
- **UnnecessaryBooleanAssertion** : Détecte les assertions inutiles sur des valeurs booléennes. Cela rend le test plus fragile, car des changements dans le code peuvent entraîner des échecs de test non pertinents.

### Exemple de Test Smells : 

#### Apache Commons Collections
Dans le [fichier html ](../code/tp3-test-smell/detect_test_smell_commons-collections) nous avons : 
`commons-collections\src\test\java\org\apache\commons\collections4\ArrayStackTest.java 87 Unit tests should not contain more than 1 assert(s).`

***Code d'origine :***
```java
@Test
@Override
@SuppressWarnings("unchecked")
public void testSearch() {
    final ArrayStack<E> stack = makeObject();

    stack.push((E) "First Item");
    stack.push((E) "Second Item");
    assertEquals(1, stack.search("Second Item"), "Top item is 'Second Item'");
    assertEquals(2, stack.search("First Item"), "Next Item is 'First Item'");
    assertEquals(-1, stack.search("Missing Item"), "Cannot find 'Missing Item'");
}
```
Le test contient plusieurs assertions, ce qui peut rendre les résultats flous si l'une des assertions échoue. Cela complique le débogage et nuit à la clarté du test.

***Amélioration :***
```java
@Test
public void testSearchSecondItem() {
    final ArrayStack<E> stack = makeObject();
    stack.push((E) "First Item");
    stack.push((E) "Second Item");
    assertEquals(1, stack.search("Second Item"), "Top item is 'Second Item'");
}

@Test
public void testSearchFirstItem() {
    final ArrayStack<E> stack = makeObject();
    stack.push((E) "First Item");
    stack.push((E) "Second Item");
    assertEquals(2, stack.search("First Item"), "Next Item is 'First Item'");
}

@Test
public void testSearchMissingItem() {
    final ArrayStack<E> stack = makeObject();
    stack.push((E) "First Item");
    stack.push((E) "Second Item");
    assertEquals(-1, stack.search("Missing Item"), "Cannot find 'Missing Item'");
}
```
#### Apache Commons CLI
Dans le [fichier html ](../code/tp3-test-smell/detect_test_smell_commons-cli) nous avons : 
`commons-cli\src\test\java\org\apache\commons\cli\AbstractParserTestCase.java 153 Unit tests should not contain more than 1 assert(s).`

***Code d'origine :***

```java
@Test
public void testBursting() throws Exception {
    final String[] args = { "-acbtoast", "foo", "bar" };
    final CommandLine cl = parser.parse(options, args);
    assertTrue(cl.hasOption("a"), "Confirm -a is set");
    assertTrue(cl.hasOption("b"), "Confirm -b is set");
    assertTrue(cl.hasOption("c"), "Confirm -c is set");
    assertEquals("toast", cl.getOptionValue("b"), "Confirm arg of -b");
    assertEquals(2, cl.getArgList().size(), "Confirm size of extra args");
}
```
Le test contient plusieurs assertions, ce qui rend le test plus difficile à maintenir et à déboguer. Chaque fonctionnalité devrait être testée séparément pour améliorer la lisibilité.

***Amélioration :***
```java
@Test
public void testOptionA() throws Exception {
    final String[] args = { "-acbtoast", "foo", "bar" };
    final CommandLine cl = parser.parse(options, args);
    assertTrue(cl.hasOption("a"), "Confirm -a is set");
}

@Test
public void testOptionB() throws Exception {
    final String[] args = { "-acbtoast", "foo", "bar" };
    final CommandLine cl = parser.parse(options, args);
    assertEquals("toast", cl.getOptionValue("b"), "Confirm arg of -b");
}

@Test
public void testOptionC() throws Exception {
    final String[] args = { "-acbtoast", "foo", "bar" };
    final CommandLine cl = parser.parse(options, args);
    assertTrue(cl.hasOption("c"), "Confirm -c is set");
}

@Test
public void testExtraArgsSize() throws Exception {
    final String[] args = { "-acbtoast", "foo", "bar" };
    final CommandLine cl = parser.parse(options, args);
    assertEquals(2, cl.getArgList().size(), "Confirm size of extra args");
}
````

#### Apache Commons Math
Dans le [fichier html ](../code/tp3-test-smell/detect_test_smell_commons-math) nous avons : 
`commons-math\commons-math-legacy-core\src\test\java\org\apache\commons\math4\legacy\core\MathArraysTest.java 48 Unit tests should not contain more than 1 assert(s).`

***Code d'origine :***

```java
@Test
public void testScale() {
    final double[] test = new double[] {-2.5, -1, 0, 1, 2.5};
    final double[] correctTest = Arrays.copyOf(test, test.length);
    final double[] correctScaled = new double[]{5.25, 2.1, 0, -2.1, -5.25};

    final double[] scaled = MathArrays.scale(-2.1, test);

    // Make sure test has not changed
    for (int i = 0; i < test.length; i++) {
        Assert.assertEquals(correctTest[i], test[i], 0);
    }

    // Test scaled values
    for (int i = 0; i < scaled.length; i++) {
        Assert.assertEquals(correctScaled[i], scaled[i], 0);
    }
}
```

***Amélioration :***

```java
@Test
public void testArrayUnchanged() {
    final double[] test = new double[] {-2.5, -1, 0, 1, 2.5};
    final double[] correctTest = Arrays.copyOf(test, test.length);

    final double[] scaled = MathArrays.scale(-2.1, test);

    for (int i = 0; i < test.length; i++) {
        Assert.assertEquals(correctTest[i], test[i], 0);
    }
}

@Test
public void testScaledValues() {
    final double[] test = new double[] {-2.5, -1, 0, 1, 2.5};
    final double[] correctScaled = new double[]{5.25, 2.1, 0, -2.1, -5.25};

    final double[] scaled = MathArrays.scale(-2.1, test);

    for (int i = 0; i < scaled.length; i++) {
        Assert.assertEquals(correctScaled[i], scaled[i], 0);
    }
}
``` 
#### Apache Commons Lang
Dans le [fichier html ](../code/tp3-test-smell/detect_test_smell_commons-lang) nous avons : 
`commons-lang\src\test\java\org\apache\commons\lang3\AnnotationUtilsTest.java 408 Unit tests should not contain more than 1 assert(s).`

***Code d'origine :***

```java
@Test
public void testAnnotationsOfDifferingTypes() {
    assertFalse(AnnotationUtils.equals(field1.getAnnotation(TestAnnotation.class), field4.getAnnotation(NestAnnotation.class)));
    assertFalse(AnnotationUtils.equals(field4.getAnnotation(NestAnnotation.class), field1.getAnnotation(TestAnnotation.class)));
}
```
***Amélioration :***

```java
@Test
public void testAnnotationComparison1() {
    assertFalse(AnnotationUtils.equals(field1.getAnnotation(TestAnnotation.class), field4.getAnnotation(NestAnnotation.class)));
}

@Test
public void testAnnotationComparison2() {
    assertFalse(AnnotationUtils.equals(field4.getAnnotation(NestAnnotation.class), field1.getAnnotation(TestAnnotation.class)));
}
```
Ces modifications permettent de respecter la règle PMD en divisant les tests pour qu'ils contiennent une seule assertion, améliorant ainsi la lisibilité et la maintenabilité.
