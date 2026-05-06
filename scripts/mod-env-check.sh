#!/usr/bin/env bash
# mod-env-check.sh — Auto-detects project configuration for OpenComputers port
set -euo pipefail

echo "=== OpenComputers NeoForge 1.21.1 — Environment Check ==="
echo ""

# Java version
JAVA_VER=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}')
echo "Java version  : $JAVA_VER"
if [[ "$JAVA_VER" < "21" ]]; then
  echo "  !! WARN: Java 21 required (Minecraft 1.21.1 ships Java 21)"
fi

# Gradle version
if command -v ./gradlew &>/dev/null; then
  GRADLE_VER=$(./gradlew --version 2>/dev/null | grep "Gradle" | awk '{print $2}')
  echo "Gradle version: $GRADLE_VER"
else
  echo "Gradle wrapper: NOT FOUND — run './gradlew wrapper' first"
fi

# Read gradle.properties
PROPS="gradle.properties"
if [[ -f "$PROPS" ]]; then
  MC_VER=$(grep "^minecraft_version=" "$PROPS" | cut -d= -f2)
  NEO_VER=$(grep "^neo_version=" "$PROPS" | cut -d= -f2)
  MOD_ID=$(grep "^mod_id=" "$PROPS" | cut -d= -f2)
  MOD_VER=$(grep "^mod_version=" "$PROPS" | cut -d= -f2)
  echo ""
  echo "Minecraft     : $MC_VER"
  echo "NeoForge      : $NEO_VER"
  echo "Mod ID        : $MOD_ID"
  echo "Mod version   : $MOD_VER"
else
  echo "  !! ERROR: gradle.properties not found"
fi

echo ""
# Detect native libs
if ls libs/OC-JNLua*.jar &>/dev/null; then
  echo "JNLua natives : FOUND ($(ls libs/OC-JNLua*.jar | wc -l) file(s))"
else
  echo "JNLua natives : MISSING — place OC-JNLua.jar in /libs"
fi

if ls libs/OC-LuaJ*.jar &>/dev/null; then
  echo "LuaJ          : FOUND"
else
  echo "LuaJ          : MISSING — place OC-LuaJ.jar in /libs (or use Maven: org.luaj:luaj-jse:3.0.1)"
fi

echo ""
echo "=== Quick commands ==="
echo "  ./gradlew runData            — regenerate resources"
echo "  ./gradlew runClient          — launch client for testing"
echo "  ./gradlew runGameTestServer  — run game tests"
echo "  ./gradlew build              — build the mod JAR"
