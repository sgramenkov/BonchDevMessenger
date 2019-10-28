package bonch.dev.school.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import bonch.dev.school.R
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import bonch.dev.school.ui.fragments.ChatFragment
import bonch.dev.school.ui.fragments.ProfileFragment
import com.google.android.material.navigation.NavigationView

class MainAppActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
lateinit var toolbar:Toolbar
    private lateinit var drawer:DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_app)
        toolbar =findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawer=findViewById(R.id.drawer_layout)
        val navigationView:NavigationView=findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle=ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState==null){
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container,ChatFragment()).commit()
        navigationView.setCheckedItem(R.id.chat_fragment)
    }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        if(p0.itemId==R.id.chat_fragment){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,ChatFragment()).commit()
        }
        if(p0.itemId==R.id.profile_fragment){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,ProfileFragment()).commit()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }


}
