package com.example.appclickbehavior

import androidx.compose.ui.text.style.TextAlign
import android.os.Bundle
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appclickbehavior.ui.theme.AppClickBehaviorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppClickBehaviorTheme {
                LemonadeApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {

    var currentStep by remember { mutableStateOf(1) }


    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Color(0xFFF9E44C)
                )
            )
        }
    ) { innerPadding ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background),
            color = MaterialTheme.colorScheme.background
        ) {

            when (currentStep) {
                1 -> {
                    LemonTextAndImage(
                        textLabel = stringResource(R.string.lemon_select),
                        drawableResourceId = R.drawable.lemon_tree,
                        contentDescription = stringResource(R.string.lemon_tree_content_description),
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()
                        }
                    )
                }
                2 -> {
                    LemonTextAndImage(
                        textLabel = stringResource(R.string.lemon_squeeze),
                        drawableResourceId = R.drawable.lemon_squeeze,
                        contentDescription = stringResource(R.string.lemon_content_description),
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }
                3 -> {
                    LemonTextAndImage(
                        textLabel = stringResource(R.string.lemon_drink),
                        drawableResourceId = R.drawable.lemon_drink,
                        contentDescription = stringResource(R.string.lemonade_content_description),
                        onImageClick = {
                            currentStep = 4
                        }
                    )
                }
                4 -> {
                    LemonTextAndImage(
                        textLabel = stringResource(R.string.lemon_empty_glass),
                        drawableResourceId = R.drawable.lemon_restart,
                        contentDescription = stringResource(R.string.empty_glass_content_description),
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabel: String,
    drawableResourceId: Int,
    contentDescription: String,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC3ECD2)),
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(drawableResourceId),
                contentDescription = contentDescription,
                modifier = Modifier
                    .width(128.dp)
                    .height(128.dp)
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = textLabel,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppClickBehaviorTheme {
        LemonadeApp()
    }
}