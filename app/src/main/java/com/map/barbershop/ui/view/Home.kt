package com.map.barbershop.ui.view

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.map.barbershop.R
import com.map.barbershop.databinding.ActivityHomeBinding
import com.map.barbershop.ui.Api.client.ApiClient
import com.map.barbershop.ui.Api.entity.User
import com.map.barbershop.ui.fragment.bottomnavigation.BottomBarberiaFragment
import com.map.barbershop.ui.fragment.bottomnavigation.BottomHomeFragment
import com.map.barbershop.ui.fragment.bottomnavigation.BottomPerfilFragment
import com.map.barbershop.ui.fragment.bottomnavigation.BottomServicioFragment
import com.map.barbershop.ui.fragment.sidenavigation.SideAgendaFragment
import com.map.barbershop.ui.fragment.sidenavigation.SideAyudaFragment
import com.map.barbershop.ui.fragment.sidenavigation.SideContactoFragment
import com.map.barbershop.ui.fragment.sidenavigation.SideNotificacionFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var fab: FloatingActionButton;
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var bottomNavigationView: BottomNavigationView;
    private lateinit var toggle: ActionBarDrawerToggle;
    private lateinit var binding: ActivityHomeBinding

    private var ID_USERS : Int = 0
    private var NAME_USERS : String? = null
    private var EMAIL_USERS : String? = null
    private var USUARIO : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dateUser()

        ID_USERS = intent.getIntExtra("id_user", 0)
        NAME_USERS = intent.getStringExtra("name_user")
        EMAIL_USERS = intent.getStringExtra("email_user")
        USUARIO = intent.getStringExtra("usuario")

        val perfilBundle = Bundle().apply {
            putInt("id_user", ID_USERS)
            putString("name_user", NAME_USERS)
            putString("email_user", EMAIL_USERS)
            putString("usuario", USUARIO)
        }

        //sendDataFragment()
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        drawerLayout = findViewById(R.id.drawer_layout);

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.home -> replaceFragment(BottomHomeFragment(), Bundle())
                R.id.barberia -> replaceFragment(BottomBarberiaFragment(), Bundle())
                R.id.servicio -> replaceFragment(BottomServicioFragment(), Bundle())
                R.id.perfil -> replaceFragment(BottomPerfilFragment(), perfilBundle)
            }
            true
        }


        val toolbar: Toolbar = findViewById(R.id.toolbar);
        val navigationView : NavigationView = findViewById(R.id.nav_view);

        setSupportActionBar(binding.toolbar)
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, BottomHomeFragment()).commit()
            navigationView.setCheckedItem(R.id.home)
        }
    }

    open fun replaceFragment(fragment: Fragment, perfilBundle: Bundle) {

        fragment.arguments = perfilBundle

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }



/*    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)
        val videoLayout: LinearLayout = dialog.findViewById(R.id.layoutVideo)
        val shortsLayout: LinearLayout = dialog.findViewById(R.id.layoutShorts)
        val liveLayout: LinearLayout = dialog.findViewById(R.id.layoutLive)
        val cancelButton: ImageView = dialog.findViewById(com.map.barbershop.R.id.cancelButton)
        videoLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
                Toast.makeText(this@Home, "Upload a Video is clicked", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        shortsLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
                Toast.makeText(this@Home, "Create a short is Clicked", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        liveLayout.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                dialog.dismiss()
                Toast.makeText(this@Home, "Go live is Clicked", Toast.LENGTH_SHORT).show()
            }
        })
        cancelButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                dialog.dismiss()
            }
        })
        dialog.show()
        dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.getWindow()?.getAttributes()?.windowAnimations = R.style.DialogAnimation
        dialog.getWindow()?.setGravity(Gravity.BOTTOM)
    }*/

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                replaceFragment(BottomHomeFragment(), Bundle())
            }
            R.id.nav_agenda -> {
                replaceFragment(SideAgendaFragment(), Bundle())
            }
            R.id.nav_notificacion -> {
                replaceFragment(SideNotificacionFragment(), Bundle())
            }
            R.id.nav_contacto -> {
                replaceFragment(SideContactoFragment(), Bundle())
            }
            R.id.nav_about -> {
                replaceFragment(SideAyudaFragment(), Bundle())
            }
            R.id.nav_logout -> {
                if(!isFinishing){
                    messageLogout()
                }
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            if(!isFinishing){
                messageLogout()
            }else{
                super.onBackPressed()
            }
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?){
        super.onPostCreate(savedInstanceState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun logout(){
        val intent = Intent(this@Home, com.map.barbershop.MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    fun messageLogout(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("¿Estás seguro de que deseas cerrar sesión?")
        builder.setCancelable(false)

        builder.setPositiveButton("Sí") { _, _ ->
            logout()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.cancel()
        }

        val dialog = builder.create()
        dialog.show()
    }

    fun dateUser() {

        val retrofitTraer = ApiClient.consumirApi.getIdUser()

        retrofitTraer.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {


                        if (NAME_USERS != null && EMAIL_USERS != null && USUARIO != null ){
                            runOnUiThread {
                                val name_user = findViewById<TextView>(R.id.name_users)
                                val email_user = findViewById<TextView>(R.id.email_users)

                                name_user.text = NAME_USERS
                                email_user.text = EMAIL_USERS

                                sendDataFragment()
                                //sendDataFragmentPerfil()
                                //sendDataFragmentPerfil(id_users, name_users, email_users, usuario)
                            }
                        }

                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Maneja errores
            }
        })

    }

    fun sendDataFragment(){

        val fmanager = supportFragmentManager
        val fmanagertrans = fmanager.beginTransaction()

        // BottomHomeFragment
        val fragment = BottomHomeFragment()
        val databundle = Bundle()
        databundle.putInt("id_user", ID_USERS)
        databundle.putString("name_user", NAME_USERS)

        replaceFragment(BottomHomeFragment(), databundle)

        fragment.arguments = databundle
        fmanagertrans.replace(R.id.frameLayout, fragment).commit()

    }
}