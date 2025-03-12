//
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//class PaymentActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Retrieve the selected date from the intent
//        val selectedDate = intent.getStringExtra("selectedDate") ?: "No date selected"
//
//        setContent {
//            ModePaymentScreen(selectedDate)
//        }
//    }
//}
//
//@Composable
//fun ModePaymentScreen(selectedDate: String) {
//    val context = LocalContext.current
//    PaymentScreen(context, selectedDate)
//}
//
//@Composable
//fun PaymentScreen(context: Context, selectedDate: String) {
//    var selectedPaymentMethod by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "Select Payment Method", fontSize = 20.sp)
//
//        // Display selected date
//        Text(text = "Delivery Date: $selectedDate", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
//
//        // Radio Buttons
//        RadioButtonRow("Cash on Delivery", selectedPaymentMethod) { selectedPaymentMethod = "COD" }
//        RadioButtonRow("Online Payment", selectedPaymentMethod) { selectedPaymentMethod = "Online" }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                if (selectedPaymentMethod == "COD") {
//                    val toast = Toast.makeText(context, "Order placed and delivered on $selectedDate", Toast.LENGTH_LONG)
//                    toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//                    toast.show()
//                    context.startActivity(Intent(context, MainActivity::class.java))
//                } else if (selectedPaymentMethod == "Online") {
////                    context.startActivity(Intent(context, OnlinePaymentActivity::class.java))
////                    val intent = Intent(context, OnlinePaymentActivity::class.java).apply {
////                        putExtra("selectedDate", selectedDate) // Pass the selected date
////                    }
//                    val intent = Intent(context, OnlinePaymentActivity::class.java).apply {
//                        putExtra("selectedDate", selectedDate) // Pass the selected date
//                    }
//                    context.startActivity(intent) // Start OnlinePaymentActivity
//                } else {
//                    val toastError = Toast.makeText(context, "Please select a payment method", Toast.LENGTH_SHORT)
//                    toastError.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//                    toastError.show()
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Proceed")
//        }
//    }
//}
//
//@Composable
//fun RadioButtonRow(label: String, selected: String, onClick: () -> Unit) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick)
//            .padding(8.dp)
//    ) {
//        RadioButton(selected = selected == label, onClick = onClick)
//        Text(label, fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp))
//    }
//}====================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//class PaymentActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        val selectedDate = intent.getStringExtra("selectedDate") ?: "No date selected"
//
//        setContent {
//            ModePaymentScreen(selectedDate)
//        }
//    }
//}
//
//@Composable
//fun ModePaymentScreen(selectedDate: String) {
//    val context = LocalContext.current
//    PaymentScreen(context, selectedDate)
//}
//
//@Composable
//fun PaymentScreen(context: Context, selectedDate: String) {
//    var selectedPaymentMethod by remember { mutableStateOf("") }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(text = "Select Payment Method", fontSize = 20.sp)
//
//        Text(text = "Delivery Date: $selectedDate", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
//
//        RadioButtonRow("Cash on Delivery", selectedPaymentMethod) { selectedPaymentMethod = "COD" }
//        RadioButtonRow("Online Payment", selectedPaymentMethod) { selectedPaymentMethod = "Online" }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                when (selectedPaymentMethod) {
//                    "COD" -> {
//                        Toast.makeText(context, "Order placed and delivered on $selectedDate", Toast.LENGTH_LONG).show()
//                        context.startActivity(Intent(context, MainActivity::class.java))
//                    }
//                    "Online" -> {
//                        val intent = Intent(context, OnlinePaymentActivity::class.java).apply {
//                            putExtra("selectedDate", selectedDate)
//                        }
//                        context.startActivity(intent)
//                    }
//                    else -> {
//                        Toast.makeText(context, "Please select a payment method", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Proceed")
//        }
//    }
//}
//
//@Composable
//fun RadioButtonRow(label: String, selected: String, onClick: () -> Unit) {
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable(onClick = onClick)
//            .padding(8.dp)
//    ) {
//        RadioButton(selected = selected == label, onClick = onClick)
//        Text(label, fontSize = 16.sp, modifier = Modifier.padding(start = 8.dp))
//    }
//}
package com.example.laundry


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laundry.ui.theme.AppFonts

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedDate = intent.getStringExtra("selectedDate") ?: "No date selected"
        setContent {
            PaymentScreen(selectedDate)
        }
    }
}

@Composable
fun PaymentScreen(selectedDate: String) {
    val context = LocalContext.current
    var selectedPaymentMethod by remember { mutableStateOf("") } // Holds the full label text

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDAE1ED)),
        contentAlignment = Alignment.Center // Centers the Column
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Select Payment Method",
                style = AppFonts.topic,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Delivery Date: $selectedDate",
                style = AppFonts.subtopic,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                PaymentOptionRow("Cash on Delivery", selectedPaymentMethod) { selectedPaymentMethod = "Cash on Delivery" }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                PaymentOptionRow("Online Payment", selectedPaymentMethod) { selectedPaymentMethod = "Online Payment" }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    when (selectedPaymentMethod) {
                        "Cash on Delivery" -> {
                            Toast.makeText(context, "Order placed! Delivery on $selectedDate", Toast.LENGTH_LONG).show()
                            context.startActivity(Intent(context, MainActivity::class.java))
                        }
                        "Online Payment" -> {
                            val intent = Intent(context, OnlinePaymentActivity::class.java).apply {
                                putExtra("selectedDate", selectedDate)
                            }
                            context.startActivity(intent)
                        }
                        else -> {
                            Toast.makeText(context, "Please select a payment method", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6A4FC8))
            ) {
                Text("Proceed", style = AppFonts.buttoninside, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}



@Composable
fun PaymentOptionRow(label: String, selected: String, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        RadioButton(
            selected = selected == label,  // ✅ Fix: Compare label directly
            onClick = onClick,
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF6A4FC8)) // Optional: Custom selected color
        )
        Text(label, fontSize = 18.sp, style = AppFonts.contents, fontWeight = FontWeight.ExtraBold, modifier = Modifier.padding(start = 12.dp))
    }
}

