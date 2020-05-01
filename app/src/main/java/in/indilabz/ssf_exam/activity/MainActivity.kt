@file:Suppress("DEPRECATION")

package `in`.indilabz.ssf_exam.activity

import `in`.indilabz.ssf_exam.R
import `in`.indilabz.ssf_exam.fragment.main.ProfileFragment
import `in`.indilabz.ssf_exam.utils.Toaster
import `in`.indilabz.yorneeds.utils.INDIPreferences
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)

        toolbar.post {
            val d: Drawable = ResourcesCompat.getDrawable(resources,R.drawable.ic_dehaze,null)!!
            toolbar.navigationIcon = d
        }

        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment,
                R.id.profileFragment,
                R.id.demoFragment,
                R.id.appliedFragment,
                R.id.registeredFragment,
                R.id.tacFragment,
                R.id.privacyFragment,
                R.id.refundFragment),
            drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener {

            var int = 0

            when (it.itemId) {
                R.id.nav_logout -> {
                    logout()
                }
                R.id.nav_support -> {
                    contact()
                }
                else -> {
                    int = when (it.itemId) {
                        R.id.nav_home     -> (R.id.homeFragment)
                        R.id.nav_profile -> (R.id.profileFragment)
                        R.id.nav_demo    -> (R.id.demoFragment)
                        R.id.nav_applied    -> (R.id.appliedFragment)
                        R.id.nav_registered    -> (R.id.registeredFragment)
                        R.id.nav_tac    -> (R.id.tacFragment)
                        R.id.nav_privacy   -> (R.id.privacyFragment)
                        R.id.nav_refund    -> (R.id.refundFragment)


                        else -> R.id.homeFragment
                    }
                    drawerLayout.closeDrawer(navView)
                    toolbar.post {
                        val d: Drawable = ResourcesCompat.getDrawable(resources,R.drawable.ic_dehaze,null)!!
                        toolbar.navigationIcon = d
                    }
                    change(int)
                }
            }
        }

        navView.menu.getItem(0).isChecked = true
    }

    private fun contact(): Boolean{

        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("Contact us")

        val gender = arrayOf("Call us", "E-mail")
        builder.setItems(gender) { dialog, which ->
            when (which) {
                0 -> { call() }
                1 -> { mail() }
            }
        }

        val dialog = builder.create()
        dialog.show()

        return true
    }

    private fun call(){

        try{
            val intent = Intent(Intent.ACTION_DIAL);
            intent.data = (Uri.parse("tel:+919953953395"));
            startActivity(intent)
        }
        catch (e : Exception){

            Toaster.longt("Unable to make phone calls!")
        }
    }

    private fun mail(){

       try {
           val  mail = Intent(Intent.ACTION_SEND);
           mail.setType("message/rfc822");
           mail.putExtra(Intent.EXTRA_EMAIL  , arrayOf("info@hackplanet.in"));
           startActivity(Intent.createChooser(mail, "Send mail..."));
       }
       catch (e : Exception){
           Toaster.longt("Unable to open any email app, make sure you have one!")
       }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun change(destination: Int, args: Bundle){


        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(destination, args)
    }

    fun change(destination: Int): Boolean{
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(destination)
        return true
    }

    fun logout(): Boolean {

        INDIPreferences.preferenceEditor().clear().commit()
        INDIPreferences.session(false)
        startActivity(Intent(this@MainActivity, SplashActivity::class.java))
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.nav_notify){
            startActivity(Intent(this,Notification::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}
