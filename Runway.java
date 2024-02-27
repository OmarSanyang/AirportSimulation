public class Runway {
    private boolean isOccupied;
    private int timeToFree;
    private final int landingTime = 5; // Time taken for a plane to land and clear the runway in minutes

    public Runway() {
        this.isOccupied = false;
        this.timeToFree = 0;
    }

    public void landPlane(Plane plane) {
        if (!isOccupied) {
            isOccupied = true;
            timeToFree = landingTime;
            System.out.println("Plane " + plane.getId() + " is landing on runway.");
        } else {
            System.out.println("Runway is currently occupied. Plane " + plane.getId() + " cannot land at the moment.");
        }
    }

    public void updateStatus() {
        if (isOccupied && timeToFree > 0) {
            timeToFree--;
            if (timeToFree == 0) {
                isOccupied = false;
                System.out.println("Runway is now free.");
            }
        }
    }

    public boolean isAvailable() {
        return !isOccupied;
    }
}
