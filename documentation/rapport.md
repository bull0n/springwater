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

Pour le cours de J2EE nous avons réalisé une application avec l'aide de Spring Boot. Le but de ce projet est de créer un site communautaire où les utilisateurs uploadent des images et vote pour les meilleures images. La partie test du projet à été faite en parralèlle avec le cours de Qualité Logiciel.

# Réalisation 



## Architecture du code

Nous avons essayé de grouper les classes par fonctionnement dans le projet. Par exemple, nous avons groupé les controlleurs dans un package.

- config
- controllers
- exceptions
- models
  - entities
  - repository
- security
- service
  - impl

## Architecture

![Architecture Monolithe](monolithe.jpg){ width=70% }

## Etat du projet

# Conclusion