# Anleitung: Mod herunterladen und JAR erstellen

## Option 1: Repository klonen und mit VS Code bauen

### Schritt 1: Repository herunterladen
```bash
# Mit Git klonen
git clone https://github.com/ARISE0712/fabric-utility-mod.git
cd fabric-utility-mod
```

Oder: Klicke auf GitHub auf "Code" → "Download ZIP" und entpacke es.

### Schritt 2: VS Code öffnen
1. Öffne VS Code
2. Datei → Ordner öffnen → Wähle den `fabric-utility-mod` Ordner

### Schritt 3: Java Development Kit (JDK) installieren
- Lade Java 21 herunter: https://adoptium.net/
- Installiere es und stelle sicher, dass es in deinem PATH ist
- Prüfe mit: `java -version` (sollte 21 oder höher zeigen)

### Schritt 4: Mod bauen
Im VS Code Terminal (Terminal → Neues Terminal):

**Windows:**
```bash
gradlew.bat build
```

**macOS/Linux:**
```bash
./gradlew build
```

### Schritt 5: JAR-Datei finden
Nach erfolgreichem Build findest du die JAR-Datei hier:
```
build/libs/fabric-utility-mod-1.0.0.jar
```

## Option 2: Direkt die Dateien herunterladen

### GitHub Releases
1. Gehe zu: https://github.com/ARISE0712/fabric-utility-mod
2. Klicke auf "Releases" (rechte Seite)
3. Lade die `.jar` Datei herunter

*Hinweis: Wenn noch kein Release existiert, musst du Option 1 verwenden.*

## Option 3: Mit GitHub Desktop

1. Installiere GitHub Desktop: https://desktop.github.com/
2. Klone das Repository über GitHub Desktop
3. Öffne den Ordner in VS Code
4. Folge Schritt 3-5 von Option 1

## Installation der JAR-Datei in Minecraft

1. Stelle sicher, dass Fabric Loader installiert ist:
   - Lade den Fabric Installer: https://fabricmc.net/use/
   - Wähle Minecraft Version **1.21.10**
   - Installiere Fabric Loader **0.16.14**

2. Lade Fabric API herunter:
   - https://modrinth.com/mod/fabric-api
   - Version: **0.110.0+1.21.10**

3. Platziere die JAR-Dateien:
   ```
   Windows: %APPDATA%\.minecraft\mods\
   macOS: ~/Library/Application Support/minecraft/mods/
   Linux: ~/.minecraft/mods/
   ```
   
   Du brauchst:
   - `fabric-api-0.110.0+1.21.10.jar`
   - `fabric-utility-mod-1.0.0.jar`

4. Starte Minecraft mit dem Fabric Profil

## Schnellübersicht: Alle benötigten Downloads

| Was | Version | Link |
|-----|---------|------|
| Java | 21+ | https://adoptium.net/ |
| Minecraft | 1.21.10 | Minecraft Launcher |
| Fabric Loader | 0.16.14 | https://fabricmc.net/use/ |
| Fabric API | 0.110.0+1.21.10 | https://modrinth.com/mod/fabric-api |
| Diese Mod | 1.0.0 | Selbst bauen mit Gradle |

## VS Code Extensions (Optional aber hilfreich)

1. **Extension Pack for Java** (von Microsoft)
   - Installiere über VS Code: Strg+Shift+X → Suche "Extension Pack for Java"

2. **Gradle for Java** (von Microsoft)
   - Für bessere Gradle-Integration

## Troubleshooting

### "Java not found" Fehler
- Stelle sicher, dass Java 21 installiert ist
- Setze die JAVA_HOME Umgebungsvariable

### Build schlägt fehl
- Lösche den `.gradle` Ordner
- Führe aus: `./gradlew clean build`

### Mod lädt nicht in Minecraft
- Prüfe die Minecraft-Version (muss 1.21.10 sein)
- Prüfe die Fabric Loader Version (muss 0.16.14 sein)
- Stelle sicher, dass Fabric API installiert ist

## Steuerung im Spiel

- **F-Taste**: Flug aktivieren/deaktivieren
- **K-Taste**: ESP aktivieren/deaktivieren (zeigt Spieler, Mobs, Items durch Wände)

## Farben der ESP-Boxen

- **Rot**: Spieler
- **Orange**: Mobs
- **Grün**: Items (auf dem Boden liegende Gegenstände)
