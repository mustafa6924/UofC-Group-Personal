import java.util.Random;
/**
 * The FighterShip class represents a spaceship specialized in combat within the galactic space.
 * It inherits from the Spaceship class and defines specific movement and interaction behaviors.
 * @author Muhammad Mustafa Amer
 * @date 03/12/2024
 * email: muhammadmustafa.amer@ucalgary.ca
 * TA : Tejas Shrestha
 */
public class FighterShip extends Spaceship {
    private int damage;// The damage inflicted by the fighter ship during combat

    /**
     * Constructs a FighterShip object with the specified attributes.
     *
     * @param id     The unique identifier of the fighter ship.
     * @param x      The initial x-coordinate of the fighter ship.
     * @param y      The initial y-coordinate of the fighter ship.
     * @param damage The damage inflicted by the fighter ship during combat.
     */
    public FighterShip(String id, int x, int y, int damage) {
        // Initialize FighterShip attributes
        super(id, x, y, SpaceshipType.EXPLORER);
        this.damage = damage;
    }

    /**
     * Generates a random direction for the fighter ship to move.
     *
     * @return A random integer representing the direction (0 to 7).
     */
    private int getRandomDirection() {
        Random random = new Random();
        return random.nextInt(8); // Generates a random integer between 0 and 7
    }

    /**
     * Implements the movement behavior of the fighter ship within the galactic map.
     * The movement of the fighter ship is random, as it changes direction randomly.\
     * @param galacticMap the map it will move on
     * ..............
     *
     */
    @Override
    public void move(GalacticMap galacticMap) {
        System.out.print("........Moving.......");
        // Implementation for fighter ship movement
        Spaceship ship = new FighterShip(super.getId(), super.getX(), super.getY(), damage);
        int previousX = ship.getX();
        int previousY = ship.getY();
        int direction = getRandomDirection();
        int newX = 0;
        int newY = 0;

        if (direction == 0) {
            newX = previousX;
            newY = previousY - 1;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }else if (direction == 1) {
            newX = previousX;
            newY = previousY + 1;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }else if (direction == 2) {
            newX = previousX - 1;
            newY = previousY;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }else if (direction == 3) {
            newX = previousX + 1;
            newY = previousY;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }else if (direction == 4) {
            newX = previousX + 1;
            newY = previousY + 1;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }else if (direction == 5) {
            newX = previousX - 1;
            newY = previousY + 1;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }else if (direction == 6) {
            newX = previousX + 1;
            newY = previousY - 1;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }else if (direction ==  7) {
            newX = previousX + 1;
            newY = previousY;
            galacticMap.moveSpaceshipTo(ship, newX, newY);
            System.out.println("Move Configuration:");
            System.out.println(galacticMap.toString());
        }

    }

    /**
     * Implements the interaction behavior of the fighter ship with another spaceship.
     * The fighter ship engages in combat with other spaceships during interaction.
     * @param galacticMap The map it will interact on
     * @param other The spaceship it will interact with
     * .........
     *
     */
    @Override
    public void interact(GalacticMap galacticMap, Spaceship other) {
        System.out.println(".........interacting...........with.... " + other.getName());
        // Implementation for fighter ship interaction (e.g., combat)
        Spaceship ship = new FighterShip(super.getId(), super.getX(), super.getY(), damage);
        int distance = ship.calculateDistance(other);

        if (other.getType() == SpaceshipType.EXPLORER || other.getType() == SpaceshipType.CARGOSHIP) {
            if (this.damage < distance) {
                System.out.println("damage is less than distance");
            }else {
                int otherX = other.getX();
                int otherY = other.getY();
                galacticMap.removeSpaceshipAt(other.getX(), other.getY());
                galacticMap.moveSpaceshipTo(ship, other.getX(), other.getY());
                System.out.println("Interaction Configuration");
                System.out.println(galacticMap.toString());
                System.out.println("FIGHTER " + ship.getId() + " destroyed spaceship: " + "EXPLORER " + other.getId());

            }
        }else if (other.getType() == SpaceshipType.FIGHTER) {
            System.out.println("Fighters do not fight fighters!");
        }
    }
}

