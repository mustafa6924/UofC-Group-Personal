/**
 * The abstract class Spaceship represents a generic spaceship entity in the galactic space.
 * It defines common attributes and behaviors for different types of spaceships.
 * @author Muhammad Mustafa Amer
 * @date 03/12/2024
 * email: muhammadmustafa.amer@ucalgary.ca
 * TA : Tejas Shrestha
 */
public abstract class Spaceship {

    // attributes:

    // The current coordinates of the spaceship
    private int x;
    private int y;

    // The unique identifier of the spaceship
    private String id;

    // The type of the spaceship
    private SpaceshipType spaceshipType;




    // methods:

    /**
     * Constructs a Spaceship object with the specified attributes.
     *
     * @param id, the id of the spaceship
     * @param x, x coordinate of the spaceship
     * @param y, y coordinate of the spaceship
     * @param spaceshipType type of spaceship
     */
    // constructor goes here...
    public Spaceship(String id, int x, int y, SpaceshipType spaceshipType) {
        this.x = x;
        this.y=y;
        this.id = id;
        this.spaceshipType = spaceshipType;
    }

    /**
     * Abstract method to define the movement behavior of the spaceship.
     *
     * @param galacticMap map that the spaceship is moving on
     */
    // move ....
    public abstract void move(GalacticMap galacticMap);


    /**
     * Abstract method to define the interaction behavior of the spaceship with another spaceship.
     *
     * @param galacticMap map the interaction is happening on
     * @param other Other SPaceship being interacted with
     */
    // interact ...
    public abstract void interact(GalacticMap galacticMap, Spaceship other);



    // Getters and setters

    /**
     * Retrieves the unique identifier of the spaceship.
     *
     * ....
     */
    // getID
    protected String getId() {
        return id;
    }

    /**
     * Retrieves the current x-coordinate of the spaceship.
     *
     * ....
     */
    // getX
    protected int getX() {
        return x;
    }

    /**
     * Retrieves the current y-coordinate of the spaceship.
     *
     * .....
     */
    // getY
    protected int getY() {
        return y;
    }

    /**
     * Retrieves the type of the spaceship.
     * <p>
     * .....
     */
    // getType
    protected SpaceshipType getType() {
        return spaceshipType;
    }
    /**
     * Sets the x-coordinate of the spaceship to the specified value.
     *
     * @param newX
     */
    // setX
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Sets the y-coordinate of the spaceship to the specified value.
     *
     * @param newY
     */
    // setY
    public void setY(int newY) {
        this.y = newY;
    }


    /**
     * Calculates the distance between this spaceship and another spaceship.
     *
     * @param other The other spaceship to calculate the distance to.
     * @return The distance between this spaceship and the other spaceship.
     */
    public int calculateDistance(Spaceship other) {
        int deltaX = Math.abs(this.getX() - other.getX());
        int deltaY = Math.abs(this.getY() - other.getY());
        return Math.max(deltaX, deltaY);
    }

    /**
     * Retrieves the name of the spaceship.
     *
     * @return The name of the spaceship, which includes its type and ID.
     */
    public String getName() {
        return this.getType() + " " + this.getId();
    }


}
