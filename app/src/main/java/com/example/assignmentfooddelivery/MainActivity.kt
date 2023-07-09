package com.example.assignmentfooddelivery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignmentfooddelivery.ui.theme.AssignmentFoodDeliveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AssignmentFoodDeliveryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FullScreenComponent()
                }
            }
        }
    }
}

@Composable
fun Header(onLocationClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFBAFF9E),
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "1214 Hollywood Road",
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.width(5.dp))
        IconButton(onClick = onLocationClick) {
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Location Dropdown")
        }
    }
}

@Composable
fun FullScreenComponent() {
    Column(modifier = Modifier.fillMaxSize()) {
        // Fixed Header component at the top
        Header(onLocationClick = { /* Handle location click */ })

        // Middle part taking the remaining height
        Column(modifier = Modifier.weight(1f)) {
            MiddlePart()
        }

        // Fixed BottomPart component at the bottom
        BottomPart(
            items = footerItemList, // Replace with your list of items
            onItemClick = { item -> /* Handle item click */ }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiddlePart() {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = { /* Handle search input change */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 5.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.DarkGray,
                        unfocusedBorderColor = Color.Gray,
                        cursorColor = Color.Black,
                        placeholderColor = Color.LightGray
                    ),
                    placeholder = { Text("Search") }
                )
                IconButton(onClick = { /* Handle search button click */ }) {
                    Icon(
                        painter = painterResource(R.drawable.vector_search_24),
                        contentDescription = "Search"
                    )
                }
            }
        }
        item {
            // HorizontalScrollable row with items
            LazyRow(
                modifier = Modifier
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(8) { index ->
                    ScrollableRowItem(
                        imageResId = R.drawable.pizza_icon,
                        text = "Pizza"
                    )
                }
            }
            Divider(color = Color.LightGray, thickness = 2.dp)
        }

        item {
            // Text-containing row
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                TextContainingRowItem(
                    texts = listOf("Text 1", "Text 2", "Text 3"),
                    highlightedText = "Text 2"
                )
            }
            Divider(color = Color.LightGray, thickness = 2.dp)
        }

        items(ItemDescriptionList){
            ResponsiveColumnItem(
                item=it
            )
            Divider(color = Color.Gray, thickness = 1.dp)
        }
    }
}

@Composable
fun ScrollableRowItem(@DrawableRes imageResId: Int, text: String) {
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
            .clickable { /*Will add click functionality here*/ },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(imageResId),
            contentDescription = text,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = text,
            modifier = Modifier.padding(top = 8.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TextContainingRowItem(texts: List<String>, highlightedText: String?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        texts.forEach { text ->
            Text(
                text = text,
                modifier = Modifier
                    .padding(8.dp),
                fontSize = 18.sp,
                fontWeight = if(text==highlightedText) FontWeight.Bold else FontWeight.Medium,
                color = if (text == highlightedText) Color.Black else Color.Gray
            )
        }
    }
}

@Composable
fun ResponsiveColumnItem(
    item:ItemDescription,
) {
    Row(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
        Image(
            painter = painterResource(item.imageResId),
            contentDescription = item.name,
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(
                text = item.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = item.description,
                color = Color.DarkGray
            )
            Row(modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(1f),
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(text = item.rating)
                Text(text = item.price)
                Text(text = item.deliveryTime)
            }
        }
    }
}


@Composable
fun BottomPart(
    items: List<FooterItem>,
    onItemClick: (FooterItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFBAFF9E),
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 20.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .height(10.dp)
                .fillMaxWidth(1f)
        ) {
            Spacer(modifier = Modifier.fillMaxSize())
        }
        Row(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            items.forEach { item ->
                Column(
                    modifier = Modifier
                        .clickable { onItemClick(item) }
                        .height(IntrinsicSize.Min)
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(item.imageResId),
                        contentDescription = item.name,
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .aspectRatio(1f),
                        colorFilter = if (item.isSelected) ColorFilter.tint(Color.Black) else ColorFilter.tint(
                            Color.DarkGray)
                    )
                    Text(
                        text = item.name,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        fontSize = 16.sp,
                        fontWeight = if (item.isSelected) FontWeight.Bold else FontWeight.SemiBold,
                        color = if (item.isSelected) Color.Black else Color.DarkGray
                    )
                }
            }
        }
    }
}




//Defining Data class and items
// Data class for each item in the BottomPart
data class FooterItem(
    @DrawableRes val imageResId: Int,
    val name: String,
    val isSelected: Boolean
)
val footerItemList = listOf(
    FooterItem(R.drawable.vector_home_24, "Home", true),
    FooterItem(R.drawable.vector_home_24, "Item 1", false),
    FooterItem(R.drawable.vector_home_24, "Item 1", false),
    FooterItem(R.drawable.vector_home_24, "Item 1", false),
)

data class ItemDescription(
    @DrawableRes val imageResId: Int,
    val name: String,
    val description: String,
    val rating: String,
    val price: String,
    val deliveryTime: String
)

val ItemDescriptionList = listOf(
    ItemDescription(R.drawable.burger_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.pizza_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.hotdog_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.icecream_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.noodles_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.burger_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.pizza_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.hotdog_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.icecream_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.noodles_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.burger_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.pizza_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.hotdog_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.icecream_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
    ItemDescription(R.drawable.noodles_icon,"Pizza Burger King","Burger","4.5/5","$5","35 min"),
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AssignmentFoodDeliveryTheme {
        FullScreenComponent()
    }
}