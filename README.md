# pglp_9.9
- Exercice réalisé pour le module PGLP, M1 INFORMATIQUE
- Julien Reynaud

# Description général
Le programme contient les fonctionnalités suivantes :
- Dessin de formes (Carré, Rectangle, Triangle, Cercle)
- Groupements de formes ou de groupes de formes
- Déplacement de formes 
- Déplacement de groupes de formes
- Effacement de formes ou groupes du dessin actuel
- Sauvegarde/Chargement de formes ou de groupes dans la BD
- Modification de formes dans la BD
- Suppression de formes dans la BD

Au niveau implémentation :

- Pattern composite pour les groupes de formes
- Pattern DAO pour la BD
- Pattern Command pour l'exécution des commandes
- Gestion des erreurs avec les exceptions personnalisées

Voir le manuel utilisateur et le manuel technique pour le fonctionnement.

# MANUEL UTILISATEUR
## Installation du programme :
```shell
# Installer :
mvn install

# Générer les rapports :
mvn site

# Exécuter le programme :
java -jar target\pglp_9.9_REYNAUD-jar-with-dependencies.jar
```
## Utilisation du programme
### Initialisation de la BD
- ## IMPORTANT
Pour la première utilisation du programme, il faut **impérativement** initialiser la BD avec:

```shell
initBD
```
Cette commande ne doit pas être utilisée **uniquement pour la première utilisation**.
- Supprimer tout le contenu de la BD :

```shell
deleteBD
```
### Commandes

- #### Dessiner des formes :
Le dessin se fait avec la commannde **draw**. Les points se renseigne sous la forme (x,y).

``` shell
# Carré
draw carre nom (x,y) taille

# rectangle
draw rectangle nom (x,y) longeur hauteur

#cercle
draw cercle nom (x,y) rayon

#triangle
draw triangle nom (x,y) (x,y) (x,y)


# EXEMPLES :
draw carre c1 (55,12) 66
draw rectangle rec (85,8) 45
draw cercle cer (5,5) 45
draw triangle T1 (55,-2) 6
exit
```

- #### Grouper des formes ou groupes de formes
La commande **group nomGroupe forme1 forme2 group1 form6...** offre la possibilité de grouper des formes dessinées.
Il est possible de **grouper des groupes déjà formés**. 

```shell
draw carre c1 (55,12) 66
draw rectangle rec (85,8) 45
group Firstgroup c1 rec

draw triangle t (x,y) (x,y) (x,y)
group SecondGroup FirstGroup t

exit
```- ### Déplacer des dessins
**moove nomDessin abscisse ordonnée** permet de déplacer un dessin ou groupe de dessin dans une direction x et y.

```shell
moove group2 5 -3
```

- ### Effacer des dessins de la session actuelle
**erase nomDessin** permet d'effacer des formes dessinées dans la session actuelle. Les groupes de dessins peuvent aussi être effacés

```shell
draw carre carretest (4,5) 55
erase carretest
exit
```

- ### Sauvegarder des dessins dans la base de données
Si la base de données a bien été initialisé la commande **save nomDessin** peut être utilisée. Si des modifications on été apporté
au dessin (délacement) elles seront sauvegardées.

```shell
draw carre carretest (4,5) 55
save carretest

draw triangle T (4,5) 2
draw rectangle rec (5,0) 2
group newGroupe T rec 
save newGroupe
exit
```

- ### Voir les dessins de la base de données
La commande **recap** permet de voir une récapitulatif des dessins sauvegardés.


- ### Charger des dessins depuis la base de données
**load nomDessin**

```shell
# dessin avec sauvegarde 
draw carre carretest (4,5) 55
save carretest
exit

# chargement du dessin
load carretest
exit
```

- ### Effacer des dessins de la base de données
**delete nomDessin** à condition que le dessin est sauvegardé.

```shell
delete carretest
exit
```

- ### Exit et Help
Les commande **exit** et **help** permmettent de quitter le programme et de voir un récapitulatif des commandes.



##Description technique

Un objet GroupeForme est instancié au début du programme, il sert à stocker les objets métier de la session en cours.