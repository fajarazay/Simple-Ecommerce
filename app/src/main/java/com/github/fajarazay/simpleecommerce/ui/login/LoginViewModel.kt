package com.github.fajarazay.simpleecommerce.ui.login

import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.github.fajarazay.simpleecommerce.ui.login.LoginActivity.Companion.RC_SIGN_IN
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

/**
 * Created by fajarseptian on 1/31/21.
 *
 * @Author Fajar Septian
 * @Email fajarajay10@gmail.com
 * @Github https://github.com/fajarazay
 */
class LoginViewModel : ViewModel() {
    private val tag = "LoginViewModel"

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    private val _taskAuthFB = MutableLiveData<Task<AuthResult>>()
    val taskAuthFB: LiveData<Task<AuthResult>> = _taskAuthFB

    private val _taskAuthGoogle = MutableLiveData<Task<AuthResult>>()
    val taskAuthGoogle: LiveData<Task<AuthResult>> = _taskAuthGoogle

    private val _onMessageStatus = MutableLiveData<String>()
    val messageStatus: LiveData<String> = _onMessageStatus

    fun handleOnActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN && data != null) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                Log.d(tag, "firebaseAuthWithGoogle:" + account?.id)
                account?.let {
                    val credential = GoogleAuthProvider.getCredential(it.idToken, null)
                    _taskAuthGoogle.value = auth.signInWithCredential(credential)
                }
            } catch (e: ApiException) {
                _onMessageStatus.value = e.message
                Log.d(tag, "Google sign in failed", e)
            }

        }
    }

    fun handleFacebookAccessToken(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        _taskAuthFB.value = auth.signInWithCredential(credential)
    }
}