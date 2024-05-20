package com.example.zerotarmacc.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.zerotarmacc.model.Application
import com.example.zerotarmacc.model.SubmitApplication
import com.example.zerotarmacc.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class applicationviewmodel(var navController: NavHostController, var context: Context) {
    var authRepository: AuthViewModel
    var progress: ProgressDialog

    init {
        authRepository = AuthViewModel(navController, context)
        if (!authRepository.isloggedin()) {
            navController.navigate(ROUTE_LOGIN)
        }
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }

    fun saveApplication(
        applicationFirstname: String,
        applicationSurname: String,
        applicationLocation: String,
        applicationJtitle: String,
    ) {
        var id = System.currentTimeMillis().toString()
        var applicationData = Application(applicationFirstname, applicationSurname, applicationLocation, applicationJtitle, id)
        var applicationRef = FirebaseDatabase.getInstance().getReference()
            .child("Applications/$id")
        progress.show()
        applicationRef.setValue(applicationData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewApplications(
        application: MutableState<Application>,
        applications: SnapshotStateList<Application>
    ): SnapshotStateList<Application> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Applications")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                applications.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Application::class.java)
                    application.value = value!!
                    applications.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return applications
    }

    fun deleteApplication(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Applications/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Application deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateApplication(firstname: String, surname: String, location: String, jtitle: String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Applications/$id")
        progress.show()
        var updateData = Application(firstname, surname, location, jtitle, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveApplicationWithImage(applicationFirstname:String, applicationSurname:String, applicationLocation:String, applicationJtitle:String, filePath: Uri){
        var id = System.currentTimeMillis().toString()
        var storageReference = FirebaseStorage.getInstance().getReference().child("SubmitApplications/$id")
        progress.show()

        storageReference.putFile(filePath).addOnCompleteListener{
            progress.dismiss()
            if (it.isSuccessful){
                // Proceed to store other data into the db
                storageReference.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var houseData = SubmitApplication(applicationFirstname,applicationSurname,applicationLocation,
                        applicationJtitle,imageUrl,id)
                    var dbRef = FirebaseDatabase.getInstance()
                        .getReference().child("Applications/$id")
                    dbRef.setValue(houseData)
                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun viewSubmitApplications(submitApplication: MutableState<SubmitApplication>, submitApplications: SnapshotStateList<SubmitApplication>): SnapshotStateList<SubmitApplication> {
        var ref = FirebaseDatabase.getInstance().getReference().child("SubmitApplications")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                submitApplications.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(SubmitApplication::class.java)
                    submitApplication.value = value!!
                    submitApplications.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return submitApplications
    }

}


