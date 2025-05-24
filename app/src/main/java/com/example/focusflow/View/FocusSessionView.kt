package com.example.focusflow.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun FocusSessionView(
    navController: NavController,
    focusDuration: Int,  // seconds
    restDuration: Int    // seconds
) {
    var isFocus by remember { mutableStateOf(true) }
    var isPlaying by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(focusDuration) }

    // Timer countdown
    LaunchedEffect(isPlaying, timeLeft) {
        if (isPlaying && timeLeft > 0) {
            delay(1000L)
            timeLeft -= 1
        } else if (isPlaying && timeLeft == 0) {
            isFocus = !isFocus
            timeLeft = if (isFocus) focusDuration else restDuration
        }
    }

    val backgroundColor = if (isFocus) Color.Black else Color.White
    val textColor = if (isFocus) Color.White else Color.Black

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Session Label (Focus / Rest)
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = if (isFocus) "Focus" else "Rest",
            color = textColor,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .background(Color.Transparent)
                .border(
                    width = 1.dp,
                    color = textColor,
                    shape = MaterialTheme.shapes.small
                )
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )

        // Timer display
        Text(
            text = formatTime(timeLeft),
            fontSize = 64.sp,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Center
        )

        // Controls (Reset, Pause/Play)
        Row(
            modifier = Modifier
                .padding(bottom = 24.dp)
                .background(Color.DarkGray, shape = MaterialTheme.shapes.extraLarge)
                .padding(horizontal = 32.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Reset",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        timeLeft = if (isFocus) focusDuration else restDuration
                        isPlaying = false
                    }
            )
            Icon(
                imageVector = if (isPlaying) Icons.Default.Person else Icons.Default.PlayArrow,
                contentDescription = if (isPlaying) "Pause" else "Play",
                tint = Color.White,
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        isPlaying = !isPlaying
                    }
            )
        }
    }
}

// Formats seconds to MM:SS
fun formatTime(seconds: Int): String {
    val m = seconds / 60
    val s = seconds % 60
    return String.format("%02d:%02d", m, s)
}

@Preview(showBackground = true)
@Composable
fun PreviewFocusSessionView() {
    val navController = rememberNavController()
    FocusSessionView(
        navController = navController,
        focusDuration = 2 * 60, // 25 minutes
        restDuration = 1 * 60    // 5 minutes
    )
}