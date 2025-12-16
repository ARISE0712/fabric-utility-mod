# Fabric Utility Mod

A client-side Minecraft Fabric mod for version 1.21.10 that adds Flight and ESP (Extra Sensory Perception) features.

## Features

### Flight System
- **Toggle Key**: F
- Allows players to fly in survival mode
- Smooth flight controls similar to creative mode
- Toggle on/off with the F key

### ESP (Extra Sensory Perception) System
- **Toggle Key**: K
- Shows colored boxes/outlines around all entities through walls
- Entity types:
  - **Players**: Red colored boxes
  - **Mobs**: Orange colored boxes
  - **Items**: Green colored boxes (dropped items on ground)
- Renders through all blocks (wallhack effect)
- Single toggle for all ESP features

## Installation

### Prerequisites
- Minecraft 1.21.10
- Fabric Loader 0.16.14 or higher
- Fabric API 0.110.0+1.21.10 or higher

### Steps
1. Install Fabric Loader for Minecraft 1.21.10 from [FabricMC](https://fabricmc.net/use/)
2. Download Fabric API from [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) or [Modrinth](https://modrinth.com/mod/fabric-api)
3. Place both Fabric API and this mod's JAR file in your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile

## Building from Source

### Requirements
- Java 21 or higher
- Gradle 8.10.2 (included via wrapper)

### Build Steps
```bash
# Clone the repository
git clone https://github.com/ARISE0712/fabric-utility-mod.git
cd fabric-utility-mod

# Build the mod
./gradlew build

# The built JAR will be in build/libs/
```

## Usage

### Controls
- Press **F** to toggle Flight mode
- Press **K** to toggle ESP (shows all entities through walls)

### In-Game Messages
- When Flight is enabled: Green message "[Flight] Enabled"
- When Flight is disabled: Red message "[Flight] Disabled"
- When ESP is enabled: Green message "[ESP] Enabled"
- When ESP is disabled: Red message "[ESP] Disabled"

## Technical Details

### Project Structure
```
src/main/java/com/arise/fabricutility/
├── FabricUtilityClient.java          # Main mod entry point
├── client/
│   └── KeyBindings.java              # Keybind registration and handling
├── features/
│   ├── FlightManager.java            # Flight toggle and management
│   ├── ESPManager.java               # ESP toggle management
│   └── ESPRenderer.java              # ESP box rendering
└── mixin/
    ├── ClientPlayerEntityMixin.java  # Player tick mixin for flight
    └── WorldRendererMixin.java       # World render mixin for ESP
```

### Dependencies
- Minecraft 1.21.10
- Fabric Loader 0.16.14+
- Fabric API 0.110.0+1.21.10
- Java 21

### Mixins
- `ClientPlayerEntityMixin`: Maintains flight abilities during player tick
- `WorldRendererMixin`: Renders ESP boxes around entities with depth testing disabled

## License

MIT License - See LICENSE file for details

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Disclaimer

This mod is designed for educational and personal use. Use responsibly and in accordance with server rules and Minecraft's Terms of Service.
