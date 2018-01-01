package com.packtpublishing.tddjava.ch04ship;

public class Ship {

    private final Location location;
    private Planet planet;

    public Ship(Location location, Planet planet) {
        this.location = location;
        this.planet = planet;
    }

    public Location getLocation() {
        return location;
    }

    public Planet getPlanet() {
        return planet;
    }

    public boolean moveForward() {
        return location.forward(planet.getMax());
    }

    public boolean moveBackward() {
        return location.backward(planet.getMax());
    }

    public void turnLeft() {
        location.turnLeft();
    }

    public void turnRight() {
        location.turnRight();
    }

    public String receiveCommands(String commands) {
        StringBuilder output = new StringBuilder();
        for (char command : commands.toCharArray()) {
            boolean status = true;
            switch (command) {
                case 'f':
                    status = moveForward();
                    break;
                case 'b':
                    status = moveBackward();
                    break;
                case 'r':
                    turnRight();
                    break;
                case 'l':
                    turnLeft();
                    break;
            }
            if (status) {
                output.append("0");
            } else {
                output.append("X");
            }
        }
        return output.toString();
    }
}
