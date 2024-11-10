package com.example.sliderswitch

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sliderswitch.ui.theme.SliderSwitchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataLoadingApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataLoadingApp() {
    val context = LocalContext.current
    var isSwitchOn by remember { mutableStateOf(false) }
    var isDataLoaded by remember { mutableStateOf(false) }

    val sampleData = """
        В начале было Слово, и Слово было у Бога, и Слово было Бог.
        Оно было в начале у Бога. Всё через Него начало быть, и без Него ничто не начало быть, что начало быть.
    """.trimIndent()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Загрузка данных") })
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Switch(
                    checked = isSwitchOn,
                    onCheckedChange = { isSwitchOn = it },
                    colors = SwitchDefaults.colors(checkedThumbColor = MaterialTheme.colorScheme.primary)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (isSwitchOn) {
                            isDataLoaded = true
                            Toast.makeText(context, "Загрузка данных...", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Нет доступа", Toast.LENGTH_SHORT).show()
                        }
                    },
                ) {
                    Text("Загрузка данных")
                }
                Spacer(modifier = Modifier.height(24.dp))

                if (isDataLoaded) {
                    Text(
                        text = sampleData,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    )
}

