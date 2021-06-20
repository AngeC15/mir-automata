# JOURNAL du Jeudi 17 juin

## Répartition des tâches

#### *Théo + Julian* : HitBox ( branche `hitbox` )
- [x] `DONE` : *HitBox fonctionnel*
- [x] `DONE` : *Merge avec la branche master*

#### *Théo + Julian* : Loader des SpriteSheet ( branche `dev.loader` )
- [x] `DONE` : *Loader fonctionnel*
- [x] `DONE` : *Merge avec la branche master*

#### *Julian* : Moteur physique

#### *Théo* : Génération d'éléments sur la Map

#### *Gergely + Samuel* : Animation des avatars ( branche `dev.avatar`)
- Correctifs divers
- [x] `DONE` : *Avatar fonctionnel*
- [x] `DONE` : *Merge et documenté avec la branche master*

#### *Joan + Cyprien* : Gestion des armes ( branche `shot.try`)
- Avancement sur la classe `Bullet`

#### *Camille* : MiniMap ( branche `dev.MiniMap` )
- Génération d'une miniMap avec l'avatar qui bouge.
- [x] `DONE` : *Affichage de la miniMap avec l'avatar qui bouge*
- [ ] `TODO` : *Ajuster la map affichée*


--- 
# JOURNAL du Jeudi 17 juin

## Répartition des tâches

#### *Cyprien + Julian* : AST ( branche `proto.ast` )
- [x] `DONE` : *AST fonctionnel*
- [x] `DONE` : *Merge avec la branche master*

#### *Joan + Camille + Cyprien* : Gestion des armes ( branche `shot.try`)
- Création des classes `Bullet`, `Weapon`,`Dagger` et `Gun`
- Modification de la classe `DirectionExtension` pour obtenir la direction relative
- Génération des balles

- [ ]  `TODO` :  *Générer des balles dans la bonne direction lors du clic souris*

#### *Théo + Julian* : Loader des SpriteSheet ( branche `dev.loader` )
- Modification de la classe `AutomataLoader` et `Automaton`

- [x] `DONE` : *`AutomataLoader` terminé et testé*
- [ ] `TODO` : *`TemplatesLoader`*

#### *Julian* : GameView
- Modification de la classe `GameView`
- Camera suivant le joueur
- Affichage d'une grille en fond

- [x] `DONE` : *Fonctionnel et merge avec le master*


#### *Gergely + Samuel* : Animation des avatars ( branche `dev.avatar`)
- Refonte des fichiers .ani :modification de `AnimNode` pour Parser le fichier.

- [x] `DONE` : *Fonctionnel, documenté et merge avec le master*

**Format des fichiers d'animation .ani** :

```
<Action> <Temps> <Interrompable> <SEQUENCE D'ANIM>
```

- `Action` est une action de l'automate de type MOVE, HIT,... définie dans `EnumAction`
- `Temps` est le temps en millisecondes que prendra la séquence d'animation
- `Interrompable` définie si la séquence d'animation doit être interompue lorque l'entité ne fait rien
- `SEQUENCE D'ANIM` est la suite des index des sprites représentant la séquence d'animation

```
HIT 500 NON_INTERRUPT 12 13 14 13 12
MOVE 1000 INTERRUPT 0 1 2 1 0
```

Ci-dessus, un exemple de `.ani`. Les séquences sont dans **L'ordre décroissant de priorité** ce qui veut dire que 
l'animation du HIT intérrompra l'animation du MOVE. 

#### *Théo + Julian* : HitBox ( branche `hitbox` )
-  Création des classes `HitBox`, `Newton`,`PhysicsObject`,`Primitive` et `CirclePrimitive`
- Ajout de méthodes dans `Vector2`



---

# JOURNAL du Mercredi 16 juin

## Répartition des tâches


#### *Théo* : Loader des SpriteSheet ( branche `dev.loader` )

#### *Cyprien + Julian* : AST ( branche `proto.ast` )
- Test de la classe `AstToObject`
- Résolution d'erreurs et modification de `ActionList`

#### *Gergely + Camille + Samuel* : Animation des avatars ( branche `dev.avatar`)
- Test de l'animation d'un avatar avec les mouvements
- Modification des classes `Avatar`, `Enum_Action` et `GameView`
- Test de l'automate d'animation : langage pas optimal car ne permet pas de boucler. 

- [ ]  `TODO` :  *Changer le contenu et l'interprétation du fichier .ani : nouveau langage d'automate*
- [ ]  `TODO` :  *Hiérarchie des animations*

#### *Joan* : Réalisation des Sprites

---

# JOURNAL du Mardi 15 juin

## Répartition des tâches

#### Pour l'ensemble du groupe : 
- Réunion avec Emmanuel DUFOUR à propos de la gestion d'équipe
- Revue de code, questions / réponses, mise en commun de l'avancement, explicitation de l'architecture
- Avancement selon la même répartition de la veille

---

# JOURNAL du Lundi 14 juin

## Répartition des tâches

#### *Théo + Julian* : architecture MVC et entrées clavier


#### *Cyprien + Samuel* : représentation exécutable des automates à partir de l'AST ( branche `prototype.controller` et  `proto.ast` )
- Refactorisation des differentes classes, modification des packages pour plus de lisibilité et de compréhension du code. Avancement sur la classe `AstToObject`
- Test du Parseur

#### *Gergely + Camille* : Animation des avatars ( branche `dev.avatar`)
- Création des classes `Avatar`, `SpriteSheet` et `AutoAnim`

#### *Joan* : Réalisation des Sprites

---
# JOURNAL du Vendredi 11 juin

## Répartition des tâches

#### Pour l'ensemble du groupe : 
- Répartition des taches

#### *Théo + Julian* : architecture MVC et entrées clavier
- Travail sur les entrées du clavier
- Implémentation de l'architecture MVC

#### *Cyprien + Samuel* : représentation exécutable des automates à partir de l'AST
- Compréhension globale

#### *Gergely* : Rédaction du contrat

#### *Joan* : Recherche de Sprite

---
# JOURNAL du Jeudi 10 juin

## Répartition des tâches

#### Pour l'ensemble du groupe : 
- Mise en place de l'environnement de developpement.
- Réalisation de prototype en sous-groupes.

- [ ]  `TODO` :  *Implémentation des classes du Model*

#### *Cyprien + Julian* : implémentation du moteur d'automates
- Création des automates, ajout de transitions, lancement des actions avec la fonction step.
- Les transitions contiennent des conditions avec leur fonction `eval()` et une action effectuée avec la fonction `apply()`. 
- Implémentation des classes `Action` et `Transition`


- [x] `DONE` : *Prototype fonctionnel*

#### *Théo + Samuel + Camille* : mouvement d'un joueur 
- Mise en place de la MVC.
- Récupération des évenements souris et clavier.
- Mouvement du joueur à l'écran avec les touches du clavier.

- [x] `DONE` : *Prototype fonctionnel*

- [ ] `TODO` :  *Gestion du mouvement lors d'un appui long et gestion de plusieurs entitées*

#### *Gergely* : Rédaction du contrat

`ON GOING` 

---

# JOURNAL du Mercredi 9 juin

## Répartition des tâches

#### Pour l'ensemble du groupe :
- Reflexion autour du systéme de coordonnées.
- Elaboration de la hiérarchie de classe.
- Explicitation du flot d'execution et des mécaniques de la MVC.
- Discussions diverses afin de se mettre d'accord sur différents aspects du jeu

---

# JOURNAL du Mardi 8 juin

## Répartition des tâches

#### Pour l'ensemble du groupe :
- Réflexion et approfondissement des sujets abordés la veille.
- Prise en compte des remarques sur des éléments du jeu non pertinent
 - Réflexion sur la proposition de jeu et discussion avec le tuteur sur les idées évoquées.

#### Gergely :
- Rédaction du fichier `PROPOSAL.md`

---

# JOURNAL du Lundi 7 juin

## Répartition des tâches 

#### Pour l'ensemble du groupe : 
- Prise de connaissances des consignes et contraintes du projet.
- Réflexion sur la conception du jeu.

---



