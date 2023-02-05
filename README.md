# DEV-ANDROID-

Présentation de l'application :  

Cette application dénommée 'Do it', est une application de rappels. C'est-à-dire permettre 
d'ajouter une tache à une liste de tache déjà existante avec la possibilité de mettre à jour les tâches. 
Cette application, au fonctionnement simple, peut se résumer en quatre interactions 
basiques : 

– ajouter une tache
– modifier une tache
– mettre à jour une tache
– supprimer une tache

Ainsi le concept de l'application tourne autour de ces points principaux. Codée en 
Java pour le back-end et en XML pour le front-end, cette application permet de se remémorer 
aisément nos tâches à accomplir.
Cette application est ainsi composé de différents fichiers avec des utilités particulières, dont voici 
une liste non exhaustive :

– MainActivity.java : représente l'activité principale.
– RecyclerItemTouchHelper.java : gère le contrôle du tactile ( swipe à gauche pour edit, à 
droite pour supprimer)
– DbHandler.java : permet de gérer une simple base de données pour les tâches.
On y trouve également des fichiers xml, gérant quant à eux, le front-end, le visuel
