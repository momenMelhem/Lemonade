package com.example.lemonade.registration.viewModel

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.lemonade.R
import com.example.lemonade.utils.validConfirmPassword
import com.example.lemonade.utils.validEmail
import com.example.lemonade.utils.validPassword

class RegisterViewModel : ViewModel() {
    var email  = mutableStateOf("")
    var name = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var passwordVisibility = mutableStateOf(false)
    var iconState = mutableStateOf(R.drawable.baseline_visibility_24)

    fun passwordIcon(){
        passwordVisibility.value=!passwordVisibility.value
        if (passwordVisibility.value)
            iconState.value= R.drawable.baseline_visibility_off_24
        else
            iconState.value= R.drawable.baseline_visibility_24
    }


    fun registerButton(context:Context) {
            if(email.value.isEmpty() || password.value.isEmpty() || confirmPassword.value.isEmpty()){

                Toast.makeText(context, "Please Fill The Required Filed", Toast.LENGTH_SHORT).show()
            }
        else
            {
                if (!email.value.validEmail())
                    Toast.makeText(context, "Invalid Email", Toast.LENGTH_SHORT).show()

                if (!password.value.validPassword())
                    Toast.makeText(context, "Invalid Password", Toast.LENGTH_SHORT).show()
                if(!confirmPassword.value.validConfirmPassword(password.value))
                    Toast.makeText(context, "Confirm Password", Toast.LENGTH_SHORT).show()
            }
    }
    fun registerWithGoogleButton(){
        TODO("Register With Google On Click")
    }
}
