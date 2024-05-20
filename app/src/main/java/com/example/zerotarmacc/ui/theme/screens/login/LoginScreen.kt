package com.example.zerotarmacc.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.zerotarmacc.data.AuthViewModel
import com.example.zerotarmacc.navigation.ROUTE_REGISTER

@Composable
fun LoginScreen(navController: NavHostController) {
    val gradientColorList = listOf(
        Color.Red,
        Color.Blue,
    )
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = false,
                    colors = gradientColorList
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Zero Tarmac",
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            fontSize = 50.sp
        )
        Text(
            text = "Job Portal",
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Login Page",
            color = Color.Cyan,
            fontFamily = FontFamily.Monospace,
            fontSize = 30.sp)

        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "personIcon")
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(value =email , onValueChange = {email=it},
            label = { Text(text = "Enter Email :",
                fontSize = 20.sp,
                color = Color.White)
            },
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )

        OutlinedTextField(value =pass , onValueChange = {pass=it},
            label = { Text(text = "Enter Password :",
                fontSize = 20.sp,
                color = Color.White) },
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "passwordIcon") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(onClick = {
            val mylogin= AuthViewModel(navController, context )
            mylogin.login(email.text.trim(),pass.text.trim())
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Log In",
                color = Color.White,
                fontSize = 25.sp,)
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "Don't have an account?",
                fontSize = 20.sp)
        }

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_REGISTER)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Register",
                color = Color.White,
                fontSize = 25.sp,)
        }


    }

}

@Composable
private fun GradientBackgroundBrush(
    isVerticalGradient: Boolean,
    colors: List<Color>
): Brush {
    val  endOffset = if(isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    }else {
        Offset(Float.POSITIVE_INFINITY,0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )

}


@Preview
@Composable
private fun Loginprev() {
    LoginScreen(rememberNavController())
}