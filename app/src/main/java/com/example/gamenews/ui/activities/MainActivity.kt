package com.example.gamenews.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.gamenews.R
import com.example.gamenews.SearchBarComponent
import com.example.gamenews.ui.theme.GameNewsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameNewsTheme {
                var text by remember { mutableStateOf("") }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        SearchBarComponent(
                            text = text,
                            onValueChange = { text = it }
                        )

                        Box {
                            AsyncImage(
                                model = "https://www.kotaku.com.au/wp-content/uploads/sites/3/2023/05/12/08933e4a622ddb89d721158f62ce8060.png?quality=80=1280,720",
                                contentDescription = null,
                                Modifier
                                    .size(100.dp)
                                    .offset(y = 100.dp / 2)
                                    .clip(shape = CircleShape)
                                    .align(Alignment.BottomCenter),
                                contentScale = ContentScale.Crop,
                                placeholder = painterResource(id = R.drawable.logo),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GameNewsTheme {
        Greeting("Android")
    }
}
