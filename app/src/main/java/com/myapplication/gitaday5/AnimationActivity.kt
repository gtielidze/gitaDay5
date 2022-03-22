package com.myapplication.gitaday5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView

class AnimationActivity : AppCompatActivity() {
    private var animation: Animation? = null
    private var image: ImageView? = null
    private var button: Button? = null

    private var firsTask: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        animation()

        button = findViewById(R.id.openTelegram)

        button?.setOnClickListener {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse("https://telegram.me/InfotechAvl_bot"))
            startActivity(telegram)
        }

        firsTask = findViewById(R.id.openFirstTask)

        firsTask?.setOnClickListener {
            val intent = Intent(this, FirstTaskActivity::class.java)
            startActivity(intent)
        }

    }

    private fun animation() {
        animation = AnimationUtils.loadAnimation(this, R.anim.animation)
        image = findViewById(R.id.checkboxImage)
        image?.startAnimation(animation)
    }
}