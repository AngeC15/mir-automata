# PLA - Polytech Grenoble

## Propositions pour le jeu

### Besante Joan, Capet Théo, Conjard Samuel, Fodor Gergely, Pelisse-Verdoux Cyprien, Royet Julian, Viallet Camille.

------------------------------------------------------

### Proposition

Notre jeu sera de type bullet hell dans une arène en tore, qui nous permettrait donc de pouvoir avoir un déplacement infini.
Le jeu comportera un seul joueur. Le but du jeu est de survivre les vagues ennemis jusqu'à la fin du jeu et de vaincre le
boss final. Le joueur devient de plus en plus fort au fil des niveaux et acquiert de meilleurs armes. Les deux viewports
seront implémentés grâce à une minimap.

La camera est centré sur le joueur, c'est le monde qui ce déplace en dessous de son avatar. Quand le joueur arrive au bout de la carte,
il ne voit pas le bout de la carte dans la viewport principal. Il voit directement l'autre coté de la carte.

Les ennemis apparaisent sur la cartes de manière aléatoire. Une fois qu'un ennemi apparait, il est attiré par le joueur et va l'attaquer.

L'environment sera créé avec une répartition par cases mais l'integralité du jeu sera géré par coordonnée.

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

Le `Model` à connaisance de la saison actuelle et tient compte du changement de saison au fil du temps qui s'écoule.
Lors d'un changement de saison, le `Model` effectue les changements physiques nécessaires. Le `Controleur` détecte le
changement de saison, change la musique et notifie la `Vue` afin d'effectuer les changements graphiques (affecter de
nouveau sprite sheet aux avatars).

#### Inventaire :

Le joueur aura 2 emplacements d'armes, une de corps à corps (CaC) et une à distance. Au début du jeu, le personnage n'a
qu'une arme de CaC faible (ex. poignard) et aucune arme à distance. Au cours du jeu, le joueur pourra ramasser d'autre
armes plus forte et qui se comporte de manière différentes et remplacer ces armes. Néanmoins, le joueur ne peut avoir qu'une
seul arme de Cac et une arme à distance à la fois. Les armes de CaC n'ont pas de durabilité donc une fois ramasser, elles
peuvent être utilisées jusqu'à se qu'elles soient remplacées. Les armes à distances sont limité en munitions et donc
doivent être utilisé de manière stratégique.

Du point de vue du `Model`, toute entitée à entre 0 et 2 armes. Les éléments "non-vivant" tel que les arbres ou le flaque
de lave ainsi que les ennemis qui foncent sur le joueur pour lui faire des dégats n'ont aucune armes. Les ennemis classiques
on une seul arme et les joueur en à une ou deux. Les armes tireront de manière différente (tout droit, bombe, zigzag,
plus ou moins rapide) et donc, selon l'arme, l'automate des balles tirées change. Changer d'arme veut dire changer l'automate
des balles associées.

#### Hitbox

Par difficulté d'implémentation :

- Les hitbox sont des ronds. Il suffit de calculer des distances pour les collisions.
- Par compositions de polygones
- Algorithme de GJK

Etant donné la quantité d'ennemis et de balles qui vont exister en simultané, la détection de collisions pourrait très vite
devenir problématique. La carte sera découper en "région" de la taille maximale d'une hitbox et la colision sera testé seulement
entre une entité donnée et les entités dans sa région et les régions voisines. De plus, une la détection de collisions faite
entre une entité A et B, il n'y à aucun intérêt à faire la détection de colision entre B et A.

#### Entrées utilisateur

Le jeu se jouera au clavier et à la souris. Le joueur visera avec le déplacement de la souris et tirera avec un clique de la souris.
Lors d'un tir, un vecteur de déplacement sera calculer pour la trajectoire des balles.

Le joueur se déplacera avec les touche du clavier donc il faudra gérer les `KeyEvent`. Nous serons confronté à deux
problèmes en particulier :

- On veut un déplacement fluide quand le joueur maintient une touche enfoncée
- On veut prendre en compte lorque plusieurs touches sont enfoncées, notamment pour les déplacements diagonales

Notre solution est d'avoir un objet `Clavier` dans lequel on stock l'état des touches clés. Le `Controleur` modifie
cette structure au fil des `KeyEvent` et lorsque le `Model` doit être mis à jour, il lit dans le `Clavier` directement.

#### Caches d’armes :

Afin de forcer le joueur à se déplacer dans la carte, des caches d'armes vont apparaitre de manière aléatoire dans l'arène.
Lorsqu'une cache d'arme apparait, le joueur est notifié de son emplacement via une minimap. Une fois sur place, pour
accéder au contenu, le joueur doit remplir un objectif tel que briser la cache d'arme en l'attaquant ou défendre la cache
de vague d'ennemis. Dans une première implémentation, on peut imaginer que les armes apparaissent directement sans cache
d'arme.

Les caches d'armes auront leurs automate qui finira toujours par créer une arme avec `Egg` et se détruira en allant dans
l'état `()`.

#### Musique :

Le monde réagira à la musique. Les ennemis tireront en rythme avec la musique et les vagues ennemis apparaitront à des
moments clés de la musique. Nous aurons aussi une musique différente pour chaque monde.

Dans le `Controleur` il y aura une classe `Music` s'occupant de cet aspect. L'objet `Music` recevera les ticks
et notifira le `Controleur` lorsqu'une certaine actions doit être effectuer. `Music` chargera les metadata des
musique tel que le bpm et les time stamp des moments clés de la musique.

#### Ticks

Le jeu tourne à 30 fps donc un `paint` environ tout les 40 ms. Il y a peu (ou pas) d'intérêt à mettre à jour le `Model`
à chaque ticks de 1ms et, si le `Model` devient trop grand, mettre à jour tout le `Model` en même temps (au même ticks)
prendra trop de temps.

### Extenssion

#### Boss :

Le boss final sera au milieu de la carte depuis le début du jeu mais il sera inaccessible.
À la fin de tous les niveaux, il devient disponible et il faudra le tuer. Cela mettra fin au jeu

Le boss diffère des autre entité par quelques points :

- La hitbox est beaucoup plus grande, pouvant être sur plusieurs régions (cf. **Hitbox**)
- Le boss peut avoir plusieurs armes spécifiques au boss
- La mort du boss met fin au jeu

#### XP :

Le joueur gagne de l’XP en tuant des ennemis. À chaque niveau il augmente ses stats (vie, puissance). Tous les 5 niveaux,
il débloque une compétence spéciale (dash, renvoyer des projectiles).

#### Drones :

Le joueur aura à sa disposition des drones qui vont l'aider. Cela peut aller des drones basiques qui tirent sur les ennemis
jusqu'à des drones très spécifiques. Par exemple un drone qui éclaire dans un certain rayon pour la saison des ténèbres.

#### Pathfinding

On évite les algorithmes de recherche de chemin complexes. On veut juste se rapprocher du joueur naïvement quite à adapter
la génération de terrain (éviter les culs-de-sac).
