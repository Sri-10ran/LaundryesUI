//package com.example.laundry
//
//import android.app.DatePickerDialog
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//import java.util.*
//
//class TimeDateActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TimeDateScreen()
//        }
//    }
//}
//
//@Composable
//fun TimeDateScreen() {
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    var selectedDate by remember { mutableStateOf("") }
//    val timeSlots = listOf("9 AM - 12 PM", "12 PM - 3 PM", "3 PM - 6 PM")
//    var selectedTimeSlot by remember { mutableStateOf(timeSlots.first()) }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Title
//        Text(
//            text = "Select Drop-off Date & Time",
//            fontSize = 20.sp,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        // Date Picker
//        OutlinedButton(
//            onClick = { showDatePicker(context, calendar) { selectedDate = it } },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text(if (selectedDate.isEmpty()) "Select Date" else selectedDate)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Time Slot Selection
//        Text("Select Time Slot", fontSize = 18.sp)
//        timeSlots.forEach { time ->
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//                    .background(if (selectedTimeSlot == time) Color.Cyan else Color.LightGray, RoundedCornerShape(8.dp))
//                    .clickable { selectedTimeSlot = time }
//                    .padding(12.dp),
//                contentAlignment = Alignment.Center
//            ) {
//                Text(time, fontSize = 16.sp, textAlign = TextAlign.Center)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(24.dp))
//
//        // Confirm Button
//        Button(
//            onClick = {
//                if (selectedDate.isNotEmpty()) {
//                    val intent = Intent(context, PaymentActivity::class.java).apply {
//                        putExtra("selectedDate", selectedDate)
//                        putExtra("selectedTimeSlot", selectedTimeSlot)
//                    }
//                    context.startActivity(intent)
//                } else {
//                    val toast = Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT)
//                    toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//                    toast.show()
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Proceed to Payment Mode")
//        }
//
//    }
//}
//
//// Show Date Picker
//fun showDatePicker(context: android.content.Context, calendar: Calendar, onDateSelected: (String) -> Unit) {
//    val datePicker = DatePickerDialog(
//        context,
//        { _, year, month, day ->
//            val selectedDate = "$day/${month + 1}/$year"
//            onDateSelected(selectedDate)
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    )
//    datePicker.datePicker.minDate = calendar.timeInMillis // Today
//    calendar.add(Calendar.DAY_OF_YEAR, 7)
//    datePicker.datePicker.maxDate = calendar.timeInMillis // 7 days limit
//    datePicker.show()
//}=============================================
//package com.example.laundry
//
//import android.app.DatePickerDialog
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import java.util.*
//
//class TimeDateActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TimeDateScreen()
//        }
//    }
//}
//
//@Composable
//fun TimeDateScreen() {
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    var selectedDate by remember { mutableStateOf("") }
//    val timeSlots = listOf("9 AM - 12 PM", "12 PM - 3 PM", "3 PM - 6 PM")
//    var selectedTimeSlot by remember { mutableStateOf(timeSlots.first()) }
//
//    Scaffold { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//                .padding(paddingValues),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Select Drop-off Date & Time",
//                fontSize = 20.sp,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            OutlinedButton(
//                onClick = { showDatePicker(context, calendar) { selectedDate = it } },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(if (selectedDate.isEmpty()) "Select Date" else selectedDate)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text("Select Time Slot", fontSize = 18.sp)
//            timeSlots.forEach { time ->
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .background(if (selectedTimeSlot == time) Color.Cyan else Color.LightGray, RoundedCornerShape(8.dp))
//                        .clickable { selectedTimeSlot = time }
//                        .padding(12.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(time, fontSize = 16.sp, textAlign = TextAlign.Center)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = {
//                    if (selectedDate.isNotEmpty()) {
//                        val intent = Intent(context, PaymentActivity::class.java).apply {
//                            putExtra("selectedDate", selectedDate)
//                            putExtra("selectedTimeSlot", selectedTimeSlot)
//                        }
//                        context.startActivity(intent)
//                    } else {
//                        Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).apply {
//                            setGravity(android.view.Gravity.BOTTOM or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//                        }.show()
//                    }
//                },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Proceed to Payment Mode")
//            }
//        }
//    }
//}
//
//fun showDatePicker(context: android.content.Context, calendar: Calendar, onDateSelected: (String) -> Unit) {
//    val datePicker = DatePickerDialog(
//        context,
//        { _, year, month, day ->
//            val selectedDate = "$day/${month + 1}/$year"
//            onDateSelected(selectedDate)
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    )
//    datePicker.datePicker.minDate = calendar.timeInMillis
//    calendar.add(Calendar.DAY_OF_YEAR, 7)
//    datePicker.datePicker.maxDate = calendar.timeInMillis
//    datePicker.show()
//}=================================================
//package com.example.laundry
//
//import android.app.DatePickerDialog
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import java.util.*
//
//class TimeDateActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TimeDateScreen()
//        }
//    }
//}
//
//@Composable
//fun TimeDateScreen() {
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    calendar.add(Calendar.DAY_OF_YEAR, 1) // Disable today's date
//    var selectedDate by remember { mutableStateOf("") }
//    val timeSlots = listOf("9 AM - 12 PM", "12 PM - 3 PM", "3 PM - 6 PM")
//    var selectedTimeSlot by remember { mutableStateOf("") }
//
//    Scaffold { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//                .padding(paddingValues),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Select Drop-off Date & Time",
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            Button(
//                onClick = { showDatePicker(context, calendar) { selectedDate = it } },
//                shape = RoundedCornerShape(8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29ABE2)),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(if (selectedDate.isEmpty()) "Select Date" else selectedDate, color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text("Select Time Slot", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
//
//            timeSlots.forEach { time ->
//                Button(
//                    onClick = { selectedTimeSlot = time },
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = if (selectedTimeSlot == time) Color.Cyan else Color.LightGray
//                    ),
//                    shape = RoundedCornerShape(8.dp),
//                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
//                ) {
//                    Text(time, fontSize = 16.sp, textAlign = TextAlign.Center)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Button(
//                onClick = {
//                    if (selectedDate.isNotEmpty() && selectedTimeSlot.isNotEmpty()) {
//                        val intent = Intent(context, PaymentActivity::class.java).apply {
//                            putExtra("selectedDate", selectedDate)
//                            putExtra("selectedTimeSlot", selectedTimeSlot)
//                        }
//                        context.startActivity(intent)
//                    } else {
//                        Toast.makeText(context, "Please select a date and time slot", Toast.LENGTH_SHORT).show()
//                    }
//                },
//                shape = RoundedCornerShape(8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7)),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Proceed to Payment Mode", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//}
//
//fun showDatePicker(context: android.content.Context, calendar: Calendar, onDateSelected: (String) -> Unit) {
//    val datePicker = DatePickerDialog(
//        context,
//        { _, year, month, day ->
//            val selectedDate = "$day/${month + 1}/$year"
//            onDateSelected(selectedDate)
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    )
//    datePicker.datePicker.minDate = calendar.timeInMillis // Disable today's date
//    calendar.add(Calendar.DAY_OF_YEAR, 7)
//    datePicker.datePicker.maxDate = calendar.timeInMillis
//    datePicker.show()
//}

//package com.example.laundry
//
//import android.app.DatePickerDialog
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import java.util.*
//
//class TimeDateActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            TimeDateScreen()
//        }
//    }
//}
//
//@Composable
//fun TimeDateScreen() {
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    calendar.add(Calendar.DAY_OF_YEAR, 1) // Disable today's date
//
//    var selectedDate by remember { mutableStateOf("") }
//    val timeSlots = listOf("9 AM - 12 PM", "12 PM - 3 PM", "3 PM - 6 PM")
//    var selectedTimeSlot by remember { mutableStateOf("") }
//
//    Scaffold { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(16.dp)
//                .padding(paddingValues),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(
//                text = "Select Drop-off Date & Time",
//                fontSize = 22.sp,
//                fontWeight = FontWeight.Bold,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//
//            // Date Selection Card
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2))
//            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text("Select Date", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    Button(
//                        onClick = { showDatePicker(context, calendar) { selectedDate = it } },
//                        shape = RoundedCornerShape(8.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Text(
//                            text = if (selectedDate.isEmpty()) "Choose Date" else selectedDate,
//                            color = if (selectedDate.isEmpty()) Color.Black else Color.Black,
//                            fontSize = 16.sp
//                        )
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Time Slot Selection Card
//            Card(
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2))
//            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text("Select Time Slot", fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
//
//                    Spacer(modifier = Modifier.height(8.dp))
//
//                    timeSlots.forEach { time ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(vertical = 4.dp)
//                                .clickable { selectedTimeSlot = time }
//                                .background(Color.White, shape = RoundedCornerShape(8.dp))
//                                .padding(12.dp),
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(time, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.Black, modifier = Modifier.weight(1f))
//
//                            if (selectedTimeSlot == time) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.delivered), // ✅ Use your tick drawable
//                                    contentDescription = "Selected",
//                                    tint = Color.Green
//                                )
//                            }
//                        }
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//
//            // Proceed Button
//            Button(
//                onClick = {
//                    if (selectedDate.isNotEmpty() && selectedTimeSlot.isNotEmpty()) {
//                        val intent = Intent(context, PaymentActivity::class.java).apply {
//                            putExtra("selectedDate", selectedDate)
//                            putExtra("selectedTimeSlot", selectedTimeSlot)
//                        }
//                        context.startActivity(intent)
//                    } else {
//                        Toast.makeText(context, "Please select a date and time slot", Toast.LENGTH_SHORT).show()
//                    }
//                },
//                shape = RoundedCornerShape(8.dp),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7)),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Proceed to Payment Mode", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//}
//
//// Date Picker Function
//fun showDatePicker(context: android.content.Context, calendar: Calendar, onDateSelected: (String) -> Unit) {
//    val datePicker = DatePickerDialog(
//        context,
//        { _, year, month, day ->
//            val selectedDate = "$day/${month + 1}/$year"
//            onDateSelected(selectedDate)
//        },
//        calendar.get(Calendar.YEAR),
//        calendar.get(Calendar.MONTH),
//        calendar.get(Calendar.DAY_OF_MONTH)
//    )
//    datePicker.datePicker.minDate = calendar.timeInMillis // Disable today's date
//    calendar.add(Calendar.DAY_OF_YEAR, 7)
//    datePicker.datePicker.maxDate = calendar.timeInMillis
//    datePicker.show()
//}
package com.example.laundry

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border

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
import java.util.*

class TimeDateActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TimeDateScreen()
        }
    }
}

@Composable
fun TimeDateScreen() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.DAY_OF_YEAR, 1) // Disable today's date

    var selectedDate by remember { mutableStateOf("") }
    val timeSlots = listOf("9 AM - 12 PM", "12 PM - 3 PM", "3 PM - 6 PM")
    var selectedTimeSlot by remember { mutableStateOf("") }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDAE1ED))
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Select Drop-off Date & Time",
                    style = AppFonts.topic,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                // Date Selection Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Select Date",style=AppFonts.subtopic, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = Color.White)

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { showDatePicker(context, calendar) { selectedDate = it } },
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (selectedDate.isEmpty()) "Choose Date" else selectedDate,
                                color = Color.Black,
                                style = AppFonts.buttoninside,
                                fontSize = 16.sp
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Time Slot Selection Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Select Time Slot", style = AppFonts.subtopic, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp, color = Color.White)

                        Spacer(modifier = Modifier.height(8.dp))

                        timeSlots.forEach { time ->
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clickable { selectedTimeSlot = time }
                                    .border(
                                        width = if (selectedTimeSlot == time) 2.dp else 0.dp,
                                        color = if (selectedTimeSlot == time) Color.Black else Color.Transparent,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                shape = RoundedCornerShape(8.dp),
                                color = Color.White,
                                shadowElevation = if (selectedTimeSlot == time) 6.dp else 0.dp
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(12.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(time, style = AppFonts.buttoninside, fontSize = 16.sp, color = Color.Black)
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Proceed Button
                Button(
                    onClick = {
                        if (selectedDate.isNotEmpty() && selectedTimeSlot.isNotEmpty()) {
                            val intent = Intent(context, PaymentActivity::class.java).apply {
                                putExtra("selectedDate", selectedDate)
                                putExtra("selectedTimeSlot", selectedTimeSlot)
                            }
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "Please select a date and time slot", Toast.LENGTH_SHORT).show()
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Proceed to Payment Mode", style = AppFonts.buttoninside, color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}

// Date Picker Function
fun showDatePicker(context: android.content.Context, calendar: Calendar, onDateSelected: (String) -> Unit) {
    val datePicker = DatePickerDialog(
        context,
        { _, year, month, day ->
            val selectedDate = "$day/${month + 1}/$year"
            onDateSelected(selectedDate)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
    datePicker.datePicker.minDate = calendar.timeInMillis // Disable today's date
    calendar.add(Calendar.DAY_OF_YEAR, 7)
    datePicker.datePicker.maxDate = calendar.timeInMillis
    datePicker.show()
}

