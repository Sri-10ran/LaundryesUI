



package com.example.laundry
import android.widget.Toast
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.laundry.ui.theme.AppFonts
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class SignInActivity : ComponentActivity() {
    private lateinit var viewModel: GoogleSignInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[GoogleSignInViewModel::class.java]

        setContent {
            SignInScreen()
        }
    }

    @Composable
    fun SignInScreen() {
        val context = LocalContext.current
        var showDialog by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Centered Column for Logo and Tagline
            Column(
                modifier = Modifier.align(Alignment.Center), // Ensures it's centered properly
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // App Logo
                Image(
                    painter = painterResource(id = R.drawable.logo), // Ensure this resource exists
                    contentDescription = "Laundryes Logo",
                    modifier = Modifier.size(120.dp) // Increased size for better visibility
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Tagline
                Text(
                    text = "Crafting Yourselves",
                    style = AppFonts.topic,
                    fontSize = 20.sp
                )
            }

            // Bottom-aligned Sign-In Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter) // This ensures it's positioned at the bottom
                    .background(Color(0xFF29ABE2), shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                    .padding(vertical = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Top Drag Handle
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(4.dp)
                            .background(Color.White, shape = RoundedCornerShape(50))
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // "New to Laundryes?" text
                    Text(
                        text = "Join or Log in to Laundryes ",
                        style = AppFonts.subtopic,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 22.sp,
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Google Sign-In Button
                    Button(
                        onClick = { signInWithGoogle(context, onSignInFailed = { showDialog = true }) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        modifier = Modifier
                            .height(56.dp)
                            .padding(horizontal = 24.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.go),
                            contentDescription = "Google Logo",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Continue with Google", style = AppFonts.buttoninside,color = Color.Black, fontSize = 18.sp)
                    }
                }
            }

            // Sign-In Error Dialog
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Sign-In Failed") },
                    text = { Text("An error occurred while signing in. Please try again.") },
                    confirmButton = {
                        Button(onClick = { showDialog = false }) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }


    private fun signInWithGoogle(context: Context, onSignInFailed: () -> Unit) {
        lifecycleScope.launch {
            viewModel.handleGoogleSignIn(this@SignInActivity) { success ->
                if (success) {
                    saveUserDetailsToPreferences()

                    Toast.makeText(context, "Sign-in successful!", Toast.LENGTH_SHORT).show() // Toast at the bottom
                    navigateToHome()
                } else {
                    Log.e("SignInActivity", "Google Sign-In failed")
                    Toast.makeText(context, "Sign-in failed. Try again!", Toast.LENGTH_SHORT).show() // Toast at the bottom
                    onSignInFailed()
                }
            }
        }
    }

    private fun saveUserDetailsToPreferences() {
        val user = FirebaseAuth.getInstance().currentUser
        user?.let {
            val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("USER_NAME", user.displayName)
                putString("USER_EMAIL", user.email)
                apply()
            }
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

