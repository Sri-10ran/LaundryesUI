//package com.example.laundry
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import android.net.Uri
//import androidx.compose.foundation.Image
//import androidx.compose.ui.res.painterResource
//
//class ContactActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            ContactScreen {
//
//                val intent = Intent(this, ComplaintActivity::class.java)
//                startActivity(intent)
//            }
//        }
//    }
//}
//
//
//@Composable
//fun ContactScreen(onIssueClick: () -> Unit) {
//    val context = LocalContext.current
//    TopBar2()
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(top = 70.dp, start = 16.dp, end = 16.dp, bottom = 16.dp), // Adjusted padding
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        // Add TopBar2 here
//
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        ContactCard("Address:", "N.R.K.R Road, near Reg.Office, Sivakasi - 626124.")
//        ContactCard("Phone:", "+91 12345 67890")
//
//        // Clickable Website Link
//        ContactCard("Website:", "https://laundryes.netlify.app") {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://laundryes.netlify.app"))
//            context.startActivity(intent)
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(
//            onClick = { onIssueClick() },
//            modifier = Modifier.padding(8.dp)
//        ) {
//            Text("Any Issues")
//        }
//    }
//}
//
//@Composable
//fun ContactCard(title: String, info: String, onClick: (() -> Unit)? = null) {
//    Card(
//        shape = RoundedCornerShape(12.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .clickable(enabled = onClick != null) { onClick?.invoke() } // Make clickable if onClick exists
//    ) {
//        Column(modifier = Modifier.padding(16.dp)) {
//            Text(text = title, fontWeight = FontWeight.Bold)
//            Spacer(modifier = Modifier.height(4.dp))
//            Text(text = info, color = if (onClick != null) Color.Blue else Color.Unspecified)
//        }
//    }
//}
//
//@Composable
//fun TopBar2() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF29ABE2))
//            .padding(16.dp),
//
//    ) {
//        Text(
//            text = "Contact",
//            color = Color.White,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.weight(1f)
//        )
//        Image(
//            painter = painterResource(id = R.drawable.contact),
//            contentDescription = "Home Icon",
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewContactScreen() {
//    ContactScreen(onIssueClick = {})
//}
package com.example.laundry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.laundry.ui.theme.AppFonts
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ContactActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContactScreen {
                val intent = Intent(this, ComplaintActivity::class.java)
                startActivity(intent)
            }
        }
    }
}

@Composable
fun ContactScreen(onIssueClick: () -> Unit) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFDAE1ED))
    ) {
        TopBar2()
        Column(
        modifier = Modifier
            .fillMaxSize()

         .padding( start = 16.dp, end = 16.dp, bottom = 16.dp), // Adjusted padding
      horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(10.dp))

//        ContactCard("Address", "N.R.K.R Road, near Reg.Office, \nSivakasi - 626124.", Icons.Default.LocationOn)
            ContactCard("Address", "N.R.K.R Road, near Reg.Office, \nSivakasi - 626124.", Icons.Default.LocationOn) {
                val gmmIntentUri = Uri.parse("geo:0,0?q=N.R.K.R+Road,+near+Reg.Office,+Sivakasi+-+626124")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                context.startActivity(mapIntent)
            }

            ContactCard("Phone", "+91 12345 67890", Icons.Default.Phone) {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+911234567890"))
            context.startActivity(intent)
        }
        ContactCard("Website", "https://laundryes.netlify.app", Icons.Default.Email) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://laundryes.netlify.app"))
            context.startActivity(intent)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { onIssueClick() },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text("Report an Issue", style = AppFonts.buttoninside, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}
    }

@Composable
fun ContactCard(
    title: String,
    info: String,
    icon: ImageVector,
    onClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(105.dp) // Ensuring equal height
            .padding(vertical = 8.dp)
            .clickable(enabled = onClick != null) { onClick?.invoke() },
        colors = CardDefaults.cardColors(containerColor = Color(0xFF29ABE2)) // Background color updated
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = title,
                tint = Color.White, // Icon color updated
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = title,
                    style = AppFonts.subtopic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White // Text color updated
                )
                Text(
                    text = info,
                    fontSize = 15.sp,
                    color = Color.White ,
                    style = AppFonts.contents,
                    fontWeight = FontWeight.Bold,// Info text color updated
                )
            }
        }
    }
}


@Composable
fun TopBar2() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF29ABE2))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Contact",
            style = AppFonts.topic,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(id = R.drawable.contact),
            contentDescription = "Contact Icon",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
    }
}
