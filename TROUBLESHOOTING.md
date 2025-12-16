# Troubleshooting Guide

## Common Issues and Solutions

### Build Issues

#### Issue: "Plugin [id: 'fabric-loom'] was not found"
**Cause**: Cannot connect to maven.fabricmc.net or Fabric repository
**Solutions**:
1. Check internet connection
2. Try again later (repository may be temporarily down)
3. Use VPN if repository is blocked in your region
4. Check if firewall is blocking Maven repositories

#### Issue: "Could not resolve dependencies"
**Cause**: Missing or incompatible dependencies
**Solutions**:
1. Ensure Java 21 is installed: `java -version`
2. Clear Gradle cache: `./gradlew clean --refresh-dependencies`
3. Delete `.gradle` folder and rebuild
4. Check internet connectivity to Maven Central

#### Issue: "Unsupported Java version"
**Cause**: Wrong Java version
**Solution**: Install Java 21 or higher from [Adoptium](https://adoptium.net/)

### Runtime Issues

#### Issue: Flight doesn't work in multiplayer
**Cause**: Server-side anti-cheat or vanilla server restrictions
**Solutions**:
1. This mod is client-side only and may not work on vanilla servers
2. Server admins may need to disable fly-check
3. Works best in singleplayer or modded servers that allow client-side flight

#### Issue: ESP boxes not showing
**Causes & Solutions**:
1. **ESP not enabled**: Press K to toggle
2. **No entities nearby**: Move around to find entities
3. **Render distance**: Increase render distance in settings
4. **Mod not loaded**: Check F3+C shows mod is loaded

#### Issue: Keybinds don't work
**Solutions**:
1. Check for keybind conflicts in Controls settings
2. Try reassigning keys in Minecraft options
3. Ensure mod is properly loaded (check logs)

#### Issue: Crash on startup
**Causes & Solutions**:
1. **Missing Fabric API**: Install Fabric API mod
2. **Wrong Minecraft version**: Ensure you're running 1.21.10
3. **Incompatible mods**: Try with just this mod and Fabric API
4. **Check logs**: Look in `.minecraft/logs/latest.log` for error details

### Installation Issues

#### Issue: Mod not showing in mods list
**Solutions**:
1. Ensure JAR is in `.minecraft/mods` folder
2. Check you're using Fabric Loader, not Forge
3. Verify Fabric API is installed
4. Check Minecraft version matches (1.21.10)

#### Issue: "Incompatible mod set" error
**Cause**: Version mismatch
**Solutions**:
1. Verify Minecraft version is 1.21.10
2. Update Fabric Loader to 0.16.14 or higher
3. Update Fabric API to 0.110.0+1.21.100 or higher
4. Remove incompatible mods

## Performance Issues

#### Issue: Low FPS with ESP enabled
**Solutions**:
1. Reduce render distance
2. Disable ESP when not needed (Press K)
3. Update graphics drivers
4. Allocate more RAM to Minecraft (4GB+ recommended)
5. Use performance mods like Sodium (ensure compatibility)

## Development Issues

#### Issue: Mixin not applying
**Solutions**:
1. Check mixin JSON syntax in `fabricutility.mixins.json`
2. Verify mixin class names match exactly
3. Run with `--mixin.debug=true` for detailed logs
4. Check mixin target exists in current Minecraft version

#### Issue: ClassNotFoundException
**Solutions**:
1. Rebuild project: `./gradlew clean build`
2. Check package names match between files
3. Verify all imports are correct
4. Delete `build` folder and rebuild

## Getting Help

### Log Files
Important log locations:
- Latest game log: `.minecraft/logs/latest.log`
- Crash reports: `.minecraft/crash-reports/`
- Debug log: `.minecraft/logs/debug.log`

### Reporting Bugs
When reporting issues, please include:
1. Minecraft version
2. Fabric Loader version
3. Fabric API version
4. Mod version
5. Relevant log files
6. Steps to reproduce
7. Other mods installed

### Useful Commands
```bash
# Check Java version
java -version

# Check Gradle version
./gradlew --version

# Clean build
./gradlew clean

# Build with debug info
./gradlew build --stacktrace --debug

# Refresh dependencies
./gradlew --refresh-dependencies
```

## FAQ

### Q: Does this work on servers?
A: Flight may be kicked by server anti-cheat. ESP rendering is client-side and should work for seeing entities.

### Q: Is this a cheat/hack?
A: This mod provides utility features. Use responsibly and follow server rules.

### Q: Can I change the keybinds?
A: Yes, in Minecraft's Controls settings under "Fabric Utility" category.

### Q: Does this work with Optifine?
A: Optifine compatibility is not guaranteed. Use Sodium/Iris as alternatives.

### Q: Can I use this in versions other than 1.21.10?
A: Minor versions (1.21.x) might work but aren't officially supported. Major version changes require code updates.

### Q: How do I build from source?
A: See README.md for build instructions. Requires Java 21 and internet connection.

### Q: The mod isn't loading after installation
A: Ensure you have both Fabric Loader and Fabric API installed for Minecraft 1.21.10.

## Still Having Issues?

1. Check existing issues on GitHub
2. Search for similar problems
3. Create new issue with detailed information
4. Join community Discord (if available)

## Version Information

- Mod Version: 1.0.0
- Minecraft Version: 1.21.10
- Java Version: 21+
- Fabric Loader: 0.16.14+
- Fabric API: 0.110.0+1.21.100
