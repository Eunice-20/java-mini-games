# java_minigames

 L'objetif de ce projet était de mettre en pratique nos compétences  acquise en programmation java en développant plusieurs mini jeux avec Swing en Java. La  tâche a été se développer une interface graphique avec Swing, en s' assurant qu'elle implémente le jeu choisi avec succès.

 ## Explication & Fonctionnement Programme

  ### Introfuction

Tous ces jeux  utilisent une base de données MySQL pour stocker les données, y compris les scores, les réponses aux questions True/False, et la vitesse de l'utilisateur. Ces jeux sont principalement développés en Java, un langage très adapté pour ce type de projet de création de mini-jeux.

<img src="./Imgred/image.png" alt="texte alternatif" style="width:70px;height:px;"> 

<img src="./Imgred/image-1.png"  alt="texte alternatif" style="width:70px;height:px;">


 ### Jeux Realiser 

 <!-- definit le jeux

  le but jeux 

comment  jouer  -->


#### Jeux du + ou - 




#### True Or False



#### Jeu du Pendu



#### Memory




#### Sudoku



#### 2048



#### Snake



#### Flappy Bird




 ## Structure programme
 
 


 ## Acce projet 

 <!-- Instalation du projet -->
Les étapes pour accéder projet  :

Clonez ce dépôt github sur votre machine:

```
https://github.com/Eunice-20/java-mini-games.git
```

Démarrage du menu des jeux 

```
```


 ## Remeciment

 Ce projet a été d'une grande aide pour """""""" la création de plusieur mini jeux . Ravi d'y avoir participé, Merci d'avoir consacré votre temps et votre attention à la conception du projet.




<!-- 
-- git config --global --add safe.directory C:/Users/Usager/OneDrive/Bureau/java-mini-games



-- SHOW DATABASES;

-- SELECT * FROM ma_table;
-- SELECT * FROM ma_table; :
-- Cette commande est une requête de sélection (SELECT) qui récupère toutes les lignes et toutes les colonnes de la table spécifiée (ma_table). Lorsque vous exécutez cette commande, MySQL retournera toutes les données stockées dans la table ma_table.

-- data base 
-- mysql> SHOW DATABASES;mysql> CREATE DATABASE database_DB;
-- Query OK, 1 row affected (0.01 sec)

-- mysql> SHOW DATABASES;
-- +--------------------+
-- | Database           |
-- +--------------------+
-- | database_db        |
-- | information_schema |
-- | mysql              |
-- | performance_schema |
-- | sys                |
-- +--------------------+
-- 5 rows in set (0.00 sec)

-- mysql>





-- PS C:\Users\Usager\java-minigames> Copy-Item "C:\Program Files\Java\jdk-17\lib\jrt-fs.jar" ".\lib"

-- ette commande utilise Copy-Item pour copier le fichier jrt-fs.jar dans le dossier lib que vous avez créé à la racine de votre projet


-- ```
-- Enter password: ******
-- Welcome to the MySQL monitor.  Commands end with ; or \g.
-- Your MySQL connection id is 13
-- Server version: 8.0.36 MySQL Community Server - GPL

-- Copyright (c) 2000, 2024, Oracle and/or its affiliates.

-- Oracle is a registered trademark of Oracle Corporation and/or its
-- affiliates. Other names may be trademarks of their respective
-- owners.

-- Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

-- mysql> SHOW DATABASES;
-- +--------------------+
-- | Database           |
-- +--------------------+
-- | database_db        |
-- | information_schema |
-- | mysql              |
-- | performance_schema |
-- | sys                |
-- +--------------------+
-- 5 rows in set (0.03 sec)

-- mysql> use database_db;
-- Database changed
-- mysql> show tables;
-- Empty set (0.02 sec)

-- mysql>





-- mysql> use database_db;
-- Database changed
-- mysql> show tables;
-- +-----------------------+
-- | Tables_in_database_db |
-- +-----------------------+
-- | jeux1                 |
-- +-----------------------+
-- 1 row in set (0.01 sec)

-- mysql> CREATE TABLE IF NOT EXISTS HangmanBase (
--     ->     id INT AUTO_INCREMENT PRIMARY KEY,
--     ->     score INT
--     -> );
-- Query OK, 0 rows affected (0.11 sec)

-- mysql> INSERT INTO HangmanBase (score) VALUES (100);
-- Query OK, 1 row affected (0.01 sec)

-- mysql> INSERT INTO HangmanBase (score) VALUES (score);
-- Query OK, 1 row affected (0.01 sec)

-- mysql> show tables;
-- +-----------------------+
-- | Tables_in_database_db |
-- +-----------------------+
-- | hangmanbase           |
-- | jeux1                 |
-- +-----------------------+
-- 2 rows in set (0.00 sec)

-- mysql>
-- ```

-- ----------


-- mysql>  CREATE TABLE IF NOT EXISTS Memorybase (
--     ->      id INT AUTO_INCREMENT PRIMARY KEY,
--     ->      score INT
--     ->      );
-- ERROR 1046 (3D000): No database selected
-- mysql> SHOW DATABASES;
-- +--------------------+
-- | Database           |
-- +--------------------+
-- | database_db        |
-- | information_schema |
-- | mysql              |
-- | performance_schema |
-- | sys                |
-- +--------------------+
-- 5 rows in set (0.01 sec)

-- mysql> show tables;
-- ERROR 1046 (3D000): No database selected
-- mysql> use database_db;
-- Database changed
-- mysql> show tables;
-- +-----------------------+
-- | Tables_in_database_db |
-- +-----------------------+
-- | hangmanbase           |
-- | jeux1                 |
-- | memorybase            |
-- +-----------------------+
-- 3 rows in set (0.01 sec)

-- mysql>





--      CREATE TABLE IF NOT EXISTS Memorybase (
--      id INT AUTO_INCREMENT PRIMARY KEY,
--      score INT
--      );

--      mysql> ALTER TABLE Memorybase
--     -> ADD COLUMN elapsedTime DOUBLE DEFAULT 0;
-- Query OK, 0 rows affected (0.13 sec)
-- Records: 0  Duplicates: 0  Warnings: 0

-- ---- pour modifier 

-- mysql> ALTER TABLE Memorybase
--     -> MODIFY COLUMN elapsedTime DOUBLE;
-- Query OK, 0 rows affected (0.04 sec)
-- Records: 0  Duplicates: 0  Warnings: 0



--  ALTER TABLE Memorybase
-- DROP COLUMN ;


-- --------

-- mysql> select * from memorybase;
-- +----+-------+-------------+
-- | id | score | elapsedTime |
-- +----+-------+-------------+
-- |  1 |    52 |           0 |
-- |  2 |  NULL |      69.845 |
-- |  3 |    55 |           0 |
-- |  4 |  NULL |      67.149 |
-- |  5 |    59 |           0 |
-- |  6 |  NULL |      60.772 |
-- |  7 |    60 |           0 |
-- |  8 |  NULL |      77.931 |
-- |  9 |    50 |        NULL |
-- | 10 |  NULL |       88.91 |
-- | 11 |    39 |        NULL |
-- +----+-------+-------------+
-- 11 rows in set (0.00 sec)

-- mysql> flush memorydatabase
--     -> ;
-- ERROR 1064 (42000): You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'memorydatabase' at line 1
-- mysql> DELETE FROM memorybase;
-- Query OK, 11 rows affected (0.01 sec)

-- mysql> select * from memorybase;
-- Empty set (0.00 sec)

-- mysql> select * from memorybase;
-- Empty set (0.00 sec)

-- mysql> select * from memorybase;
-- +----+-------+-------------+
-- | id | score | elapsedTime |
-- +----+-------+-------------+
-- | 12 |  NULL |     105.159 |
-- | 13 |    34 |        NULL |
-- +----+-------+-------------+
-- 2 rows in set (0.00 sec)

-- mysql>

-- ALTER TABLE Memorybase
--     ADD COLUMN cp Int;

--     mysql> ALTER TABLE Memorybase
--     ->     ADD COLUMN cp Int;
-- Query OK, 0 rows affected (0.05 sec)
-- Records: 0  Duplicates: 0  Warnings: 0

-- mysql> select * from memorybase;
-- +----+-------+-------------+------+
-- | id | score | elapsedTime | cp   |
-- +----+-------+-------------+------+
-- | 12 |  NULL |     105.159 | NULL |
-- | 13 |    34 |        NULL | NULL |
-- | 14 |    43 |        NULL | NULL |
-- | 15 |    69 |        NULL | NULL |
-- | 16 |    55 |        NULL | NULL |
-- | 17 |    57 |      58.605 | NULL |
-- +----+-------+-------------+------+
-- 6 rows in set (0.00 sec)

-- mysql>



-- mysql> CREATE TABLE IF NOT EXISTS Snakebase (
--     ->     id INT AUTO_INCREMENT PRIMARY KEY,
--     ->     score INT
--     ->      );
-- Query OK, 0 rows affected (0.06 sec)

-- mysql> show tables;
-- +-----------------------+
-- | Tables_in_database_db |
-- +-----------------------+
-- | hangmanbase           |
-- | jeux1                 |
-- | memorybase            |
-- | snakebase             |
-- +-----------------------+
-- 4 rows in set (0.01 sec)



-- TrueFalsebase

-- CREATE TABLE IF NOT EXISTS Snakebase (
--         id INT AUTO_INCREMENT PRIMARY KEY,
--        score INT
--          ); -->
