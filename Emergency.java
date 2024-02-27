public class Emergency {

    // Method to handle emergency scenarios for planes
    public static void handleEmergency(Plane plane, Airport airport) {
        System.out.println("Emergency declared for Plane " + plane.getId());
        plane.setEmergency(true);

        // Logic to handle the emergency situation
        // For example, move the plane to the front of the Ready to Land Queue
        airport.handleEmergencyLanding(plane);
    }
}
