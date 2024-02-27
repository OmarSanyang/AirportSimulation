import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Application {
    private static final int MAX_DISTANCE = 10;
    private static final double PROBABILITY_OF_NEW_PLANE = 0.5;
    private static final double PROBABILITY_OF_EMERGENCY = 0.1;

    public static void main(String[] args) {
        Airport airport = new Airport();
        List<Runway> runways = new ArrayList<>();
        runways.add(new Runway()); // Add more runways as needed
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int timeStep = 0;

        while (true) {
            System.out.println("Time Step: " + timeStep);
            airport.getApproachQueue().forEach(Plane::updateStatus);
            if (random.nextDouble() < PROBABILITY_OF_NEW_PLANE) {
                Plane newPlane = new Plane(random.nextInt(MAX_DISTANCE) + 1);
                airport.addPlaneToApproach(newPlane);
                System.out.println("New plane added: " + newPlane);
            }

            airport.updateQueues();
            for (Runway runway : runways) {
                if (runway.isAvailable()) {
                    Plane planeToLand = airport.getNextPlaneToLand();
                    if (planeToLand != null) {
                        runway.landPlane(planeToLand);
                    }
                }
                runway.updateStatus();
            }

            if (random.nextDouble() < PROBABILITY_OF_EMERGENCY) {
                Plane emergencyPlane = airport.selectPlaneForEmergency();
                if (emergencyPlane != null) {
                    airport.handleEmergencyLanding(emergencyPlane);
                }
            }

            System.out.println(airport.getQueueStatus());
            System.out.println("Press Enter for next time step, type 'exit' to end simulation.");
            String input = scanner.nextLine();
            if ("exit".equalsIgnoreCase(input)) {
                break;
            }

            clearScreen();
            timeStep++;
        }

        scanner.close();
        System.out.println("Simulation ended.");
    }

    
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
