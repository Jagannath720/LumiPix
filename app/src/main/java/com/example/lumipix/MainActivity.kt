package com.example.lumipix


import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair // Import androidx.core.util.Pair

class MainActivity : AppCompatActivity() {

    companion object {
        private const val SPLASH_SCREEN: Long = 5000 // 5 seconds delay
    }

    private lateinit var topAnim: android.view.animation.Animation
    private lateinit var bottomAnim: android.view.animation.Animation
    private lateinit var image: ImageView
    private lateinit var logo: TextView
    private lateinit var slogan: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "onCreate called")

        // Load animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation)

        // Initialize views
        image = findViewById(R.id.imageView)
        logo = findViewById(R.id.textView)
        slogan = findViewById(R.id.textView2)

        // Start animations
        image.startAnimation(topAnim)
        logo.startAnimation(bottomAnim)
        slogan.startAnimation(bottomAnim)

        // Transition to Login activity with shared element transition after splash screen delay
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, Login::class.java)

            // Create pairs for shared element transitions using androidx.core.util.Pair
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this,
                Pair(image, "logo_image"),
                Pair(logo, "logo_text")
            )

            // Start the new activity with the transition options
            startActivity(intent, options.toBundle())
            finish() // Close this activity
        }, SPLASH_SCREEN)
    }
}


//new Handler () .postDelayed (() {
// Intent intent = new Intent (packageContext: MainActivity. this, Login.class);
// pair[] pairs = new Pair[2];
////pairs[0] = new pair <View, string>(image,"logo_image");
////pairs[0] = new pair <View, string>(logo,"logo_text");
//ActivityOpitions options = ActivityOptions.makesceneTransitionAnimation(activity:MainActivity.this,pairs);
//startActivity(intent,options.toBundle());
// } , SPLASH_SCREEN);
//

