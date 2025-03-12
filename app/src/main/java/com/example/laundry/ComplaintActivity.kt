//
//package com.example.laundry
//
//
//import android.content.Context
//import android.content.Intent
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.rememberLauncherForActivityResult
//import androidx.activity.compose.setContent
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import kotlinx.coroutines.*
//import okhttp3.*
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//
//import okhttp3.RequestBody.Companion.asRequestBody
//import okhttp3.RequestBody.Companion.toRequestBody
//import java.io.File
//
//class ComplaintActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ComplaintScreen()
//        }
//    }
//}
//
//@Composable
//fun ComplaintScreen() {
//    val context = LocalContext.current
//    var orderId by remember { mutableStateOf("") }
//    var problemDescription by remember { mutableStateOf("") }
//    var imageUri by remember { mutableStateOf<Uri?>(null) }
//    var showDialog by remember { mutableStateOf(false) }
//    val dialogMessage = remember { mutableStateOf("") }
//
//    val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
//        imageUri = uri
//        if (uri != null) {
//            val toast = Toast.makeText(context, "Image selected successfully!", Toast.LENGTH_SHORT)
//            toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//            toast.show()
//        }
//    }
//
//
//    Column(
//        modifier = Modifier.fillMaxSize().padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        OutlinedTextField(
//            value = orderId,
//            onValueChange = { orderId = it },
//            label = { Text("Order ID") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        OutlinedTextField(
//            value = problemDescription,
//            onValueChange = { problemDescription = it },
//            label = { Text("Problem Description") },
//            modifier = Modifier.fillMaxWidth()
//        )
//
//        Spacer(modifier = Modifier.height(12.dp))
//
//        Button(onClick = { pickImageLauncher.launch("image/*") }, modifier = Modifier.fillMaxWidth()) {
//            Text("Attach Photo Proof")
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = {
//                if (orderId.isEmpty() || problemDescription.isEmpty() || imageUri == null) {
//                    val toast = Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT)
//                    toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//                    toast.show()
//                } else {
//                    CoroutineScope(Dispatchers.IO).launch {
//                        val success = sendComplaintToServer(context, orderId, problemDescription, imageUri!!)
//                        withContext(Dispatchers.Main) {
//                            dialogMessage.value = if (success) "Email sent" else "Failed to send"
//                            showDialog = true
//                        }
//                    }
//                }
//            },
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Submit Complaint")
//        }
//
//
//        if (showDialog) {
//            AlertDialog(
//                onDismissRequest = { showDialog = false },
//                title = { Text("Status") },
//                text = { Text(dialogMessage.value) },
//                confirmButton = {
//                    Button(onClick = {
//                        showDialog = false
//                        if (dialogMessage.value == "Email sent") {
//                            val intent = Intent(context, MainActivity::class.java)
//                            context.startActivity(intent)
//                        }
//                    }) {
//                        Text("OK")
//                    }
//                }
//            )
//        }
//    }
//}
//
//// Function to send complaint using OkHttp
//suspend fun sendComplaintToServer(
//    context: Context,
//    orderId: String,
//    problemDescription: String,
//    imageUri: Uri
//): Boolean {
//    val client = OkHttpClient()
//
//    val imageFile = File(context.cacheDir, "proof.jpg")
//    context.contentResolver.openInputStream(imageUri)?.use { input ->
//        imageFile.outputStream().use { output -> input.copyTo(output) }
//    }
//
//    val orderIdBody = orderId.toRequestBody("text/plain".toMediaTypeOrNull())
//    val problemDescriptionBody = problemDescription.toRequestBody("text/plain".toMediaTypeOrNull())
//    val imageRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//
//    val requestBody = MultipartBody.Builder()
//        .setType(MultipartBody.FORM)
//        .addFormDataPart("order_id", null, orderIdBody)  // Matching backend key
//        .addFormDataPart("problem_description", null, problemDescriptionBody)  // Matching backend key
//        .addFormDataPart("photo", imageFile.name, imageRequestBody)  // Matching backend key
//        .build()
//
//    val request = Request.Builder()
//        .url("https://mailsupportlaundryes-1.onrender.com/send-email") // Replace with Render URL
//        .post(requestBody)
//        .build()
//
//    return try {
//        val response = client.newCall(request).execute()
//        val responseBody = response.body?.string()
//        Log.d("ComplaintAPI", "Response: $responseBody")  // Log response
//        response.isSuccessful
//    } catch (e: Exception) {
//        Log.e("ComplaintAPI", "Error: ${e.message}", e)
//        false
//    }
//}
//
//
//
//

package com.example.laundry

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laundry.ui.theme.AppFonts

import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ComplaintActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComplaintScreen()
        }
    }
}

@Composable
fun ComplaintScreen() {
    val context = LocalContext.current
    var orderId by remember { mutableStateOf("") }
    var problemDescription by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    val dialogMessage = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDAE1ED))
    ) {
        TopBarC()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp), // Adjusted padding
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Moved inside Column for proper layout

        Spacer(modifier = Modifier.height(16.dp)) // Space between TopBar and Form

        OutlinedTextField(
            value = orderId,
            onValueChange = { orderId = it },
            label = { Text(text = "Order ID",style=AppFonts.subtopic, fontWeight = FontWeight.Bold) },
            textStyle = AppFonts.contents,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = problemDescription,
            onValueChange = { problemDescription = it },
            label = { Text(text = "Problem description",style=AppFonts.subtopic, fontWeight = FontWeight.Bold) },
            textStyle = AppFonts.contents,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        val pickImageLauncher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            imageUri = uri
            if (uri != null) {
                Toast.makeText(context, "Image selected successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        Button(onClick = { pickImageLauncher.launch("image/*") }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Attach Photo Proof",style=AppFonts.buttoninside, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (orderId.isEmpty() || problemDescription.isEmpty() || imageUri == null) {
                    Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show()
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        val success = sendComplaintToServer(context, orderId, problemDescription, imageUri!!)
                        withContext(Dispatchers.Main) {
                            dialogMessage.value = if (success) "Email sent" else "Failed to send"
                            showDialog = true
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Submit complaint",style=AppFonts.buttoninside, fontWeight = FontWeight.Bold)
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Status") },
                text = { Text(dialogMessage.value) },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                        if (dialogMessage.value == "Email sent") {
                            val intent = Intent(context, MainActivity::class.java)
                            context.startActivity(intent)
                        }
                    }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}}

//suspend fun sendComplaintToServer(
//    context: Context,
//    orderId: String,
//    problemDescription: String,
//    imageUri: Uri
//): Boolean {
//    val client = OkHttpClient()
//
//    val imageFile = File(context.cacheDir, "proof.jpg")
//    context.contentResolver.openInputStream(imageUri)?.use { input ->
//        imageFile.outputStream().use { output -> input.copyTo(output) }
//    }
//
//    val orderIdBody = orderId.toRequestBody("text/plain".toMediaTypeOrNull())
//    val problemDescriptionBody = problemDescription.toRequestBody("text/plain".toMediaTypeOrNull())
//    val imageRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())
//
//    val requestBody = MultipartBody.Builder()
//        .setType(MultipartBody.FORM)
//        .addFormDataPart("order_id", null, orderIdBody)
//        .addFormDataPart("problem_description", null, problemDescriptionBody)
//        .addFormDataPart("photo", imageFile.name, imageRequestBody)
//        .build()
//
//    val request = Request.Builder()
//        .url("https://mailsupportlaundryes-1.onrender.com/send-email")
//        .post(requestBody)
//        .build()
//
//    return try {
//        val response = client.newCall(request).execute()
//        val responseBody = response.body?.string()
//        Log.d("ComplaintAPI", "Response: $responseBody")
//        response.isSuccessful
//    } catch (e: Exception) {
//        Log.e("ComplaintAPI", "Error: ${e.message}", e)
//        false
//    }
//}


suspend fun sendComplaintToServer(
    context: Context,
    orderId: String,
    problemDescription: String,
    imageUri: Uri
): Boolean {
    val client = OkHttpClient()

    // Retrieve user email from SharedPreferences
    val sharedPref = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val userEmail = sharedPref.getString("USER_EMAIL", "noemail@example.com") ?: "noemail@example.com"

    // Append email to orderId
    val orderIdWithEmail = "$orderId ($userEmail)"

    val imageFile = File(context.cacheDir, "proof.jpg")
    context.contentResolver.openInputStream(imageUri)?.use { input ->
        imageFile.outputStream().use { output -> input.copyTo(output) }
    }

    val orderIdBody = orderIdWithEmail.toRequestBody("text/plain".toMediaTypeOrNull())
    val problemDescriptionBody = problemDescription.toRequestBody("text/plain".toMediaTypeOrNull())
    val imageRequestBody = imageFile.asRequestBody("image/*".toMediaTypeOrNull())

    val requestBody = MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("order_id", null, orderIdBody)
        .addFormDataPart("problem_description", null, problemDescriptionBody)
        .addFormDataPart("photo", imageFile.name, imageRequestBody)
        .build()

    val request = Request.Builder()
        .url("https://mailsupportlaundryes-1.onrender.com/send-email")
        .post(requestBody)
        .build()

    return try {
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        Log.d("ComplaintAPI", "Response: $responseBody")
        response.isSuccessful
    } catch (e: Exception) {
        Log.e("ComplaintAPI", "Error: ${e.message}", e)
        false
    }
}


@Composable
fun TopBarC() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF29ABE2))
            .padding(16.dp)
            .statusBarsPadding(), // Prevents overlap with status bar
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Complaint",
            style = AppFonts.buttoninside,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.contact),
            contentDescription = "Contact Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}





