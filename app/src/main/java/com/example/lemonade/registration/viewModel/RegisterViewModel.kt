package com.example.lemonade.registration.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.lemonade.R
import com.example.lemonade.utils.AppScreens
import com.example.lemonade.utils.SharedState
import com.example.lemonade.utils.auth.UserData
import com.example.lemonade.utils.validConfirmPassword
import com.example.lemonade.utils.validEmail
import com.example.lemonade.utils.validPassword
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class RegisterViewModel : ViewModel() {
    var email  = mutableStateOf("")
    var name = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var passwordVisibility = mutableStateOf(false)
    var iconState = mutableStateOf(R.drawable.baseline_visibility_24)
    private val db: FirebaseFirestore = Firebase.firestore
    val sharedPref = SharedState()

    private val dbUsers = db.collection("Users")

    fun passwordIcon(){
        passwordVisibility.value=!passwordVisibility.value
        if (passwordVisibility.value)
            iconState.value= R.drawable.baseline_visibility_off_24
        else
            iconState.value= R.drawable.baseline_visibility_24
    }


    fun registerButton(context:Context,navController: NavHostController) {
            if(email.value.isEmpty() || password.value.isEmpty() || confirmPassword.value.isEmpty()){

                Toast.makeText(context, "Please Fill The Required Filed", Toast.LENGTH_SHORT).show()
            }
        else
            {
                if (!email.value.validEmail())
                    Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()
                else if (!password.value.validPassword())
                    Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show()
                else if(!confirmPassword.value.validConfirmPassword(password.value))
                    Toast.makeText(context, "Confirm Password", Toast.LENGTH_SHORT).show()
                else {
                    viewModelScope.launch (Dispatchers.IO){
                        if (!isDocumentExists(email.value)) {
                            val documentId = addDocumentToCollection()
                            if (documentId != null) {
                                updateDocumentWithIdField(documentId)
                            }
                            sharedPref.setUserName(context, name.value)
                            sharedPref.setEmail(context, email.value)
                            sharedPref.setUserID(context, documentId ?: "")
                         withContext(Dispatchers.Main){
                             navController.popBackStack(navController.graph.startDestinationId, true)

                             navController.navigate(AppScreens.Main.rout)

                         }

                        }
                        else{
                            withContext(Dispatchers.Main){

                                Toast.makeText(context, "This Email Alredy Exixst", Toast.LENGTH_SHORT).show()

                            }
                        }


                    }

                }
            }
    }
    private suspend fun addDocumentToCollection(): String? {
        return try {
            val user = hashMapOf(
                "email" to email.value,
                "password" to password.value,
                "userName" to name.value,
                "userId" to null,

                )
            val result = dbUsers
                .add(user)
                .await()
            // Add the document to the collection

            result.id // Return the document ID
        } catch (e: Exception) {
            Log.d("asasasas",e.message.toString())

            e.printStackTrace()
            null
        }
    }
    private suspend fun updateDocumentWithIdField(documentId: String) {
        try {
            // Update the document with its own ID as a field
            val updateData = hashMapOf(
                "userId" to documentId
                // Add other fields as needed
            )
                dbUsers
                .document(documentId)
                .update(updateData as Map<String, Any>)
                .await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    suspend fun isDocumentExists(email: String): Boolean {

        try {
            val querySnapshot: QuerySnapshot = dbUsers
                .whereEqualTo("email", email)
                .get()
                .await()

            // If the query has results, it means the document exists
            return !querySnapshot.isEmpty
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle the exception, log, or return false based on your use case
            return false
        }
    }
    fun registerWithGoogleButton(){
       // TODO("Register With Google On Click")
    }
}
