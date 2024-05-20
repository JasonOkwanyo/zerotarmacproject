package com.example.zerotarmacc.ui.theme.screens.usernav

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.zerotarmacc.R
import com.example.zerotarmacc.navigation.ROUTE_APPLICANTDASHBOARD
import com.example.zerotarmacc.navigation.ROUTE_RECRUITERDASHBOARD

@Composable
fun UserNavScreen(navController: NavHostController) {
    val gradientColorList = listOf(
        Color.Red,
        Color.Blue,
    )
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

        Image(painter = painterResource(id= R.drawable.logo), contentDescription = "JobPortal")

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Welcome to",
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp
        )
        Text(
            text = "Zero Tarmac",
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            fontSize = 50.sp
        )
        Text(
            text = "User Navigation",
            color = Color.White,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Pick your user!",
            color = Color.White,
            fontFamily = FontFamily.SansSerif,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_APPLICANTDASHBOARD)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Applicant",
                color = Color.White,
                fontSize = 25.sp,)
        }

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_RECRUITERDASHBOARD)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Recruiter",
                color = Color.White,
                fontSize = 25.sp,)
        }

    }
}


@Composable
fun GradientBackgroundBrush(
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
private fun UserNavprev() {
    UserNavScreen(rememberNavController())
}