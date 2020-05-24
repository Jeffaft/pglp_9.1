# pglp_9.9
- Exercice réalisé pour le module PGLP, M1 INFORMATIQUE
- Julien Reynaud

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

#### Dessiner des formes :
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

#### Grouper des formes ou groupes de formes
La commande **group nomGroupe forme1 forme2 group1 form6...** offre la possibilité de grouper des formes dessinées.
Il est possible de **grouper des groupes déjà formés**. 

```shell
draw carre c1 (55,12) 66
draw rectangle rec (85,8) 45
group Firstgroup c1 rec

draw triangle t (x,y) (x,y) (x,y)
group SecondGroup FirstGroup t

exit
```### Déplacer des dessins
**moove nomDessin abscisse ordonnée** permet de déplacer un dessin ou groupe de dessin dans une direction x et y.

```shell
moove group2 5 -3
```

### Effacer des dessins de la session actuelle
**erase nomDessin** permet d'effacer des formes dessinées dans la session actuelle. Les groupes de dessins peuvent aussi être effacés

```shell
draw carre carretest (4,5) 55
erase carretest
exit
```

draw cercle nom (.,.) rayon
draw carre nom (x,y) taille
draw rectangle nom (x,y) hauteur longueur
group nom figure1 figure2 .. ..
moove
save 
possibilité de save plusieurs en meme temps
delete
exit

##Description technique

Un objet GroupeForme est instancié au début du programme, il sert à stocker les objets métier de la session en cours.