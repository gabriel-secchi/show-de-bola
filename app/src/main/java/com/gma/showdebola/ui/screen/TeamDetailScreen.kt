package com.gma.showdebola.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.gma.showdebola.viewmodel.TeamViewModel
import com.gma.showdebola.viewmodel.state.SharedDataState
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420"
)
@Composable
fun TeamDetailScreenPreview() {
    TeamDetailScreen()
}

@Composable
fun TeamDetailScreen(
    viewModel: TeamViewModel = koinViewModel(),
    sharedDataState: SharedDataState = koinInject()
) {
    val team by sharedDataState.selectedTeam.collectAsState()
    val gameTeams by sharedDataState.gameTeams.collectAsState()
    var valueName by rememberSaveable { mutableStateOf(team?.name ?: "") }
    var valueTime by rememberSaveable { mutableStateOf(team?.time ?: "") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = valueName,
                label = { Text("nome") },
                onValueChange = { valueName = it },
                modifier = Modifier
                    .fillMaxWidth()
            )

            TextField(
                value = valueTime,
                label = { Text("Horário") },
                onValueChange = { valueTime = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            team?.let {
                PlayerListScreen(it.id)
            }
        }

        FloatingActionButton(
            onClick = {
                viewModel.updateTeam(
                    id = team?.id,
                    name = valueName,
                    time = valueTime
                )
            },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)

        ) {
            Icon(Icons.Filled.Add, "")
        }

        gameTeams?.let {
            Popup(
                alignment = Alignment.Center,
                onDismissRequest = {
                    sharedDataState.updateGameTeams(null)
                },
                properties = PopupProperties( focusable = true )
            ) {
                GameTeamsScreen(gameTeams)
            }
        }
    }
}