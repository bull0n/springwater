---
title: SpringWater
subtitle: Plan de test
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
1. Sécurité des comptes
      1. Authentification
      1. Création de compte
1. Temps de réponse acceptable (2s.)
1. Stockage d'image
1. Votes et favoris utilisables

## Objectifs de tests

**Elément Secondaire** : Visualisation des boissons

La fonctionnalité principale de notre application web est la visualisation des boissons.

**Elément secondaire** : Authentification

L'application se voulant communautaire, l'authentification est aussi un aspect crucial au bon fonctionnement.

**Couverture de tests** : 50%

Les testes devront couvrir minimum 50% du code de toute l'application.

## Périmètre de test

Pour ce projet, nous avons décidé de réalisé quatre types de testes.

- tests unitaires avec Spring
- des tests d'intégration pour les repository
- des tests d'intégration avec Katalon
- tests de performance avec Octoperf (JMeter)

# Producédure de test

## Equipe

- Bulloni Lucas
- Fleury Malik
- Wermeille Bastien

## Exécution des tests

La partie ci-dessous va présenter la procédure d'exécution des différents tests. Quand, avec quels outils et comment le résultat va être analysé. Mise à part les tests de performance, les tests sont exécutés dans une Pileline Jenkins qui doit être lancé manuellement. Les githooks n'ont pas pu être implémentés par manque de temps et n'étaient pas réellement crucial aux tests de l'application.

### Tests unitaire et intégration de repository

Les tests unitaires et d'intégration de repository sont exécutés lors du build de l'application par Spring.

### Tests d'intégration avec Katalon

Les tests Katalons sont exécutés dans un Docker via Jenkins, donc tout est exécuté en ligne de commande.

### Tests de performance

Les tests de performances ne sont pas intégrés à la pipeline Jenkins. Il faut donc enregistrer manuellement un fichier HAR avec Chrome et l'importer dans Octoperf pour lancer le test de performance. Le but est d'atteindre un temps de réponse acceptable (voir **Objectifs de tests**).




# Tests de performance

- Requêtes / S
- Consommation CPU / Mémoire

# Test d'acceptation

# Tableau de stratégie de tests

A: risque élevé
B: risque moyennement élevé
C: risque faible

| Caractéristique | Classe de risque | Test de validation | Tests unitaires | Tests de charges |
| :-------------- | :--------------- | :----------------- | :-------------- | :--------------- |
| Fonctionnalités |                  |                    |                 |                  |
| - Gestion des boissons | C         |                    |                 |                  |
| - Images | A                       |                    |                 |                  |
| - Notation des boissons | B        |                    |                 |                  |
| - Authentification | C             |                    |                 |                  |
| - Création de compte | C           |                    |                 |                  |
| Ergonomie |                        |                    |                 |                  |
| - Interface simple | C             |                    |                 |                  |
| Efficience |                       |                    |                 |                  |
| - Temps de réponse max de 2s | B   |                    |                 |                  |

\newpage

\listoffigures

# References
