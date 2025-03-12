////package com.example.laundry
////
////import android.content.Context
////import android.content.Intent
////import android.os.Bundle
////import androidx.activity.ComponentActivity
////import androidx.activity.compose.setContent
////import androidx.compose.foundation.Image
////import androidx.compose.foundation.background
////import androidx.compose.foundation.layout.*
////import androidx.compose.material3.*
////import androidx.compose.runtime.*
////import androidx.compose.ui.Alignment
////import androidx.compose.ui.Modifier
////import androidx.compose.ui.graphics.Color
////import androidx.compose.ui.res.painterResource
////import androidx.compose.ui.text.font.FontWeight
////import androidx.compose.ui.unit.dp
////import androidx.compose.ui.unit.sp
////import com.google.firebase.auth.FirebaseAuth
////
////class Profile : ComponentActivity() {
////    override fun onCreate(savedInstanceState: Bundle?) {
////        super.onCreate(savedInstanceState)
////
////        setContent {
////            ProfileScreen(this)
////            TopBar()
////
////        }
////    }
////    @Composable
////    fun TopBar() {
////        Row(
////            modifier = Modifier
////                .fillMaxWidth()
////                .background(Color(0xFF29ABE2))
////                .padding(16.dp),
////            verticalAlignment = Alignment.CenterVertically
////        ) {
////            Text(
////                text = "Account",
////                color = Color.White,
////                fontSize = 20.sp,
////                fontWeight = FontWeight.Bold,
////                modifier = Modifier.weight(1f)
////            )
////            Image(
////                painter = painterResource(id = R.drawable.profile),
////                contentDescription = "Home Icon",
////                modifier = Modifier.size(24.dp)
////            )
////        }
////    }
////
////    @Composable
////    fun ProfileScreen(context: Context) {
////        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
////        val userName = sharedPreferences.getString("USER_NAME", "User") // Fixed key
////        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") // Fixed key
////
////        Column(
////            modifier = Modifier.fillMaxSize(),
////            verticalArrangement = Arrangement.Center,
////            horizontalAlignment = Alignment.CenterHorizontally
////        ) {
////            Text("Profile Name: $userName")
////            Text("Email: $email")
////
////            Button(onClick = { logout() }) {
////                Text("Logout")
////            }
////
////
////            Spacer(modifier = Modifier.height(16.dp))
////
////            // Delete Account Button
////            Button(
////                onClick = {
////                    val intent = Intent(context, ConfirmationActivity::class.java)
////                    context.startActivity(intent)
////                },
////                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
////            ) {
////                Text("Delete Account")
////            }
////        }
////    }
////
////    private fun logout() {
////        FirebaseAuth.getInstance().signOut()
////        getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
////        startActivity(Intent(this, SignInActivity::class.java))
////        finish()
////    }
////}
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.google.firebase.auth.FirebaseAuth
//import java.text.SimpleDateFormat
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter
//import java.util.Calendar
//import java.util.Locale
//
//class Profile : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        saveJoinDate(this) // Ensure joining date is stored
//        setContent {
//            ProfileScreen(this)
//        }
//    }
//
//    @Composable
//    fun ProfileScreen(context: Context) {
//        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val userName = sharedPreferences.getString("USER_NAME", "User") ?: "User"
//        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") ?: "Not Available"
//        val joinDate = sharedPreferences.getString("JOIN_DATE", "Date Unavailable") ?: "Date Unavailable"
//        val totalOrders = 0 // To be integrated with Firebase later
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFFFF0F5))
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "Profile Image",
//                modifier = Modifier.size(100.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = userName, fontSize = 22.sp, fontWeight = FontWeight.Bold)
//            Text(text = email, fontSize = 16.sp, color = Color.Gray)
//
//            Text(text = "Joined $joinDate", fontSize = 14.sp, color = Color.Gray)
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Card(
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(Color.White),
//                elevation = CardDefaults.cardElevation(4.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Row(
//                    modifier = Modifier.padding(16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.order),
//                            contentDescription = "Orders Icon",
//                            tint = Color(0xFF29ABE2),
//                            modifier = Modifier.size(32.dp)
//                        )
//                        Text(text = "$totalOrders", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "Total Orders", fontSize = 12.sp, color = Color.Gray)
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Button(
//                onClick = { logout(context) },
//                colors = ButtonDefaults.buttonColors(Color(0xFF29ABE2)),
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.logout_icon),
//                    contentDescription = "Logout Icon",
//                    tint = Color.White
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Logout", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    val intent = Intent(context, ConfirmationActivity::class.java)
//                    context.startActivity(intent)
//                },
//                colors = ButtonDefaults.buttonColors(Color.Red),
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.delete_icon),
//                    contentDescription = "Delete Icon",
//                    tint = Color.White
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Delete Account", color = Color.White)
//            }
//        }
//    }
//
//    private fun logout(context: Context) {
//        FirebaseAuth.getInstance().signOut()
//        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        (context as? ComponentActivity)?.finish()
//    }
//
//    private fun saveJoinDate(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        if (!sharedPreferences.contains("JOIN_DATE")) {
//            val currentDate = getCurrentDate() // Get date using SimpleDateFormat
//            sharedPreferences.edit().putString("JOIN_DATE", currentDate).apply()
//        }
//    }
//
//    private fun getCurrentDate(): String {
//        val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
//        return dateFormat.format(Calendar.getInstance().time)
//    }
//
//}=============================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.google.firebase.auth.FirebaseAuth
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//class Profile : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        saveJoinDate(this)
//        setContent {
//            ProfileScreen(this)
//        }
//    }
//
//    @Composable
//    fun ProfileScreen(context: Context) {
//        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val userName = sharedPreferences.getString("USER_NAME", "User") ?: "User"
//        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") ?: "Not Available"
//        val joinDate = sharedPreferences.getString("JOIN_DATE", "Date Unavailable") ?: "Date Unavailable"
//        val totalOrders = 0
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFFFF0F5))
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "Profile Image",
//                modifier = Modifier.size(100.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = userName, fontSize = 22.sp, fontWeight = FontWeight.Bold)
//            Text(text = email, fontSize = 16.sp, color = Color.Gray)
//            Text(text = "Joined $joinDate", fontSize = 14.sp, color = Color.Gray)
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Card(
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(Color.White),
//                elevation = CardDefaults.cardElevation(4.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Row(
//                    modifier = Modifier.padding(16.dp),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.order),
//                            contentDescription = "Orders Icon",
//                            tint = Color(0xFF29ABE2),
//                            modifier = Modifier.size(32.dp)
//                        )
//                        Text(text = "$totalOrders", fontSize = 18.sp, fontWeight = FontWeight.Bold)
//                        Text(text = "Total Orders", fontSize = 12.sp, color = Color.Gray)
//                    }
//                }
//            }
//            Spacer(modifier = Modifier.height(20.dp))
//
//            Button(
//                onClick = { logout(context) },
//                colors = ButtonDefaults.buttonColors(Color(0xFF29ABE2)),
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.logout_icon),
//                    contentDescription = "Logout Icon",
//                    tint = Color.White
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Logout", color = Color.White)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    val intent = Intent(context, ConfirmationActivity::class.java)
//                    context.startActivity(intent)
//                },
//                colors = ButtonDefaults.buttonColors(Color.Red),
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.delete_icon),
//                    contentDescription = "Delete Icon",
//                    tint = Color.White
//                )
//                Spacer(modifier = Modifier.width(8.dp))
//                Text("Delete Account", color = Color.White)
//            }
//        }
//    }
//
//    private fun logout(context: Context) {
//        FirebaseAuth.getInstance().signOut()
//        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        (context as? ComponentActivity)?.finish()
//    }
//
//    private fun saveJoinDate(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        if (!sharedPreferences.contains("JOIN_DATE")) {
//            val currentDate = getCurrentDate()
//            sharedPreferences.edit().putString("JOIN_DATE", currentDate).apply()
//        }
//    }
//
//    private fun getCurrentDate(): String {
//        return SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(System.currentTimeMillis())
//    }
//}===================================================================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.google.firebase.auth.FirebaseAuth
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//class Profile : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        saveJoinDate(this)
//        setContent {
//            ProfileScreen(this)
//        }
//    }
//
//    @Composable
//    fun ProfileScreen(context: Context) {
//        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val userName = sharedPreferences.getString("USER_NAME", "User") ?: "User"
//        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") ?: "Not Available"
//        val joinDate = sharedPreferences.getString("JOIN_DATE", "Date Unavailable") ?: "Date Unavailable"
//        val totalOrders = 0
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFF5F5F5))
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "Profile Image",
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFF29ABE2))
//
//
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(text = userName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
//            Text(text = email, fontSize = 16.sp, color = Color.Gray)
//            Text(text = "Joined: $joinDate", fontSize = 14.sp, color = Color.Gray)
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Card(
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(Color.White),
//                elevation = CardDefaults.cardElevation(6.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(
//                    modifier = Modifier.padding(20.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.order),
//                        contentDescription = "Orders Icon",
//                        tint = Color.Black,
//                        modifier = Modifier.size(36.dp)
//                    )
//                    Spacer(modifier = Modifier.height(8.dp))
//                    Text(text = "$totalOrders", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//                    Text(text = "Total Orders", fontSize = 14.sp, color = Color.Gray)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//            Button(
//                onClick = { logout(context) },
//                colors = ButtonDefaults.buttonColors(Color(0xFF29ABE2)),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.logout_icon),
//                    contentDescription = "Logout Icon",
//                    tint = Color.White,
//                    modifier = Modifier
//                        .size(29.dp)
//                )
//                Spacer(modifier = Modifier.width(3.dp))
//                Text("Logout", color = Color.White, fontSize = 16.sp)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    val intent = Intent(context, ConfirmationActivity::class.java)
//                    context.startActivity(intent)
//                },
//                colors = ButtonDefaults.buttonColors(Color.Red),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.delete_icon),
//                    contentDescription = "Delete Account Icon",
//                    tint = Color.White ,
//                    modifier = Modifier
//                            .size(29.dp)
//                )
//                Spacer(modifier = Modifier.width(3.dp))
//                Text("Delete Account", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//
//    private fun logout(context: Context) {
//        FirebaseAuth.getInstance().signOut()
//        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        (context as? ComponentActivity)?.finish()
//    }
//
//    private fun saveJoinDate(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        if (!sharedPreferences.contains("JOIN_DATE")) {
//            val currentDate = getCurrentDate()
//            sharedPreferences.edit().putString("JOIN_DATE", currentDate).apply()
//        }
//    }
//
//    private fun getCurrentDate(): String {
//        return SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(System.currentTimeMillis())
//    }
//}==============================================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.google.firebase.auth.FirebaseAuth
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//class Profile : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        saveJoinDate(this)
//        setContent {
//            ProfileScreen(this)
//        }
//    }
//
//    @Composable
//    fun ProfileScreen(context: Context) {
//        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val userName = sharedPreferences.getString("USER_NAME", "User") ?: "User"
//        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") ?: "Not Available"
//        val joinDate = sharedPreferences.getString("JOIN_DATE", "Date Unavailable") ?: "Date Unavailable"
//        val totalOrders = 0
//        val deliveredOrders = 0
//        val pendingOrders = 0
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFF5F5F5))
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "Profile Image",
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFF29ABE2))
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(text = userName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
//            Text(text = email, fontSize = 16.sp, color = Color.Gray)
//            Text(text = "Joined: $joinDate", fontSize = 14.sp, color = Color.Gray)
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Card(
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(Color.White),
//                elevation = CardDefaults.cardElevation(6.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Row(
//                    modifier = Modifier.padding(20.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    OrderStat(iconId = R.drawable.order, label = "Total Orders", count = totalOrders)
//                    OrderStat(iconId = R.drawable.delivered, label = "Delivered", count = deliveredOrders)
//                    OrderStat(iconId = R.drawable.pending, label = "Pending", count = pendingOrders)
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//            Button(
//                onClick = { logout(context) },
//                colors = ButtonDefaults.buttonColors(Color(0xFF29ABE2)),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.logout_icon),
//                    contentDescription = "Logout Icon",
//                    tint = Color.White,
//                    modifier = Modifier.size(29.dp)
//                )
//                Spacer(modifier = Modifier.width(5.dp))
//                Text("Logout", color = Color.White, fontSize = 16.sp)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    val intent = Intent(context, ConfirmationActivity::class.java)
//                    context.startActivity(intent)
//                },
//                colors = ButtonDefaults.buttonColors(Color.Red),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.delete_icon),
//                    contentDescription = "Delete Account Icon",
//                    tint = Color.White,
//                    modifier = Modifier.size(29.dp)
//                )
//                Spacer(modifier = Modifier.width(5.dp))
//                Text("Delete Account", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//
//    @Composable
//    fun OrderStat(iconId: Int, label: String, count: Int) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Icon(
//                painter = painterResource(id = iconId),
//                contentDescription = label,
//                tint = Color.Black,
//                modifier = Modifier.size(36.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = "$count", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            Text(text = label, fontSize = 14.sp, color = Color.Gray)
//        }
//    }
//
//    private fun logout(context: Context) {
//        FirebaseAuth.getInstance().signOut()
//        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        (context as? ComponentActivity)?.finish()
//    }
//
//    private fun saveJoinDate(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        if (!sharedPreferences.contains("JOIN_DATE")) {
//            val currentDate = getCurrentDate()
//            sharedPreferences.edit().putString("JOIN_DATE", currentDate).apply()
//        }
//    }
//
//    private fun getCurrentDate(): String {
//        return SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()).format(System.currentTimeMillis())
//    }
//}============================================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.google.firebase.auth.FirebaseAuth
//import java.text.SimpleDateFormat
//import java.util.Locale
//
//class Profile : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        saveJoinDate(this)
//        setContent {
//            ProfileScreen(this)
//        }
//    }
//
//    @Composable
//    fun ProfileScreen(context: Context) {
//        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val userName = sharedPreferences.getString("USER_NAME", "User") ?: "User"
//        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") ?: "Not Available"
//        val joinDate = sharedPreferences.getString("JOIN_DATE", "Date Unavailable") ?: "Date Unavailable"
//        val totalOrders = 0
//        val deliveredOrders = 0
//        val pendingOrders = 0
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFDAE1ED))
//                .padding(16.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Spacer(modifier = Modifier.height(20.dp))
//            Image(
//                painter = painterResource(id = R.drawable.profile),
//                contentDescription = "Profile Image",
//
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(CircleShape)
//                    .background(Color(0xFFDAE1ED))
//            )
//            Spacer(modifier = Modifier.height(12.dp))
//            Text(text = userName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
//            Text(text = email, fontSize = 16.sp, color = Color.Gray)
//            Text(text = "Joined: $joinDate", fontSize = 14.sp, color = Color.Gray)
//            Spacer(modifier = Modifier.height(24.dp))
//
//            Card(
//                shape = RoundedCornerShape(12.dp),
//                colors = CardDefaults.cardColors(Color.White),
//                elevation = CardDefaults.cardElevation(6.dp),
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Column(
//                    modifier = Modifier.padding(20.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween,
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        OrderStat(iconId = R.drawable.order, label = "Total Orders", count = totalOrders)
//                        OrderStat(iconId = R.drawable.delivered, label = "Delivered", count = deliveredOrders)
//                        OrderStat(iconId = R.drawable.pending, label = "Pending", count = pendingOrders)
//                    }
//                }
//            }
//
//            Spacer(modifier = Modifier.height(24.dp))
//            Button(
//                onClick = { logout(context) },
//                colors = ButtonDefaults.buttonColors(Color(0xFF29ABE2)),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.logout_icon),
//                    contentDescription = "Logout Icon",
//                    tint = Color.White,
//                    modifier = Modifier.size(29.dp)
//                )
//                Spacer(modifier = Modifier.width(5.dp))
//                Text("Logout", color = Color.White, fontSize = 16.sp)
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//            Button(
//                onClick = {
//                    val intent = Intent(context, ConfirmationActivity::class.java)
//                    context.startActivity(intent)
//                },
//                colors = ButtonDefaults.buttonColors(Color.Red),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(50.dp),
//                shape = RoundedCornerShape(8.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.delete_icon),
//                    contentDescription = "Delete Account Icon",
//                    tint = Color.White,
//                    modifier = Modifier.size(29.dp)
//                )
//                Spacer(modifier = Modifier.width(5.dp))
//                Text("Delete Account", color = Color.White, fontSize = 16.sp)
//            }
//        }
//    }
//
//    @Composable
//    fun OrderStat(iconId: Int, label: String, count: Int) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Icon(
//                painter = painterResource(id = iconId),
//                contentDescription = label,
//                tint = Color.Black,
//                modifier = Modifier.size(36.dp)
//            )
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(text = "$count", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            Text(text = label, fontSize = 14.sp, color = Color.Gray)
//        }
//    }
//
//    private fun logout(context: Context) {
//        FirebaseAuth.getInstance().signOut()
//        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        (context as? ComponentActivity)?.finish()
//    }
//
//    private fun saveJoinDate(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        if (!sharedPreferences.contains("JOIN_DATE")) {
//            val currentDate = getCurrentDate()
//            sharedPreferences.edit().putString("JOIN_DATE", currentDate).apply()
//        }
//    }
//
//    private fun getCurrentDate(): String {
//        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(System.currentTimeMillis())
//    }
//}============================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.google.firebase.auth.FirebaseAuth
//import java.text.SimpleDateFormat
//
//
//import java.util.Locale
//
//class Profile : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        saveJoinDate(this)
//        setContent {
//            ProfileScreen(this)
//        }
//    }
//
//
//    @Composable
//    fun ProfileScreen(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        val userName = sharedPreferences.getString("USER_NAME", "User") ?: "User"
//        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") ?: "Not Available"
//        val joinDate = sharedPreferences.getString("JOIN_DATE", "Date Unavailable") ?: "Date Unavailable"
//        val totalOrders = 0
//        val deliveredOrders = 0
//        val pendingOrders = 0
//
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(Color(0xFFDAE1ED))
//        ) {
//            // ✅ TopBarProfile inside the Column
//            TopBarProfile()
//
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Spacer(modifier = Modifier.height(20.dp))
//                Image(
//                    painter = painterResource(id = R.drawable.profile),
//                    contentDescription = "Profile Image",
//                    modifier = Modifier
//                        .size(120.dp)
//                        .clip(CircleShape)
//                        .background(Color(0xFFDAE1ED))
//                )
//                Spacer(modifier = Modifier.height(12.dp))
//                Text(text = userName, fontSize = 24.sp, fontWeight = FontWeight.Bold)
//                Text(text = email, fontSize = 16.sp, color = Color.Gray)
//                Text(text = "Joined: $joinDate", fontSize = 14.sp, color = Color.Gray)
//                Spacer(modifier = Modifier.height(24.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    OrderStatCard(iconId = R.drawable.order, label = "Total Orders", count = totalOrders)
//                    OrderStatCard(iconId = R.drawable.delivered, label = "Dropped Off", count = deliveredOrders)
//                    OrderStatCard(iconId = R.drawable.pending, label = "In Progress", count = pendingOrders)
//                }
//
//                Spacer(modifier = Modifier.height(24.dp))
//                Button(
//                    onClick = { logout(context) },
//                    colors = ButtonDefaults.buttonColors(Color(0xFF29ABE2)),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.logout_icon),
//                        contentDescription = "Logout Icon",
//                        tint = Color.White,
//                        modifier = Modifier.size(29.dp)
//                    )
//                    Spacer(modifier = Modifier.width(5.dp))
//                    Text("Logout", color = Color.White, fontSize = 16.sp)
//                }
//
//                Spacer(modifier = Modifier.height(16.dp))
//                Button(
//                    onClick = {
//                        val intent = Intent(context, ConfirmationActivity::class.java)
//                        context.startActivity(intent)
//                    },
//                    colors = ButtonDefaults.buttonColors(Color.Red),
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(50.dp),
//                    shape = RoundedCornerShape(8.dp)
//                ) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.delete_icon),
//                        contentDescription = "Delete Account Icon",
//                        tint = Color.White,
//                        modifier = Modifier.size(29.dp)
//                    )
//                    Spacer(modifier = Modifier.width(5.dp))
//                    Text("Delete Account", color = Color.White, fontSize = 16.sp)
//                }
//            }
//        }
//    }
//
//
//    @Composable
//    fun OrderStatCard(iconId: Int, label: String, count: Int) {
//        Card(
//            shape = RoundedCornerShape(12.dp),
//            colors = CardDefaults.cardColors(Color(0xFF29ABE2)),
//            elevation = CardDefaults.cardElevation(6.dp),
////            modifier = Modifier.weight(1f).padding(4.dp)
//        ) {
//            Column(
//                modifier = Modifier.padding(16.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Icon(
//                    painter = painterResource(id = iconId),
//                    contentDescription = label,
//                    tint = Color.White,
//                    modifier = Modifier.size(36.dp)
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//                Text(text = "$count", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
//                Text(text = label, fontSize = 14.sp, color = Color.White)
//            }
//        }
//    }
//
//    private fun logout(context: Context) {
//        FirebaseAuth.getInstance().signOut()
//        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        (context as? ComponentActivity)?.finish()
//    }
//
//    private fun saveJoinDate(context: Context) {
//        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        if (!sharedPreferences.contains("JOIN_DATE")) {
//            val currentDate = getCurrentDate()
//            sharedPreferences.edit().putString("JOIN_DATE", currentDate).apply()
//        }
//    }
//
//    private fun getCurrentDate(): String {
//        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(System.currentTimeMillis())
//    }
//}
//@Composable
//fun TopBarProfile() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF29ABE2))
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "Account",
//            color = Color.White,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.weight(1f)
//        )
//        Image(
//            painter = painterResource(id = R.drawable.profile),
//            contentDescription = "Home Icon",
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}
package com.example.laundry

import android.content.Context
import android.content.Intent
import com.example.laundry.ui.theme.AppFonts
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Locale

class Profile : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        saveJoinDate(this)
        setContent {
            ProfileScreen(this)
        }
    }

    @Composable
    fun ProfileScreen(context: Context) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userName = sharedPreferences.getString("USER_NAME", "User") ?: "User"
        val email = sharedPreferences.getString("USER_EMAIL", "Not Available") ?: "Not Available"
        val joinDate = sharedPreferences.getString("JOIN_DATE", "Date Unavailable") ?: "Date Unavailable"
        val totalOrders = 0
        val deliveredOrders = 0
        val pendingOrders = 0

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFDAE1ED))
        ) {
            TopBarProfile() // ✅ Top bar inside Column

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFDAE1ED))
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = userName, fontSize = 24.sp, style = AppFonts.subtopic,fontWeight = FontWeight.Bold)
                Text(text = email, fontSize = 16.sp, style = AppFonts.subtopic,fontWeight = FontWeight.Normal, color = Color.Gray)
                Text(text = "Joined: $joinDate", fontSize = 14.sp,style = AppFonts.subtopic,fontWeight = FontWeight.Normal,  color = Color.Gray)
                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    OrderStatCard(iconId = R.drawable.order, label = "Total Orders", count = totalOrders)
                    OrderStatCard(iconId = R.drawable.delivered, label = "Dropped Off", count = deliveredOrders)
                    OrderStatCard(iconId = R.drawable.pending, label = "In Progress", count = pendingOrders)
                }

                Spacer(modifier = Modifier.height(24.dp))

                // ✅ Logout Button
                Button(
                    onClick = { logout(context) },
                    colors = ButtonDefaults.buttonColors(Color(0xFF29ABE2)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout_icon),
                        contentDescription = "Logout Icon",
                        tint = Color.White,
                        modifier = Modifier.size(29.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Logout", style = AppFonts.buttoninside, color = Color.White, fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ✅ Delete Account Button
                Button(
                    onClick = {
                        val intent = Intent(context, ConfirmationActivity::class.java)
                        context.startActivity(intent)
                        // Do not finish() here, so the user can return to Profile if they cancel deletion
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete_icon),
                        contentDescription = "Delete Account Icon",
                        tint = Color.White,
                        modifier = Modifier.size(29.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Delete Account", style = AppFonts.buttoninside, color = Color.White, fontSize = 16.sp)
                }

            }
        }
    }

    @Composable
    fun OrderStatCard(iconId: Int, label: String, count: Int) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(Color(0xFF29ABE2)),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = label,
                    tint = Color.White,
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "$count",style = AppFonts.contents, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = label, style = AppFonts.contents, fontSize = 14.sp,fontWeight = FontWeight.Bold, color = Color.White)
            }
        }
    }

    private fun logout(context: Context) {
        FirebaseAuth.getInstance().signOut()
        context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE).edit().clear().apply()

        val intent = Intent(context, SignInActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Ensures no back navigation
        context.startActivity(intent)

        (context as? ComponentActivity)?.finish() // ✅ Finishes Profile activity
    }

    private fun saveJoinDate(context: Context) {
        val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        if (!sharedPreferences.contains("JOIN_DATE")) {
            val currentDate = getCurrentDate()
            sharedPreferences.edit().putString("JOIN_DATE", currentDate).apply()
        }
    }

    private fun getCurrentDate(): String {
        return SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(System.currentTimeMillis())
    }
}

@Composable
fun TopBarProfile() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF29ABE2))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Account",
            color = Color.White,
            style = AppFonts.topic,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Home Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}
