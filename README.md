# pglp_9.9
- Exercice réalisé pour le module PGLP, M1 INFORMATIQUE
- Julien Reynaud

## MANUEL UTILISATEUR
### Installation du programme :
```shell
# Installer :
mvn install

# Générer les rapports :
mvn site

# Exécuter le programme :
java -jar target\pglp_9.9_REYNAUD-jar-with-dependencies.jar
```
### Utilisation du programme
## Initialisation de la BD
- ## IMPORTANT
Pour la première utilisation du programme, il faut impérativement initialiser la BD avec:
```shell
initBD
```
Cette commande ne doit pas être utilisée uniquement pour la première utilisation.
- Supprimer tout le contenu de la BD :
```shell
deleteBD
```
##Commandes

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