package com.packtpublishing.tddjava.ch04ship;

import org.testng.annotations.*;

import java.util.List;
import java.util.ArrayList;

import static org.testng.Assert.*;

@Test
public class ShipSpec {

    private Ship ship;
    private Planet planet;
    private Location location;

    @BeforeMethod
    public void beforeTest() {
        Point max = new Point(50, 50);
        location = new Location(new Point(21, 13), Direction.NORTH);
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(44, 44));
        obstacles.add(new Point(45, 46));
        planet = new Planet(max, obstacles);
        ship = new Ship(location, planet);
    }

    public void whenInstantiatedThenLocationIsSet() {
        assertEquals(ship.getLocation(), location);
    }

    public void whenMoveForwardThenForward() {
        Location expected = location.copy();
        expected.forward();
        ship.moveForward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenMoveBackwardThenBackward() {
        Location expected = location.copy();
        expected.backward();
        ship.moveBackward();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnLeftThenLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.turnLeft();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenTurnRightThenRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.turnRight();
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsFThenForward() {
        Location expected = location.copy();
        expected.forward();
        ship.receiveCommands("f");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsBThenBackward() {
        Location expected = location.copy();
        expected.backward();
        ship.receiveCommands("b");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsRThenRight() {
        Location expected = location.copy();
        expected.turnRight();
        ship.receiveCommands("r");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenReceiveCommandsLThenLeft() {
        Location expected = location.copy();
        expected.turnLeft();
        ship.receiveCommands("l");
        assertEquals(ship.getLocation(), expected);
    }

    public void WhenReceiveCommandsThenAllAreExecuted() {
        Location expected = location.copy();
        expected.turnLeft();
        expected.forward();
        expected.forward();
        expected.turnRight();
        expected.turnRight();
        expected.backward();
        ship.receiveCommands("lffrrb");
        assertEquals(ship.getLocation(), expected);
    }

    public void whenInstantiatedThenPlanetIsStored() {
        assertEquals(ship.getPlanet(), planet);
    }

    public void whenOverpassEastBoundaryForwardThenWrap() {
        location.getPoint().setX(planet.getMax().getX());
        location.setDirection(Direction.EAST);
        ship.receiveCommands("f");
        assertEquals(location.getX(), 1);
    }

    public void whenOverpassWestBoundaryBackwardThenWrap() {
        location.getPoint().setX(1);
        location.setDirection(Direction.EAST);
        ship.receiveCommands("b");
        assertEquals(location.getX(), planet.getMax().getX());
    }

    public void whenReceiveCommandsThenStopOnObstacle() {
        List<Point> obstacles = new ArrayList<>();
        obstacles.add(new Point(location.getX() + 1, location.getY()));
        ship.getPlanet().setObstacles(obstacles);
        Location expected = location.copy();
        expected.turnRight();
        expected.forward(new Point(0, 0), new ArrayList<Point>());
        ship.receiveCommands("rf");
        assertEquals(ship.getLocation(), expected);
    }
}
