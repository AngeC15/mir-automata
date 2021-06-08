# PLA - Polytech Grenoble 

## Propositions pour le jeu 

### Besante Joan, Capet Théo, Conjard Samuel, Fodor Gergely, Pelisse-Verdoux Cyprien, Royet Julian, Viallet Camille.

------------------------------------------------------

### Proposition

Notre jeu sera de type bullet hell dans une arène en tore, qui nous permettrait donc de pouvoir avoir un déplacement infini.
Le jeu comportera un seul joueur. Le but du jeu est de survivre les vagues ennemis jusqu'à la fin du jeu et de vaincre le 
boss final. Le joueur devient de plus en plus fort au fil des niveaux et acquiert de meilleurs armes. Les deux viewports
seront implémentés grâce à une minimap. 

#### Inventaire :

Le joueur aura 2 emplacements d'armes, une de corps à corps (CaC) et une à distance. Au début du jeu, le personnage n'a 
qu'une arme de CaC faible (ex. poignard) et aucune arme à distance. Au cours du jeu, le joueur pourra ramasser d'autre 
armes plus forte et qui se comporte de manière différentes et remplacer ces armes. Néanmoins, le joueur ne peut avoir qu'une 
seul arme de Cac et une arme à distance à la fois. Les armes de CaC n'ont pas de durabilité donc une fois ramasser, elles 
peuvent être utilisées jusqu'à se qu'elles soient remplacées. Les armes à distances sont limité en munitions et donc 
doivent être utilisé de manière stratégique.

#### Caches d’armes :

Afin de forcer le joueur à se déplacer dans la carte, des caches d'armes vont apparaitre de manière aléatoire dans l'arène.
Lorsqu'une cache d'arme apparait, le joueur est notifié de son emplacement via une minimap. Une fois sur place, pour 
accéder au contenu, le joueur doit remplir un objectif tel que briser la cache d'arme en l'attaquant ou défendre la cache 
de vague d'ennemis. Dans une première implémentation, on peut imaginer que les armes apparaissent directement sans cache
d'arme.

#### Saisons :

Au fil du jeu, l'arène va évoluer. Nous appelons cela des saisons et lors d'un changement de saison, les sprites, les musiques
ainsi que certains éléments de gameplay vont changer. **Il est important de noter que nous ne changeons pas de monde.**
C'est bien le monde qui évolue. Nous avons 4 idées de saison :

- **Normal** : rien de spéciale.
- **Glace** : cases glissantes, stalagmites pouvant être utilisés comme projectile, rester immobile trop longtemps inflige des dégâts.
- **Feu** : cases de lave infligeant des dégâts, météores tombant du ciel.
- **Ténèbre** : visibilité réduite.

Le changement de saison sera mis en oeuvre avec une phase de transition. Par exemple, entre la saison normale et celle 
de glace, il se mettra à neiger, montrant ainsi l'arriver de l'hiver

#### Boss :

Le boss final sera au milieu de la carte depuis le début du jeu mais il sera inaccessible. 
À la fin de tous les niveaux, il devient disponible et il faudra le tuer. Cela mettra fin au jeu

#### XP :

Le joueur gagne de l’XP en tuant des ennemis. À chaque niveau il augmente ses stats (vie, puissance). Tous les 5 niveaux, 
il débloque une compétence spéciale (dash, renvoyer des projectiles).

#### Musique :

Le monde réagira à la musique. Les ennemis tireront en rythme avec la musique et les vagues ennemis apparaitront à des 
moments clés de la musique. Nous aurons aussi une musique différente pour chaque monde.

#### Drones :

Le joueur aura à sa disposition des drones qui vont l'aider. Cela peut aller des drones basiques qui tirent sur les ennemis
jusqu'à des drones très spécifiques. Par exemple un drone qui éclaire dans un certain rayon pour la saison des ténèbres.
