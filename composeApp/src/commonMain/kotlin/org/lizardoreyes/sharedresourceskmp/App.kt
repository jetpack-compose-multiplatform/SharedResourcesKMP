package org.lizardoreyes.sharedresourceskmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import sharedresourceskmp.composeapp.generated.resources.Res
import sharedresourceskmp.composeapp.generated.resources.click_me
import sharedresourceskmp.composeapp.generated.resources.compose_multiplatform
import sharedresourceskmp.composeapp.generated.resources.gradle
import sharedresourceskmp.composeapp.generated.resources.say_hello

@Composable
@Preview
fun App() {
    CustomTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var showContent by remember { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.gradle), null, Modifier.width(150.dp))
                Button(onClick = { showContent = !showContent }) {
                    Text(stringResource(Res.string.click_me))
                }
                AnimatedVisibility(showContent) {
                    val greeting = remember { Greeting().greet() }
                    val ip = remember { mutableStateOf("") }

                    LaunchedEffect(Unit) {
                        ip.value = readFile()
                    }

                    Column(
                        Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Load Text from resources
                        Image(painterResource(Res.drawable.compose_multiplatform), null)
                        Text("Compose: $greeting")
                        Text(stringResource(Res.string.say_hello, "Lizardo"))
                        Text("IP: ${ip.value}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
suspend fun readFile(): String {
    val content = Res.readBytes("files/ip.txt")
    return content.decodeToString()
}