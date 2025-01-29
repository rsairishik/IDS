import java.util.*;

class DeliveryPoint {
    String name;
    int x, y;
    String priority;

    public DeliveryPoint(String name, int x, int y, String priority) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.priority = priority;
    }
}

public class SmartDeliveryRoutePlanner {

    public static void main(String[] args) {
        List<DeliveryPoint> points = new ArrayList<>();
        points.add(new DeliveryPoint("A", 2, 3, "high"));
        points.add(new DeliveryPoint("B", 5, 8, "medium"));
        points.add(new DeliveryPoint("C", 1, 1, "high"));
        points.add(new DeliveryPoint("D", 6, 3, "low"));
        points.add(new DeliveryPoint("E", 4, 4, "medium"));

        points.sort((a, b) -> {
            if (!a.priority.equals(b.priority)) {
                return priorityValue(a.priority) - priorityValue(b.priority);
            }
            return 0;
        });

        List<DeliveryPoint> route = new ArrayList<>();
        Set<DeliveryPoint> visited = new HashSet<>();
        DeliveryPoint current = points.get(0);
        route.add(current);
        visited.add(current);

        while (route.size() < points.size()) {
            DeliveryPoint next = null;
            double minDistance = Double.MAX_VALUE;

            for (DeliveryPoint point : points) {
                if (!visited.contains(point)) {
                    double distance = calculateDistance(current, point);
                    if (distance < minDistance) {
                        minDistance = distance;
                        next = point;
                    }
                }
            }

            route.add(next);
            visited.add(next);
            current = next;
        }

        for (DeliveryPoint point : route) {
            System.out.println(point.name + " (" + point.priority + ")");
        }
    }

    private static int priorityValue(String priority) {
        switch (priority) {
            case "high":
                return 1;
            case "medium":
                return 2;
            case "low":
                return 3;
            default:
                return 4;
        }
    }

    private static double calculateDistance(DeliveryPoint a, DeliveryPoint b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }
}