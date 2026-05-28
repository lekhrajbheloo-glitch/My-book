package com.lekhrajbheloo.mybook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentScreen by remember { mutableStateOf("Splash") }
            
            LaunchedEffect(key1 = true) {
                delay(3000)
                currentScreen = "Home"
            }

            Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFF000000)) {
                if (currentScreen == "Splash") {
                    SplashScreenView()
                } else {
                    HomeScreenView()
                }
            }
        }
    }
}

@Composable
fun SplashScreenView() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF000000)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .border(3.dp, Color(0xFFFF0000), RoundedCornerShape(50.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text("B", color = Color.White, fontSize = 45.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(25.dp))
        Text("MY BOOK", color = Color.White, fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Text("BY LEKHRAJ SINGH", color = Color(0xFFFF3333), fontSize = 12.sp)
    }
}

@Composable
fun HomeScreenView() {
    var showPopup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFF000000)).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("MY BOOK", color = Color(0xFFFF0000), fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(30.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.weight(1f).background(Color(0xFF111111)).border(1.dp, Color(0xFFFF0000), RoundedCornerShape(12.dp)).padding(15.dp)) {
                Column {
                    Text("TODAY'S TOTAL", color = Color.Gray, fontSize = 10.sp)
                    Text("₹2,500", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
            Spacer(modifier = Modifier.width(10.dp))
            Box(modifier = Modifier.weight(1f).background(Color(0xFF111111)).border(1.dp, Color(0xFFFF0000), RoundedCornerShape(12.dp)).padding(15.dp)) {
                Column {
                    Text("ALL TIME TOTAL", color = Color.Gray, fontSize = 10.sp)
                    Text("₹4,200", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = { showPopup = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF111111)),
            modifier = Modifier.fillMaxWidth().border(1.dp, Color(0xFFFF3333), RoundedCornerShape(8.dp))
        ) {
            Text("⚡ SIMULATE PHONEPE PAYMENT", color = Color(0xFFFF3333))
        }

        if (showPopup) {
            AlertDialog(
                onDismissRequest = { showPopup = false },
                containerColor = Color(0xFF000000),
                modifier = Modifier.border(2.dp, Color(0xFFFF0000), RoundedCornerShape(15.dp)),
                title = { Text("⚠️ PHONEPE DETECTED", color = Color(0xFFFF0000), fontSize = 16.sp, fontWeight = FontWeight.Bold) },
                text = {
                    Column {
                        Text("Sender: Simran Jeet", color = Color.White, fontSize = 18.sp)
                        Text("Amount: ₹2,500", color = Color(0xFFFF3333), fontSize = 26.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text("Do you want to save this transaction?", color = Color.Gray)
                    }
                },
                confirmButton = {
                    Button(onClick = { showPopup = false }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF111111))) {
                        Text("YES", color = Color.White)
                    }
                },
                dismissButton = {
                    Button(onClick = { showPopup = false }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF000000))) {
                        Text("NO", color = Color.White)
                    }
                }
            )
        }
    }
}
