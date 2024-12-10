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

1. **`assertEquals`**
   - Compare **les valeurs** des objets.

2. **`assertSame`**
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
