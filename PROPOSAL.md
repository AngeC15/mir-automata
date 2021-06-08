# PLA - Polytech Grenoble 

## Propositions pour le jeu 

### Besante Joan Capet Théo, Conjard Samuel, Fodor Gergely, Pelisse-Verdoux Cyprien, Royet Julian, Viallet Pauline.

------------------------------------------------------

### Partie Technique

- 1 joueur.

- Plusieurs mondes avec un système de saisons:

La carte reste la même mais il y a un nouveau thème et des changement visuel et de gameplay

    - Monde normal : rien
    - Mone de glace : ça glisse, stalagmite, il faut bouger
    - Monde de feu : case magma, météore 
    - Monde des ténèbre : visibilité réduite

- En ce qui concerne les déplacements, nous allons sûrement opter pour un déplacement pixel par pixel, avec un système de case pour tout ce qui est du décor.

- Les deux vues seraient avec une vue principal ainsi qu'une minimap qui permettrai d'avoir une vision global de la carte.

### Partie Game Design

- Notre jeu serait donc de type bullet hell dans une arène en tore, qui nous permettrait donc de pouvoir avoir un deplacement infini 

- Notre héros disposerai donc d'un inventaire comprenant:
    2 slots d’arme, une de corps à corps, une à distance.
    De base, cela serait un poignard seulement.
    Les armes de corps à corps n’on pas de durabilité et les armes à distance on des munitions limitées.
    Apparition d’armes ce qui permettrai au joueur de remplacer ses armes si il le souhaite.

- Les caches d’armes sont des entités qui apparaitraient aléatoirement :
    Les caches d’armes seraient à attaquer ou à défendre.
    Lorsqu’une cache apparaît, le joueur est notifié via la minimap.

Il est à noter qu'au début, les armes apparaissent toutes seules.

- Pour finir le jeu, nous avons pensés à un boss qu'il faut donc battre :
    Le boss final est au milieu de la carte depuis le début du jeu mais il est inaccessible. À la fin des niveaux, il devient disponible et il faut le tuer.

- Au niveau de l'expérience :
    On gagne de l’xp en tuant des ennemis.
    A chaque niveau on augmente nos stats. Tous les 5 niveaux, on gagne un talent (skill)

Nous avons pensé à une Variété d’arme et d’ennemi ainsi que des drones support.

- Au niveau de la musique :
    Le monde réagit à la musique.
    Les ennemis tirent sur la musique.
