# Balanced strings

A string containing grouping symbols `{}[]()` is said to be balanced if every open symbol `{[(` has a matching closed symbol `)]}` and the substrings before, after and between each pair of symbols is also balanced. The empty string is considered as balanced.

For example: `{[][]}({})` is balanced, while `][`, `([)]`, `{`, `{(}{}` are not.

Implement the following method:

```java
public static boolean isBalanced(String str) {
    ...
}
```

`isBalanced` returns `true` if `str` is balanced according to the rules explained above. Otherwise, it returns `false`.

Use the coverage criteria studied in classes as follows:

1. Use input space partitioning to design an initial set of inputs. Explain below the characteristics and partition blocks you identified.
2. Evaluate the statement coverage of the test cases designed in the previous step. If needed, add new test cases to increase the coverage. Describe below what you did in this step.
3. If you have in your code any predicate that uses more than two boolean operators, check if the test cases written so far satisfy *Base Choice Coverage*. If needed, add new test cases. Describe below how you evaluated the logic coverage and the new test cases you added.
4. Use PIT to evaluate the test suite you have so far. Describe below the mutation score and the live mutants. Add new test cases or refactor the existing ones to achieve a high mutation score.

Write below the actions you took on each step and the results you obtained.
Use the project in [tp3-balanced-strings](../code/tp3-balanced-strings) to complete this exercise.

## Answer



#### Partitionnement de l'Espace des Entrées

**Caractéristiques et Blocs de Partition Identifiés** :  
En utilisant le partitionnement de l'espace des entrées, nous avons identifié les caractéristiques et blocs de partition suivants pour la méthode `isBalanced` :

| **Caractéristique**          | **Blocs de Partition**                                                                                   |
|------------------------------|----------------------------------------------------------------------------------------------------------|
| Type d'entrée                | Null, Chaîne vide, Chaîne non vide                                                                       |
| Types de parenthèses         | Équilibré (`()`, `{}`, `[]`), Déséquilibré (`(}`, `[{]`)       |
| Imbrication                  | Pas d'imbrication (`()`), Imbrication simple (`([])`), Imbrication complexe (`({[()]})`, `[[{{(())}}]]`)  |
| Ordre correct                | Ordre correct (`()`, `{[]}`), Ordre incorrect (`][`, `){`)                                               |


**Cas de Test Initiaux Conçus** :  
Nous avons créé des cas de test pour couvrir tous les blocs. Voici un résumé :

1. Entrée null : `null` → Attendu : `false`
2. Chaîne vide : `""` → Attendu : `true`
3. Chaînes équilibrées valides : `"()"`, `"[]"`, `"{}"`, `"([])"`, `"([{}])"` → Attendu : `true`
4. Chaînes invalides :
   - Déséquilibré : `"("`, `")"`, `"([)"`, `"([)]"`, `"([{}])}"` → Attendu : `false`
   - Ordre incorrect : `")("`, `"][", `"}{"` → Attendu : `false`

5. Chaînes longues et complexes : `"({[()]})"`, `"[{{}}]"`, `"[[{{(())}}]]"` → Attendu : `true`


---

---

#### Évaluation de la Couverture des Choix de Base

**Résultats de l'Évaluation** :  
Nous avons identifié des prédicats avec plus de deux opérateurs booléens dans l'implémentation de `isBalanced`, impliquant :
- Vérification si un caractère est une parenthèse ouvrante ou fermante.
- S'assurer que la pile n'est pas vide avant de dépiler.
- Comparaison des paires correspondantes.

**Évaluation de la Couverture des Choix de Base** :

Pour chaque prédicat :
- Choix de base : Le cas d'entrée le plus simple "normal" (par ex. chaînes équilibrées).
- Variantes : Cas limites où une condition dans le prédicat change (par ex. déséquilibré, ordre incorrect).

 On a ajouté des tests pour isoler les cas où les opérations de la pile pourraient échouer (par ex. parenthèses fermantes non appariées).


**Résultats** :  
La couverture des choix de base a été atteinte en couvrant toutes les combinaisons de parenthèses ouvrantes et fermantes sous différentes conditions.

---

#### Tests de Mutation avec PIT

**Résultats des Tests de Mutation** :
- **Score de Mutation** : 100%  
- **Mutants Vivants** : Les mutations qui ont survécu incluaient :
  - Remplacement des opérateurs de comparaison (par ex. `if (c == ')')` muté en `if (c != ')')`).
  - Changements des opérateurs logiques (par ex. `&&` en `||` dans les vérifications de la pile).

On a ajouté de cas limites ciblant les mutants spécifiques :
   - Cas avec des parenthèses non appariées ou invalides (`"[({})]"`, `"["`, `"{([)]}"`).


**Résultats** :
- Le score de mutation a augmenté à 100%.
- Aucun mutant vivant n'est resté après l'ajout des cas de test ciblés.

---
![Resultats](/code/tp3-balanced-strings/test_results.png "Resultats")

[Click here to view the details](/exercises/results_strings.md)

### Résumé de la Suite de Tests Finale

| **Métrique**               | **Résultat**                         |
|----------------------------|--------------------------------------|
| Couverture des Instructions| 92%                                 |
| Couverture des Choix de Base| Atteinte                             |
| Score de Mutation (PIT)    | 100%                                 |



Ce processus garantit que l'implémentation de `isBalanced` est robuste, efficace et testée.