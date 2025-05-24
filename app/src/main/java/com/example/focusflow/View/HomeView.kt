package com.example.focusflow.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.focusflow.ViewModel.FocusModeViewModel

@Composable
fun HomeView(navController: NavController) {
    val viewModel: FocusModeViewModel = viewModel()

    val focusList by viewModel.focusList.collectAsState()

    val usedCount = focusList.size
    val doneCount = focusList.count { it.isCompleted }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        //focus flow title
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                "FOCUS",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
            Text(
                "FLOW",
                fontSize = 64.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(Modifier.height(20.dp))

        //sudah pakai berapa kali
        UsedCount("Used Count", usedCount) //ganti $ sesuai viewmodel

        Spacer(modifier = Modifier.height(16.dp))

        //sudah selesai berapa kali
        DoneCount("Done Count", doneCount) //ganti $ sesuai viewmodel

        Spacer(Modifier.height(20.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            //start button
            Button(
                onClick = { navController.navigate("focussetup") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 64.dp)
            ) {
                Text(
                    "START FOCUS",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black

                )
            }

            Spacer(Modifier.height(10.dp))

            //history button
            Button(
                onClick = { navController.navigate("history") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF444444),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 64.dp)
            ) {
                Text(
                    "HISTORY",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun UsedCount(label: String, usedcount: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF444444), shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 44.dp, vertical = 12.dp)
        ) {
            Text(
                text = label,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .background(Color(0xFF444444), shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 60.dp, vertical = 14.dp)
        ) {
            Text(
                text = "x $usedcount",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DoneCount(label: String, donecount: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xFF444444), shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 44.dp, vertical = 12.dp)
        ) {
            Text(
                text = label,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .background(Color(0xFF444444), shape = RoundedCornerShape(30.dp))
                .padding(horizontal = 60.dp, vertical = 14.dp)
        ) {
            Text(
                text = "x $donecount",
                color = Color.White,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomeViewPreview() {
    val navController = rememberNavController()
    HomeView(navController = navController)
}