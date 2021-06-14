# PLA - Polytech Grenoble

## Propositions pour le jeu

### Besante Joan, Capet Théo, Conjard Samuel, Fodor Gergely, Pelisse-Verdoux Cyprien, Royet Julian, Viallet Camille.

------------------------------------------------------

### Proposition

Notre jeu sera de type bullet hell dans une arène en tore, qui nous permettrait donc d'avoir un déplacement infini.
Le jeu comportera un seul joueur. Le but du jeu est de survivre les vagues ennemis jusqu'à la fin du jeu et de vaincre le
boss final. Le joueur et les ennemis devienent de plus en plus forts au fil des niveaux et acquierent de meilleures armes.

Une viewport principale centrée sur le joueur et sur l'action. Cette viewport n'affiche pas l'entièreté de l'arène et le joueur doit
se déplacer pour voir en dehors de son 'horizon'. Une viewport 'minimap' affichera en temps réel l'entièreté de l'arène de manière minimaliste
(le joueur un point vert, les obstacles foncés, ...).

La caméra est centrée sur le joueur, c'est le monde qui se déplace en dessous de son avatar. Quand le joueur arrive au bout de la carte,
il ne voit pas le bout de la carte dans la viewport principale. Il voit directement l'autre côté de la carte.

Les ennemis apparaissent sur la carte de manière aléatoire. Une fois qu'un ennemi apparait, il est attiré par le joueur et va l'attaquer.

L'environnement sera créé avec une répartition par case, mais l'intégralité du jeu sera gérée par coordonnée.

#### Contraintes :

- **2 (ou plus) mondes différents** : Changement de l'arène au fil du jeu. Changements visuel, audio et physique (cf. Saisons).
- **2 viewports** : Vue principale centrée sur le joueur mais qui n'englobe pas l'arène en entier. Vue minimap dans un coin de l'écran
qui affiche l'arène dans sa globalitée mais de manière minimaliste.
- **Une (ou plusieurs) dimenssion infinie** : L'arène est un tore (à la Pacman).

#### Saisons :

Au fil du jeu, l'arène va évoluer. Nous appelons cela des saisons et lors d'un changement de saison, les sprites, les musiques
ainsi que certains éléments de gameplay vont changer. **Il est important de noter que nous ne changeons pas de monde.**
C'est bien le monde qui évolue. Nous avons 4 idées de saison :

- **Normal** : rien de spécial.
- **Glace** : cases glissantes, stalagmites pouvant être utilisées comme projectile, rester immobile trop longtemps inflige des dégâts.

Le changement de saison sera mis en oeuvre avec une phase de transition. Par exemple, entre la saison normale et celle
de glace, il se mettra à neiger, montrant ainsi l'arrivée de l'hiver.

Le `Model` a connaissance de la saison actuelle et tient compte du changement de saison au fil du temps qui s'écoule.
Lors d'un changement de saison, le `Model` effectue les changements physiques nécessaires. Le `Contrôleur` détecte le
changement de saison, change la musique et notifie la `Vue` afin d'effectuer les changements graphiques (affecter de
nouveau sprite sheet aux avatars).

#### Inventaire :

Le joueur aura 2 emplacements d'armes, une de corps à corps (CàC) et une à distance. Au début du jeu, le personnage n'a
qu'une arme de CàC faible (ex. poignard) et aucune arme à distance. Au cours du jeu, le joueur pourra ramasser d'autres
armes plus fortes et qui se comportent de manière différente et remplacer ses armes. Néanmoins, le joueur ne peut avoir qu'une
seule arme de Càc et une arme à distance à la fois. Les armes de CàC n'ont pas de durabilité donc une fois ramasser, elles
peuvent être utilisées jusqu'à se qu'elles soient remplacées. Les armes à distances sont limitées en munitions et donc
doivent être utilisées de manière stratégique.

Du point de vue du `Model`, toute entité à entre 0 et 2 armes. Les éléments "non-vivant" tels que les arbres ou les flaques
de lave ainsi que les ennemis qui foncent sur le joueur pour lui faire des dégâts n'ont aucune arme. Les ennemis classiques
ont une seule arme et les joueur en à une ou deux. Les armes tireront de manière différente (tout droit, bombe, zigzag,
plus ou moins rapide) et donc, selon l'arme, l'automate des balles tirées change. Changer d'arme veut dire changer l'automate
des balles associées. **Attention** ! Cela ne changera pas l'automate de balles déjà tirées mais qui volent encore vers leur cible.

#### Hitbox

Par difficulté d'implémentation :

- Les hitbox sont des ronds. Il suffit de calculer des distances pour les collisions.
- Par compositions de polygones
- Algorithme de GJK

Étant donné la quantité d'ennemis et de balles qui vont exister en simultané, la détection de collisions pourrait très vite
devenir problématique. La carte sera découpée en "région" de la taille maximale d'une hitbox et la collision sera testée seulement
entre une entité donnée et les entités dans sa région et les régions voisines. De plus, une la détection de collisions faite
entre une entité A et B, il n'y a aucun intérêt à faire la détection de collision entre B et A.

#### Entrées utilisateur

Le jeu se jouera au clavier et à la souris. Le joueur visera avec le déplacement de la souris et tirera avec un clic de la souris.
Ainsi, on pointe ce que l'on veut viser.
Lors d'un tir, un vecteur de déplacement sera calculé pour la trajectoire des balles.

Le joueur se déplacera avec les touches du clavier donc il faudra gérer les `KeyEvent`. Nous serons confrontés à deux
problèmes en particulier :

- On veut un déplacement fluide quand le joueur maintient une touche enfoncée
- On veut prendre en compte lorsque plusieurs touches sont enfoncées, notamment pour les déplacements diagonaux

Notre solution est d'avoir un objet `Clavier` dans lequel on stocke l'état des touches clés. Le `Contrôleur` modifie
cette structure au fil des `KeyEvent` et lorsque le `Model` doit être mis à jour, il lit dans le `Clavier` directement.

#### Ticks

Le jeu tourne à 30 fps donc un `paint` environ toutes les 40 ms. Il y a peu (ou pas) d'intérêt à mettre à jour le `Model`
à chaque tic de 1ms et, si le `Model` devient trop grand, mettre à jour tout le `Model` en même temps (aux mêmes tics)
prendra trop de temps. Notre idée est mettre à jour disont un quart du `Model` toutes les 10 ms afin de palier aux deux
problèmes vuent précedement.

#### Pathfinding

On évite les algorithmes de recherche de chemin complexes. On veut juste se rapprocher du joueur naïvement quitte à adapter
la génération de terrain (éviter les culs-de-sac).

Ainsi, la détection du joueur se fera par la condition `Closest()` du langage GAL, implémenté de manière à avoir un
cône de détection pour chaque direction (pour 8 directions, des cônes de 45°).

### Plan de développement

------------------------------------------------------

- Jeu de base : personnage controllé par le joueur + automates simples
- Armes, Inventaire et Hitbox
- Plus d'ennemis
- Saisons

**Extensions**

- Cache d'armes
- Musique
- Plus de saisons
- Boss
- XP
- Drones

### Extensions

------------------------------------------------------

Les fonctionnalités suivantes sont celles qui ne sont pas essentielles pour le projet, mais que nous trouvons tout de
même très intéressantes.  

#### Caches d’armes :

Afin de forcer le joueur à se déplacer dans la carte, des caches d'armes vont apparaitre de manière aléatoire dans l'arène.
Lorsqu'une cache d'arme apparait, le joueur est notifié de son emplacement via une minimap. Une fois sur place, pour
accéder au contenu, le joueur doit remplir un objectif tel que briser la cache d'arme en l'attaquant ou défendre la cache
de vague d'ennemis. Dans une première implémentation, on peut imaginer que les armes apparaissent directement sans cache
d'arme.

Les caches d'armes auront leurs automates qui finiront toujours par créer une arme avec `Egg` et se détruiront en allant dans
l'état `()`.

#### Musique :

Le monde réagira à la musique. Les ennemis tireront en rythme avec la musique et les vagues ennemis apparaitront à des
moments clés de la musique. Nous aurons aussi une musique différente pour chaque monde.

Dans le `Contrôleur` il y aura une classe `Music` s'occupant de cet aspect. L'objet `Music` recevra les tics
et notifiera le `Contrôleur` lorsqu'une certaine action doit être effectuée. `Music` chargera les metadata des
musiques telles que le bpm et les time stamp des moments clés de la musique.

#### Saisons ++ :

Deux saisons remplirais la contraint d'avoir deux mondes mais nous aimerions en faire plus. Nos deux idées de
saisons supplémentaire sont :

- **Feu** : cases de lave infligeant des dégâts.
- **Ténèbres** : visibilité réduite.

#### Boss :

Le boss final sera au milieu de la carte depuis le début du jeu, mais il sera inaccessible.
À la fin de tous les niveaux, il devient disponible et il faudra le tuer, cela mettra fin au jeu.

Le boss diffère des autre entité par quelques points :

- La hitbox est beaucoup plus grande, pouvant être sur plusieurs régions (cf. **Hitbox**)
- Le boss peut avoir plusieurs armes spécifiques au boss
- La mort du boss met fin au jeu

#### XP :

Le joueur gagne de l’XP en tuant des ennemis. À chaque niveau il augmente ses stats (vie, puissance). Tous les 5 niveaux,
il débloque une compétence spéciale (dash, renvoyer des projectiles).

Un système de niveau avec les stats qui augmente est plutôt simple à implémenter. Il s'agit principalement de calcule de pourcentage.
Les compétences spéciales sont bien plus difficiles à implémenter (et très intéressantes d'un point de vue gameplay).
Chaque compétence apporterait un changement à la physique du jeu et probablement une nouvelle entrée utilisateur.
En plus du code supplémentaire, il faudrait faire attention à ne pas créer des situations impossibles (dasher dans un mur).

#### Drones :

Le joueur aura à sa disposition des drones qui vont l'aider. Cela peut aller des drones basiques qui tirent sur les ennemis
jusqu'à des drones très spécifiques. Par exemple un drone qui éclaire dans un certain rayon pour la saison des ténèbres.

Les drones fonctionneraient dans le `Model` tout comme les ennemis. Ce serait des entités dans le camp du joueur.
Sans les drones, les ennemis n'ont pas le choix de leur cible, il n'y a que le joueur à attaquer. L'introduction des drones
impliquerait d'augmenter les implémentations de `Closest()` afin de pouvoir choisir un ennemi en particulier à attaquer.
