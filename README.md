# MagicalArenaSwiggy

Problem Statement:

Design a Magical Arena. Every Player is defined by a “health” attribute, “strength” attribute and an “attack” attribute - all positive integers. The player dies if his health attribute touches 0



## Overview
The project consists of the following components:

*  Main.java: This is the entry for application.

*  Audience.java:This is to update the status of match and give output. 

*  BattleContext.java: Orchestrates matches within the arena.

*  BattleMain.java: Entrypoint to the battles.

*  Player.Java:Players attributes are initiateda and updated here.

*  MainTesting.java, PlayerTest.java: Unit tests for corresponding classes.
## Getting Started
To run the Magical Arena simulation, follow these steps:

1. Clone or download the project repository to your local machine.
2. Open the project in your preferred Java IDE, such as Eclipse or IntelliJ IDEA.
## Running the Application

1. Locate the Main.java file in the project structure.
2. Run the main() method in Main.java to start the application.
3. Follow the prompts or input required parameters to simulate matches in the Magical Arena.
## Main Class 
Explanation:

* The Main class serves as the entry point for the application.
* In the main() method, it creates two players, playerA and playerB, with their respective attributes: health, strength, and attack.
* It then initiates a player as Observer usign Audience class.
* After that a battle is inittiated between both the players using BattleMain class and is started in BattleContext Class by checking Gamestatus.


       public class Main {
      public static void main(String[] args) {
        final TeamMember teamMemberA = new Player(100, 10, 5);
        final TeamMember teamMemberB = new Player(50, 5, 10);

        final GameObserver audience;
        if(teamMemberA.getHealth() < teamMemberB.getHealth())
            audience = new Audience("PlayerA", "PlayerB");
        else
            audience = new Audience("PlayerB", "PlayerA");

        final BattleMain battleMain = new BattleMain(teamMemberA, teamMemberB, audience);

        final BattleContext context = new BattleContext();

        context.handle(battleMain);
    }
}


## Player Class
Explaination:

1. Attributes:

The Player class encapsulates the attributes of a player in the Magical Arena. These attributes include:
* health: Represents the health points of the player.
* strength: Denotes the strength attribute of the player, which is used for defending against attacks.
* attack: Indicates the attack attribute of the player, used for inflicting damage on the opponent.
2. Constructor:

* The class has a constructor that initializes a Player object with specific values for health, strength, and attack.
* It sets the initial values of the player's attributes based on the parameters passed to the constructor.

        public class Player implements TeamMember {
        private final int initialHealth;
        private final int strength;
        private final int attackPower;
        private int health;
        private final Random random;

        public Player(final int initialHealth, final int strength, final int attackPower) {
        this.initialHealth = initialHealth;
        this.strength = strength;
        this.attackPower = attackPower;
        this.health = initialHealth;
        this.random = new Random();
      }

      @Override
      public int getHealth() {
       return health;
      }

      @Override
      public int getStrength() {
        return strength;
      }

      @Override
      public int getAttackPower() {
          return attackPower;
      }

      @Override
      public int rollDice() {
          return random.nextInt(6) + 1;
      }

      @Override
      public void reduceHealth(int damage) {
          health -= damage;
          if (health < 0) {
              health = 0; // Ensure health doesn't go below zero
          }
      }


      @Override
      public boolean isAlive() {
          return health > 0;
      }
  
      @Override
      public void useSpecialAbility(TeamMember opponent) {
        // Implement special ability logic
      }
  
      public int getInitialHealth() {
          return initialHealth;
        }
      }

         
## Match Class
Explaination:

1. Instance Variables:

* attacker: Represents the player who is currently attacking.
* defender: Represents the player who is currently defending.
* random: An instance of the Random class to generate random numbers for dice rolls.

2. Constructor:

* Initializes the Match object with three attributes players (player1 and player2) and observer.
* Determines the initial attacker and defender based on the players' health. The player with lower health becomes the attacker, and the other becomes the defender.

3. startBattle() Method:

* Simulates the progression of the match until one of the players' health drops to 0 or below.
* Inside a loop, it rolls dice for attack and defense using Random.nextInt(6) + 1 to simulate a 6-sided die.
* Calculates attack and defense damages based on dice rolls and player attributes.
* Updates the defender's health by deducting the net damage after defense.
* Switches roles for the next turn, ensuring that the attacker and defender alternate.


        package main.java.com.swiggy.magicalarena.code.classes;
      import main.java.com.swiggy.magicalarena.code.interfaces.*;
      import java.util.Random;

      public class BattleMain {
      private final TeamMember teamMemberA;
      private final TeamMember teamMemberB;
      private final GameObserver observer;

      public BattleMain(final TeamMember teamMemberA, final TeamMember   teamMemberB, final GameObserver observer) {
        this.teamMemberA = teamMemberA;
        this.teamMemberB = teamMemberB;
        this.observer = observer;
        Random random = new Random();
      }

      public void startBattle() {
        TeamMember attacker = teamMemberA.getHealth() < teamMemberB.getHealth() ? teamMemberA : teamMemberB;
        TeamMember defender = attacker == teamMemberA ? teamMemberB : teamMemberA;

        while (teamMemberA.isAlive() && teamMemberB.isAlive()) {
            int attackRoll = attacker.rollDice();
            int defenseRoll = defender.rollDice();

            int damage = (attackRoll * attacker.getAttackPower()) - (defenseRoll * defender.getStrength());

            if (damage > 0) {
                defender.reduceHealth(damage);
            }

            observer.update(attacker, defender, damage);

            // Swap attacker and defender
            TeamMember temp = attacker;
            attacker = defender;
            defender = temp;
        }


    }
}




## How to Run Unit Tests

1. Navigate to the test directory:

   ```
   cd src/test/java/com/swiggy/magicalarena/testing
   ```

2. Run the `PlayerTest.java` and `MainTesting.java` files as JUnit test cases to test the functionality of player classes and game mechanics.
