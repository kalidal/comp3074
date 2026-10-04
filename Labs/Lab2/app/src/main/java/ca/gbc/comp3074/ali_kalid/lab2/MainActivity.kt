package ca.gbc.comp3074.ali_kalid.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import ca.gbc.comp3074.ali_kalid.lab2.ui.theme.Lab2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Lab2Theme {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() {

    // The current number displayed on the screen
    var counter by remember { mutableStateOf(0) }

    // The amount that the Add and Subtract buttons change the counter by
    // Default behaviour is 1
    var step by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // App logo
        // App logo
        Image(
            painter = painterResource(id = R.drawable.counter_logo),
            contentDescription = "Counter app logo",
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(25.dp))

        // Output number
        Text(
            text = counter.toString(),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Minus and Plus buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Subtract button
            Button(
                onClick = {
                    counter -= step
                },
                modifier = Modifier.size(
                    width = 100.dp,
                    height = 55.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text(
                    text = "−",
                    fontSize = 28.sp
                )
            }

            // Add button
            Button(
                onClick = {
                    counter += step
                },
                modifier = Modifier.size(
                    width = 100.dp,
                    height = 55.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4CAF50)
                )
            ) {
                Text(
                    text = "+",
                    fontSize = 28.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Reset and Step buttons
        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            // Reset button
            Button(
                onClick = {
                    counter = 0
                    step = 1
                },
                modifier = Modifier.size(
                    width = 100.dp,
                    height = 55.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE53935)
                )
            ) {
                Text(
                    text = "Reset",
                    fontSize = 16.sp
                )
            }

            // Step button
            Button(
                onClick = {
                    step = if (step == 1) 2 else 1
                },
                modifier = Modifier.size(
                    width = 100.dp,
                    height = 55.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF81C784)
                )
            ) {
                Text(
                    text = "Step $step",
                    fontSize = 16.sp
                )
            }
        }
    }
}