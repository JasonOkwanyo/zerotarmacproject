package com.example.zerotarmacc.ui.theme.screens.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.example.zerotarmacc.data.joblistingviewmodel
import com.example.zerotarmacc.model.SubmitJob
import com.example.zerotarmacc.navigation.ROUTE_UPDATEJOB

@Composable
fun ManageJobsScreen(navController: NavHostController) {
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
        var jobRepository = joblistingviewmodel(navController, context)


        val emptySubmitJobState = remember { mutableStateOf(SubmitJob("","","","","","","","","")) }
        var emptySubmitJobsListState = remember { mutableStateListOf<SubmitJob>() }

        var submitJobs = jobRepository.viewSubmitJobs(emptySubmitJobState, emptySubmitJobsListState)

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
            Text(text = "All Submitted Jobs",
                fontSize = 24.sp,
                fontFamily = FontFamily.Serif,
                color = Color.Red)

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn(){
                items(submitJobs){
                    SubmittedJobItem(
                        compname = it.compname,
                        jobtitle = it.jobtitle,
                        salary = it.salary,
                        city = it.city,
                        industry = it.industry,
                        jobtype = it.jobtype,
                        jobreq = it.jobreq,
                        idealcand = it.idealcand,
                        id = it.id,
                        navController = navController,
                        jobRepository = jobRepository
                    )
                }
            }
        }

    }
}

@Composable
fun SubmittedJobItem(compname:String, jobtitle:String, salary:String, city:String, industry:String, jobtype:String, jobreq:String, idealcand:String, id:String,
                     navController: NavHostController, jobRepository: joblistingviewmodel
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = compname)
        Text(text = jobtitle)
        Text(text = salary)
        Text(text = city)
        Text(text = industry)
        Text(text = jobtype)
        Text(text = jobreq)
        Text(text = idealcand)

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            jobRepository.deleteJob(id)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Delete",
                color = Color.White,
                fontSize = 25.sp,)
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            navController.navigate(ROUTE_UPDATEJOB+"/$id")
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Update",
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
    val endOffset = if (isVerticalGradient) {
        Offset(0f, Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY, 0f)
    }

    return Brush.linearGradient(
        colors = colors,
        start = Offset.Zero,
        end = endOffset
    )
}


@Preview
@Composable
private fun ManageJobsprev() {
    ManageJobsScreen(rememberNavController())
}