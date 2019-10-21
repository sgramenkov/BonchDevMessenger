package bonch.dev.school.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import bonch.dev.school.R

class SignInActivity : AppCompatActivity() {
private lateinit var signInButton:Button
    private lateinit var signUpButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initializeViews()
        setListeners()
    }
    fun initializeViews(){
        signInButton=findViewById(R.id.sign_in_button)
        signUpButton=findViewById(R.id.sign_up_button)
    }
    fun setListeners(){
        signInButton.setOnClickListener(){
            val intent=Intent(this,MainAppActivity::class.java)
            startActivity(intent)
        }
        signUpButton.setOnClickListener(){
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
