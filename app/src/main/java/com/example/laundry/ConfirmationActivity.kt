//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.google.firebase.auth.FirebaseAuth
//
//
//class ConfirmationActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ConfirmationScreen()
//        }
//    }
//
//    @Composable
//    fun ConfirmationScreen() {
//        val context = LocalContext.current
//        val user = FirebaseAuth.getInstance().currentUser
//        var showDialog by remember { mutableStateOf(false) }
//        var isDeleting by remember { mutableStateOf(false) }
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text("Account Deletion", style = MaterialTheme.typography.headlineSmall)
//            Spacer(modifier = Modifier.height(20.dp))
//            Text("Are you sure you want to delete ${user?.email ?: "your account"}?")
//            Spacer(modifier = Modifier.height(20.dp))
//            Button(
//                onClick = { showDialog = true },
//                enabled = !isDeleting
//            ) {
//                Text(if (isDeleting) "Deleting..." else "Confirm Deletion")
//            }
//        }
//
//        if (showDialog) {
//            AlertDialog(
//                onDismissRequest = { showDialog = false },
//                title = { Text("Confirm Deletion") },
//                text = { Text("This action is irreversible. Are you sure?") },
//                confirmButton = {
//                    Button(
//                        onClick = {
//                            showDialog = false
//                            isDeleting = true
//                            deleteUserAccount(context)
//                        }
//                    ) {
//                        Text("Yes, Delete")
//                    }
//                },
//                dismissButton = {
//                    Button(onClick = { showDialog = false }) {
//                        Text("Cancel")
//                    }
//                }
//            )
//        }
//    }
//
//    private fun deleteUserAccount(context: Context) {
//        val user = FirebaseAuth.getInstance().currentUser
//        user?.delete()
//            ?.addOnSuccessListener {
//                Log.d("ConfirmationActivity", "User deleted from Firebase")
//                clearUserPreferences(context)
//                navigateToSignIn(context)
//            }
//            ?.addOnFailureListener { e ->
//                Log.e("ConfirmationActivity", "Failed to delete user: ${e.message}")
//            }
//    }
//
//    private fun clearUserPreferences(context: Context) {
//        val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        with(sharedPref.edit()) {
//            remove("USER_NAME")
//            remove("USER_EMAIL")
//            apply()
//        }
//    }
//
//    private fun navigateToSignIn(context: Context) {
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        finish()
//    }
//}=====================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.google.firebase.auth.FirebaseAuth
//
//class ConfirmationActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ConfirmationScreen(this)
//        }
//    }
//
//    @Composable
//    fun ConfirmationScreen(activity: ConfirmationActivity) {
//        val context = LocalContext.current
//        val user = FirebaseAuth.getInstance().currentUser
//        var showDialog by remember { mutableStateOf(false) }
//        var isDeleting by remember { mutableStateOf(false) }
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Card(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2))
//            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text("Delete Account", style = MaterialTheme.typography.headlineSmall, color = Color.White)
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Text("Delete your account (${user?.email ?: "this"}) permanently?", color = Color.White)
//                    Spacer(modifier = Modifier.height(20.dp))
//                    Button(
//                        onClick = { showDialog = true },
//                        enabled = !isDeleting,
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
//                    ) {
//                        Text(if (isDeleting) "Deleting..." else "Confirm", color = Color.Black)
//                    }
//                }
//            }
//        }
//
//        if (showDialog) {
//            AlertDialog(
//                onDismissRequest = { showDialog = false },
//                title = { Text("Confirm Deletion") },
//                text = { Text("This action cannot be undone. Proceed?") },
//                confirmButton = {
//                    Button(
//                        onClick = {
//                            showDialog = false
//                            isDeleting = true
//                            deleteUserAccount(context, activity)
//                        }
//                    ) {
//                        Text("Yes, Delete")
//                    }
//                },
//                dismissButton = {
//                    Button(onClick = { showDialog = false }) {
//                        Text("Cancel")
//                    }
//                }
//            )
//        }
//    }
//
//    private fun deleteUserAccount(context: Context, activity: ConfirmationActivity) {
//        val user = FirebaseAuth.getInstance().currentUser
//        user?.delete()
//            ?.addOnSuccessListener {
//                Log.d("ConfirmationActivity", "User deleted from Firebase")
//                clearUserPreferences(context)
//                navigateToSignIn(context, activity)
//            }
//            ?.addOnFailureListener { e ->
//                Log.e("ConfirmationActivity", "Failed to delete user: ${e.message}")
//            }
//    }
//
//    private fun clearUserPreferences(context: Context) {
//        val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        with(sharedPref.edit()) {
//            remove("USER_NAME")
//            remove("USER_EMAIL")
//            apply()
//        }
//    }
//
//    private fun navigateToSignIn(context: Context, activity: ConfirmationActivity) {
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        activity.finish() // Ensures ConfirmationActivity is closed
//    }
//}
//
//===================================================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import com.google.firebase.auth.FirebaseAuth
//
//class ConfirmationActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ConfirmationScreen(this)
//        }
//    }
//
//    @Composable
//    fun ConfirmationScreen(activity: ConfirmationActivity) {
//        val context = LocalContext.current
//        val user = FirebaseAuth.getInstance().currentUser
//        var showDialog by remember { mutableStateOf(false) }
//        var isDeleting by remember { mutableStateOf(false) }
//
//        // Get the back press dispatcher
//        val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
//
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Card(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth(),
//                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
//                colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2))
//            ) {
//                Column(
//                    modifier = Modifier.padding(16.dp),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text("Delete Account", style = MaterialTheme.typography.headlineSmall, color = Color.White)
//                    Spacer(modifier = Modifier.height(10.dp))
//                    Text("Delete your account (${user?.email ?: "this"}) permanently?", color = Color.White)
//                    Spacer(modifier = Modifier.height(20.dp))
//                    Button(
//                        onClick = { showDialog = true },
//                        enabled = !isDeleting,
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
//                    ) {
//                        Text(if (isDeleting) "Deleting..." else "Confirm", color = Color.Black)
//                    }
//                    Spacer(modifier = Modifier.height(10.dp))
//                    // Back to Profile button
//                    TextButton(onClick = { backDispatcher?.onBackPressed() }) {
//                        Text("Cancel and Go Back", color = Color.White)
//                    }
//                }
//            }
//        }
//
//        if (showDialog) {
//            AlertDialog(
//                onDismissRequest = { showDialog = false },
//                title = { Text("Confirm Deletion") },
//                text = { Text("This action cannot be undone. Proceed?") },
//                confirmButton = {
//                    Button(
//                        onClick = {
//                            showDialog = false
//                            isDeleting = true
//                            deleteUserAccount(context, activity)
//                        }
//                    ) {
//                        Text("Yes, Delete")
//                    }
//                },
//                dismissButton = {
//                    Button(onClick = { showDialog = false }) {
//                        Text("Cancel")
//                    }
//                }
//            )
//        }
//    }
//
//    private fun deleteUserAccount(context: Context, activity: ConfirmationActivity) {
//        val user = FirebaseAuth.getInstance().currentUser
//        user?.delete()
//            ?.addOnSuccessListener {
//                Log.d("ConfirmationActivity", "User deleted from Firebase")
//                clearUserPreferences(context)
//                navigateToSignIn(context, activity)
//            }
//            ?.addOnFailureListener { e ->
//                Log.e("ConfirmationActivity", "Failed to delete user: ${e.message}")
//            }
//    }
//
//    private fun clearUserPreferences(context: Context) {
//        val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
//        with(sharedPref.edit()) {
//            remove("USER_NAME")
//            remove("USER_EMAIL")
//            apply()
//        }
//    }
//
//    private fun navigateToSignIn(context: Context, activity: ConfirmationActivity) {
//        context.startActivity(Intent(context, SignInActivity::class.java))
//        activity.finish() // Ensures ConfirmationActivity is closed
//    }
//}
package com.example.laundry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.example.laundry.ui.theme.AppFonts

class ConfirmationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConfirmationScreen(this)
        }
    }

    @Composable
    fun ConfirmationScreen(activity: ConfirmationActivity) {
        val context = LocalContext.current
        val user = FirebaseAuth.getInstance().currentUser
        var showDialog by remember { mutableStateOf(false) }
        var isDeleting by remember { mutableStateOf(false) }

        // Get the back press dispatcher
        val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

        Column(
            modifier = Modifier.fillMaxSize()
            .background(Color(0xFFDAE1ED)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2))
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Delete Account", style = AppFonts.topic, color = Color.White,  fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("Are you deleting ${user?.email ?: "this"} account on Laundryes permanently?",style=AppFonts.contents, fontWeight = FontWeight.ExtraBold, color = Color.White,fontSize = 16.sp)
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = { showDialog = true },
                        enabled = !isDeleting,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            disabledContainerColor = Color.White
                        )
                    ) {
                        Text(if (isDeleting) "Deleting..." else "Confirm", color = Color.Black,style=AppFonts.buttoninside)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    // Back to Profile button
                    TextButton(onClick = { backDispatcher?.onBackPressed() }) {
                        Text("Cancel and Go Back", color = Color.White,style=AppFonts.topic)
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirm Deletion") },
                text = { Text("This action cannot be undone. Proceed?") },
                confirmButton = {
                    Button(
                        onClick = {
                            showDialog = false
                            isDeleting = true
                            deleteUserAccount(context, activity)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text("Yes, Delete", color = Color.Black)
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }

    private fun deleteUserAccount(context: Context, activity: ConfirmationActivity) {
        val user = FirebaseAuth.getInstance().currentUser
        user?.delete()
            ?.addOnSuccessListener {
                Log.d("ConfirmationActivity", "User deleted from Firebase")
                clearUserPreferences(context)
                navigateToSignIn(context, activity)
            }
            ?.addOnFailureListener { e ->
                Log.e("ConfirmationActivity", "Failed to delete user: ${e.message}")
            }
    }

    private fun clearUserPreferences(context: Context) {
        val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove("USER_NAME")
            remove("USER_EMAIL")
            apply()
        }
    }

    private fun navigateToSignIn(context: Context, activity: ConfirmationActivity) {
        context.startActivity(Intent(context, SignInActivity::class.java))
        activity.finish() // Ensures ConfirmationActivity is closed
    }
}
