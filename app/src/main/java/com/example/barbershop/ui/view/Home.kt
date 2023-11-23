package com.example.barbershop.ui.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.barbershop.MainActivity
import com.example.barbershop.R
import com.example.barbershop.databinding.ActivityHomeBinding
import com.example.barbershop.ui.Api.client.ApiClient
import com.example.barbershop.ui.Api.entity.User
import com.example.barbershop.ui.fragment.bottomnavigation.BottomBarberiaFragment
import com.example.barbershop.ui.fragment.bottomnavigation.BottomHomeFragment
import com.example.barbershop.ui.fragment.bottomnavigation.BottomPerfilFragment
import com.example.barbershop.ui.fragment.bottomnavigation.BottomServicioFragment
import com.example.barbershop.ui.fragment.sidenavigation.SideAgendaFragment
import com.example.barbershop.ui.fragment.sidenavigation.SideAyudaFragment
import com.example.barbershop.ui.fragment.sidenavigation.SideContactoFragment
import com.example.barbershop.ui.fragment.sidenavigation.SideNotificacionFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{
    private lateinit var fab: FloatingActionButton;
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var bottomNavigationView: BottomNavigationView;
    private lateinit var toggle: ActionBarDrawerToggle;
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);

        dateUser()

        val toolbar: Toolbar = findViewById(R.id.toolbar);
        val navigationView : NavigationView = findViewById(R.id.nav_view);

        setSupportActionBar(binding.toolbar)

        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.home -> replaceFragment(BottomHomeFragment())
                R.id.barberia -> replaceFragment(BottomBarberiaFragment())
                R.id.servicio -> replaceFragment(BottomServicioFragment())
                R.id.perfil -> replaceFragment(BottomPerfilFragment())
            }
            true
        }

        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                showBottomDialog()
            }
        })

        navigationView.setNavigationItemSelectedListener(this)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, BottomHomeFragment()).commit()
            navigationView.setCheckedItem(R.id.nav_home)
        }

    }

    open fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }

    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)
        val videoLayout: LinearLayout = dialog.findViewById(R.id.layoutVideo)
        val shortsLayout: LinearLayout = dialog.findViewById(R.id.layoutShorts)
        val liveLayout: LinearLayout = dialog.findViewById(R.id.layoutLive)
        val cancelButton: ImageView = dialog.findViewById(com.example.barbershop.R.id.cancelButton)
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
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                replaceFragment(BottomHomeFragment())
            }
            R.id.nav_agenda -> {
                replaceFragment(SideAgendaFragment())
            }
            R.id.nav_notificacion -> {
                replaceFragment(SideNotificacionFragment())
            }
            R.id.nav_contacto -> {
                replaceFragment(SideContactoFragment())
            }
            R.id.nav_about -> {
                replaceFragment(SideAyudaFragment())
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
        val intent = Intent(this@Home, MainActivity::class.java)
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
        // Obtén el ID de usuario almacenado en las preferencias compartidas
        val sharedPref: SharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val userId = sharedPref.getString("user_id", "")

        val retrofitTraer = ApiClient.consumirApi.getUserId(userId)

        retrofitTraer.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    val objectsUser = response.body()?.`object`?.get(0)
                    if (objectsUser != null) {
                        // Accede a los campos del objeto
                        val correo = objectsUser.correo
                        val nombre = objectsUser.nombre

                        // Actualiza tus vistas con los datos
                        runOnUiThread {
                            val name_user = findViewById<TextView>(R.id.name_users)
                            val email_user = findViewById<TextView>(R.id.email_users)
                            name_user.text = nombre
                            email_user.text = correo
                        }
                    }
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                // Maneja errores
            }
        })
    }

}