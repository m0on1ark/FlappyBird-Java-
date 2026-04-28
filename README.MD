# Flappy Bird (Custom Edition) - Documentation

## 1. Project Overview
This project is a classic "Flappy Bird" arcade game implementation written in **Java** using the **Swing** library. It focuses on clean object-oriented design and procedural graphics rendering.

## 2. Technical Stack
* **Language:** Java 17+
* **Framework:** Java Swing / AWT
* **Graphics:** Graphics2D with anti-aliasing and gradient shading.

## 3. Project Structure
* **Main.java**: The entry point of the application. Contains the `main` method that initializes and starts the game.
* **Game.java**: The core engine. Manages the game loop, state transitions, and coordinate-based scoring logic.
* **Bird.java**: Handles player physics, including gravity constants and jump velocity.
* **Walls.java**: Manages obstacle generation and movement.

## 4. Key Features
* **Procedural Rendering**: High-quality pipes drawn using `GradientPaint` to simulate 3D volume.
* **Collision Detection**: Precise intersection checks using `java.awt.Rectangle`.
* **Dynamic Scoring**: Score increments as the bird successfully passes each obstacle.
* **Resource Resilience**: Implements a fallback system to render geometric shapes if image assets are missing.

## 5. Controls
* **Mouse**: Click **START GAME** in the menu or anywhere on the screen to restart.
* **Keyboard**: Press **SPACEBAR** to jump.

## 6. Deployment & Execution
To compile and bundle the project manually:
1. **Compile**: `javac Main.java Game.java Bird.java Walls.java`
2. **Package**: `jar cfe FlappyBird.jar Main *.class *.png *.jpg`
3. **Run**: `java -jar FlappyBird.jar`

