package ca.gbc.comp3074.ali_kalid.assignment1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.gbc.comp3074.ali_kalid.assignment1.ui.theme.Assignment1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PayrollScreen(
                        onOpenAbout = {
                            // Explicit Intent to launch AboutActivity
                            val intent = Intent(this, AboutActivity::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PayrollScreen(onOpenAbout: () -> Unit) {
    // 1. State variables for user inputs
    var hoursInput by remember { mutableStateOf("") }
    var rateInput by remember { mutableStateOf("") }
    var taxRateInput by remember { mutableStateOf("") }

    // 2. State variables for calculation results
    var payResult by remember { mutableStateOf(0.0) }
    var overtimePayResult by remember { mutableStateOf(0.0) }
    var totalPayResult by remember { mutableStateOf(0.0) }
    var taxResult by remember { mutableStateOf(0.0) }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Payroll Calculator", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Input Field: Hours Worked
        OutlinedTextField(
            value = hoursInput,
            onValueChange = { hoursInput = it },
            label = { Text("Hours Worked") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input Field: Hourly Rate
        OutlinedTextField(
            value = rateInput,
            onValueChange = { rateInput = it },
            label = { Text("Hourly Rate ($)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Input Field: Tax Rate
        OutlinedTextField(
            value = taxRateInput,
            onValueChange = { taxRateInput = it },
            label = { Text("Tax Rate (e.g., 0.15)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Calculate Button
        Button(
            onClick = {
                errorMessage = ""
                val hours = hoursInput.toDoubleOrNull()
                val rate = rateInput.toDoubleOrNull()
                val taxRate = taxRateInput.toDoubleOrNull()

                //  validation check
                if (hours == null || rate == null || taxRate == null) {
                    errorMessage = "Please enter valid numeric values"
                    return@Button
                }

                // 3. Calculation formulas
                val pay: Double
                val overtimePay: Double

                if (hours <= 40.0) {
                    pay = hours * rate
                    overtimePay = 0.0
                } else {
                    pay = 40.0 * rate
                    overtimePay = (hours - 40.0) * rate * 1.5
                }

                val totalPay = pay + overtimePay
                val tax = pay * taxRate

                // Update result states
                payResult = pay
                overtimePayResult = overtimePay
                totalPayResult = totalPay
                taxResult = tax
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calculate Pay")
        }

        if (errorMessage.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 4. Output Results Display
        Text(text = "Pay: $${String.format("%.2f", payResult)}")
        Text(text = "Overtime Pay: $${String.format("%.2f", overtimePayResult)}")
        Text(text = "Total Pay: $${String.format("%.2f", totalPayResult)}")
        Text(text = "Tax: $${String.format("%.2f", taxResult)}")

        Spacer(modifier = Modifier.height(32.dp))

        // Navigation Button to AboutActivity
        Button(
            onClick = onOpenAbout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("About Developer")
        }
    }
}