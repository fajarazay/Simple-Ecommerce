package com.github.fajarazay.simpleecommerce.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.github.fajarazay.simpleecommerce.MainActivity
import com.github.fajarazay.simpleecommerce.R
import com.github.fajarazay.simpleecommerce.core.utils.toast
import com.github.fajarazay.simpleecommerce.core.utils.viewBinding
import com.github.fajarazay.simpleecommerce.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class LoginActivity : AppCompatActivity() {
    private val binding by viewBinding(ActivityLoginBinding::inflate)
    private lateinit var auth: FirebaseAuth
    private val tag = "LoginActivity"
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var callbackManager: CallbackManager
    private val viewModel: LoginViewModel by viewModel()

    companion object {
        const val RC_SIGN_IN = 9001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initGoogleClient()

        binding.btnLoginUsername.setOnClickListener {
            gotoHome()
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        binding.btnFacebookLogin.setReadPermissions("email", "public_profile")

        binding.btnGoogleLogin.setOnClickListener {
            signIn()
        }

        viewModel.taskAuthFB.observe(this, { authFB ->
            Log.d(tag, authFB.isSuccessful.toString())

            authFB.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(tag, "isSuccessful")
                    this@LoginActivity.toast("Success")
                    gotoHome()
                } else {
                    this@LoginActivity.toast(task.exception?.message.toString())
                }
            }
        })

        viewModel.messageStatus.observe(this, { message ->
            this@LoginActivity.toast(message)
        })

        viewModel.taskAuthGoogle.observe(this, { authGoogle ->
            Log.d(tag, authGoogle.isSuccessful.toString())
            authGoogle.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(tag, "isSuccessful")
                    this@LoginActivity.toast("Success")
                    gotoHome()
                } else {
                    this@LoginActivity.toast(task.exception?.message.toString())
                }
            }
        })

        binding.btnFacebookLogin.registerCallback(
            callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    Log.d("onSuccess loginResult: ", loginResult.toString())
                    loginResult?.accessToken?.let {
                        viewModel.handleFacebookAccessToken(it)
                    }
                }

                override fun onCancel() {
                    this@LoginActivity.toast("Cancel")
                }

                override fun onError(exception: FacebookException) {
                    Log.d("onError: ", exception.toString())
                    this@LoginActivity.toast("onError $exception")
                }
            })
    }

    private fun initGoogleClient() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
    }

    private fun gotoHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
        viewModel.handleOnActivityResult(requestCode, resultCode, data)
    }
}