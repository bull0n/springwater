---
title: SpringWater
subtitle: Concept and testplan
lang: fr
author:
- Bulloni Lucas <lucas.bulloni@he-arc.ch>
- Fleury Malik <malik.fleury@he-arc.ch>
- Wermeille Bastien <bastien.wermeille@he-arc.ch>
date: \today
pagesize: A4
numbersections: true
documentclass: scrartcl
geometry: margin=2.5cm
header-includes: |
      \usepackage{fancyhdr}
      \pagestyle{fancy}
      \fancyhead[R]{Lucas Bulloni, Malik Fleury \& Bastien Wermeille}
      \usepackage{float}
      \floatplacement{figure}{H}
---
\newpage

\tableofcontents

\newpage

# Stratégie de test

## Attentes qualitatif du projet

1. Interface utilisable simplement
2. Sécurité des comptes
      1. Authentification
      2. Création de compte
3. Temps de réponse acceptable (3s.)
4. Stockage d'image
5. Votes et favoris utilisables


## Objectifs de tests

**Elément Secondaire** : Visualisation des boissons

La fonctionnalité principale de notre application web est la visualisation des boissons.

**Elément secondaire** : Authentification

L'application se voulant communautaire, l'authentification est également un aspect crucial au bon fonctionnement.

**Couverture de tests** : 60%

Les tests devront couvrir au minimum 60% du code de toute l'application.

## Périmètre de test

Pour ce projet, nous avons décidé de réaliser quatre types de tests.

- tests unitaires avec Spring
- tests de qualité avec SonarCloud
- tests d'intégration avec Katalon
- tests de performance avec Octoperf (JMeter)

# Producédure de test

## Equipe

- Bulloni Lucas
- Fleury Malik
- Wermeille Bastien

## Exécution des tests

La partie ci-dessous va présenter la procédure d'exécution des différents tests. Quand, avec quels outils et comment le résultat va être analysé. Mise à part les tests de performance, les tests sont exécutés dans une Pileline Jenkins qui doit être lancé manuellement. Les githooks n'ont pas pu être implémentés par manque de temps et n'étaient pas réellement crucial aux tests de l'application.

### Tests unitaire et intégration de repository

Les tests unitaires et d'intégration de repository sont exécutés lors du build de l'application par Spring. Le but sera de tester un nombre maximum de controlleur. Les fonctions de bases devront être testés en premier.

Voici l'ordre de tests des controlleurs :

1. BoissonController
2. UserController
3. CategorieController
4. SearchControlleur
5. VoteContreoller
6. FavorisController
7. ImageController

### Tests de qualité avec SonarCloud

Les tests de qualités s'éxecutent automatiquement avec Jenkins. Le but est d'atteindre 60% de couverture de tests et réduire au maximum les codes smells et failles de vulnérabilité.

### Tests d'intégration avec Katalon

Les tests Katalons sont exécutés dans un Docker via Jenkins, donc tout est exécuté en ligne de commande. On testera les interfaces graphiques, telle que les formulaires.

### Tests de performance

Les tests de performances ne sont pas intégrés à la pipeline Jenkins. Il faut donc enregistrer manuellement un fichier HAR avec Chrome et l'importer dans Octoperf pour lancer le test de performance. Le but est d'atteindre un temps de réponse acceptable (voir **Objectifs de tests**).

# Tests de performance

- Requêtes / S
- Consommation CPU / Mémoire

# Organisation

**Bulloni Lucas**

- Ecriture de tests unitaires
- Ecriture de tests Katalon

**Fleury Malik**

- Ecriture de tests unitaires
- Tests de performance

**Wermeille Bastien**

- Ecriture de tests unitaires
- Corrections des tests de qualité

# Tableau de stratégie de tests

A: risque élevé
B: risque moyennement élevé
C: risque faible

| Caractéristique | Classe de risque | Test de validation | Tests unitaires | Tests de charges |
|-----------------|------------------|--------------------|-----------------|------------------|
| Gestion des boissons       | A | ** | *** | *  |                                           
| Visualisation des boissons | A | ** | *** | *  |
| Images                     | C |    | **  |    |                                                                
| Notation des boissons      | C |    | **  |    |
| Authentification           | A |    |     |    |
| Création de compte         | B | *  | *   |    |
| **Ergonomie**              |   |    |     |    |
| Interface simple           | B | ** |     |    |
| **Efficience**             |   |    |     |    |
| Temps de réponse max de 3s | B |    |     | ** |
