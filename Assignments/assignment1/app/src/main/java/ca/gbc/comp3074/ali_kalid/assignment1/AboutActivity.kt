package ca.gbc.comp3074.ali_kalid.assignment1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.gbc.comp3074.ali_kalid.assignment1.ui.theme.Assignment1Theme

class AboutActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "About Developer",
                            fontSize = 24.sp
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Student Name
                        Text(
                            text = "Student Name: Kalid Ali",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Student ID
                        Text(
                            text = "Student ID: 101577665",
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(32.dp))

                        // Back Button (calls finish() to return to MainActivity)
                        Button(
                            onClick = { finish() },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = "Back to Main")
                        }
                    }
                }
            }
        }
    }
}