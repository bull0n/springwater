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

# Réalisation

## Architecture

![Architecture Monolithe](monolithe.jpg)

L'architecture choisie pour ce projet est le monolithe car c'est la solution qui nous semblait la plus adéquate. En effet, c'est un petit projet et c'est la solution la plus simple à mettre en place.

Voici les différentes parties de l'architecture de haut en bas:

1. L'ordinateur rerpésente le client qui se connecte à notre application.
2. Le bloc du milieu représente le monolithe comportant la partie frontend et backend.
3. Le dernier bloc représente la base de données afin de persister les données.

# Conclusion
