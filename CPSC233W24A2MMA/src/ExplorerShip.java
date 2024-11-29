/**
 * The ExplorerShip class represents a spaceship specialized in exploration within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Muhammad Mustafa Amer
 * @date 03/12/2024
 * email: muhammadmustafa.amer@ucalgary.ca
 * TA : Tejas Shrestha
 */
public class ExplorerShip extends Spaceship {
    private int scanRange; // The range within which the explorer ship can scan for nearby spaceships
    private boolean moveHorizontally = true; // Flag to track horizontal movement

    /**
     * Constructs an ExplorerShip object with the specified attributes.
     *
     * @param id        The unique identifier of the explorer ship.
     * @param x         The initial x-coordinate of the explorer ship.
     * @param y         The initial y-coordinate of the explorer ship.
     * @param scanRange The range within which the explorer ship can scan for nearby spaceships.
     */
    public ExplorerShip(String id, int x, int y, int scanRange) {
        super(id, x, y, SpaceshipType.EXPLORER);
        this.scanRange = scanRange;
    }


    /**
     * Implements the movement behavior of the explorer ship within the galactic map.
     * The explorer ship moves in a zigzag pattern, alternating between horizontal and vertical movements.
     * @param galacticMap the map the ship will move on
     * .........
     *
     */
    @Override
    public void move(GalacticMap galacticMap) {
        // Print message indicating movement is occurring
        System.out.print("........Moving.......");

        // Implementation for explorer ship movement
        boolean horizontalprevious = false; // Flag to track if the previous movement was horizontal
        boolean verticalprevious = false; // Flag to track if the previous movement was vertical
        Spaceship ship = new ExplorerShip(getId(), getX(), getY(), this.scanRange); // Create a new ExplorerShip instance
        int previousX = ship.getX(); // Get the previous X-coordinate
        int previousY = ship.getY(); // Get the previous Y-coordinate
        int newX = 0; // Variable to store the new X-coordinate
        int newY = 0; // Variable to store the new Y-coordinate

        // Check if the previous movement was not horizontal
        if (!horizontalprevious) {
            newX = previousX;
            newY = previousY + 1; // Move one unit to the right (horizontal movement)
            galacticMap.moveSpaceshipTo(ship, newX, newY); // Move the ship to the new position
            System.out.println("Move Configuration");
            System.out.println(galacticMap.toString()); // Print the updated GalacticMap using toString()
            horizontalprevious = true; // Set horizontalprevious flag to true
            verticalprevious = false; // Reset verticalprevious flag to false
        } else if (!verticalprevious) { // Check if the previous movement was not vertical
            newX = previousX + 1;
            newY = previousY; // Move one unit down (vertical movement)
            galacticMap.moveSpaceshipTo(ship, newX, newY); // Move the ship to the new position
            System.out.println("Move Configuration");
            System.out.println(galacticMap.toString()); // Print the updated GalacticMap using toString()
            verticalprevious = true; // Set verticalprevious flag to true
            horizontalprevious = false; // Reset horizontalprevious flag to false
        }
        // they move in a zigzag pattern, alternating between horizontal and vertical movements.
    }




    /**
     * Implements the interaction behavior of the explorer ship with another spaceship.
     * The explorer ship reports nearby spaceships found within its scan range during interaction.
     * @param galacticMap the map that it will interact on
     * @param other the spaceship it will interact with
     * ......
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        // Print message indicating interaction is occurring
        System.out.println(".........interacting...........with.... " + other.getName());

        // Create a new ExplorerShip instance with the same coordinates and scan range as the current ship
        Spaceship ship = new ExplorerShip(getId(), getX(), getY(), scanRange);

        // Calculate the distance between the current ship and the other ship
        int distance = ship.calculateDistance(other);

        // Check if the other ship is within the scan range
        if (distance <= scanRange) {
            // Print message indicating the other ship is found within scan range
            System.out.println("Found: " + other.getId() + " at distance: " + distance);
        } else if (distance > scanRange) {
            // Print message indicating the other ship is not within scan range
            System.out.println("Spaceship: " + other.getType() + " " + other.getId() + " is not in scan-range");
        } else if (other.getId().equals(ship.getId())) {
            // Print message indicating the ship cannot interact with itself
            System.out.println("the spaceship cannot interact with itself");
        }
    }

}
