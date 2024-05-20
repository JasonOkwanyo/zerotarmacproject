package com.example.zerotarmacc.ui.theme.screens.recruiter

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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.zerotarmacc.navigation.ROUTE_ALLAPPLICANTS
import com.example.zerotarmacc.navigation.ROUTE_CREATEJOB
import com.example.zerotarmacc.navigation.ROUTE_HOME
import com.example.zerotarmacc.navigation.ROUTE_MANAGEJOBS
import com.example.zerotarmacc.navigation.ROUTE_UPDATEJOB

@Composable
fun RecruiterDashboardScreen(navController: NavHostController) {
    val gradientColorList = listOf(
        Color.Cyan,
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

        Text(
            text = "Zero Tarmac",
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            fontSize = 50.sp
        )

        Text(text = "Recruiter Dashboard",
            color = Color.LightGray,
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp)

        Spacer(modifier = Modifier.height(50.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_CREATEJOB)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Create Job Ads",
                color = Color.White,
                fontSize = 25.sp,)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_MANAGEJOBS)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Manage Jobs",
                color = Color.White,
                fontSize = 25.sp,)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_UPDATEJOB)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Update Job",
                color = Color.White,
                fontSize = 25.sp,)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_ALLAPPLICANTS)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "All Applicants",
                color = Color.White,
                fontSize = 25.sp,)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_HOME)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Logout",
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
private fun RecDashprev() {
    RecruiterDashboardScreen(rememberNavController())
}