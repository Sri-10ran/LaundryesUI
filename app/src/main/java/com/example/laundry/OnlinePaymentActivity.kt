//package com.example.laundry
//
//import android.app.Activity
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import com.razorpay.Checkout
//import com.razorpay.PaymentResultListener
//import org.json.JSONObject
//
//class OnlinePaymentActivity : ComponentActivity(), PaymentResultListener {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // Fetch total amount from SharedPreferences
//        val sharedPreferences = getSharedPreferences("Laundryes", Context.MODE_PRIVATE)
//        val totalAmount = sharedPreferences.getInt("totalAmount", 0)
//
//        // Start Razorpay Payment
//        startPayment(totalAmount)
//    }
//
//    private fun startPayment(amount: Int) {
//        val activity: Activity = this
//        val checkout = Checkout()
//        checkout.setKeyID("rzp_test_WfSM1DjLjw38FI") // Replace with your Razorpay Test Key
//
//        try {
//            val options = JSONObject().apply {
//                put("name", "Laundryes")
//                put("description", "Laundry Service Payment")
//                put("currency", "INR")
//                put("amount", amount * 100) // Convert to paise
//                put("prefill.email", "laundryes@gmail.com")
//                put("prefill.contact", "9626825027")
//                put("image", "https://raw.githubusercontent.com/Sri-10ran/LaundryesApp/refs/heads/master/app/src/main/res/drawable/logo.webp")
//
//            }
//
//            checkout.open(activity, options)
//
//        } catch (e: Exception) {
//            Toast.makeText(this, "Payment Error: ${e.message}", Toast.LENGTH_LONG).show()
//        }
//    }
//
//    // Razorpay Payment Success
//    override fun onPaymentSuccess(razorpayPaymentID: String?) {
//        val selectedDate = intent.getStringExtra("selectedDate") ?: "No date selected"
//        val toast = Toast.makeText(this, "Order placed and delivered on $selectedDate", Toast.LENGTH_LONG)
//        toast.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//        toast.show()
//
//        val intent = Intent(this, MainActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
//        startActivity(intent)
//        finish() // Close OnlinePaymentActivity
//
//    }
//
//
//    // Razorpay Payment Failure
//    override fun onPaymentError(code: Int, response: String?) {
//        val toastError = Toast.makeText(this, "Payment Failed: $response", Toast.LENGTH_SHORT)
//        toastError.setGravity(android.view.Gravity.TOP or android.view.Gravity.CENTER_HORIZONTAL, 0, 100)
//        toastError.show()
//        finish() // Close activity after failure
//    }
//}
package com.example.laundry

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class OnlinePaymentActivity : ComponentActivity(), PaymentResultListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnlinePaymentScreen()
        }
    }

    @Composable
    fun OnlinePaymentScreen() {
        val context = LocalContext.current
        val coroutineScope = rememberCoroutineScope()

        val sharedPreferences = context.getSharedPreferences("Laundryes", Context.MODE_PRIVATE)
        val totalAmount = sharedPreferences.getInt("totalAmount", 0)

        LaunchedEffect(Unit) {
            startPayment(totalAmount, coroutineScope, context)
        }

        Scaffold { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }

    private fun startPayment(
        amount: Int,
        coroutineScope: CoroutineScope,
        context: Context
    ) {
        val activity: Activity = context as Activity
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_WfSM1DjLjw38FI")

        try {
            val options = JSONObject().apply {
                put("name", "Laundryes")
                put("description", "Laundry Service Payment")
                put("currency", "INR")
                put("amount", amount * 100)
                put("prefill.email", "laundryes@gmail.com")
                put("prefill.contact", "9626825027")
                put("image", "https://raw.githubusercontent.com/Sri-10ran/LaundryesApp/refs/heads/master/app/src/main/res/drawable/logo.webp")
            }

            checkout.open(activity, options)

        } catch (e: Exception) {
            coroutineScope.launch {
                Toast.makeText(context, "Payment Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onPaymentSuccess(razorpayPaymentID: String?) {
        val selectedDate = intent.getStringExtra("selectedDate") ?: "No date selected"
        Toast.makeText(this, "Order placed and delivered on $selectedDate", Toast.LENGTH_LONG).show()

        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    override fun onPaymentError(code: Int, response: String?) {
        Toast.makeText(this, "Payment Failed: $response", Toast.LENGTH_LONG).show()
        finish()
    }
}
