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

Voici les règles de PMD qui aident à identifier des "test smells":

- **DetachedTestCase** : Détecte les méthodes qui semblent être des cas de test mais qui ne sont pas annotées avec @Test.
- **UnitTestAssertionsShouldIncludeMessage** : Détecte les assertions sans message explicite, ce qui rend difficile de -diagnostiquer les échecs de test.
- **UnitTestContainsTooManyAsserts** : Identifie les tests qui contiennent trop d'assertions, ce qui peut rendre le test difficile à comprendre et à maintenir (lié à "Eager test").
- **UnitTestShouldIncludeAssert** : Vérifie que chaque test inclut au moins une assertion, ce qui peut résoudre le "Free Ride" ou "Piggyback".
- **UnnecessaryBooleanAssertion** : Détecte les assertions inutiles sur des valeurs booléennes, ce qui peut rendre le test plus fragile ou erratique.

### Tests smell 



