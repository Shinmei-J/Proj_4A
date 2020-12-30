package com.firstest.kotapp.presentation.main

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.firstest.kotapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel.loginLiveData.observe(this, Observer {
            when (it){
                is LoginSuccess -> {
                   startListActivity()
                    MaterialAlertDialogBuilder(this)
                            .setTitle("Success")
                            .setMessage("Logged In Successfully")
                            .setPositiveButton("ok"){dialog, which ->
                                dialog.dismiss()
                            }
                            .show()
                }

                    LoginError -> {
                        MaterialAlertDialogBuilder(this)
                                .setTitle("Erreur")
                                .setMessage("Compte inconnu")
                                .setPositiveButton("ok"){ dialog, which ->
                                    dialog.dismiss()
                                }
                                .show()
                    }
            }
        })
        login_button2.setOnClickListener{
            mainViewModel.onClickedLogin(login_edit2.text.toString().trim(), password_edit2.text.toString())
        }
        create_account_button2.setOnClickListener{
            startChangeActivity()
        }


    }

    private fun startChangeActivity() {
        val intent = Intent( this, AccountPageActivity::class.java )
        this.startActivity(intent)

    }
    private fun startListActivity() {
        val intent = Intent( this, com.firstest.kotapp.presentation.List.ListActivity::class.java )
        this.startActivity(intent)

    }
}