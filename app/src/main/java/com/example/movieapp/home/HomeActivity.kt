package com.example.movieapp.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.movieapp.movie.MovieListActivity
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.util.isValidEmail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme() {
                MainContentUI()
            }
        }
    }
}

@Composable
fun MainContentUI() {
    val context = LocalContext.current
    val emailId = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val buttonEnable = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(key1 = emailId.value, key2 = password.value ) {
        buttonEnable.value = emailId.value.isValidEmail() && password.value.length>7 && password.value.length<16
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(
                label = {
                    Text(text = "Email Id")
                },
                value = emailId.value,
                onValueChange = {
                    emailId.value = it
                })
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = password.value,
                label = {
                        Text(text = "Password")
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                onValueChange = {
                    password.value = it
                })
            Button(
                enabled = buttonEnable.value,
                onClick = {
                context.startActivity(Intent(context, MovieListActivity::class.java))
            }) {
               Text(text = "Submit")
            }
        }
    }
}