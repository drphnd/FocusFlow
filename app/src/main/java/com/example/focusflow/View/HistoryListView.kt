package com.example.focusflow.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.focusflow.Model.FocusModel
import com.example.focusflow.ViewModel.FocusModeViewModel

@Composable
fun HistoryListView(navController: NavController) {
    val viewModel: FocusModeViewModel = viewModel()
    val focusList by viewModel.focusList.collectAsState()

    val dummyData = listOf(
        FocusModel(1, "Focus ALP ComNet", "Study", "Konfigurasi Cisco", 15, 5, true),
        FocusModel(2, "Focus ALP WebProg", "Study", "Konfigurasi Cisco", 15, 5, true),
        FocusModel(3, "Focus ALP OOP", "Study", "Konfigurasi Cisco", 15, 5, false),
        FocusModel(4, "Focus ALP Kentang", "Study", "Konfigurasi Cisco", 15, 5, false),
        FocusModel(5, "Focus ALP BBk", "Study", "Konfigurasi Cisco", 15, 5, true),
        FocusModel(6, "Focus ALP MNG", "Study", "Konfigurasi Cisco", 15, 5, true)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Column {
            IconButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .size(32.dp)
                        .clickable {
                            navController.navigate("home")
                        }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "HISTORY",
                fontSize = 44.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "FOCUS FLOW",
                fontSize = 44.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(dummyData) { item ->
                    HistoryCard(item)
                }
            }
        }

        FloatingActionButton(
            onClick = { navController.navigate("focussetup") },
            containerColor = Color.White,
            contentColor = Color.Black,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Focus")
        }
    }
}

@Composable
fun HistoryCard(focus: FocusModel) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        color = Color(0xFF2C2C2C)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = focus.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1.2f)) {
                    Text(
                        text = "Goals: ${focus.goals}",
                        color = Color.White)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Status: ${if (focus.isCompleted) "Done" else "In Progress"}",
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Focus Duration: ${formatMMSS(focus.focusDuration)}",
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Rest Duration: ${formatMMSS(focus.restDuration)}",
                        color = Color.White
                    )
                }
            }
        }
    }
}

fun formatMMSS(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}


@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewHistoryListView() {
    // Gunakan dummy NavController untuk preview
    val navController = rememberNavController()
    HistoryListView(navController = navController)
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewHistoryCard() {
    val dummyFocus = FocusModel(
        focus_id = 1,
        title = "Focus ALP ComNet",
        category = "Study",
        goals = "Konfigurasi Cisco",
        focusDuration = 15,
        restDuration = 5,
        isCompleted = true
    )
    HistoryCard(focus = dummyFocus)
}