# Quick Start Guide - Building with VS Code

## Prerequisites

1. **Install Java 21**
   - Download from: https://adoptium.net/
   - After installation, verify: `java -version`

2. **Install Visual Studio Code**
   - Download from: https://code.visualstudio.com/

3. **Install Git** (optional, for cloning)
   - Download from: https://git-scm.com/

## Step-by-Step Build Instructions

### Method 1: Using VS Code (Recommended)

1. **Get the source code:**
   ```bash
   git clone https://github.com/ARISE0712/fabric-utility-mod.git
   ```
   
   Or download ZIP from GitHub and extract it.

2. **Open in VS Code:**
   - Launch VS Code
   - File → Open Folder → Select `fabric-utility-mod` folder

3. **Install Java Extension (First time only):**
   - Press `Ctrl+Shift+X` (or `Cmd+Shift+X` on Mac)
   - Search for "Extension Pack for Java"
   - Click Install

4. **Build the mod:**
   
   Open VS Code terminal (`Terminal → New Terminal` or `` Ctrl+` ``):
   
   **On Windows:**
   ```bash
   gradlew.bat build
   ```
   
   **On macOS/Linux:**
   ```bash
   ./gradlew build
   ```

5. **Find your JAR file:**
   ```
   build/libs/fabric-utility-mod-1.0.0.jar
   ```

### Method 2: Command Line Only

```bash
# Clone repository
git clone https://github.com/ARISE0712/fabric-utility-mod.git
cd fabric-utility-mod

# Build (choose one based on your OS)
./gradlew build        # macOS/Linux
gradlew.bat build      # Windows

# JAR file will be in:
# build/libs/fabric-utility-mod-1.0.0.jar
```

## Installing the Mod in Minecraft

1. **Install Fabric Loader:**
   - Download installer: https://fabricmc.net/use/
   - Select Minecraft version: **1.21.10**
   - Install Fabric Loader: **0.16.14**

2. **Download Fabric API:**
   - Visit: https://modrinth.com/mod/fabric-api
   - Download version: **0.110.0+1.21.10**

3. **Copy mods to folder:**
   
   Copy these files to your mods folder:
   - `fabric-api-0.110.0+1.21.10.jar`
   - `fabric-utility-mod-1.0.0.jar` (the one you built)
   
   **Mods folder location:**
   - Windows: `%APPDATA%\.minecraft\mods\`
   - macOS: `~/Library/Application Support/minecraft/mods/`
   - Linux: `~/.minecraft/mods/`

4. **Launch Minecraft:**
   - Select the "Fabric" profile
   - Start the game

## Using the Mod

### Controls
- **F Key**: Toggle Flight
- **K Key**: Toggle ESP (Extra Sensory Perception)

### ESP Colors
- **Red boxes**: Players
- **Orange boxes**: Mobs
- **Green boxes**: Items (dropped on ground)

## Troubleshooting

### Build fails with "Java not found"
- Make sure Java 21 is installed
- Check with: `java -version`
- Ensure JAVA_HOME environment variable is set

### Build fails with dependency errors
- Check your internet connection
- Try: `./gradlew clean build`
- Delete `.gradle` folder and rebuild

### Mod doesn't load in Minecraft
- Verify Minecraft version is **1.21.10**
- Verify Fabric Loader is **0.16.14**
- Ensure Fabric API is installed
- Check logs: `.minecraft/logs/latest.log`

## Project Structure

```
fabric-utility-mod/
├── src/main/java/          # Java source code
│   └── com/arise/fabricutility/
│       ├── FabricUtilityClient.java
│       ├── client/
│       ├── features/
│       └── mixin/
├── src/main/resources/     # Resources and configs
│   ├── fabric.mod.json
│   └── fabricutility.mixins.json
├── build.gradle            # Build configuration
├── gradle.properties       # Version settings
└── README.md              # Documentation
```

## Build Output

After running `./gradlew build`, you'll see:

```
BUILD SUCCESSFUL in Xs
```

The compiled JAR will be located at:
```
build/libs/fabric-utility-mod-1.0.0.jar
```

This file is what you need to copy to your Minecraft mods folder!

## Additional Resources

- **Fabric Documentation**: https://fabricmc.net/wiki/
- **Minecraft Modding Wiki**: https://minecraft.fandom.com/wiki/Mods
- **Java Documentation**: https://docs.oracle.com/en/java/

## Questions?

For issues or questions, please open an issue on the GitHub repository:
https://github.com/ARISE0712/fabric-utility-mod/issues
