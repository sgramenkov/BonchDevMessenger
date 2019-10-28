package bonch.dev.school.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import bonch.dev.school.R

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        initializeViews()
        setListeners()
    }

    fun initializeViews() {
        signUpButton = findViewById(R.id.complete_sign_up_button)
    }

    fun setListeners() {
        signUpButton.setOnClickListener() {
            val intent = Intent(this, MainAppActivity::class.java)
            startActivity(intent)
        }
    }

}
