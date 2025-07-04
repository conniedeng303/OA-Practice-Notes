
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class BalloonFestival {
    private List<String> balloonNames;

    private List<String> names = new ArrayList<>();
    private List<Boolean> isFlying = new ArrayList<>();
    private List<Double> altitude = new ArrayList<>();
    private List<Double> lastTimestamp = new ArrayList<>();
    private List<Boolean> isStable = new ArrayList<>();
    private List<Double> stableSince = new ArrayList<>();

    private List<Double> windTimestamps = new ArrayList<>();
    private List<Double> windAltitudes = new ArrayList<>();
    private List<Double> windSpeeds = new ArrayList<>();

    public BalloonFestival(List<String> yourBalloonNames) {
        this.balloonNames = new ArrayList<>(yourBalloonNames);
        for (String name : yourBalloonNames) {
            names.add(name);
            isFlying.add(false);
            altitude.add(0.0);
            lastTimestamp.add(0.0);
            isStable.add(true);
            stableSince.add(-1.0);
        }
    }

    public boolean balloonAscended(double timestamp, String balloonName, double newAltitude) {
        if (newAltitude <= 0 || newAltitude >= Math.pow(2, 15)) return false;
        int idx = names.indexOf(balloonName);
        if (idx == -1) return false;

        isFlying.set(idx, true);
        altitude.set(idx, newAltitude);
        lastTimestamp.set(idx, timestamp);
        isStable.set(idx, true);
        stableSince.set(idx, timestamp);
        return true;
    }

    public boolean balloonDescended(double timestamp, String balloonName) {
        int idx = names.indexOf(balloonName);
        if (idx == -1 || !isFlying.get(idx)) return false;

        isFlying.set(idx, false);
        altitude.set(idx, 0.0);
        isStable.set(idx, true);
        stableSince.set(idx, -1.0);
        lastTimestamp.set(idx, timestamp);
        return true;
    }

    public boolean setWindSpeed(double timestamp, double alt, double windSpeed) {
        if (alt <= 0 || alt >= Math.pow(2, 15)) return false;
        if (windSpeed < 0 || windSpeed >= 25) return false;

        windTimestamps.add(timestamp);
        windAltitudes.add(alt);
        windSpeeds.add(windSpeed);

        for (int i = 0; i < names.size(); i++) {
            if (!isFlying.get(i)) continue;

            double a = altitude.get(i);
            double totalWind = 0;

            for (int j = 0; j < windTimestamps.size(); j++) {
                if (windTimestamps.get(j) > timestamp) continue;
                double d = (a - windAltitudes.get(j)) / 100.0;
                totalWind += windSpeeds.get(j) / (1 + d * d);
            }

            if (totalWind > 15) {
                isStable.set(i, false);
                stableSince.set(i, -1.0);
            } else {
                if (!isStable.get(i)) {
                    if (stableSince.get(i) == -1.0) {
                        stableSince.set(i, timestamp);
                    } else if (timestamp - stableSince.get(i) >= 300) {
                        isStable.set(i, true);
                    }
                }
            }
        }

        return true;
    }

    public List<String> inspectBalloons(double timestamp) {
        double highest = 0;
        for (int i = 0; i < names.size(); i++) {
            if (isFlying.get(i) && isStable.get(i)) {
                highest = Math.max(highest, altitude.get(i));
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (isFlying.get(i) && isStable.get(i) && altitude.get(i) >= highest) {
                res.add(names.get(i));
            }
        }

        return res;
    }
}
