package bonch.dev.school.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import bonch.dev.school.R
import bonch.dev.school.ui.fragments.ChatFragment
import bonch.dev.school.ui.fragments.PasswordFragment
import bonch.dev.school.ui.fragments.ProfileFragment

class MainAppActivity : AppCompatActivity() {
val fm=supportFragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        val chatFragment= ChatFragment()
        fm.beginTransaction().add(R.id.fg,chatFragment).commit()
    }
    fun replace(){
        val profileFragment= ProfileFragment()
        fm.beginTransaction().replace(R.id.fg,profileFragment).addToBackStack("fragment_chat").commit()
    }
    fun passFrag(){
        val passFragment=PasswordFragment()
        fm.beginTransaction().replace(R.id.fg,passFragment).commit()
    }
}
