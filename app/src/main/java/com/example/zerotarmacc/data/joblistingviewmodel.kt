package com.example.zerotarmacc.data

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.example.zerotarmacc.model.Job
import com.example.zerotarmacc.model.SubmitJob
import com.example.zerotarmacc.navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class joblistingviewmodel(var navController: NavHostController, var context: Context) {
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


    fun saveJob(jobCompname: String, jobJobtitle: String, jobSalary: String, jobCity: String, jobIndustry: String, jobJobtype: String, jobJobreq: String, jobIdealcand: String,) {
        var id = System.currentTimeMillis().toString()
        var jobData = Job(jobCompname, jobJobtitle, jobSalary, jobCity, jobIndustry, jobJobtype, jobJobreq, jobIdealcand, id)
        var jobRef = FirebaseDatabase.getInstance().getReference()
            .child("Products/$id")
        progress.show()
        jobRef.setValue(jobData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun viewJobs(
        job: MutableState<Job>,
        jobs: SnapshotStateList<Job>
    ): SnapshotStateList<Job> {
        var ref = FirebaseDatabase.getInstance().getReference().child("Jobs")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                jobs.clear()
                for (snap in snapshot.children) {
                    val value = snap.getValue(Job::class.java)
                    job.value = value!!
                    jobs.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return jobs
    }

    fun deleteJob(id: String) {
        var delRef = FirebaseDatabase.getInstance().getReference()
            .child("Jobs/$id")
        progress.show()
        delRef.removeValue().addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Job deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun updateJob(compname: String, jobtitle: String, salary: String, city: String, industry: String, jobtype: String, jobreq: String, idealcand: String, id: String) {
        var updateRef = FirebaseDatabase.getInstance().getReference()
            .child("Jobs/$id")
        progress.show()
        var updateData = Job(compname, jobtitle, salary, city, industry, jobtype, jobreq, idealcand, id)
        updateRef.setValue(updateData).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun saveProductWithImage(productName:String, productQuantity:String, productPrice:String, filePath: Uri){
//        var id = System.currentTimeMillis().toString()
//        var storageReference = FirebaseStorage.getInstance().getReference().child("Uploads/$id")
//        progress.show()
//
//        storageReference.putFile(filePath).addOnCompleteListener{
//            progress.dismiss()
//            if (it.isSuccessful){
//                // Proceed to store other data into the db
//                storageReference.downloadUrl.addOnSuccessListener {
//                    var imageUrl = it.toString()
//                    var houseData = Upload(productName,productQuantity,
//                        productPrice,imageUrl,id)
//                    var dbRef = FirebaseDatabase.getInstance()
//                        .getReference().child("Uploads/$id")
//                    dbRef.setValue(houseData)
//                    Toast.makeText(context, "Upload successful", Toast.LENGTH_SHORT).show()
//                }
//            }else{
//                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
//            }
//        }
//    }


    fun viewSubmitJobs(submitJob: MutableState<SubmitJob>, submitJobs: SnapshotStateList<SubmitJob>): SnapshotStateList<SubmitJob> {
        var ref = FirebaseDatabase.getInstance().getReference().child("SubmitJobs")

        progress.show()
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                progress.dismiss()
                submitJobs.clear()
                for (snap in snapshot.children){
                    val value = snap.getValue(SubmitJob::class.java)
                    submitJob.value = value!!
                    submitJobs.add(value)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
        return submitJobs
    }


}