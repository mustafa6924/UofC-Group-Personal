import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Test {

    /** Tests for FighterShip.move()
     *
     */
    @org.junit.jupiter.api.Test
    public void FighterShipMove1() {
        GalacticMap galacticMap = new GalacticMap(5);
        FighterShip ship = new FighterShip("f1342", 0, 0, 0);
        int y = ship.getY();
        int x = ship.getX();

        ship.move(galacticMap);
        assertEquals(x, ship.getX());
        assertEquals(y, ship.getY());
    }

    @org.junit.jupiter.api.Test
    public void FighterShipMove2() {
        GalacticMap map = new GalacticMap(5);
        FighterShip ship = new FighterShip("f2362", 3, 4, 10);

        ship.move(map);
        assertEquals(3, ship.getX());
    }

    /** Tests for FighterShip.interact()
     *
     */
    @org.junit.jupiter.api.Test
    public void FighterShipInteract1() {
        GalacticMap map = new GalacticMap(10);
        Spaceship fighter = new FighterShip("f2222", 3, 3, 10);
        Spaceship explorer = new ExplorerShip("e2222", 4, 3, 0);
        fighter.interact(map, explorer);

        assertEquals(3, fighter.getX());
    }

    @org.junit.jupiter.api.Test
    public void FighterShipInteract2() {
        GalacticMap map = new GalacticMap(10);
        Spaceship fighter = new FighterShip("f2222", 3, 3, 10);
        Spaceship cargo = new CargoShip("c2222", 4, 3, 0, 0, 0, 0);
        fighter.interact(map, cargo);

        assertEquals(3, fighter.getX());
    }

    /**
     * Tests for ExplorerShip.move()
     */
    @org.junit.jupiter.api.Test
    public void ExplorerShipMove1() {
        GalacticMap map = new GalacticMap(10);
        Spaceship explorer = new ExplorerShip("e2222", 3, 6, 10);
        explorer.move(map);
        assertEquals(3, explorer.getX());

    }

    @org.junit.jupiter.api.Test
    public void ExplorerShipMove2() {
        GalacticMap map = new GalacticMap(2);
        Spaceship explorer = new ExplorerShip("e2222", 0, 0, 10);
        explorer.move(map);
        assertEquals(0, explorer.getY());
    }

    /**
     * Tests for GalacticMap.toString()
     */
    @org.junit.jupiter.api.Test
    public void toString1() {
        GalacticMap map = new GalacticMap(2);
        String expectedString = "[      ][      ]\n" + "[      ][      ]\n" ;

        assertEquals(expectedString, map.toString());
    }

    @org.junit.jupiter.api.Test
    public void toString2() {
        GalacticMap map = new GalacticMap(2);
        String expectedString = "[][]\n" + "[][]\n" ;

        assertNotEquals(expectedString, map.toString());
    }

    @org.junit.jupiter.api.Test
    public void toString3() {
        GalacticMap map = new GalacticMap(4);
        String expectedString = "[      ][      ]\n" + "[      ][      ]\n" ;

        assertNotEquals(expectedString, map.toString());
    }

    /**
     * Tests for FileReader.readfromfile()
     */
    @org.junit.jupiter.api.Test
    public void readFromFile1() {
        GalacticMap map = FileReader.readFromFile("config.txt");
        assertNotNull(map);
    }

    /**
     * Test for ExplorerShip.interact()
     */
    @org.junit.jupiter.api.Test
    public void ExplorerShipInteract1() {
        GalacticMap map = new GalacticMap(10);
        Spaceship explorer = new ExplorerShip("e2222", 4, 5, 10);
        Spaceship fighter = new ExplorerShip("f2222", 5, 6, 0);
        explorer.interact(map, fighter);
        Scanner s = new Scanner(System.out.toString());
        String TestString = s.nextLine();

        assertNotEquals("Found: " + fighter.getId() + " at distance: " + 2, TestString);
    }

    @org.junit.jupiter.api.Test
    public void ExplorerShipInteract2() {
        GalacticMap map = new GalacticMap(10);
        Spaceship explorer = new ExplorerShip("e2222", 1, 2, 2);
        Spaceship fighter = new FighterShip("f2222", 8, 9, 2);
        Scanner s = new Scanner(System.out.toString());
        String TestString = s.nextLine();
        explorer.interact(map, fighter);

        assertNotEquals("Spaceship: " + fighter.getType() + " " + fighter.getId() + " is not in scan-range", TestString);
    }


}
