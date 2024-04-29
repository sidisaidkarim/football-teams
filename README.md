# football-teams

Ce projet est une application Spring qui met en œuvre un système de gestion d'équipes. Il utilise une base de données PostgreSQL pour stocker les données des équipes.

## Configuration de la base de données

Assurez-vous d'avoir une instance PostgreSQL en cours d'exécution sur le port 5432 avec les paramètres suivants :
- Nom de la base de données : postgres
- Utilisateur : postgres
- Mot de passe : postgres

## Lancement de l'application
### Pour récupérer toutes les équipes avec trie et pagination, utilisez l'URL suivante :
http://localhost:8080/api/team?page=0&size=5&sort=budget

- page : le numéro de la page (0-indexé)
- size : le nombre d'éléments par page
- sort : le champ de trie (name, acronym, ou budget)

## Documentation de l'API
La documentation de l'API est générée avec Swagger et est accessible à l'adresse suivante :
http://localhost:8080/swagger-ui/index.html
