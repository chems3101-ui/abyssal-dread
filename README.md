# Abyssal Dread — mod d'horreur océanique (Forge 1.20.1)

Les profondeurs ne sont plus vides. Sous la surface, trois créatures inventées te traquent, et un shader sous-marin transforme l'écran dès que ta tête est dans l'eau.

## Contenu

| Créature | Où | Particularité |
|---|---|---|
| **Noyé-Lanterne** | tous les océans | Sa lanterne éclate toutes les 5 s et t'aveugle ; ses coups te ralentissent. |
| **Rôdeur Abyssal** | océans profonds | Anguille géante très rapide ; sa morsure inflige *Ténèbres*. |
| **Gueule du Fond** | océans profonds (rare) | Baudroie colossale ; son leurre t'aspire vers sa gueule. |

Drop commun : la **Perle Abyssale**. Chaque créature a son œuf d'apparition (onglet créatif « Œufs d'apparition »).

**Shader** : actif automatiquement quand les yeux du joueur sont dans l'eau (houle, aberration chromatique, teinte glaciale, vignette qui respire, grain, battement de cœur).

## Compiler

Prérequis : JDK 17.

1. Ce dossier ne contient pas le *Gradle wrapper*. Télécharge le MDK Forge 1.20.1 (https://files.minecraftforge.net/), puis copie `gradlew`, `gradlew.bat` et le dossier `gradle/` ici.
2. `./gradlew build`
3. Le fichier `.jar` se trouve dans `build/libs/`.

Pour tester directement : `./gradlew runClient`.

## Installer (TLauncher)

Mets le `.jar` dans le dossier `mods` d'une instance **Forge 1.20.1** (la même base que ton modpack). Pour voir les monstres tout de suite : commande `/give @s abyssaldread:deep_stalker_spawn_egg`, ou plonge dans un océan profond.

## Réglages à ajuster en jeu

- Vitesse des monstres : `Attributes.MOVEMENT_SPEED` dans `DeepStalker` et `SeabedMaw` (les nageurs se règlent à tâtons).
- Fréquence d'apparition : fichiers `data/abyssaldread/forge/biome_modifier/*.json` (`weight`) et `AbyssalSwimmer.checkAbyssSpawn`.
- Force du shader : `assets/abyssaldread/shaders/program/abyss.fsh`.
