# java_minigames

 L'objetif de ce projet était de mettre en pratique nos compétences  acquise en programmation java en développant plusieurs mini jeux avec Swing en Java. La  tâche a été se développer une interface graphique avec Swing, en s' assurant qu'elle implémente le jeu choisi avec succès.

 ## Explication & Fonctionnement Programme

  ### Introfuction

Tous ces jeux  utilisent une base de données MySQL pour stocker les données, y compris les scores, les réponses aux questions True/False, et la vitesse de l'utilisateur. Ces jeux sont principalement développés en Java, un langage très adapté pour ce type de projet de création de mini-jeux.

<img src="./Game/asset/image.png" alt="texte alternatif" style="width:70px;height:px;"> 

<img src="./Game/asset/image-1.png"  alt="texte alternatif" style="width:70px;height:px;">


 ### Jeux Realiser 


#### Jeux du + ou - 

Le jeu du + ou - est un jeu de devinette où le joueur doit deviner un nombre secret choisi aléatoirement par l'ordinateur. 

- **But du jeu**

Le but du jeu est de deviner le nombre secret en un minimum de tentatives.

- **Comment jouer**

1. Proposez un nombre à l'ordinateur.
2. Cliquez sur le bouton pour vérifier si le nombre à deviner est correct.
3. L'ordinateur vous indiquera si le nombre deviné est plus grand, plus petit ou égal au nombre proposé.
4. Un message de victoire vous sera adressé lorsque le nombre aura été trouvé.


### True Or False

True Or False est un jeu de questions-réponses où le joueur doit déterminer si une affirmation donnée est vraie ou fausse.

- **But du jeu**
Le but du jeu est de répondre correctement au maximum d'affirmations pour obtenir le meilleur score possible.

- **Comment jouer**

1.  lit une affirmation et sélectionne "True" (vrai) ou "False" (faux) selon semble correct.
2.  Un message vous sera adressé lorsque l'affirmation est correct ou non.

### Jeu du Pendu

 Le Jeu du Pendu est un jeu où le joueur doit deviner un mot en proposant des lettres. 

**But du jeu**

Le but du jeu est de deviner le mot avant que le dessin d'un pendu complet ne soit dessiné.

**Comment jouer**

1. le joueur propose des lettres. 
2. Si la lettre proposée est dans le mot à deviner, elle est affichée à sa position.
3. Sinon, une partie du pendu est dessinée.
4. Le joueur a un nombre limité de tentatives pour deviner le mot.
5. Un message de victoire vous sera adressé lorsque le mot aura été trouvé.


### Memory
Le jeu Memory est un jeu de société où le joueur doit retrouver des paires de cartes identiques.

**But du jeu**

Le but du jeu est de retrouver toutes les paires de cartes en un minimum de coups.

**Comment jouer**

1. Retournez deux cartes à la fois. 
2. Si les cartes retournées sont identiques, elles restent découvertes et votre score augumente de 2. 
3. Sinon, les cartes sont à nouveau retournées face cachée.
4. Rappelez vous de l'emplacement des cartes pour former des paires.


### Sudoku

Le Sudoku est un jeu de logique où le joueur remplit une grille de 9x9 cases avec des chiffres de 1 à 9.

**But du jeu**

Le but du jeu est de remplir la grille de manière à ce que chaque ligne, chaque colonne et chaque région de 3x3 cases contienne tous les chiffres de 1 à 9 sans répétition.

**Comment jouer**

1. Remplicez la grille en suivant les règles du Sudoku. 
2. Chaque chiffre doit être unique dans chaque ligne, 
3. Chaque colonne et chaque région de 3x3 cases, les chiffre doit être unique
4. Un message de victoire vous sera adressé lorsque la grille  aura été Resolut.



### 2048

2048 est un jeu de puzzle où le joueur combine des tuiles numérotées pour atteindre la tuile 2048.

**But du jeu**

Le but du jeu est d'obtenir une tuile numérotée 2048 en combinant les tuiles adjacentes portant le même numéro.

**Comment jouer**

1. Déplacez les tuiles numérotées sur une grille 4x4 en utilisant les touches fléchées.
2. Si deux tuiles du même numéro entrent en collision, elles fusionnent pour former une tuile portant le double de leur valeur.
3. Le jeu prend fin lorsque qu'une tuile atteint le nombre 2048.


### Snake

Snake est un jeu où le joueur contrôle un serpent qui se déplace sur l'écran en mangeant des fruits pour grandir.

**But du jeu**

Le but du jeu est de faire grandir le serpent autant que possible sans le faire entrer en collision avec lui-même ou avec les bords de l'écran.

**Comment jouer**

1. Utilisez les touches directionnelles pour diriger le serpent. 
2. Chaque fois que le serpent mange un fruit, sa longueur augmente et le joueur gagne des points.
3. Le jeu se termine si le serpent entre en collision avec lui-même ou avec les bords de l'écran.

### Flappy Bird

Flappy Bird est un jeu où le joueur contrôle un oiseau qui vole à travers des obstacles en tapant sur l'écran pour battre des ailes.

**But du jeu**

Le but du jeu est de voler le plus loin possible en évitant les tuyaux sans toucher le sol ou les obstacles.

**Comment jouer**

1. Appuie sur la touche flèche du haut de votre ordinateur pour faire battre des ailes à l'oiseau.
2. Chaque battement d'aile fait monter légèrement l'oiseau.
3. Synchronisez vos battements d'ailes pour naviguer à travers les espaces entre les tuyaux et marquer des points.
4. Un message de fin de partie vous sera adressé lorsque vous aurez heurté un tuyau.

### Structure du Programme Java Mini-Jeux

**Dossier Game**

Ce dossier contient le code source et les ressources des jeux Java :

- **asset** : Contient les ressources graphiques.
  
- **Memory** : Contient les images des cartes spécifiques au jeu de Memory.

- **Jeux_Java** : Contient le code source des différents jeux Java.

- **file** : Contient les fichiers des mots aléatoires du Hangman.

- **Ressources** : Contient les ressources spécifiques aux jeux, telles que les images de menu et la musique.

- **MenuImg** : Contient les images des interfaces graphiques de chaque jeu dans les menus pour une meilleure distinction.

- **Music** : Contient les fichiers audio utilisés dans les jeux.

**Dossier lib**

Ce dossier contient la bibliothèque externe et le fichier JAR nécessaires au projet.

 ## Accès projet

Les étapes pour accéder projet  :

Clonez ce dépôt github sur votre machine:

```
https://github.com/Eunice-20/java-mini-games.git
```

Démarrage du menu des jeux 

```
PS C:\Users\Usager\OneDrive\Bureau\java-mini-games>  c:; cd 'c:\Users\Usager\OneDrive\Bureau\java-mini-games'; & 'C:\Program Files\Java\jdk-17\bin\java.exe' '@C:\Users\Usager\AppData\Local\Temp\cp_csim4huze8r7a9ma5onbt3x8u.argfile' 'Jeux_Java.User'
```


 ## Remeciment

 Ce projet a été d'une grande aide pour  ma mise en pratique de nos compwthence acquise en java en mettant en plase la création de plusieur mini jeux . Ravi d'y avoir participé, Merci d'avoir consacré votre temps et votre attention à la conception du projet.
