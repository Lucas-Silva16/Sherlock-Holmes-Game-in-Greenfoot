# 🕵️‍♂️ Sherlock & Watson: The Case of the Pixelated Monsters

![Language](https://img.shields.io/badge/Language-Java-orange)
![Framework](https://img.shields.io/badge/Framework-Greenfoot-5382A1)
![Genre](https://img.shields.io/badge/Genre-Co--op_Platformer-success)

## 🎮 About the Game
Welcome to the London underworld! This is a **2-player local co-op** action-platformer developed in Java using the **Greenfoot** framework. In this adventure, deduction gives way to survival as Sherlock Holmes and Dr. Watson team up to face hordes of pixelated monsters.

Teamwork is the core mechanic. While one player focuses on crowd control and combat, the other must solve environmental puzzles or cover the rear. The game tests your coordination in precise platforming, logic in solving riddles, and endurance in intense combat scenarios.

---

## ✨ Core Gameplay Mechanics
* **👥 Local Co-op:** Play with a friend on the same keyboard, requiring real-time communication.
* **🧱 Platforming & Physics:** Traverse dangerous Victorian environments with custom collision detection and jump mechanics.
* **🧩 Interactive Puzzles:** Environmental riddles that demand concurrent interaction from both players to progress.
* **👾 Combat System:** Engage distinct creatures with unique attack patterns and basic AI behaviors.
* **🌀 The Maze:** A navigational challenge testing spatial orientation before the final act.
* **⚔️ Wave Survival Mode:** An arena-based endurance test against progressively difficult enemy waves.
* **👹 Final Boss:** A multi-phase tactical battle requiring peak strategic coordination.

---

## 🛠️ Technical Implementation
* **Engine:** [Greenfoot](https://www.greenfoot.org/) (Powered by standard Java).
* **Architecture:** Strict Object-Oriented Programming (OOP) design, clearly separating `World` state management and `Actor` behaviors.
* **Concurrent Input Handling:** Custom control logic engineered to cleanly process simultaneous keyboard inputs for two independent actors without thread blocking or input ghosting.

---

## 🚀 How to Play

1. **Prerequisites:** Ensure you have the [Greenfoot IDE](https://www.greenfoot.org/download) installed.
2. **Download:** Clone this repository or download the `.zip` file.
3. **Open:** Launch Greenfoot and open the `project.greenfoot` file.
4. **Run:** Hit **Run** and call your partner in crime!

### 🕹️ Controls

| Action | Player 1 (Sherlock) | Player 2 (Watson) |
| :--- | :--- | :--- |
| **Movement** | `W`, `A`, `S`, `D` | Arrows `↑`, `←`, `↓`, `→` |
| **Attack** | `F` | `L` |
| **Jump** | `E` | `K` |

---

## 📁 Project Structure
* **Actors:** Specific modular classes for `Sherlock` and `Watson`, alongside polymorphic classes for `Enemies`, `Projectiles`, and `Obstacles`.
* **Worlds:** Structured level progression with distinct state classes: *Platforms -> Puzzles -> Maze -> Waves -> Boss*.

---

> *"The game is afoot, Watson! And this time, the clues are made of gunpowder and monsters"* 🔍🚀
