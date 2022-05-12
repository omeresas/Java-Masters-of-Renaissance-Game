## Masters of Renaissance Multiplayer Card Game

This repository contains the code and documentation artifacts for the Java implementation of the Masters of Renaissance: Lorenzo il Magnifico card game. The project has been implemented by myself and another international student to be the graduation project of PoliMi computer science and engineering undergraduate programme in academic year 2020-2021, which I have been given as an extra compulsory course given the fact that I started my MSc programme with a non-cs (electronics) undergraduate degree.

### About the Game

The game is a multiplayer card game that can be played by up to 4 people. Each player have different kinds of resources and different kinds of cards. Players take turns to execute their selection of actions. There are different kinds of situations that triggers the end of the game, and the winner is chosen according a specific point-counting system.

- For the official webiste of the game, refer to [https://craniointernational.com/products/masters-of-renaissance/](https://craniointernational.com/products/masters-of-renaissance/)

- For the entire set of rules on how to play the game, refer to the [rulebook](Masters-of-Renaissance-Rules.pdf)
- For a short video tutorial of the game, refer to [https://youtu.be/0DaaO49X4xw](https://youtu.be/0DaaO49X4xw)
- For a complete playthrough of two players, refer to [https://youtu.be/PqQhw71V2R0](https://youtu.be/PqQhw71V2R0)

### Below is Old

- [Features implemented](#features-implemented)
- [How to play the game](#how-to-play-the-game)
  - [Set up of the shell and information about color](#Set-up-of-the-shell-and-information-about-color)
- [Building](#building)
  - [Using provided jars](#using-provided-jars)
- [Server](#server)
  - [Run your server](#run-your-server)
- [Client](#client)
- [Documentation](#documentation)
  - [UML diagrams](#uml-diagrams)
- [External libraries used](#external-libraries-used)
- [Authors (alphabetical order)](#authors-alphabetical-order)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Features implemented

| Functionality  |
| :------------- |
| Basic rules    |
| Complete rules |
| CLI            |

## How to play the game

1. First step : run the server. The port number is 30000,and it is hardcoded in the server class.

2. Second Step : run the first client. If you play:

   - locally the ip of the router is "localhost" while port number as written before.
   - on a different network you should use as ip the public of your PC.

3. The view of the first player should ask name and number of the player than wait till there are enough people.

4. (for example if there are 2 player) after the connection of the second player and set up of the name, randomly the server assign a turn order of the player

5. Then the server send to the player 4 leader card and ask to choose two of them. After the choice the first player received the menu of the action while the second player choose a free resource w.r.t. game's rule

### Set up of the shell and information about color

To have a better visualization of the personal board faith track you should scale down the shell's zoom.
The stone color inside the market tray is black while inside all the other stuff of the game the stone color is white

## Building

### Using provided jars

If you want you can use the jars we already built for you, you can find them [here](/deliveries/final/Jar)

## Server

### Run your server

To run the server on your machine you can use the following command, this is the easiest way to run it

```bash
java -jar Server.jar
```

## Client

To run the client you can use the following command, this is the easiest way to run it

```bash
java -jar Client.jar
```

## Documentation

### UML diagrams

UML diagrams are [here](/deliveries/final/UML)

### Sequence Diagrams

The final sequence diagrams are [here](/deliveries/final/UML/sequenceDiagram)

## Gameplay screenshots

Gameplay screenshots are in this folder [here](/deliveries/final/screenShotOfTheGame)

## External libraries used

| Library | Link                           |
| ------- | ------------------------------ |
| GSON    | https://github.com/google/gson |

## Authors (alphabetical order)

- [ Mohamed Elsheshtwai ]
- [ Omer Esas ]
- [ Amor Madhkour ]
- [ Lorenzo Mauri]
