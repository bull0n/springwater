---
title: SpringWater
subtitle: Rapport de test
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

# Rapport

Ce document va expliquer le résultat des tests et les résultats obtenus.

## Tests unitaires

Les testes unitaires sont exécutés lors du build Jenkins. Tous les tests unitaires doivent fonctionner pour l'application. Des tests unitaires ont été implémentés pour tous les controlleurs et les méthodes sont toutes testées également.

## Tests de qualités

Les tests des qualités sont passé avec un code coverage de 61% et les code smells restant sont des todo qui n'ont pas pu être implémenté. Il reste des failles de sécurité POJO, mais comme discuté en 

## Tests de performance

Les tests de performance ont bien été exécuté avec un temps de réponse moyen de XX s.
