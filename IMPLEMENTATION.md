# Fabric Utility Mod - Implementation Documentation

## Overview
This document describes the complete implementation of the Fabric Utility Mod for Minecraft 1.21.10, featuring Flight and ESP systems.

## Project Structure

```
fabric-utility-mod/
├── build.gradle                     # Gradle build configuration
├── gradle.properties                # Version and dependency properties
├── settings.gradle                  # Gradle settings
├── gradlew                          # Gradle wrapper script (Unix)
├── gradle/wrapper/                  # Gradle wrapper files
├── LICENSE                          # MIT License
├── README.md                        # User documentation
├── .gitignore                       # Git ignore rules
└── src/main/
    ├── java/com/arise/fabricutility/
    │   ├── FabricUtilityClient.java           # Main mod entry point
    │   ├── client/
    │   │   └── KeyBindings.java               # Key binding registration
    │   ├── features/
    │   │   ├── FlightManager.java             # Flight toggle logic
    │   │   ├── ESPManager.java                # ESP toggle logic
    │   │   └── ESPRenderer.java               # ESP box rendering
    │   └── mixin/
    │       ├── ClientPlayerEntityMixin.java   # Player tick mixin
    │       └── WorldRendererMixin.java        # World render mixin
    └── resources/
        ├── fabric.mod.json                    # Mod metadata
        ├── fabricutility.mixins.json          # Mixin configuration
        └── assets/fabricutility/lang/
            └── en_us.json                     # English translations
```

## Technical Implementation

### 1. Mod Entry Point (FabricUtilityClient.java)

**Purpose**: Main client-side initialization
**Key Features**:
- Implements `ClientModInitializer` interface
- Initializes `FlightManager` and `ESPManager` as static instances
- Registers key bindings
- Sets up client tick event handler

**Code Highlights**:
```java
public static final FlightManager FLIGHT_MANAGER = new FlightManager();
public static final ESPManager ESP_MANAGER = new ESPManager();
```

### 2. Key Bindings (KeyBindings.java)

**Purpose**: Register and handle F and K key presses
**Implementation**:
- F key: Toggle flight (GLFW_KEY_F)
- K key: Toggle ESP (GLFW_KEY_K)
- Uses Fabric's KeyBindingHelper for registration
- Processes key presses in client tick events

### 3. Flight System

#### FlightManager.java
**Features**:
- Toggles flight on/off
- Manages player flight abilities
- Sends in-game messages (green for enabled, red for disabled)
- Preserves creative mode flight settings

**Key Methods**:
- `toggle()`: Switches flight state and updates player abilities
- `updateFlight()`: Maintains flight during player tick
- `isEnabled()`: Returns current flight state

**Implementation Details**:
```java
player.getAbilities().allowFlying = true;
player.getAbilities().flying = true;
player.sendAbilitiesUpdate();
```

#### ClientPlayerEntityMixin.java
**Purpose**: Keep flight enabled during gameplay
**Mixin Target**: `ClientPlayerEntity.tick()`
**Injection Point**: `@At("HEAD")`
**Function**: Calls `FlightManager.updateFlight()` every tick to maintain allowFlying=true

### 4. ESP System

#### ESPManager.java
**Features**:
- Toggles ESP rendering on/off
- Sends in-game messages (green for enabled, red for disabled)
- Tracks ESP state

#### ESPRenderer.java
**Purpose**: Render colored boxes around entities
**Color Scheme**:
- Players: Red (RGB: 1.0, 0.0, 0.0)
- Mobs: Orange (RGB: 1.0, 0.5, 0.0)
- Items: Green (RGB: 0.0, 1.0, 0.0)

**Rendering Process**:
1. Get entity position relative to camera
2. Determine entity type and select color
3. Render 12 edges of bounding box using DEBUG_LINES
4. Use POSITION_COLOR vertex format

**Key Implementation**:
```java
BufferBuilder buffer = tessellator.begin(
    VertexFormat.DrawMode.DEBUG_LINES, 
    VertexFormats.POSITION_COLOR
);
```

#### WorldRendererMixin.java
**Purpose**: Inject ESP rendering into world render
**Mixin Target**: `WorldRenderer.render()`
**Injection Point**: `@At("TAIL")` (after normal rendering)

**Wallhack Implementation**:
```java
RenderSystem.disableDepthTest();  // See through walls
RenderSystem.enableBlend();
RenderSystem.lineWidth(2.0f);

// Render all entities
for (Entity entity : client.world.getEntities()) {
    ESPRenderer.renderEntityESP(matrices, entity, tickDelta);
}

RenderSystem.enableDepthTest();   // Restore normal rendering
```

## Configuration Files

### fabric.mod.json
- Schema version: 1
- Mod ID: fabricutility
- Environment: client (client-side only)
- Entry point: `com.arise.fabricutility.FabricUtilityClient`
- Mixins: `fabricutility.mixins.json`
- Dependencies:
  - Fabric Loader: >=0.16.14
  - Fabric API: any version
  - Minecraft: ~1.21.10
  - Java: >=21

### fabricutility.mixins.json
- Compatibility level: JAVA_21
- Package: `com.arise.fabricutility.mixin`
- Client mixins:
  - ClientPlayerEntityMixin
  - WorldRendererMixin

### gradle.properties
- Minecraft version: 1.21.10
- Yarn mappings: 1.21.10+build.1
- Fabric Loader: 0.16.14
- Fabric API: 0.110.0+1.21.10
- Java: 21

## Build System

### Gradle Configuration
- Gradle version: 8.10.2
- Fabric Loom: 1.6.12
- Java source/target: 21
- Build output: `build/libs/fabric-utility-mod-1.0.0.jar`

### Build Commands
```bash
./gradlew build           # Build the mod
./gradlew clean          # Clean build files
./gradlew jar            # Create JAR only
```

## Features Implementation Summary

### Flight System ✓
- [x] F key binding registered
- [x] Toggle functionality implemented
- [x] Survival mode flight enabled
- [x] Creative mode compatibility
- [x] In-game messages (green/red)
- [x] Smooth flight controls via Minecraft's native system

### ESP System ✓
- [x] K key binding registered
- [x] Toggle functionality implemented
- [x] Player detection (Red boxes)
- [x] Mob detection (Orange boxes)
- [x] Item detection (Green boxes)
- [x] Wallhack rendering (depth test disabled)
- [x] Box rendering around entity bounds
- [x] In-game messages (green/red)

## Technical Challenges Addressed

### 1. Maintaining Flight in Survival
**Challenge**: Server resets flight abilities
**Solution**: Mixin into ClientPlayerEntity.tick() to continuously set allowFlying=true

### 2. Rendering Through Walls
**Challenge**: Normal rendering respects depth testing
**Solution**: Disable depth test before ESP rendering, re-enable after

### 3. Entity Type Differentiation
**Challenge**: Identifying different entity types
**Solution**: Use instanceof checks for PlayerEntity, MobEntity, and ItemEntity

### 4. Coordinate Transformation
**Challenge**: Rendering at correct world positions
**Solution**: Calculate entity position relative to camera:
```java
double x = entityPos.x - cameraPos.x;
double y = entityPos.y - cameraPos.y;
double z = entityPos.z - cameraPos.z;
```

## Testing Recommendations

1. **Flight Testing**:
   - Press F in survival mode
   - Verify flight works like creative mode
   - Test toggling on/off
   - Check message display

2. **ESP Testing**:
   - Press K to enable
   - Verify boxes appear around players (red), mobs (orange), items (green)
   - Test visibility through walls
   - Verify boxes disappear when disabled

3. **Build Testing**:
   - Run `./gradlew build`
   - Check `build/libs/` for JAR file
   - Test in Minecraft with Fabric Loader

## Known Limitations

1. **Network Dependency**: Build requires access to maven.fabricmc.net which may not be available in all environments
2. **Client-Side Only**: This mod only works on the client side
3. **Server Compatibility**: Flight may be kicked by anti-cheat plugins on servers
4. **Performance**: ESP rendering may impact FPS with many entities

## Future Enhancement Ideas

1. Separate toggles for player/mob/item ESP
2. Customizable ESP colors
3. Configurable key bindings via config file
4. Flight speed adjustment
5. ESP distance limiting
6. Entity name labels
7. Health bars for entities
8. Tracers (lines to entities)
9. Config GUI using ModMenu
10. Persistent settings storage

## Version Compatibility

- **Minecraft**: 1.21.10 (may work on 1.21.x with adjustments)
- **Java**: 21 or higher required
- **Fabric Loader**: 0.16.14 or higher
- **Fabric API**: 0.110.0+1.21.10 recommended

## License

MIT License - See LICENSE file for full text.

## Contributors

- ARISE - Initial implementation

---

**Last Updated**: December 2024
**Mod Version**: 1.0.0
**Minecraft Version**: 1.21.10
