# OA-Practice-Notes
# 🎈 Balloon Stability Tracker

You're managing a team of pilots participating in an international hot air balloon festival. Balloons **ascend**, **descend**, and periodically update their **altitudes**. Judges inspect balloons periodically, rewarding **stable balloons** flying at or above the **highest stable competitor altitude**.

---

## 🎯 Balloon Stability Rules

- Balloons are **stable by default** upon ascending.
- Balloons become **immediately unstable** if wind speed at their altitude exceeds **15 m/s** (exclusive).
- An unstable balloon can **regain stability** if it continuously remains at an altitude with **safe wind speed (≤ 15 m/s)** for at least **300 seconds**.

---

## 🌬️ Wind Speed Formula

Wind speed at a given altitude dissipates upward and downward using the formula:
WindSpeed(h) = WindSpeedAtAltitude / (1 + ((h - WindAltitude) / 100)²)



Where:
- `h` is the balloon’s current altitude.
- `WindSpeedAtAltitude` is the reported wind at a given altitude.
- Wind speeds from **multiple altitudes are cumulative**.

---

## 🧪 Problem Statement

Implement the methods in the `BalloonFestival` class to simulate balloon tracking.

### General Notes:
- If **any constraint** is violated when performing an operation, it must **fail** (return `false`).
- Guaranteed constraints may be **assumed to hold**.
- Timestamps are represented as **positive doubles**, strictly increasing between actions.

---

## 📚 Method Specifications

### `BalloonFestival(List<String> yourBalloonNames)`
Initializes the class with a list of **unique balloon names** that belong to your team.

- ✅ Guaranteed: `1 ≤ Q < 2^20` (Q is the length of `yourBalloonNames`)

---

### `boolean balloonAscended(double timestamp, String balloonName, double altitude)`
Registers that a balloon has **ascended** to the given altitude at the given time.

- Returns `true` if successful, `false` otherwise.
- Repeated calls for the same balloon act as updates.
- ✅ Constraint: `0 < altitude < 2^15`

---

### `boolean balloonDescended(double timestamp, String balloonName)`
Registers that a balloon has **descended to the ground** at the given time.

- Returns `true` if successful, `false` otherwise.
- A balloon's **stability fully resets** after descending.
- If the balloon is **not currently flying**, return `false`.

---

### `boolean setWindSpeed(double timestamp, double altitude, double windSpeed)`
Updates wind speed at a specific altitude.

- Returns `true` if successful, `false` otherwise.
- ✅ Constraints:
  - `0 < altitude < 2^15`
  - `0 ≤ windSpeed < 25`

---

### `List<String> inspectBalloons(double timestamp)`
Performs inspection at the given timestamp.

- Returns a list of **stable balloon names** that are flying at or **above** the **highest stable competitor altitude**.
- Returns an **empty list** if none qualify.

---

## 🧠 Hints
- You are **not allowed to use extra libraries**, only `Scanner`, `List`, and `ArrayList`.
- Use **parallel lists** to simulate balloon state tracking if object classes are disallowed.
