package com.myapplication.gitaday5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private var numberField: EditText? = null
    private var textField: EditText? = null
    private var saveButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialize()
        saveButton?.setOnClickListener {
            save()
        }
    }

    private fun initialize() {
        numberField = findViewById(R.id.numberTxt)
        textField = findViewById(R.id.textTxt)
        saveButton = findViewById(R.id.saveBtn)
    }

    private fun save() {
        val intent = Intent(this, SaveActivity::class.java)
        intent.putExtra("number", numberField?.text.toString())
        intent.putExtra("text", textField?.text.toString())
        startActivity(intent)
    }

}