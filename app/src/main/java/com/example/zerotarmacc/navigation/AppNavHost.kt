package com.example.zerotarmacc.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zerotarmacc.ui.theme.screens.applicant.ApplicantDashboardScreen
import com.example.zerotarmacc.ui.theme.screens.home.HomeScreen
import com.example.zerotarmacc.ui.theme.screens.jobs.AllApplicantsScreen
import com.example.zerotarmacc.ui.theme.screens.jobs.AppliedJobsScreen
import com.example.zerotarmacc.ui.theme.screens.jobs.ApplyToJobsScreen
import com.example.zerotarmacc.ui.theme.screens.jobs.CreateJobScreen
import com.example.zerotarmacc.ui.theme.screens.jobs.JobListingScreen
import com.example.zerotarmacc.ui.theme.screens.jobs.ManageJobsScreen
import com.example.zerotarmacc.ui.theme.screens.jobs.UpdateJobScreen
import com.example.zerotarmacc.ui.theme.screens.login.LoginScreen
import com.example.zerotarmacc.ui.theme.screens.recruiter.RecruiterDashboardScreen
import com.example.zerotarmacc.ui.theme.screens.register.RegisterScreen
import com.example.zerotarmacc.ui.theme.screens.splash.SplashScreen
import com.example.zerotarmacc.ui.theme.screens.usernav.UserNavScreen

@Composable
fun AppNavHost(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController(), startDestination:String= ROUTE_SPLASH) {
    NavHost(navController = navController, modifier=modifier, startDestination = startDestination ){

        composable(ROUTE_HOME){
            HomeScreen(navController)
        }
        composable(ROUTE_SPLASH){
            SplashScreen(navController)
        }
        composable(ROUTE_LOGIN){
            LoginScreen(navController)
        }
        composable(ROUTE_REGISTER){
            RegisterScreen(navController)
        }
        composable(ROUTE_USERNAV){
            UserNavScreen(navController)
        }
        composable(ROUTE_APPLICANTDASHBOARD){
            ApplicantDashboardScreen(navController)
        }
        composable(ROUTE_RECRUITERDASHBOARD){
            RecruiterDashboardScreen(navController)
        }
        composable(ROUTE_APPLYTOJOB){
            ApplyToJobsScreen(navController)
        }
        composable(ROUTE_ALLAPPLICANTS){
            AllApplicantsScreen(navController)
        }
        composable(ROUTE_APPLIEDJOBS){
            AppliedJobsScreen(navController)
        }
        composable(ROUTE_CREATEJOB){
            CreateJobScreen(navController)
        }
        composable(ROUTE_UPDATEJOB+ "/{id}"){passedData ->
            UpdateJobScreen(navController,passedData.arguments?.getString("id")!!)
        }
        composable(ROUTE_MANAGEJOBS){
            ManageJobsScreen(navController)
        }
        composable(ROUTE_JOBLISTING){
            JobListingScreen(navController)
        }


    }

}