package com.github.fajarazay.simpleecommerce

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.fajarazay.simpleecommerce.ui.purchasehistory.PurchaseHistoryActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.navigation_profile) {
                val intent = Intent(this, PurchaseHistoryActivity::class.java)
                startActivity(intent)
            } else menuItem.isChecked = true

            false
        }

    }
}