public class Plane {
    private static int nextId = 0;
    private final int id;
    private int distanceFromAirport; // Distance from the airport
    private boolean inEmergency; // Emergency status of the plane

    public Plane(int initialDistance) {
        this.id = nextId++;
        this.distanceFromAirport = initialDistance;
        this.inEmergency = false;
    }

    // Get the ID of the plane
    public int getId() {
        return id;
    }

    // Get the distance of the plane from the airport
    public int getDistanceFromAirport() {
        return distanceFromAirport;
    }

    // Set the distance of the plane from the airport
    public void setDistanceFromAirport(int distanceFromAirport) {
        this.distanceFromAirport = distanceFromAirport;
    }

    // Check if the plane is in an emergency
    public boolean isInEmergency() {
        return inEmergency;
    }

    // Set the emergency status of the plane
    public void setEmergency(boolean inEmergency) {
        this.inEmergency = inEmergency;
    }

    // Update the status of the plane for each time step
    public void updateStatus() {
        if (distanceFromAirport > 0) {
            distanceFromAirport--;
        }
    }

    @Override
    public String toString() {
        return "Plane{" +
               "ID=" + id +
               ", Distance=" + distanceFromAirport +
               ", Emergency=" + inEmergency +
               '}';
    }
}
