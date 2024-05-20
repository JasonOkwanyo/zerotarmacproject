package com.example.zerotarmacc.ui.theme.screens.jobs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.zerotarmacc.data.joblistingviewmodel
import com.example.zerotarmacc.model.Job
import com.example.zerotarmacc.navigation.ROUTE_APPLYTOJOB
import com.example.zerotarmacc.navigation.ROUTE_UPDATEJOB

@Composable
fun JobListingScreen(navController: NavHostController) {
    val gradientColorList = listOf(
        Color.Green,
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
        val emptyJobState = remember { mutableStateOf(Job("","","","","","","","","")) }
        var emptyJobsListState = remember { mutableStateListOf<Job>() }

        var jobs = jobRepository.viewJobs(emptyJobState, emptyJobsListState)


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
            Text(
                text = "All Available Jobs",
                color = Color.Green,
                fontFamily = FontFamily.Serif,
                fontSize = 25.sp,
            )

            Spacer(modifier = Modifier.height(20.dp))

            @OptIn(ExperimentalMaterial3Api::class)
            @Composable
            fun SearchBarM3(modifier: Modifier = Modifier) {
                var query by remember { mutableStateOf("") }
                var active by remember { mutableStateOf(false) }

                SearchBar(
                    query = query,
                    onQueryChange = {query = it},
                    onSearch = { newQuery ->
                        println("Performing search on query: $newQuery")
                    },
                    active = active,
                    onActiveChange = {active = it},
                    placeholder = {
                        Text(text = "Search")
                    },
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
                    },
                    trailingIcon = if (active) {
                        {
                            IconButton(onClick = { if (query.isNotEmpty()) query = "" else active = false }) {
                                Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                            }
                        }
                    } else null

                ){

                }
            }


            LazyColumn(){
                items(jobs){
                    JobItem(
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
fun JobItem(compname:String, jobtitle:String, salary:String, city:String, industry:String, jobtype:String, jobreq:String, idealcand:String, id:String,
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

//        SOME RESTRICTIONS NEEDED
        OutlinedButton(onClick = {
            navController.navigate(ROUTE_APPLYTOJOB+"/$id")
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ){
            Text(text = "Apply",
                color = Color.White,
                fontSize = 25.sp,)
        }

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
private fun JobListingprev() {
    JobListingScreen(rememberNavController())
}