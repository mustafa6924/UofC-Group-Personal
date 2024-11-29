/**
 * The CargoShip class represents a spaceship specialized in transporting cargo within the galactic space.
 * It inherits from the Spaceship class and defines specific movement, interaction, and cargo management behaviors.
 * @author Muhammad Mustafa Amer
 * @date 03/12/2024
 * email: muhammadmustafa.amer@ucalgary.ca
 * TA : Tejas Shrestha
 */
public class CargoShip extends Spaceship {

    private double cargoCapacity; // Maximum cargo capacity of the CargoShip
    private double currentCargo;  // Current amount of cargo on the CargoShip

    private int targetX; // The x-coordinate of the cargo ship's destination.
    private int targetY; // The y-coordinate of the cargo ship's destination.

    private boolean reachedDestination; // Indicates if the cargo has reached its destination

    /**
     * Constructs a CargoShip object with the specified attributes.
     *
     * @param id            The unique identifier of the cargo ship.
     * @param x             The initial x-coordinate of the cargo ship.
     * @param y             The initial y-coordinate of the cargo ship.
     * @param cargoCapacity The maximum cargo capacity of the cargo ship.
     * @param currentCargo  The current amount of cargo on the cargo ship.
     * @param targetX       The x-coordinate of the cargo ship's destination.
     * @param targetY       The y-coordinate of the cargo ship's destination.
     */
    public CargoShip(String id, int x, int y, double cargoCapacity, double currentCargo, int targetX, int targetY) {
        // Initialize CargoShip attributes properly
        super(id, x, y, SpaceshipType.CARGOSHIP);
        this.cargoCapacity = cargoCapacity;
        this.currentCargo = currentCargo;
        this.reachedDestination = isReachedDestination();
        this.targetX = targetX;
        this.targetY = targetY;
    }

    /**
     * Implements the movement behavior of the cargo ship within the galactic map.
     * The cargo ship moves towards its designated target coordinates.
     * @param galacticMap The map that it will move on
     * ....................
     */
    @Override
    public void move(GalacticMap galacticMap) {

        // Print message indicating movement is occurring
        System.out.print("........Moving.......");

        // CargoShip-specific movement logic
        Spaceship ship = new CargoShip(getId(), getX(), getY(), this.cargoCapacity, this.currentCargo, this.targetX, this.targetY);
        int xvalue = ship.getX(); // Get the current X-coordinate of the ship
        int yvalue = ship.getY(); // Get the current Y-coordinate of the ship
        int newX = 0; // Variable to store the new X-coordinate
        int newY = 0; // Variable to store the new Y-coordinate

        // Check if the ship hasn't reached its destination yet
        if (!reachedDestination) {
            // Move towards the target X-coordinate
            if (xvalue < targetX) {
                newX = xvalue + 1; // Move one unit to the right
                newY = yvalue;
                galacticMap.moveSpaceshipTo(ship, newX, newY); // Move the ship to the new position
                System.out.println("Move Configuration:"); // Print message indicating move configuration
                System.out.println(galacticMap.toString()); // Print the updated GalacticMap
            } else if (xvalue > targetX) {
                newX = xvalue - 1; // Move one unit to the left
                newY = yvalue;
                galacticMap.moveSpaceshipTo(ship, newX, newY);
                System.out.println("Move Configuration:");
                System.out.println(galacticMap.toString());
            } else if (yvalue < targetY) { // Move towards the target Y-coordinate
                newX = xvalue;
                newY = yvalue + 1; // Move one unit down
                galacticMap.moveSpaceshipTo(ship, newX, newY);
                System.out.println("Move Configuration:");
                System.out.println(galacticMap.toString());
            } else if (yvalue > targetY) {
                newX = xvalue;
                newY = yvalue - 1; // Move one unit up
                galacticMap.moveSpaceshipTo(ship, newX, newY);
                System.out.println("Move Configuration:");
                System.out.println(galacticMap.toString());
            }
        }

        // Check if the ship has reached its destination
        if (xvalue == targetX && yvalue == targetY) {
            reachedDestination = true; // Set reachedDestination flag to true
        }

        // Print message indicating the ship is already in destination if reachedDestination is true
        if (reachedDestination) {
            System.out.println("CargoShip: " + ship.getId() + " is already in destination");
        }
    }


    /**
     * Implements the interaction behavior of the cargo ship with another spaceship.
     * The cargo ship can exchange cargo with other cargo ships during interaction.
     * @param galacticMap The map where the interaction occurs
     * @param other The spaceship that it is going to interact with
     * ............
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {

        // Print message indicating interaction is occurring
        System.out.println(".........interacting...........with.... " + other.getName());

        // CargoShip interaction logic
        // Check if the other spaceship is a FIGHTER or EXPLORER type
        if (other.getType() == SpaceshipType.FIGHTER || other.getType() == SpaceshipType.EXPLORER) {
            // Print message indicating CargoShip cannot interact with FIGHTER or EXPLORER
            System.out.println("CargoShip cannot interact with " + other.getType());
        }
        // Check if the other spaceship has the same ID as the current CargoShip
        else if (other.getId() == getId()) {
            // Print message indicating CargoShip cannot interact with itself
            System.out.println("CargoShip cannot interact with itself");
        }
        // Check if the other spaceship is a CARGOSHIP type
        else if (other.getType() == SpaceshipType.CARGOSHIP) {
            // CargoShip-specific interaction logic can be added here if needed
        }
    }


    /**
     * Loads cargo onto the CargoShip up to its maximum capacity.
     *
     * ...........
     */
    public void loadCargo(double cargoAmount) {

    }

    /**
     * Unloads cargo from the CargoShip.
     *
     * ..............
     */
    public void unloadCargo(double cargoAmount) {

    }

    /**
     * Retrieves the current amount of cargo on the CargoShip.
     *
     * @return The current cargo amount.
     */
    public double getCurrentCargo() {
        return currentCargo;
    }

    /**
     * Retrieves the x-coordinate of the CargoShip's destination.
     *
     * @return The x-coordinate of the destination.
     */
    public int getTargetX() {
        return targetX;
    }

    /**
     * Retrieves the y-coordinate of the CargoShip's destination.
     *
     * @return The y-coordinate of the destination.
     */
    public int getTargetY() {
        return targetY;
    }

    /**
     * Sets the status of whether the CargoShip has reached its destination.
     *
     * @param b True if the CargoShip has reached its destination, false otherwise.
     */
    public void setReachedDestination(boolean b) {
        reachedDestination = b;
    }

    /**
     * Checks if the CargoShip has reached its destination.
     *
     * @return True if the CargoShip has reached its destination, false otherwise.
     */
    public boolean isReachedDestination() {
        return reachedDestination;
    }
}

