import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.HashSet;
import java.util.Random;

/**
 * The GalacticMap class represents the grid-based map of the galactic space.
 * It stores information about the positions of spaceships and provides methods
 * for managing the entities within the map.
 *
 * <p>This class contains functionality to add fighters, retrieve a list of reported spaceships,
 * and check three game winning conditions for the galactic space.</p>
 *
 * @author Muhammad Mustafa Amer
 * @date 03/12/2024
 * email: muhammadmustafa.amer@ucalgary.ca
 * TA : Tejas Shrestha
 */
public class GalacticMap {
    private Spaceship[][] grid; // 2D array representing the grid of the galactic map
    private int fighterNumber = 0; // Counter for the number of fighters in the map
    private HashSet<Spaceship> reportList = new HashSet<Spaceship>(); // Set to store reported fighter spaceships

    /**
     * Constructs a GalacticMap object with the specified size.
     *
     * @param size The size of the grid for the galactic map.
     */
    public GalacticMap(int size) {
        this.grid = new Spaceship[size][size];
    }

    /**
     * Adds a fighter spaceship to the report list.
     *
     * @param fighter The fighter spaceship to add to the report list.
     */
    public void AddReportedFighter(FighterShip fighter) {
        reportList.add(fighter);
    }

    /**
     * Adds one to the count of fighters in the GalacticMap.
     */
    public void addOneFighter() {
        fighterNumber++;
    }

    /**
     * Retrieves the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to retrieve the spaceship.
     * @param y The y-coordinate of the position to retrieve the spaceship.
     * @return The spaceship at the specified coordinates.
     */
    public Spaceship getSpaceshipAt(int x, int y) {
        return this.grid[x][y];
    }

    /**
     * Retrieves a random spaceship from the GalacticMap.
     *
     * @return A random spaceship from the GalacticMap.
     */
    public Spaceship getRandomSpaceship() {
        Random random = new Random();
        Spaceship randomSpaceship = null;

        // Keep generating random coordinates until a non-null grid cell is found
        while (randomSpaceship == null) {
            int randomX = random.nextInt(grid.length);
            int randomY = random.nextInt(grid[0].length);
            randomSpaceship = grid[randomX][randomY];
        }
        return randomSpaceship;
    }

    /**
     * Returns a string representation of the GalacticMap.
     *
     * ...............
     *
     */
    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        //created new String builder object
        for(int row = 0; row < grid.length; row++){
            for(int col = 0; col < grid.length; col++){
                //iterating through both rows and columns in the grid
                Spaceship ship = grid[row][col];
                if(ship != null){
                    //checking if the ship object created isn't null
                    string.append("[  ");
                    string.append(ship.getType().toString().charAt(0));
                    string.append(ship.getId());
                    string.append("  ]");
                }else{
                    //if ship object is not empty/null it will append the following
                    string.append("[      ]");
                }
            }
            string.append("\n");
        }
        return string.toString();
    }

    /**
     * Removes the spaceship at the specified coordinates in the GalacticMap.
     *
     * @param x The x-coordinate of the position to remove the spaceship.
     * @param y The y-coordinate of the position to remove the spaceship.
     */
    public void removeSpaceshipAt(int x, int y) {
        this.grid[x][y] = null;
    }

    /**
     * Moves the specified spaceship to the new coordinates in the GalacticMap.
     * @param spaceship returns all the details of the spaceship being moved
     * @param newX new value of x for the spaceship to move to
     * @param newY new value of y for th spaceship to move to
     */
    public void moveSpaceshipTo(Spaceship spaceship, int newX, int newY) {
        // Stores the previous values of x and y of the spaceship
        int previousX = spaceship.getX();
        int previousY = spaceship.getY();
        // Checks if the new values are in bound
        if (isValidMove(newX, newY) == false) {
            System.out.println("Moving Failed! out of bound x or y!");
        }else if (isCollision(newX, newY)) {
            System.out.println("Moving Failed! the position is filled with another spaceship!");
        }else {
            // Sets the value of the old position of the spaceship value to null and puts the spaceship to the new position
            removeSpaceshipAt(previousX, previousY);
            // Sets the new value of x and y to the spaceship to where it will move
            spaceship.setX(newX);
            spaceship.setY(newY);
            grid[newY][newX] = spaceship;
            System.out.println("Updates Configuration:");
            System.out.println(toString());
        }
    }
    /**
     * Checks if the specified coordinates represent a valid move within the GalacticMap grid.
     * @param newX The x value to check of the new position
     * @param newY the y value to check of the new position
     * ............
     *
     */
    public boolean isValidMove(int newX, int newY) {
        // Check if the new position is within the grid boundaries
        if (newX >= 0 && newX <= grid.length && newY >= 0 && newY <= grid.length) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified coordinates represent a collision with another spaceship.
     *@param newX The x value to check of the new position
     *@param newY the y value to check of the new position
     * .......
     *
     */
    public boolean isCollision(int newX, int newY) {
        // Check if the new position is occupied by another spaceship
        if (grid[newY][newX] != null) {
            return true;
        }
        return false;
    }

    /**
     * Places the specified spaceship, that is read from the file, in the GalacticMap.
     * @param spaceship The spaceship that has to be placed
     * .............
     *
     */
    public void placeSpaceship(Spaceship spaceship) {
        if (isValidMove(spaceship.getX(), spaceship.getY()) && !isCollision(spaceship.getX(), spaceship.getY())) {
            // Place the spaceship in its position
            grid[spaceship.getY()][spaceship.getX()] = spaceship;
        // Checks if the x and y values given are out bounds of the array
        }else if (!isValidMove(spaceship.getX(), spaceship.getY())) {
            throw new ArrayIndexOutOfBoundsException("Wrong input file! position is outside of the map");
        // Checks if the spaceship is colliding with another
        }else if (isCollision(spaceship.getX(), spaceship.getY())) {
            throw new IllegalArgumentException("Wrong input file! the position is filled with another item!");
        }
    }

    /**
     * Checks if all cargoes have reached their destinations.
     *
     * ..............
     *
     */
    public boolean allCargoesReachedDestination() {
        boolean isCargo = false;
        for (Spaceship[] x : grid) {
            for (Spaceship spaceObject : x) {
                if (spaceObject instanceof CargoShip) {
                    isCargo = true;
                    CargoShip ship = (CargoShip) spaceObject;
                    if (!ship.isReachedDestination()) {
                        return false;
                    }
                }
            }
        }
        return isCargo;
    }


    /**
     * Checks if all explorers and cargoes have been removed by fighters.
     *
     * ............
     *
     */
    public boolean allExplorersAndCargoesRemoved() {
        for (Spaceship[] i : grid) {
            for (Spaceship Spaceshipobject : i) {
                if (!(Spaceshipobject instanceof CargoShip)) {
                    return true;
                }else if (!(Spaceshipobject instanceof ExplorerShip)) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Checks if all fighters have been reported by explorers.
     *
     * ................
     *
     */
    public boolean allFightersReported() {
        // Check if explorers have interacted/reported all fighters
        for (Spaceship[] i : grid) {
            for (Spaceship spaceship : i) {
                if (fighterNumber != 0) {
                    if (reportList.size() == fighterNumber) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
