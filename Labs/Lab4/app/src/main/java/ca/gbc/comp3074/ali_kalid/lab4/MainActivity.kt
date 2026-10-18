package ca.gbc.comp3074.ali_kalid.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ca.gbc.comp3074.ali_kalid.lab4.ui.theme.Lab4Theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.TextButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database =
            AppDatabase.getDatabase(applicationContext)

        val customerDao = database.customerDao()

        setContent {
            MaterialTheme {
                CustomerScreen(customerDao)
            }
        }
    }
}

@Composable
fun CustomerScreen(customerDao: CustomerDao) {

    // Get customers .
    val customers by customerDao.getAllCustomers()
        .collectAsStateWithLifecycle(initialValue = emptyList())

    // Run database operations
    val scope = rememberCoroutineScope()

    // Form values.
    var name by remember { mutableStateOf("") }
    var ageText by remember { mutableStateOf("") }
    var isActive by remember { mutableStateOf(true) }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Customer Database",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Customer name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = ageText,
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    ageText = newValue
                }
            },
            label = { Text("Age") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Active customer")

            Spacer(modifier = Modifier.width(8.dp))

            Switch(
                checked = isActive,
                onCheckedChange = { isActive = it }
            )
        }

        Button(
            onClick = {
                val customerName = name.trim()
                val age = ageText.toIntOrNull()
                val activeStatus = isActive

                if (customerName.isBlank() || age == null || age < 0) {
                    message = "Enter a name and a valid age."
                } else {
                    scope.launch {
                        try {
                            customerDao.insert(
                                Customer(
                                    name = customerName,
                                    age = age,
                                    isActive = activeStatus
                                )
                            )

                            // Clear the form after saving succeeds.
                            name = ""
                            ageText = ""
                            isActive = true
                            message = "Customer added successfully."
                        } catch (e: Exception) {
                            message = "Error saving customer: ${e.message}"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Customer")
        }


        if (message.isNotEmpty()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(message)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Customers",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (customers.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text("No customers in the database.")
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(
                    items = customers,
                    key = { customer -> customer.id }
                ) { customer ->

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = customer.name,
                                    style = MaterialTheme.typography.titleMedium
                                )

                                Text("ID: ${customer.id}")
                                Text("Age: ${customer.age}")

                                Text(
                                    if (customer.isActive) {
                                        "Active customer"
                                    } else {
                                        "Inactive customer"
                                    }
                                )
                            }

                            TextButton(
                                onClick = {
                                    scope.launch {
                                        customerDao.delete(customer)
                                    }
                                }
                            ) {
                                Text("Delete")
                            }
                        }
                    }
                }
            }
        }
    }
}