# On assertions

Answer the following questions:

1. The following assertion fails `assertTrue(3 * .4 == 1.2)`. Explain why and describe how this type of check should be done.

2. What is the difference between `assertEquals` and `assertSame`? Show scenarios where they produce the same result and scenarios where they do not produce the same result.

3. In classes we saw that `fail` is useful to mark code that should not be executed because an exception was expected before. Find other uses for `fail`. Explain the use case and add an example.

4. In JUnit 4, an exception was expected using the `@Test` annotation, while in JUnit 5 there is a special assertion method `assertThrows`. In your opinion, what are the advantages of this new way of checking expected exceptions?

## Answer

### 1. Vérification de l'égalité des nombres à virgule flottante

**L'assertion** `assertTrue(3 * 0.4 == 1.2)` **échoue**.
L'opération `3 * 0.4` est calculée comme `1.2000000000000002` plutôt que exactement `1.2`. Cela provient du fait que le nombre `0.4` ne peut pas être exactement représenté en binaire sous la forme d'un nombre flottant. Par conséquent, la comparaison entre le résultat de `3 * 0.4` et `1.2` échoue avec `==`, car cela compare et vérifie l'égalité exacte.

Il faut alors vérifier si les nombres sont **"suffisamment proches"** l'un de l'autre, dans une petite tolérance (également appelée epsilon). Cela peut être fait en utilisant une méthode comme : ```assertTrue(Math.abs(result - expected) < tolerance);```, où tolerance est une petite valeur qui prend en compte les erreurs d'arrondi possibles.

**Par exemple**

```assertTrue(Math.abs(3 * 0.4 - 1.2) < 1e-9) ```, avec **1e-9** est une petite valeur de tolérance. La valeur exacte de la tolérance dépend de la précision requise pour votre application.

### 2. Differences entre `assertEquals et `assertSame`

#### Différences entre `assertEquals` et `assertSame` en Java

Lors de l'écriture de tests unitaires en Java, il est important de comprendre la différence entre `assertEquals` et `assertSame`. Ce fichier explique leurs différences avec des exemples concrets.

**`assertEquals`**
   - Compare **les valeurs** des objets.

**`assertSame`**
   - Vérifie si **les deux références pointent vers le même objet** en mémoire.
     
#### Scenario `assertEquals` = `assertSame`
```java
class TestAssertEqualsAndSame {

 @Test
 void testAssertEquals() {
  assertEquals("1", "1"); // assertion will pass
 }

 @Test
 void testAssertSame() {
  assertSame("1", "1"); // assertion will pass
 }
}
```

#### Scenario `assertEquals` != `assertSame`
```java
class TestAssertEqualsAndSame {

 @Test
 void testAssertEquals() {
  assertEquals("1", new String("1")); // assertion will pass
 }

 @Test
 void testAssertSame() {
  /* 
    assertion will fail because the references of both objects are different.
    new String("1") will create a new object
  */
  assertSame("1", new String("1"));
 }
}
```
### 3. Utilisation de fail

L'assetion de fail peut etre utilisé pour d'autre cas comme : 
1. Test incomplet ou pas encore :
```java
@Test
public void incompleteTest() {
    fail("Pas encore implémenté");
}
```
2. Exception inattendue, lorsqu'une exception ne devrait pas être lancée, mais qu'elle l'est malgré tout 

```java
@Test
public void unexpectedException() {
    try {
        safeMethod();
        // Code de test supplémentaire
    } catch (Exception e) {
        fail("Exception inattendue lancée");
    }
}
```

3. Vérification de condition Vous pouvez appeler fail() lorsqu'un résultat ne respecte pas une condition attendue 
```java
@Test
public void testingCondition() {
    int result = randomInteger();
    if(result > Integer.MAX_VALUE) {
        fail("Le résultat ne peut pas dépasser la valeur maximale d'un entier");
    }
    // Code de test supplémentaire
}
```

4. Le code retourne rien ou ne se termine pas comme attendu 
```java
@Test
public void returnBefore() {
    int value = randomInteger();
    for (int i = 0; i < 5; i++) {
        // Retourne si (value + i) est un nombre pair
        if ((i + value) % 2 == 0) {
            return;
        }
    }
    fail("La méthode aurait dû retourner plus tôt");
}
```

### 4. Avantages de `assertThrows`

1. Lisibilité : assertThrows permet de spécifier explicitement l'exception attendue et l'action qui pourrait la déclencher. Il est plus direct et moins ambigu qu'une annotation @Test(expected=...) de JUnit 4.

2. Meilleure gestion des exceptions : capturer et vérifier des exceptions, et cela évite les cas où le test passe silencieusement même si l'exception attendue n'est pas lancée. Examiner d'autres aspects, comme les messages d'erreur, les causes ou les contextes associés à l'exception.

3. Flexibilité : Contrairement à @Test(expected=...), qui spécifie une exception à vérifier sans possibilité de détails supplémentaires, assertThrows permet d'exécuter des actions supplémentaires après l'exception. Cela rend les tests plus robustes et adaptables à différents scénarios.

4. Facilité d'intégration avec JUnit 5 : assertThrows est conçu pour être utilisé dans JUnit 5, ce qui s'intègre bien avec les autres fonctionnalités modernes de JUnit 5.


