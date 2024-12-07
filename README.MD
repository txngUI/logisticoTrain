# LogisticoTrain ReadMe

## Introduction

LogisticoTrain est une application conçue pour gérer les arrivées, départs et opérations de maintenance des rames.

## Architecture des Services

### Les services principaux :

sqldatabase : Base de données relationnelle MariaDB pour la production.

- nosqldatabase : Base de données MongoDB pour l'historique.
- broker : RabbitMQ pour la messagerie en temps réel.
- restapi : API REST (Flask) pour gérer les opérations.
- wsapi : API WebSocket (Spring Boot) pour la communication en temps réel.
- front : Serveur HTTP Nginx servant l'interface utilisateur.

### Outils de développement (profile dev-tool) :

- phpmyadmin : Interface web pour MariaDB.
- mongo-express : Interface web pour MongoDB.

## Réseaux

Nous avons fais le choix de configurer 2 réseaux :

- db-net : Connecte les bases de données et les API.
- broker-net : Connecte le broker RabbitMQ et l'API WebSocket.

## Problèmes Connus

- Lors de l'installation avec npm install, il y a une erreur de compilation de node-sass.
  Nous avons enlever la ligne suivante dans le package.json pour que l'installation se fasse correctement.

"node-sass": "^9.0.0"

A la place nous avons mis : "sass": "^1.64.2",

- Le nom d'utilisateur et le mot de passe pour le broker ne sont pas sous forme de variable d'environnement avec des secrets.
  Ils sont entrés en brut et constituent donc un risque de sécurité.

- Le front ne se build pas tout seul, il faut donc executer les commandes suivantes dans le répertoire app/

#### npm install && npm run build

- Nous avons également des problèmes avec nos api :
    - WSAPI: s'exit une fois son build terminé.
    - RESTAPI: ne démarre plus
- Ces erreurs sont apparu du jour au lendemain alors que notre projet fonctionnait auparavant et nous n'avons pas su les corriger a temps.

## Améliorations Futures

- Implémenter la gestion des secrets pour les identifiants RabbitMQ.

## Déploiement

Avant de commencer le déploiement, assurez vous d'avoir :

#### Secrets et configurations :

Placez les fichiers suivants dans le dossier secrets/ a la racine :

- mongo_root_username.txt
- mongo_root_password.txt
- maria_user.txt
- maria_password.txt
- maria_root_password.txt
- Assurez-vous que les fichiers init-mariadb.sql et init-mongo.js sont configurés correctement dans le dossier racine.

#### Structure des fichiers :

- nginx.conf : Configuration Nginx.
- Répertoire RESTApi
- Répertoire RealtimeAPI
- Répertoire app

### Étapes de Déploiement

#### Construire et Lancer les Services

- éxécuter la commande "docker compose --profile=dev-tool up" (si vous ne souhaitez pas voir les log rajouter "-d")
- éxécuter ensuite la commande "docker compose --profile=build up"
- Le conteneur sqldatabase initialise la base avec le script init-mariadb.sql.
- Le conteneur nosqldatabase initialise les collections MongoDB avec init-mongo.js.

### Outils d'Administration

- PhpMyAdmin : Accessible sur http://localhost:8888.
  - Utilisateur et mot de passe définis dans les secrets MariaDB.
- Mongo-Express : Accessible sur http://localhost:8081.
  - Identifiants MongoDB issus des fichiers secrets.
- RabbitMQ Management : Accessible sur http://localhost:15672.
  - Identifiants :
    - Utilisateur : brokerusr
    - Mot de passe : brokerpass
