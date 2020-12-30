package com.firstest.kotapp.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.firstest.kotapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.accountpage.*
import org.koin.android.ext.android.inject


class AccountPageActivity : AppCompatActivity() {

    private val changeaccountViewModel: ChangeAccountViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.accountpage)
        changeaccountViewModel.changeLiveData.observe(this, Observer {
            when (it) {
                is ChangeSuccess -> {
                    Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
                    startMainActivity()
                }
                NoEmail -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Error")
                        .setMessage("Please enter an email address")
                        .setPositiveButton("Ok") { dialog, which ->
                            dialog.dismiss()
                        }
                        .show()
                }
                InvalidM  -> {
                    MaterialAlertDialogBuilder(this)
                            .setTitle("Error")
                            .setMessage("Please enter a valid email address")
                            .setPositiveButton("Ok") { dialog, which ->
                                dialog.dismiss()
                            }
                            .show()
                }
                Nopassword  -> {
                    MaterialAlertDialogBuilder(this)
                            .setTitle("Error")
                            .setMessage("Please enter a password")
                            .setPositiveButton("Ok") { dialog, which ->
                                dialog.dismiss()
                            }
                            .show()
                }
                ExistingAcc  -> {
                    MaterialAlertDialogBuilder(this)
                            .setTitle("Error")
                            .setMessage("This account already exist")
                            .setPositiveButton("Ok") { dialog, which ->
                                dialog.dismiss()
                            }
                            .show()
                }

            }
        })
        create_account_button2.setOnClickListener {
            changeaccountViewModel.onClickedChange(
                login_edit2.text.toString().trim(),
                password_edit2.text.toString()
            )
        }
    }


    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }
}




