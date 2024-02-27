import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

public class Airport {
    private Queue<Plane> approachQueue;
    private PriorityQueue<Plane> readyToLandQueue;

    public Airport() {
        approachQueue = new PriorityQueue<>((p1, p2) -> Integer.compare(p1.getDistanceFromAirport(), p2.getDistanceFromAirport()));
        readyToLandQueue = new PriorityQueue<>((p1, p2) -> {
            if (p1.isInEmergency() && !p2.isInEmergency()) {
                return -1;
            } else if (!p1.isInEmergency() && p2.isInEmergency()) {
                return 1;
            } else {
                return Integer.compare(p1.getDistanceFromAirport(), p2.getDistanceFromAirport());
            }
        });
    }

    public void addPlaneToApproach(Plane plane) {
        approachQueue.add(plane);
    }

    public void updateQueues() {
        approachQueue.stream()
                .filter(plane -> plane.getDistanceFromAirport() <= 0)
                .collect(Collectors.toList())
                .forEach(plane -> {
                    approachQueue.remove(plane);
                    readyToLandQueue.add(plane);
                });
    }

    public Plane getNextPlaneToLand() {
        return readyToLandQueue.poll();
    }

    public void handleEmergencyLanding(Plane emergencyPlane) {
        emergencyPlane.setEmergency(true);
        approachQueue.remove(emergencyPlane);
        readyToLandQueue.add(emergencyPlane);
        System.out.println("Emergency landing initiated for Plane ID: " + emergencyPlane.getId());
    }

    public Plane selectPlaneForEmergency() {
        return approachQueue.stream()
                .filter(plane -> !plane.isInEmergency())
                .findAny()
                .orElse(null);
    }

    public String getQueueStatus() {
        return "Approach Queue: " + queueToString(approachQueue) +
               "\nReady to Land Queue: " + queueToString(readyToLandQueue) + "\n";
    }

    private String queueToString(Queue<Plane> queue) {
        return queue.stream()
                    .map(Plane::toString)
                    .collect(Collectors.joining(", "));
    }

    // Getter for the approach queue
    public Queue<Plane> getApproachQueue() {
        return approachQueue;
    }
}
