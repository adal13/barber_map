package com.example.barbershop.view

import android.app.Dialog
import android.content.ClipData.Item
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.barbershop.*
import com.example.barbershop.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView


open class Home : AppCompatActivity() {
    private lateinit var fab: FloatingActionButton;
    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var bottomNavigationView: BottomNavigationView;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);

        val navigationView : NavigationView = findViewById(R.id.nav_view);
        val toolbar: Toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout, HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }
        replaceFragment(HomeFragment())

        bottomNavigationView.setBackground(null)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.getItemId()) {
                R.id.home -> replaceFragment(HomeFragment())
                R.id.shorts -> replaceFragment(ShortsFragment())
                R.id.subscriptions -> replaceFragment(SubscriptionsFragment())
                R.id.library -> replaceFragment(LibraryFragment())
            }
            true
        }

        fab.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                showBottomDialog()
            }
        })
    }

    // Outside onCreate
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

}