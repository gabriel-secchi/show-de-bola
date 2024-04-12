package com.gma.showdebola.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.gma.showdebola.R
import com.gma.showdebola.databinding.ActivityMainBinding
import com.gma.showdebola.preferences.PreferenceKeys
import com.gma.showdebola.preferences.getPreferenceString

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
        setupView()
    }

    private fun setupView() {
        val selectedTeamId = getPreferenceString(PreferenceKeys.TEAM_SELECTED_ID)
        val action = if (selectedTeamId.isNullOrBlank())
            R.id.action_Splash_To_Team_List
        else
            R.id.action_Splash_To_Team_Detail

        navController.navigate(action)
    }

    private fun setupNavController() {
        navController = findNavController(R.id.main_nav_graph)
        //appBarConfiguration = AppBarConfiguration(navController.graph)
        //setupActionBarWithNavController(navController, appBarConfiguration)
    }
}