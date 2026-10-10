
package ca.gbc.comp3074.ali_kalid.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.gbc.comp3074.ali_kalid.lab3.ui.theme.Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            Lab3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFFFF8FF)
                ) {
                    MessageListScreen()
                }
            }
        }
    }
}

data class Message(
    val sender: String,
    val message: String
)

@Composable
fun MessageListScreen() {

    val messages = listOf(
        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!"),
        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!"),
        Message("Joe", "Hi!"),
        Message("Jim", "How are you?"),
        Message("Joe", "Test..1..2...3"),
        Message("Joe", "I hate coding!!!")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(Color(0xFFFFF8FF)),
        verticalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(messages) { message ->
            MessageItem(message = message)
        }
    }
}

@Composable
fun MessageItem(message: Message) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(147.dp)
            .padding(horizontal = 57.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // Circular Android avatar with a red outline
        Box(
            modifier = Modifier
                .size(101.dp)
                .border(
                    width = 2.dp,
                    color = Color.Red,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            AndroidAvatar()
        }

        // Space between the avatar and message details
        Spacer(modifier = Modifier.size(18.dp))

        // Sender name and rounded message bubble
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = message.sender,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF625B71)
            )

            Spacer(modifier = Modifier.size(14.dp))

            Text(
                text = message.message,
                fontSize = 27.sp,
                color = Color(0xFF29252D),
                modifier = Modifier
                    .shadow(
                        elevation = 2.dp,
                        shape = RoundedCornerShape(30.dp)
                    )
                    .background(
                        color = Color(0xFFFFF8FF),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = Color(0xFFF0EAF2),
                        shape = RoundedCornerShape(30.dp)
                    )
                    .padding(
                        horizontal = 10.dp,
                        vertical = 9.dp
                    )
            )
        }
    }
}

@Composable
fun AndroidAvatar() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
            .background(
                color = Color(0xFFFFF8FF),
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {

        // Simple white Android robot head
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .background(Color(0xFFCAC6CE), CircleShape)
                )

                Box(
                    modifier = Modifier
                        .size(3.dp)
                        .background(Color(0xFFCAC6CE), CircleShape)
                )
            }

            Spacer(modifier = Modifier.size(5.dp))

            Box(
                modifier = Modifier
                    .size(width = 48.dp, height = 23.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 24.dp,
                            topEnd = 24.dp,
                            bottomStart = 2.dp,
                            bottomEnd = 2.dp
                        )
                    )
            )
        }
    }
}
