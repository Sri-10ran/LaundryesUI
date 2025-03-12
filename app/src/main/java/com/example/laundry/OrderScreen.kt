//package com.example.laundry
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.runtime.saveable.listSaver
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.toMutableStateList
//
//class OrderScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            OrderScreen1()
//        }
//    }
//}
//
//@Composable
//fun OrderScreen1() {
//    var showDialog by remember { mutableStateOf(false) }
//
//    val items = listOf(
//        LaundryItem("Towel", 20, R.drawable.towel),
//        LaundryItem("Socks", 15, R.drawable.socks),
//        LaundryItem("Tie", 50, R.drawable.tie),
//        LaundryItem("Bed Sheet", 80, R.drawable.bedsheet),
//        LaundryItem("Pillow Cover", 30, R.drawable.pillowcover),
//        LaundryItem("Curtain", 90, R.drawable.curtain)
//    )
//
//    val quantities = rememberSaveable(saver = listSaver(
//        save = { it.toList() },
//        restore = { it.toMutableStateList() }
//    )) {
//        mutableStateListOf(*IntArray(items.size) { 0 }.toTypedArray())
//    }
//
//    Scaffold(
//        topBar = { TopBar3() }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//            var selectedTab by remember { mutableStateOf(0) }
//            val tabs = listOf("Other", "Men")
//
//            TabRow(selectedTabIndex = selectedTab) {
//                tabs.forEachIndexed { index, title ->
//                    Tab(
//                        selected = selectedTab == index,
//                        onClick = { selectedTab = index },
//                        text = { Text(title) }
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            LazyColumn {
//                itemsIndexed(items) { index, item ->
//                    LaundryItemCard(
//                        item = item,
//                        quantity = quantities[index],
//                        onQuantityChange = { newQty ->
//                            if (newQty in 0..10) quantities[index] = newQty
//                        }
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = { showDialog = true },
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text("Place Order")
//            }
//        }
//    }
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            title = { Text("Order Summary") },
//            text = {
//                val totalPrice = items.zip(quantities.toList()) { item, qty -> item.price * qty }.sum()
//                Text("Total Price: ₹$totalPrice")
//            },
//            confirmButton = {
//                TextButton(onClick = { showDialog = false }) {
//                    Text("OK")
//                }
//            }
//        )
//    }
//}
//
//@Composable
//fun LaundryItemCard(item: LaundryItem, quantity: Int, onQuantityChange: (Int) -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = item.imageRes),
//            contentDescription = item.name,
//            modifier = Modifier.size(60.dp)
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column(modifier = Modifier.weight(1f)) {
//            Text(text = item.name, fontWeight = FontWeight.Bold)
//            Text(text = "₹${item.price}", color = Color.Gray)
//
//            Row {
//                Tag(text = "Wash")
//                Spacer(modifier = Modifier.width(4.dp))
//                Tag(text = "Dry")
//            }
//        }
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            IconButton(onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.minus),
//                    contentDescription = "Decrease"
//                )
//            }
//
//            TextField(
//                value = quantity.toString(),
//                onValueChange = {
//                    val newQty = it.toIntOrNull() ?: 0
//                    if (newQty in 0..10) onQuantityChange(newQty)
//                },
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                modifier = Modifier.width(50.dp)
//            )
//
//            IconButton(onClick = { if (quantity < 10) onQuantityChange(quantity + 1) }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.plus),
//                    contentDescription = "Increase"
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun Tag(text: String) {
//    Box(
//        modifier = Modifier
//            .background(Color.Blue, shape = RoundedCornerShape(8.dp))
//            .padding(horizontal = 8.dp, vertical = 4.dp)
//    ) {
//        Text(text = text, color = Color.White)
//    }
//}
//
//@Composable
//fun TopBar3() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF29ABE2))
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "Laundryes",
//            color = Color.White,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.weight(1f)
//        )
//        Image(
//            painter = painterResource(id = R.drawable.order),
//            contentDescription = "Home Icon",
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}
//
//data class LaundryItem(val name: String, val price: Int, @DrawableRes val imageRes: Int)
//------------------------------------------------------------------------------------------

//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.runtime.saveable.listSaver
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.toMutableStateList
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//class OrderScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            OrderScreen1(this)
//        }
//    }
//}
//
//@Composable
//fun OrderScreen1(context: Context) {
//    TopBar3()
//    val localContext = LocalContext.current
//    var showDialog by remember { mutableStateOf(false) }
//
//    val items = listOf(
//        LaundryItem("Towel", 20, R.drawable.towel),
//        LaundryItem("Socks", 15, R.drawable.socks),
//        LaundryItem("Tie", 50, R.drawable.tie),
//        LaundryItem("Bed Sheet", 80, R.drawable.bedsheet),
//        LaundryItem("Pillow Cover", 30, R.drawable.pillowcover),
//        LaundryItem("Curtain", 90, R.drawable.curtain)
//    )
//
//    val quantities = rememberSaveable(saver = listSaver(
//        save = { it.toList() },
//        restore = { it.toMutableStateList() }
//    )) {
//        mutableStateListOf(*IntArray(items.size) { 0 }.toTypedArray())
//    }
//
//    val totalAmount by remember {
//        derivedStateOf {
//            items.zip(quantities.toList()) { item, qty -> item.price * qty }.sum()
//        }
//    }
//
//    Scaffold(
//        topBar = { TopBar3() }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//            LazyColumn {
//                itemsIndexed(items) { index, item ->
//                    LaundryItemCard(
//                        item = item,
//                        quantity = quantities[index],
//                        onQuantityChange = { newQty ->
//                            if (newQty in 0..10) quantities[index] = newQty
//                        }
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Button(
//                onClick = {
//                    if (totalAmount > 0) {
//                        showDialog = true
//
//                        // Store the total amount in SharedPreferences
//                        val sharedPreferences = localContext.getSharedPreferences("Laundryes", Context.MODE_PRIVATE)
//                        sharedPreferences.edit().putInt("totalAmount", totalAmount).apply()
//
//                        // Redirect to SelectPlace.kt and finish OrderScreen.kt
//                        val intent = Intent(localContext, SelectPlace::class.java)
//                        localContext.startActivity(intent)
//
//                        if (context is OrderScreen) {
//                            context.finish() // Ensures OrderScreen is closed after redirection
//                        }
//                    }
//                },
//                enabled = totalAmount > 0,  // Button is enabled only when totalAmount > 0
//                modifier = Modifier.fillMaxWidth(),
//                colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
//            ) {
//                Text("Place Order", color = Color.White)
//            }
//        }
//    }
//}
//
//@Composable
//fun LaundryItemCard(item: LaundryItem, quantity: Int, onQuantityChange: (Int) -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = item.imageRes),
//            contentDescription = item.name,
//            modifier = Modifier.size(60.dp)
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column(modifier = Modifier.weight(1f)) {
//            Text(text = item.name, fontWeight = FontWeight.Bold)
//            Text(text = "₹${item.price}", color = Color.Gray)
//
//            Row {
//                Tag(text = "Wash")
//                Spacer(modifier = Modifier.width(4.dp))
//                Tag(text = "Dry")
//            }
//        }
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            IconButton(onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.minus),
//                    contentDescription = "Decrease"
//                )
//            }
//
//            TextField(
//                value = quantity.toString(),
//                onValueChange = {
//                    val newQty = it.toIntOrNull() ?: 0
//                    if (newQty in 0..10) onQuantityChange(newQty)
//                },
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                modifier = Modifier.width(50.dp)
//            )
//
//            IconButton(onClick = { if (quantity < 10) onQuantityChange(quantity + 1) }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.plus),
//                    contentDescription = "Increase"
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun Tag(text: String) {
//    Box(
//        modifier = Modifier
//            .background(Color.Blue, shape = RoundedCornerShape(8.dp))
//            .padding(horizontal = 8.dp, vertical = 4.dp)
//    ) {
//        Text(text = text, color = Color.White)
//    }
//}
//
//@Composable
//fun TopBar3() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF29ABE2))
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "Order",
//            color = Color.White,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.weight(1f)
//        )
//        Image(
//            painter = painterResource(id = R.drawable.order),
//            contentDescription = "Home Icon",
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}
//
//
//data class LaundryItem(val name: String, val price: Int, @DrawableRes val imageRes: Int)
//===============================================================
//package com.example.laundry
//
//import android.content.Context
//import android.content.Intent
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//class OrderScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            OrderScreen1(this)
//        }
//    }
//}
//
//@Composable
//fun OrderScreen1(context: Context) {
//    val categories = listOf("Men", "Women", "Kids", "Others")
//    var selectedTab by remember { mutableStateOf(0) }
//    val localContext = LocalContext.current
//
//    val itemsByCategory = listOf(
//        listOf(
//            LaundryItem("Shirt", 100, R.drawable.shirt),
//            LaundryItem("Pant", 120, R.drawable.pant)
//        ),
//        listOf(
//            LaundryItem("Silk Saree", 200, R.drawable.silksaree)
//        ),
//        listOf(
//            LaundryItem("School Uniform", 80, R.drawable.uniform),
//            LaundryItem("Baby Blanket", 90, R.drawable.baby_blanket)
//        ),
//        listOf(
//            LaundryItem("Towel", 20, R.drawable.towel),
//            LaundryItem("Socks", 15, R.drawable.socks),
//            LaundryItem("Tie", 50, R.drawable.tie),
//            LaundryItem("Bed Sheet", 80, R.drawable.bedsheet),
//            LaundryItem("Pillow Cover", 30, R.drawable.pillowcover),
//            LaundryItem("Curtain", 90, R.drawable.curtain)
//        )
//    )
//
//    // Fix: Separate quantity lists for each category
//    val quantitiesByCategory = rememberSaveable {
//        categories.map { mutableStateListOf<Int>().apply { addAll(List(itemsByCategory[categories.indexOf(it)].size) { 0 }) } }
//    }
//
//    val totalAmount by remember {
//        derivedStateOf {
//            itemsByCategory.flatten().zip(quantitiesByCategory.flatten()) { item, qty -> item.price * qty }.sum()
//        }
//    }
//
//    Scaffold(
//        topBar = { TopBar3() }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//            // Tabs for category selection
//            TabRow(selectedTabIndex = selectedTab) {
//                categories.forEachIndexed { index, title ->
//                    Tab(
//                        selected = selectedTab == index,
//                        onClick = { selectedTab = index },
//                        text = { Text(title) }
//                    )
//                }
//            }
//
//            // Display items based on selected category
//            LazyColumn {
//                itemsIndexed(itemsByCategory[selectedTab]) { index, item ->
//                    LaundryItemCard(
//                        item = item,
//                        quantity = quantitiesByCategory[selectedTab][index],
//                        onQuantityChange = { newQty ->
//                            if (newQty in 0..10) quantitiesByCategory[selectedTab][index] = newQty
//                        }
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            // Place Order Button
//            Button(
//                onClick = {
//                    if (totalAmount > 0) {
//                        val sharedPreferences = localContext.getSharedPreferences("Laundryes", Context.MODE_PRIVATE)
//                        sharedPreferences.edit().putInt("totalAmount", totalAmount).apply()
//                        val intent = Intent(localContext, SelectPlace::class.java)
//                        localContext.startActivity(intent)
//                        if (context is OrderScreen) {
//                            context.finish()
//                        }
//                    }
//                },
//                enabled = totalAmount > 0,
//                modifier = Modifier.fillMaxWidth(),
//                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF29ABE2))
//            ) {
//                Text("Place Order", color = Color.White)
//            }
//        }
//    }
//}
//
//@Composable
//fun LaundryItemCard(item: LaundryItem, quantity: Int, onQuantityChange: (Int) -> Unit) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Image(
//            painter = painterResource(id = item.imageRes),
//            contentDescription = item.name,
//            modifier = Modifier.size(60.dp)
//        )
//
//        Spacer(modifier = Modifier.width(8.dp))
//
//        Column(modifier = Modifier.weight(1f)) {
//            Text(text = item.name, fontWeight = FontWeight.Bold)
//            Text(text = "₹${item.price}", color = Color.Gray)
//        }
//
//        Row(verticalAlignment = Alignment.CenterVertically) {
//            IconButton(onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.minus),
//                    contentDescription = "Decrease"
//                )
//            }
//            Text("$quantity")
//            IconButton(onClick = { if (quantity < 10) onQuantityChange(quantity + 1) }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.plus),
//                    contentDescription = "Increase"
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun TopBar3() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFF29ABE2))
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "Order",
//            color = Color.White,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.weight(1f)
//        )
//        Image(
//            painter = painterResource(id = R.drawable.order),
//            contentDescription = "Order Icon",
//            modifier = Modifier.size(24.dp)
//        )
//    }
//}
//
//data class LaundryItem(val name: String, val price: Int, @DrawableRes val imageRes: Int)

package com.example.laundry

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.laundry.ui.theme.AppFonts

class OrderScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OrderScreen1(this)
        }
    }
}

@Composable
fun OrderScreen1(context: Context) {
    val categories = listOf("Men", "Women", "Kids", "Others")
    var selectedTab by remember { mutableStateOf(0) }
    val localContext = LocalContext.current

    val itemsByCategory = listOf(
        listOf(
            LaundryItem("Shirt", 100, R.drawable.shirt),
            LaundryItem("Pant", 120, R.drawable.pant)
        ),
        listOf(
            LaundryItem("Silk Saree", 200, R.drawable.silksaree)
        ),
        listOf(
            LaundryItem("School Uniform", 80, R.drawable.uniform),
            LaundryItem("Baby Blanket", 90, R.drawable.baby_blanket)
        ),
        listOf(
            LaundryItem("Towel", 20, R.drawable.towel),
            LaundryItem("Socks(pair)", 15, R.drawable.socks),
            LaundryItem("Tie", 50, R.drawable.tie),
            LaundryItem("Bed Sheet", 80, R.drawable.bedsheet),
            LaundryItem("Pillow Cover", 30, R.drawable.pillowcover),
            LaundryItem("Curtain", 90, R.drawable.curtain)
        )
    )

    // Separate quantity lists for each category
    val quantitiesByCategory = rememberSaveable {
        categories.map { mutableStateListOf<Int>().apply { addAll(List(itemsByCategory[categories.indexOf(it)].size) { 0 }) } }
    }

    val totalAmount by remember {
        derivedStateOf {
            itemsByCategory.flatten().zip(quantitiesByCategory.flatten()) { item, qty -> item.price * qty }.sum()
        }
    }

    Scaffold(
        topBar = { TopBar3() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)

                .padding(16.dp)
        ) {
            // Tabs for category selection
            TabRow(selectedTabIndex = selectedTab) {
                categories.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(text=title, style = AppFonts.subtopic, fontWeight = FontWeight.ExtraBold) }
                    )
                }
            }

            // Display items based on selected category
            LazyColumn {
                itemsIndexed(itemsByCategory[selectedTab]) { index, item ->
                    LaundryItemCard(
                        item = item,
                        quantity = quantitiesByCategory[selectedTab][index],
                        onQuantityChange = { newQty ->
                            if (newQty in 0..10) quantitiesByCategory[selectedTab][index] = newQty
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Place Order Button
            Button(
                onClick = {
                    if (totalAmount > 0) {
                        val sharedPreferences = localContext.getSharedPreferences("Laundryes", Context.MODE_PRIVATE)
                        sharedPreferences.edit().putInt("totalAmount", totalAmount).apply()
                        val intent = Intent(localContext, SelectPlace::class.java)
                        localContext.startActivity(intent)
                        if (context is OrderScreen) {
                            context.finish()
                        }
                    }
                },
                enabled = totalAmount > 0,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF673AB7))
            ) {
                Text("Place Order", style = AppFonts.buttoninside, color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun LaundryItemCard(item: LaundryItem, quantity: Int, onQuantityChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = item.imageRes),
            contentDescription = item.name,
            modifier = Modifier.size(60.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.name, style = AppFonts.subtopic, fontWeight = FontWeight.Bold)
            Text(text = "₹${item.price}", style = AppFonts.contents, fontWeight = FontWeight.Bold,color = Color.Gray)
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { if (quantity > 0) onQuantityChange(quantity - 1) }) {
                Icon(
                    painter = painterResource(id = R.drawable.minus),
                    contentDescription = "Decrease"
                )
            }

            // Quantity TextField to allow direct input
            TextField(
                value = quantity.toString(),
                onValueChange = {
                    val newQty = it.toIntOrNull() ?: 0
                    if (newQty in 0..10) onQuantityChange(newQty)
                },
                textStyle = AppFonts.contents,

                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier.width(50.dp)
            )

            IconButton(onClick = { if (quantity < 10) onQuantityChange(quantity + 1) }) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "Increase"
                )
            }
        }
    }
}

@Composable
fun TopBar3() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF29ABE2))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Order",
            style = AppFonts.topic,
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Image(
            painter = painterResource(id = R.drawable.order),
            contentDescription = "Order Icon",
            modifier = Modifier.size(24.dp)
        )
    }
}

data class LaundryItem(val name: String, val price: Int, @DrawableRes val imageRes: Int)
