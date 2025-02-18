import java.util.Scanner;

public class MUDController {
    private Player player;
    private boolean running;

    public MUDController(Player player) {
        this.player = player;
        this.running = true;
    }

    public void runGameLoop() {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine();
            handleInput(input);
        }
        scanner.close();
    }

    private void handleInput(String input) {
        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String argument = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "look":
                lookAround();
                break;
            case "move":
                move(argument);
                break;
            case "pick":
                pickUp(argument);
                break;
            case "inventory":
                player.showInventory();
                break;
            case "help":
                showHelp();
                break;
            case "quit":
            case "exit":
                running = false;
                System.out.println("Exiting game...");
                break;
            default:
                System.out.println("Unknown command.");
        }
    }

    private void lookAround() {
        System.out.println("Room: " + player.getCurrentRoom().getDescription());
    }

    private void move(String direction) {
        Room nextRoom = player.getCurrentRoom().getExit(direction);
        if (nextRoom != null) {
            player.move(nextRoom);
            System.out.println("You moved " + direction + ".");
        } else {
            System.out.println("You can't go that way!");
        }
    }

    private void pickUp(String itemName) {
        System.out.println("No item named " + itemName + " here!");
    }

    private void showHelp() {
        System.out.println("Available commands:");
        System.out.println("look - Describes the current room.");
        System.out.println("move <forward|back|left|right> - Moves in a specified direction.");
        System.out.println("pick up <itemName> - Picks up an item from the ground.");
        System.out.println("inventory - Lists items the player is carrying.");
        System.out.println("help - Shows command usage.");
        System.out.println("quit/exit - Ends the loop.");
    }
}
