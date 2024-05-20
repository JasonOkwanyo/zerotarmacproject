package com.example.zerotarmacc.ui.theme.screens.jobs

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.zerotarmacc.data.joblistingviewmodel
import com.example.zerotarmacc.model.Job
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun UpdateJobScreen(navController: NavHostController, id:String) {
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
        var context = LocalContext.current
        var compname by remember { mutableStateOf("") }
        var jobtitle by remember { mutableStateOf("") }
        var salary by remember { mutableStateOf("") }
        var city by remember { mutableStateOf("") }
        var industry by remember { mutableStateOf("") }
        var jobtype by remember { mutableStateOf("") }
        var jobreq by remember { mutableStateOf("") }
        var idealcand by remember { mutableStateOf("") }

        var currentDataRef = FirebaseDatabase.getInstance().getReference()
            .child("Jobs/$id")
        currentDataRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var job = snapshot.getValue(Job::class.java)
                compname = job!!.compname
                jobtitle = job!!.jobtitle
                salary = job!!.salary
                city = job!!.city
                industry = job!!.industry
                jobtype = job!!.jobtype
                jobreq = job!!.jobreq
                idealcand = job!!.idealcand

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Update Job",
            color = Color.Magenta,
            fontFamily = FontFamily.Serif,
            fontSize = 25.sp
        )

        var jobCompname by remember { mutableStateOf(TextFieldValue("")) }
        var jobJobtitle by remember { mutableStateOf(TextFieldValue("")) }
        var jobSalary by remember { mutableStateOf(TextFieldValue("")) }
        var jobCity by remember { mutableStateOf(TextFieldValue("")) }
        var jobIndustry by remember { mutableStateOf(TextFieldValue("")) }
        var jobJobtype by remember { mutableStateOf(TextFieldValue("")) }
        var jobJobreq by remember { mutableStateOf(TextFieldValue("")) }
        var jobIdealcand by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(value =jobCompname , onValueChange = {jobCompname=it},
            label = { Text(text = "Company Name :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )

        OutlinedTextField(value =jobJobtitle , onValueChange = {jobJobtitle=it},
            label = { Text(text = "Job Title :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )

        OutlinedTextField(value =jobSalary , onValueChange = {jobSalary=it},
            label = { Text(text = "Salary :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )

        OutlinedTextField(value =jobCity , onValueChange = {jobCity=it},
            label = { Text(text = "City :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )

        OutlinedTextField(value =jobIndustry , onValueChange = {jobIndustry=it},
            label = { Text(text = "Industry :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )


        OutlinedTextField(value =jobJobtype , onValueChange = {jobJobtype=it},
            label = { Text(text = "Job Type :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )

        OutlinedTextField(value =jobJobreq , onValueChange = {jobJobreq=it},
            label = { Text(text = "Job Requirements :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )

        OutlinedTextField(value =jobIdealcand , onValueChange = {jobIdealcand=it},
            label = { Text(text = "Ideal Candidate :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),

            )


        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = {
            var jobRepository = joblistingviewmodel(navController,context)
            jobRepository.saveJob(jobCompname.text.trim(),jobJobtitle.text.trim(),jobSalary.text.trim(),jobCity.text.trim(),jobIndustry.text.trim(),jobJobtype.text.trim(),jobJobreq.text.trim()
                ,jobIdealcand.text.trim(),)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "Update",
                fontSize = 20.sp)
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
fun UpdateJobprev() {
    UpdateJobScreen(rememberNavController(), id = "")
}