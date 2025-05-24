package com.example.focusflow.View

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FocusSetUpView(navController: NavController) {
    var focusName by remember { mutableStateOf("") }
    var focusGoals by remember { mutableStateOf("") }

    var focusDurationRaw by remember { mutableStateOf("") }
    var restDurationRaw by remember { mutableStateOf("") }

    val defaultCategories = listOf("Choose Category", "Study", "Work", "Add Category")
    var categories by remember { mutableStateOf(defaultCategories.toMutableList()) }

    //untuk dropdown kategory
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf(categories[0]) }

    //kalautekan addcategory
    var showAddCategoryDialog by remember { mutableStateOf(false) }

    //untuk add new category
    var newCategoryText by remember { mutableStateOf("") }

    //pop up untuk add kategory baru
    if (showAddCategoryDialog) {
        AlertDialog(
            onDismissRequest = { showAddCategoryDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    if (newCategoryText.isNotBlank()) {
                        val insertIndex = categories.lastIndex
                        categories = (categories.take(insertIndex) + newCategoryText + "Add Category").toMutableList()
                        selectedCategory = newCategoryText
                        newCategoryText = ""
                        showAddCategoryDialog = false
                    }
                }) {
                    Text("Add")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    newCategoryText = ""
                    showAddCategoryDialog = false
                }) {
                    Text("Cancel")
                }
            },
            title = { Text("Add New Category") },
            text = {
                OutlinedTextField(
                    value = newCategoryText,
                    onValueChange = { newCategoryText = it },
                    placeholder = { Text("New Category Name") }
                )
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //back to have
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

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "FOCUS",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = "FLOW",
            fontSize = 44.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        //input focus title
        Column {
            Text(
                text = "Focus Name",
                color = Color.White,
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))

            OutlinedTextField(
                value = focusName,
                onValueChange = { focusName = it },
                placeholder = { Text("Enter Focus Name") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //pilih focus category
        Column {
            Text(
                text = "Category Focus",
                color = Color.White,
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = selectedCategory,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = { Text(category) },
                            onClick = {
                                if (category == "Add Category") {
                                    showAddCategoryDialog = true
                                } else {
                                    selectedCategory = category
                                }
                                expanded = false
                            },
                            enabled = category != "Choose Category"
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        //focus goals
        Column {
            Text(
                text = "Focus Goals",
                color = Color.White,
                modifier = Modifier.align(Alignment.Start),
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            OutlinedTextField(
                value = focusGoals,
                onValueChange = { focusGoals = it },
                placeholder = { Text("Enter Focus Goals") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.White,
                    focusedBorderColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedPlaceholderColor = Color.Gray,
                    focusedPlaceholderColor = Color.Gray
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        //focus and rest duration
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Focus Duration",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = formatMMSS(focusDurationRaw),
                    onValueChange = {
                        focusDurationRaw = it.filter { c -> c.isDigit() }.take(4)
                    },
                    placeholder = { Text("00:00") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray
                    )
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "Rest Duration",
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = formatMMSS(restDurationRaw),
                    onValueChange = {
                        restDurationRaw = it.filter { c -> c.isDigit() }.take(4)
                    },
                    placeholder = { Text("00:00") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedBorderColor = Color.White,
                        focusedBorderColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedPlaceholderColor = Color.Gray,
                        focusedPlaceholderColor = Color.Gray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        //save button
        Button(
            onClick = {
                if (selectedCategory == "Choose Category") {
                    println("Please select a valid category.")
                    return@Button
                }
                println("Saving focus with category: $selectedCategory")

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(
                text = "SAVE",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

// Converts raw numeric string to mm:ss format
fun formatMMSS(raw: String): String {
    val digits = raw.filter { it.isDigit() }.take(4).padStart(4, '0')
    val minutes = digits.take(2).toIntOrNull() ?: 0
    val seconds = digits.takeLast(2).toIntOrNull() ?: 0
    return String.format("%02d:%02d", minutes, seconds)
}

@Preview(showBackground = true)
@Composable
fun PreviewFocusSetUpView() {
    val navController = rememberNavController()
    FocusSetUpView(navController = navController)
}