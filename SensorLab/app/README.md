
# COMP3074 - Mobile App Development I
# Lab: Reading Android Device Sensors

**Student:** Kalid Ali  
**Project:** SensorLab  
**Platform:** Android  
**Language:** Kotlin  
**UI Framework:** Jetpack Compose  
**Test Device:** Motorola

---

## 1. Lab Overview

The purpose of this lab is to develop an Android application that reads data from device sensors and displays the readings using a Jetpack Compose user interface.

The application uses the Android SensorManager API to discover available sensors, receive sensor events, and update the interface whenever new sensor readings are received.

The two sensors used in this lab are:

- **Ambient Temperature Sensor:** Measures the surrounding air temperature in degrees Celsius.
- **Gyroscope Sensor:** Measures angular velocity around the X, Y, and Z axes in radians per second (rad/s).

The application also demonstrates how to register and unregister sensor listeners according to the Android activity lifecycle.

## 2. Learning Objectives

The objectives of this lab are to:

- Use SensorManager to access device sensors.
- Discover available sensors using getDefaultSensor().
- Implement SensorEventListener to receive sensor events.
- Read ambient temperature values in degrees Celsius.
- Read gyroscope angular velocity along the X, Y, and Z axes.
- Display sensor readings using Jetpack Compose.
- Connect sensor callbacks to observable Compose state.
- Register sensor listeners in onResume().
- Unregister sensor listeners in onPause().
- Handle unavailable sensors without crashing the application.

## 3. Application Features

The SensorLab application provides the following functionality:

### Ambient Temperature

- Checks whether the device contains an ambient temperature sensor.
- Displays the temperature in degrees Celsius when available.
- Displays an unavailable message when the sensor is not supported.
- Displays a waiting message before receiving the first reading.

### Gyroscope

- Checks whether the device contains a gyroscope.
- Displays angular velocity for the X, Y, and Z axes.
- Updates the readings when the device rotates.
- Displays values using radians per second (rad/s).
- Handles unavailable gyroscope hardware without crashing.

### Jetpack Compose Interface

The application uses Jetpack Compose to display sensor information in separate Material Design cards.

The interface automatically updates when new sensor readings are received.

### Activity Lifecycle Management

Sensor listeners are registered in onResume() and unregistered in onPause().

This prevents unnecessary sensor processing when the application is paused.

## 4. Technologies Used

| Technology | Purpose |
|---|---|
| Kotlin | Application programming language |
| Android Studio | Development environment |
| Jetpack Compose | User interface |
| Material 3 | UI components and styling |
| SensorManager | Accessing Android device sensors |
| SensorEventListener | Receiving sensor updates |
| mutableStateOf | Managing observable Compose state |

## 5. Sensors and Measurement Units

| Sensor | Android Constant | Measurement |
|---|---|---|
| Ambient Temperature | TYPE_AMBIENT_TEMPERATURE | Degrees Celsius (°C) |
| Gyroscope | TYPE_GYROSCOPE | Radians per second (rad/s) |

### Gyroscope Values

The gyroscope provides three angular velocity values:

- X: Angular velocity around the X axis.
- Y: Angular velocity around the Y axis.
- Z: Angular velocity around the Z axis.

When the device is stationary, the values should generally remain close to zero, although small fluctuations are normal.


## 6. Reflection Questions and Answers

### Question 1: Why must getDefaultSensor() be treated as potentially returning null?

The getDefaultSensor() method must be treated as potentially returning null because not all Android devices contain every type of hardware sensor.

For example, some smartphones do not include an ambient temperature sensor. When the requested sensor is unavailable, getDefaultSensor() returns null.

Checking for null allows the application to handle unavailable sensors correctly instead of attempting to register a listener for a sensor that does not exist. This prevents potential crashes and improves application compatibility across different Android devices.

### Question 2: Why should sensor listeners be unregistered when the activity is paused?

Sensor listeners should be unregistered when the activity is paused to prevent unnecessary sensor updates and processing when the application is not active.

Continuously receiving sensor events when the application is in the background can consume additional battery power and system resources.

Using onPause() to unregister the listeners ensures that the application stops receiving sensor events when it is no longer active.

### Question 3: What is the difference between gyroscope angular velocity and device orientation?

Gyroscope angular velocity measures how quickly a device rotates around a particular axis.

Device orientation describes the current rotational position of the device.

For example, the gyroscope can detect how quickly a phone is rotating, but its readings do not directly represent the phone's orientation in degrees.

Angular velocity describes rotational movement, while orientation describes the device's position or direction.

### Question 4: What units are used for the gyroscope values in this lab?

The gyroscope values are measured in radians per second (rad/s).

The sensor provides three angular velocity measurements:

- X-axis: Angular velocity around the X axis.
- Y-axis: Angular velocity around the Y axis.
- Z-axis: Angular velocity around the Z axis.

These values indicate how quickly the device is rotating around each axis.

### Question 5: Why is battery temperature not a valid replacement for TYPE_AMBIENT_TEMPERATURE?

Battery temperature is not a valid replacement because it measures the temperature of the phone's battery rather than the surrounding air.

Ambient temperature represents the temperature of the environment around the device.

These measurements are different and cannot be used interchangeably.

If the device does not contain an ambient temperature sensor, the application should clearly indicate that the sensor is unavailable instead of displaying battery temperature as an alternative.

### Question 6: How does mutableStateOf allow a SensorEvent callback to update the Compose UI?

mutableStateOf creates observable state that Jetpack Compose can monitor.

In this application, sensor readings such as temperature, gyroX, gyroY, and gyroZ are stored using mutableStateOf.

When onSensorChanged() receives a new sensor event, the corresponding state variables are updated.

Jetpack Compose detects these changes and automatically recomposes the affected UI components to display the latest sensor readings.

This allows the application to update the interface without manually refreshing the screen.

## 9. Conclusion

This lab demonstrated how to access Android device sensors using the SensorManager API and receive sensor readings through SensorEventListener.

The application uses Jetpack Compose to display ambient temperature and gyroscope readings in a simple user interface.

It also demonstrates the importance of checking sensor availability, managing sensor listeners according to the activity lifecycle, and using mutableStateOf to update the interface when new sensor events are received.

Overall, this lab provided practical experience with Android sensor programming, event-driven application development, and Jetpack Compose state management.

---

**Course:** COMP3074 - Mobile App Development I  
**Lab:** Reading Android Device Sensors  
**Project:** SensorLab
