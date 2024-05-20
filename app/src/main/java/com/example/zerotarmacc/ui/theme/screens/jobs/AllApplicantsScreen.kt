package com.example.zerotarmacc.ui.theme.screens.jobs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.zerotarmacc.data.applicationviewmodel
import com.example.zerotarmacc.model.SubmitApplication

@Composable
fun AllApplicantsScreen(navController: NavHostController) {
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

        var context = LocalContext.current
        var applicationRepository = applicationviewmodel(navController, context)


        val emptySubmitApplicationState = remember { mutableStateOf(SubmitApplication("","","","","","")) }
        var emptySubmitApplicationsListState = remember { mutableStateListOf<SubmitApplication>() }

        var submitApplications = applicationRepository.viewSubmitApplications(emptySubmitApplicationState, emptySubmitApplicationsListState)


        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Zero Tarmac",
                color = Color.White,
                fontFamily = FontFamily.Cursive,
                fontSize = 50.sp
            )
            Text(text = "All Applicants",
                fontSize = 25.sp,
                fontFamily = FontFamily.Serif,
                color = Color.White)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(submitApplications){
                    SubmitApplicationItem(
                        firstname = it.firstname,
                        surname = it.surname,
                        location = it.location,
                        jtitle = it.jtitle,
                        imageUrl = it.imageUrl,
                        id = it.id,
                        navController = navController,
                        applicationRepository = applicationRepository
                    )
                }
            }
        }
    }
}


@Composable
fun SubmitApplicationItem(firstname:String, surname:String, location:String, jtitle:String, imageUrl:String, id:String,
                          navController: NavHostController, applicationRepository: applicationviewmodel
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = firstname)
        Text(text = surname)
        Text(text = location)
        Text(text = jtitle)
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        OutlinedButton(onClick = {
            applicationRepository.deleteApplication(id)
        },modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)) {
            Text(text = "Delete")
        }
//        OutlinedButton(onClick = {
//            navController.navigate(ROUTE_UPDATE_PRODUCT+"/$id")
//        }) {
//            Text(text = "Update")
//        }
    }
}





@Composable
private fun  GradientBackgroundBrush(
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
private fun AllApplicantsprev() {
    AllApplicantsScreen(rememberNavController())
}