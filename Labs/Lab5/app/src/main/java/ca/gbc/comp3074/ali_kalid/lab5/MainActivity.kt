
package ca.gbc.comp3074.ali_kalid.lab5

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import ca.gbc.comp3074.ali_kalid.lab5.ui.theme.Lab5Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab5Theme {
                GPSLocationScreen()
            }
        }
    }
}

@Composable
fun GPSLocationScreen() {

    val context = LocalContext.current

    val locationManager = remember {
        context.getSystemService(
            Context.LOCATION_SERVICE
        ) as LocationManager
    }

    var latitude by remember { mutableStateOf("Not available") }
    var longitude by remember { mutableStateOf("Not available") }
    var message by remember { mutableStateOf("Press the button to get location.") }

    var hasPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->

            hasPermission =
                permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true

            if (hasPermission) {
                getGPSLocation(
                    locationManager,
                    latitudeUpdate = { latitude = it },
                    longitudeUpdate = { longitude = it },
                    messageUpdate = { message = it }
                )
            } else {
                message = "Location permission denied."
            }
        }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "My GPS Location",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Latitude: $latitude")

        Spacer(modifier = Modifier.height(12.dp))

        Text(text = "Longitude: $longitude")

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = message)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (hasPermission) {
                    getGPSLocation(
                        locationManager,
                        latitudeUpdate = { latitude = it },
                        longitudeUpdate = { longitude = it },
                        messageUpdate = { message = it }
                    )
                } else {
                    permissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        )
                    )
                }
            }
        ) {
            Text("Get GPS Location")
        }
    }
}


@Suppress("DEPRECATION")
fun getGPSLocation(
    manager: LocationManager,
    latitudeUpdate: (String) -> Unit,
    longitudeUpdate: (String) -> Unit,
    messageUpdate: (String) -> Unit
) {
    try {
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            messageUpdate("Please enable Location/GPS on your phone.")
            return
        }

        messageUpdate("Waiting for GPS location...")

        val listener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                latitudeUpdate(location.latitude.toString())
                longitudeUpdate(location.longitude.toString())
                messageUpdate("Location received.")

                // Stop listening after receiving the first GPS location.
                manager.removeUpdates(this)
            }
        }

        // Keep listening until the phone provides a GPS location.
        manager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            1000L,
            0f,
            listener,
            Looper.getMainLooper()
        )

    } catch (e: SecurityException) {
        messageUpdate("Location permission is required.")
    } catch (e: Exception) {
        messageUpdate("Could not get GPS location: ${e.message}")
    }
}

