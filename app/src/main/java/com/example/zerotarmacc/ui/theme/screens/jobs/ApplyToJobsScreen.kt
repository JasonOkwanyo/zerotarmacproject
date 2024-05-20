package com.example.zerotarmacc.ui.theme.screens.jobs

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.zerotarmacc.data.applicationviewmodel
import com.example.zerotarmacc.navigation.ROUTE_APPLIEDJOBS

@Composable
fun ApplyToJobsScreen(navController: NavHostController) {
    val gradientColorList = listOf(
        Color.Red,
        Color.Yellow,
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
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Zero Tarmac",
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            fontSize = 50.sp
        )
        Text(
            text = "Apply To Job",
            color = Color.Yellow,
            fontFamily = FontFamily.Serif,
            fontSize = 25.sp
        )

        var applicationFirstname by remember { mutableStateOf(TextFieldValue("")) }
        var applicationSurname by remember { mutableStateOf(TextFieldValue("")) }
        var applicationLocation by remember { mutableStateOf(TextFieldValue("")) }
        var applicationJtitle by remember { mutableStateOf(TextFieldValue("")) }
        var applicationimageUrl by remember { mutableStateOf(TextFieldValue("")) }

        OutlinedTextField(value =applicationFirstname , onValueChange = {applicationFirstname=it},
            label = { Text(text = "First Name :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )

        OutlinedTextField(value =applicationSurname , onValueChange = {applicationSurname=it},
            label = { Text(text = "Surname :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )

        OutlinedTextField(value =applicationLocation , onValueChange = {applicationLocation=it},
            label = { Text(text = "Location :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )

        OutlinedTextField(value =applicationJtitle , onValueChange = {applicationJtitle=it},
            label = { Text(text = "Job Title :",
                fontSize = 20.sp,
                color = Color.White)
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )


        Spacer(modifier = Modifier.height(20.dp))

        OutlinedButton(onClick = {
            var applicationRepository = applicationviewmodel(navController,context)
            applicationRepository.saveApplication(applicationFirstname.text.trim(),applicationSurname.text.trim(),applicationLocation.text.trim(),
                applicationJtitle.text.trim())
            navController.navigate(ROUTE_APPLIEDJOBS)
        },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Text(text = "Apply",
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))

        ImagePicker(Modifier,context, navController, applicationFirstname.text.trim(), applicationSurname.text.trim(), applicationLocation.text.trim(), applicationJtitle.text.trim())

    }
}


@Composable
fun ImagePicker(modifier: Modifier = Modifier, context: Context, navController: NavHostController, firstname:String, surname:String, location:String, jtitle:String) {
    var hasImage by remember { mutableStateOf(false) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            hasImage = uri != null
            imageUri = uri
        }
    )

    Column(modifier = modifier,) {
        if (hasImage && imageUri != null) {
            val bitmap = MediaStore.Images.Media.
            getBitmap(context.contentResolver,imageUri)
            Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Selected image")
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp), horizontalAlignment = Alignment.CenterHorizontally,) {
            OutlinedButton(
                onClick = {
                    imagePicker.launch("image/*")
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "Select Image"
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(onClick = {
                //-----------UPLOAD LOGIC---------------//
                var applicationRepository = applicationviewmodel(navController,context)
                applicationRepository.saveApplicationWithImage(firstname, surname, location, jtitle, imageUri!!)

            },modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(text = "Upload Resume")
            }
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
private fun ApplyToJobsprev() {
    ApplyToJobsScreen(rememberNavController())
}