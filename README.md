# EntreVoisins


## Compilation et Test

Pour tester cette application il faudra : 
	- Android Studio avec le SDK Android et Java. 
	- Une image d'android pour la machine virtuelle ou un téléphone physique (avec les fonctionnalité developpeur activé et un cable USB)

Télécharger la branche `main` sur le repo de l'application, directement via le site de Github ou avec git en le clonant avec  `git clonehttps://github.com/Coldragon/EntreVoisins`

Avec Android Studio ouvrez le dossier de l'application que vous venez de cloner, Android Studio ce chargera de lires les different fichier pour ouvrir le projet (fichiers gradles, manifest etc...)

Il faut que vous selectionner le device target (soit une VM que vous avez créé au préalable, soit un device physique que vous avez connecter a votre ordinateur).
Ensuite vous n'avez plus qu'a "Make" l'application avec Build -> Make Project (F7) et ensuite de la "Run" Run -> Run (CTRL+ALT+MAJ+R) et de selectionner app.

Vous pouvez aussi run les test unitaire ou tests d'integrations avec Run -> Run (CTRL+ALT+MAJ+R) en selectionnant les test et non app.