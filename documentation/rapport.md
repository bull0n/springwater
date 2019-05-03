---
title: SpringWater
subtitle: Rapport
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

# Introduction

Pour le cours de J2EE nous avons réalisé une application avec l'aide de Spring Boot. Le but de ce projet est de créer un site communautaire où les utilisateurs ajoutent des boissons et votent pour leurs favorites. La partie test du projet a été faite en parallèle avec le cours de Qualité Logiciel.

# Réalisation

Le projet a été réalisé en équipe de 3 personnes avec Spring Boot. Les tâches planifiées ont bien été complétées, la suite de cette partie explique les différents outils utilisés et l'état du projet.

## Bibliothèques

Nous n'avons pas utilisé de bibliothèque en plus de Spring pour le côté Backend, en ce qui concerne le frontend, nous avons utilisé Bootstrap et jQuery afin de donner un air moderne au site.

## Repository git

Pour des fins organisationnelles, nous avons travaillé avec Git. Voici le lien du repository:

- [https://github.com/bull0n/springwater](https://github.com/bull0n/springwater)

## Architecture du code

Cette partie du document va expliquer comment les fichiers ont été organisés.

### Java

Nous avons essayé de grouper les classes par fonctionnement dans le projet. Par exemple, nous avons groupé les contrôleurs dans un package.

L'architecture finale du projet est la suivante :

- config
- controllers
- exceptions
- models
- entities
- repository
- security
- service
- impl

### Ressources

Les ressources sont composées de deux types de fichiers, les statiques où l'on trouve les fichiers JS et CSS et les templates qui contiennent les vues de l'application.

#### Static

Nous avons séparé les fichiers JavaScript et les fichiers de styles dans chaque dossier. Nous n'avons pas décidé de plus séparés, car l'application ne possède pas beaucoup de ces ressources.

- styles
- script

#### Templates

Les templates ont été séparés par ressources et les fragments ont été isolés dans un dossier.

- boisson
- categories
- fragments
- security

## Seed de la base de données

Nous avons créé un fichier qui permet de remplir la base de données. C'est le fichier data.sql, pour lancer ce fichier il faut décommenter la ligne suivante dans le fichier `application.properties`:

```
spring.datasource.initialization-mode=always
```

## Architecture

![Architecture Monolithe](monolithe.jpg){ width=30% }

L'architecture choisie pour ce projet est le monolithe, car c'est la solution qui nous semblait la plus adéquate. En effet, c'est un petit projet et c'est la solution la plus simple à mettre en place.

Voici les différentes parties de l'architecture de haut en bas:

1. L'ordinateur représente le client qui se connecte à notre application.
2. Le bloc du milieu représente le monolithe comportant la partie frontend et backend.
3. Le dernier bloc représente la base de données afin de persister les données.

Lors de nos tests, l'application et la base de données se trouvaient chacun dans un container docker séparé.

## Etat du projet

Le projet a été réalisé en Spring. Toutes les fonctionnalités du cahier des charges ont été remplies, mais quelques bugs ont été trouvés et corrigés lors du test de l'application.

### Fonctionnalités

Les fonctionnalités implémentées sont les suivantes:

- visualisation des boissons
- gestion des boissons (avec image)
- gestion des catégories
- pagination
- recherche d'une boisson simple ou avancée
- système de votes
- gestion des favoris
- inscription & authentification

### Bugs connus

Cette partie du document présente les bugs connus encore présents dans l'application. Ceux-ci n'ont pas pu être corrigés par manque de temps.

Ces problèmes ne sont cependant pas critiques pour l'application et ne provoquent pas de failles.

#### Images

Actuellement, lorsqu'on upload une image, elle n'est pas renommée. Ce qui fait qu'on ne peut pas avoir 2 images avec le même nom. La seconde image uploadée avec un nom existant remplace l'image précédente.

#### Modification d'une boisson

Lorsqu'on modifie une boisson, on doit réuploader l'image sinon elle sera écrasée.

#### Suppression d'une boisson

La suppression d'une boisson ne supprime pas l'image liée.

### Tests

Cette section donne la liste des outils utilisés pour tester notre application, mais aussi les problèmes rencontrés et qui ne sont pas réglés pour des raisons de temps.

#### Outils utilisés

Nous avons effectué des tests unitaires, des tests de cas d'utilisation ainsi que des tests de performance. Les outils que nous avons utilisés sont:

- JUnit
- Katalon
- Octoperf

Tous les résultats des tests sont disponibles dans le document "concept-and-testplan".

#### Problèmes rencontrés

Nous avons eu un problème lors du test du contrôleur des favoris. On effectue une requête "POST" sur une url de type "../favorite/add/{idBoisson}". Dans le contrôleur "FavoriteController" l'id de la boisson est directement utilisée et convertie en un objet "Boisson" (implicitement). Hors lors de l'exécution des tests unitaires correspondants, une erreur est lancée qui indique qu'il n'est pas possible de convertir la "String" en un objet "Boisson". Le problème est encore présent et n'a malheureusement pas pu être corrigé.

### Améliorations

La première amélioration ergonomique serait de connecté l'utilisateur après son inscription. Pour le moment, quand un utilisateur crée un compte il reste sur le formulaire d'inscription, ce qui n'est pas intuitif.

Une seconde amélioration consisterait à redimensionner l'image introduite lors de l'ajout d'une boisson. Cela permettrait d'avoir des images de taille similaire pour chaque boisson, mais surtout de diminuer leur poids et ainsi le temps de chargement de la page.

# Conclusion

Pour conclure, le projet a bien rempli les fonctionnalités du cahier des charges en respectant la contrainte d'utiliser Spring malgré quelques petits bugs résiduels.
