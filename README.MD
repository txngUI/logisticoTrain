# Readme du projet

## Commentaires



## Bugs découverts

Lors de l'installation avec npm install, il y a une erreur de compilation de node-sass.
Nous avons enlever la ligne suivante dans le package.json pour que l'installation se fasse correctement.

"node-sass": "^9.0.0"

A la place nous avons mis :  "sass": "^1.64.2",

## limite du système

- Le nom d'utilisateur et le mot de passe pour le broker ne sont pas sous forme de variable d'environnement avec des secrets. 
Ils sont entrés en brut et constituent donc un risque de sécurité.