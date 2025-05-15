package com.gma.showdebola.config

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gma.showdebola.ui.screen.PlayerDetailScreen
import com.gma.showdebola.ui.screen.TeamDetailScreen
import com.gma.showdebola.ui.screen.TeamListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = "team_list") {
            composable("team_list") { TeamListScreen() }
            composable("team_details") { TeamDetailScreen() }
            composable("player_details") { PlayerDetailScreen() }
        }
    }
}