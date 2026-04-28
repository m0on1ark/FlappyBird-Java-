# Flappy Bird (Custom Edition) - Documentation

## 1. Project Overview
This project is a classic "Flappy Bird" arcade game implementation written in **Java** using the **Swing** library. It focuses on clean object-oriented design and procedural graphics rendering.

## 2. Technical Stack
* **Language:** Java 17+
* **Framework:** Java Swing / AWT
* **Graphics:** Graphics2D with anti-aliasing and gradient shading.

## 3. Project Structure
* **Game.java**: The core engine. Manages the game loop (20ms ticks), state transitions (Menu, Active, Game Over), and coordinate-based scoring logic.
* **Bird.java**: Handles player physics, including gravity constants and jump (flop) velocity.
* **Walls.java**: Manages obstacle generation, randomized gap heights, and horizontal movement.

## 4. Key Features
* **Procedural Rendering**: High-quality pipes drawn using `GradientPaint` to simulate 3D volume without external assets.
* **Collision Detection**: Precise intersection checks using `java.awt.Rectangle`.
* **Dynamic Scoring**: Score increments precisely as the bird passes the pipe's X-boundary.
* **Resource Resilience**: Implements a fallback system that renders geometric shapes if PNG/JPG assets are missing.

## 5. Controls
* **Mouse**: Click **START GAME** in the menu or anywhere on the screen to restart after a crash.
* **Keyboard**: Press **SPACEBAR** to jump.

## 6. Deployment & Execution
To compile and bundle the project manually:
1. **Compile**: `javac Game.java Bird.java Walls.java`
2. **Package**: `jar cfe FlappyBird.jar Game *.class *.png *.jpg`
3. **Run**: `java -jar FlappyBird.jar`

---
**Developer:** Ruslan Dzikanczuk
**Institution:** Warsaw University of Technology
**Faculty:** Electronics and Information Technology (Elka)