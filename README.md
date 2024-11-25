# Growham

Growham is your virtual pet or tamagotchi. It is an application where you can raise a virtual hamster, give him a name, and change the atmosphere of his home.

## Gameplay

- **Naming Your Pet**: First, you give your virtual pet a name, after which the game begins.
- **Health and Hunger**: You need to monitor your pet's health and hunger so that it does not die.
- **Store**: To meet his needs, you can go to the store and use your means to buy pet food or medicine.
- **Cash Flow**: Cash is replenished by accruing a fixed amount every day.
- **Room Upgrades**: You can also buy an improvement for your virtual pet’s room, which will give him a one-time increase in health.
- **Sleep and Charge Buttons**:
  1. **Sleep**: During sleep, your pet will slightly increase health and hunger.
  2. **Charge**: After using the charge button, the pet will increase health and hunger will decrease.
- **End of Life**: If you have not kept track of the pet or if it has reached an advanced age, you will be shown a table with indicators of the longest life expectancy for various pets.

## Project Code Overview

### Core Module

The core module contains platform-independent code that can be reused across different platforms.

- **Main Class (`Main.java`)**: The entry point of the game where the main setup and game loop are managed.
- **Hamster Class (`Hamster.java`)**: Manages the properties and behaviors of the virtual hamster, such as health, hunger, age, and various states.
- **Information Class (`Information.java`)**: Displays the hamster's health, hunger, age, and money on the screen. Includes the `setInfo` method to update these values.
- **Sleep Class (`Sleep.java`)**: Handles the logic for when the hamster sleeps, including the effects on health and hunger.
- **Background Class (`MyBackground.java`)**: Manages the background elements of the game, enhancing the visual experience.

### Android Module

The Android module contains Android-specific code and configurations.

- **AndroidLauncher Class (`AndroidLauncher.java`)**: The entry point for the Android application, setting up the necessary configurations to run the game on Android devices.

### Tools and Libraries

- **LibGDX Framework**: Used for game development, providing tools and libraries for graphics rendering, input handling, and more.
- **Box2D**: Used for creating 2D objects within the game, without incorporating physics.
- **Freetype**: Library used for font rendering to ensure clear text display.

This modular architecture allows for a clear separation between platform-independent and platform-specific code, making the project more maintainable and scalable.

---

Date: 22-04-2020
