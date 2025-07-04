# OA-Practice-Notes

📝 Question: Balloon Stability Tracker
You're managing a team of pilots participating in an international hot air balloon festival. Balloons ascend, descend, and periodically update their altitudes. Judges inspect balloons periodically, rewarding stable balloons flying at or above the highest stable competitor altitude.

🎯 Balloon Stability Rules:
Balloons are stable by default upon ascending.

Balloons become immediately unstable if wind speed at their altitude exceeds a fixed threshold of 15 m/s (exclusive).

An unstable balloon can regain stability if it continuously remains at an altitude with safe wind speeds (≤15) for at least 300 seconds.

🌬️ Wind Speed Formula:
Wind speed at a given altitude dissipates upward and downward according to the formula:

Copy
Edit
WindSpeed(h) = WindSpeedAtAltitude / (1 + ((h - WindAltitude)/100)^2)
Where:

h is the balloon’s current altitude.

WindSpeedAtAltitude is the defined wind speed at a specific altitude.

Wind speeds from multiple altitudes add cumulatively.

🧪 Problem Statement
Complete the functions in the BalloonFestival class. Keep in mind:

If any constraint is violated during an operation, the operation must fail.

You may assume that guaranteed constraints are never violated.

Timestamps are strictly increasing across all function calls.

📌 Function Descriptions
1. BalloonFestival(List<String> yourBalloonNames)
Initializes the class with a list of unique balloon names.
Guaranteed constraint: 1 ≤ Q < 2^20, where Q is the length of the list.

2. boolean balloonAscended(double timestamp, String balloonName, double altitude)
Registers that a balloon has ascended to the given altitude at the given time.
Returns true on success, false otherwise.
Repeated calls for the same balloon are treated as altitude updates.

Constraint: 0 < altitude < 2^15

3. boolean balloonDescended(double timestamp, String balloonName)
Registers that a balloon has descended to the ground at the given timestamp.
Returns true on success, false otherwise.
After descending:

The balloon’s stability resets to default.

If balloon is not currently flying, return false.

4. boolean setWindSpeed(double timestamp, double altitude, double windSpeed)
Updates wind at a specific altitude.
Returns true on success, false otherwise.

Constraints:

0 < altitude < 2^15

0 ≤ windSpeed < 25

5. List<String> inspectBalloons(double timestamp)
Returns the names of stable balloons flying at or above the highest stable competitor.
If no balloons qualify, return an empty list.

