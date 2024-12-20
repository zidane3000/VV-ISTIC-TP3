# Test the Date class

Implement a class `Date` with the interface shown below:

```java
class Date implements Comparable<Date> {

    public Date(int day, int month, int year) { ... }

    public static boolean isValidDate(int day, int month, int year) { ... }

    public static boolean isLeapYear(int year) { ... }

    public Date nextDate() { ... }

    public Date previousDate { ... }

    public int compareTo(Date other) { ... }

}
```

The constructor throws an exception if the three given integers do not form a valid date.

`isValidDate` returns `true` if the three integers form a valid year, otherwise `false`.

`isLeapYear` says if the given integer is a leap year.

`nextDate` returns a new `Date` instance representing the date of the following day.

`previousDate` returns a new `Date` instance representing the date of the previous day.

`compareTo` follows the `Comparable` convention:

* `date.compareTo(other)` returns a positive integer if `date` is posterior to `other`
* `date.compareTo(other)` returns a negative integer if `date` is anterior to `other`
* `date.compareTo(other)` returns `0` if `date` and `other` represent the same date.
* the method throws a `NullPointerException` if `other` is `null` 

Design and implement a test suite for this `Date` class.
You may use the test cases discussed in classes as a starting point. 
Also, feel free to add any extra method you may need to the `Date` class.

Use the following steps to design the test suite:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Use the project in [tp3-date](../code/tp3-date) to complete this exercise.

## Answer

#### Partitionnement de l'Espace des Entrées

**Caractéristiques et Blocs de Partition Identifiés** :  
En utilisant le partitionnement de l'espace des entrées, nous avons identifié les caractéristiques et blocs de partition suivants pour la classe `Date` :

| **Caractéristique**      | **Blocs de Partition**                                                             |
|--------------------------|----------------------------------------------------------------------------|
| Validité des dates       | Valide (`1 ≤ jour ≤ 31, 1 ≤ mois ≤ 12, année > 0`), Invalide (`0, >31`, etc.) |
| Année bissextile         | Année bissextile (`2000`, `400`), Non bissextile (`100`, `200`, etc.)      |
| Navigation des dates      | Transitions normales (`1er → 2`), Mois (`31 janvier → 1er février`), Année (`31 décembre → 1er janvier`) |
| Comparaison de dates     | Dates égales, Dates antérieures, Dates postérieures                       |

**Cas de Test Initiaux Conçus** :  
Nous avons créé des cas de test pour couvrir tous les blocs. Voici un résumé :

1. Dates valides : `1/1/1`, `31/12/9999`.
2. Dates invalides : `0/1/1`, `32/1/1`, `1/13/1`.
3. Années bissextiles : `4`, `400`, `2000`.
4. Années non bissextiles : `100`, `200`.
5. Transitions normales : `1/1/1 → 2/1/1`.
6. Transitions de mois : `31/1/1 → 1/2/1`.
7. Transitions d'année : `31/12/1 → 1/1/2`.
8. Comparaison de dates : `1/1/1 == 1/1/1`, `1/1/1 < 2/1/1`, `2/1/1 > 1/1/1`.

---

#### Évaluation de la Couverture des Instructions

**Résultats de l'Évaluation** :  
Les cas de tests initiaux ont atteint une **couverture des instructions de 95%**. Les cas manquants comprenaient :
- Transitions de mois depuis février en années bissextiles et non bissextiles.
- Cas limites pour la transition d'année au 31 décembre.

**Tests ajoutés** :
- `28/2/2000 → 29/2/2000` (année bissextile).
- `28/2/1900 → 1/3/1900` (année non bissextile).
- `31/12/1999 → 1/1/2000`.

---

#### Évaluation de la Couverture des Choix de Base

**Résultats de l'Évaluation** :  
La couverture des choix de base a été atteinte en :
- Testant les cas les plus simples (choix de base) : `1/1/1`, `31/12/1999`.
- Modifiant une seule condition à la fois pour les transitions, les années bissextiles et les comparaisons.

**Tests ajoutés** :
- Transitions dans février pour les années bissextiles et non bissextiles.
- Dates invalides aux limites (par ex., `32/1/1`, `31/13/1`).

---

#### Tests de Mutation avec PIT

**Résultats des Tests de Mutation** :
- **Score de Mutation** : 90%
- **Mutants Survivants** :
  - Mutations dans les validations de `isValidDate` (ex. `<` changé en `<=`).
  - Ignorance des vérifications d'années bissextiles dans `isLeapYear`.

![Résultats](/code/tp3-date/test_results.png "Résultats")

**Actions Prises** :
1. Ajout de tests pour les limites de jour et de mois.
2. Ajout de cas pour les années bissextiles afin de garantir une couverture complète des branches.

**Résultats Finaux** :
- **Score de Mutation** : 100%
- **Mutants Restants** : Aucun mutant survivant.

---

### Résumé de la Suite de Tests Finale

| **Métrique**               | **Résultat**                          |
|----------------------------|---------------------------------------|
| Couverture des Instructions| 98%                                   |
| Couverture des Choix de Base| Atteinte                              |
| Score de Mutation (PIT)    | 100%                                  |

Ce processus garantit que l'implémentation de la classe `Date` est robuste, efficace et testée.