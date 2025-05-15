package com.gma.showdebola.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gma.showdebola.config.LocalNavController
import com.gma.showdebola.viewmodel.PlayerViewModel
import com.gma.showdebola.viewmodel.state.UiState
import org.koin.androidx.compose.koinViewModel

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = "spec:width=411dp,height=891dp,dpi=420"
)
@Composable
fun PlayerListScreenPreview() {
    PlayerListScreen("")
}


@Composable
fun PlayerListScreen(
    teamId: String,
    viewModel: PlayerViewModel = koinViewModel()
) {
    val navController = LocalNavController.current
    val uiState by viewModel.uiPlayerListState.collectAsState()
    val uiGameState by viewModel.uiPlayersInGameState.collectAsState()

    var playersQtd by rememberSaveable { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        viewModel.searchPlayers(teamId)
    }


    Column {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            thickness = 2.dp,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        Row {
            Text(
                text = "Jogadores: $playersQtd",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )

            if (uiGameState.isNotEmpty()) {
                Text(
                    text = "| em jogo: ${uiGameState.size}",
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .align(Alignment.CenterVertically)
                        .clickable(
                            enabled = true,
                            onClick = {
                                viewModel.sorterTeams()
                            }
                        )
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                onClick = {
                    navController.navigate("player_details")
                }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }

        if (uiState is UiState.Success) {
            (uiState as UiState.Success).data?.let { playerList ->
                playersQtd = playerList.size
                LazyColumn {
                    items(playerList.size) { index ->
                        PlayerItemListScreen(playerList[index]) { statePlayer ->
                            viewModel.playersInGame(statePlayer)
                        }
                    }
                }
            }
        }
    }
}