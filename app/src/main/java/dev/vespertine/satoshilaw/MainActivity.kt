package dev.vespertine.satoshilaw

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    public val projectList = mutableListOf<Project>()
    lateinit var focusProject : Project

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragment_container, TimerFragment.newInstance())
//            .addToBackStack(null)
//            .commit()

        replaceFragment(TimerFragment.newInstance())
      //  toolbar.setTitleTextColor(resources.getColor(R.color.colorPrimary))

        val p1 = Project(1, "Project Name", "Client Name",
            0.00, 0, 0, 0)
        val p2 = Project(2, "Summer Hackathon", "Lambda School",
            30.00, 24, 9, 21 )

        projectList.add(p1)
        projectList.add(p2)


    }

    override fun onResume() {
        super.onResume()


    }

    override fun onBackPressed() {
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_timer -> {
                replaceFragment(TimerFragment.newInstance())
            }
            R.id.nav_insert -> {
                replaceFragment(EditProjectFragment.newInstance())

            }
            R.id.nav_people -> {

            }
            R.id.nav_settings -> {

            }

        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun replaceFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()

    }

    public fun getProjects(): MutableList<Project> {
        return projectList
    }

    fun addProject(toAdd: Project) {
        projectList.add(toAdd)
    }

    fun setProjectFocus(project:Project) {
        focusProject = project
    }

}
