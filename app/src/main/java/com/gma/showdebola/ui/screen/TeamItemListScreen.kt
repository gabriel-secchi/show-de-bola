package com.gma.showdebola.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gma.infrastructure.model.Team
import com.gma.showdebola.config.LocalNavController
import com.gma.showdebola.viewmodel.state.SharedDataState
import org.koin.compose.koinInject


@Composable
fun TeamItemListScreen(
    team: Team,
    sharedTeamState: SharedDataState = koinInject()
) {
    val navController = LocalNavController.current

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    sharedTeamState.updateSelectedTeam(team)
                    navController.navigate("team_details")
                }
            )
    ) {
        Text(
            text = team.name
        )
        Text(
            text = team.time,
            modifier = Modifier
                .padding(top = 6.dp)

        )
    }
}