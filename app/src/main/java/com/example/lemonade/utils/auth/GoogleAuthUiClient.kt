package com.example.lemonade.utils.auth

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import com.example.lemonade.R
import com.example.lemonade.utils.SharedState
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.Serializable
import java.util.concurrent.CancellationException
import kotlin.coroutines.EmptyCoroutineContext

class GoogleAuthUiClient  (
    private val context: Context,
    private val oneTapClient : SignInClient
    ) : Serializable
{
    private val auth = Firebase.auth
    private val currentEmail = mutableStateOf(auth.currentUser?.email)
    private val db: FirebaseFirestore = Firebase.firestore
    private val dbUsers = db.collection("Users")
    val coroutine = CoroutineScope(EmptyCoroutineContext)
    val sharedPref = SharedState()

    suspend fun signIn():IntentSender?{
        val result =
            try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()
            }
                catch (e:Exception){
                e.printStackTrace()
                if(e is CancellationException) throw e
                null
            }
        return result?.pendingIntent?.intentSender
    }
    suspend fun signInWithIntent(intent: Intent):SignInResult{
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken,null)
        return try {
            val user = auth.signInWithCredential(googleCredentials).await().user
            Log.d("asassasa",user?.email.toString())
            SignInResult(
                data = user?.run {
                    UserData(
                        userId = uid,
                        userName = displayName,
                        email = email,
                        password = null
                    )
                },
                errorMessage = null
                )
        }
        catch (e : Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        }
        catch (e : Exception){
            e.printStackTrace()
            if(e is CancellationException) throw e
            SignInResult(
                data = null,
                errorMessage = e.message
            )
        }
    }
    private suspend fun addDocumentToCollection(): String? {
        return try {
            val user = hashMapOf(
                "email" to (this.getSignedInUser()?.email ?: ""),
                "password" to (this.getSignedInUser()?.email ?: ""),
                "userName" to(this.getSignedInUser()?.userName ?: ""),
                "userId" to null,

                )
            val result = dbUsers
                .add(user)
                .await()
            // Add the document to the collection

            result.id // Return the document ID
        } catch (e: Exception) {

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
    suspend fun addUserDocument(){

        //insert user to firestore
        var emailExist = false


        dbUsers.whereEqualTo("email", currentEmail.value).get().addOnSuccessListener { task->
            for(document in task){

                if (document.exists()){
                    emailExist=true
                    break
                }

            }
            try {
                if(!emailExist&&currentEmail.value!=null){
                    coroutine.launch {
                        val documentId = addDocumentToCollection()
                        if (documentId != null) {
                            updateDocumentWithIdField(documentId)
                        }

                    }

                   // dbUsers.add(user)
                }

            }
            catch (e:Exception){
                Log.d("Email","Email Exist")
            }


        }.addOnFailureListener { e->
            Log.d("failed ", e.message.toString())

        }
    }
    fun updateUserName(userName:String){
        val user = UserData(
            FirebaseAuth.getInstance().uid,
            userName,
            this.getSignedInUser()?.email,
            this.getSignedInUser()?.email
        )

        db.collection("Users").document(auth.currentUser?.uid ?: "")
            .set(user)
            .addOnSuccessListener {

                Log.d("userNameUpdate","User Updated Successfully")
                Toast.makeText(context,"User Name Updated !",Toast.LENGTH_LONG).show()

            }

    }
  fun getSignedInUser() : UserData? = auth.currentUser?.run {
       UserData(
           userId = uid,
           userName = displayName,
           email = email,
           password = email
       )
   }


    private fun buildSignInRequest (): BeginSignInRequest{
        return BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.Builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.web_client_id))
                    .build()
            )
            .setAutoSelectEnabled(true)
            .build()
    }
}