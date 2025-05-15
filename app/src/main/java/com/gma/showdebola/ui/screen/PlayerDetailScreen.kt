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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gma.showdebola.config.LocalNavController
import com.gma.showdebola.viewmodel.PlayerParams
import com.gma.showdebola.viewmodel.PlayerViewModel
import com.gma.showdebola.viewmodel.state.SharedDataState
import com.gma.showdebola.viewmodel.state.UiState
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Preview
@Composable
fun PlayerDetailScreenPreview() {
    PlayerDetailScreen()
}

@Composable
fun PlayerDetailScreen(
    viewModel: PlayerViewModel = koinViewModel(),
    sharedDataState: SharedDataState = koinInject()
) {
    val navController = LocalNavController.current
    val uiState by viewModel.uiPlayerListState.collectAsState()
    val player by sharedDataState.selectedPlayer.collectAsState()

    var valueName by rememberSaveable { mutableStateOf(player?.name ?: "") }
    var valuePosition by rememberSaveable { mutableStateOf(player?.mainPosition ?: "") }
    var valueAbility by rememberSaveable { mutableIntStateOf(player?.ability ?: 0) }

    LaunchedEffect(uiState) {
        if (uiState is UiState.Success)
            navController.popBackStack()
    }

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
                value = valuePosition,
                label = { Text("Posição") },
                onValueChange = { valuePosition = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )

            TextField(
                value = valueAbility.toString(),
                label = { Text("Habilidade") },
                onValueChange = {
                    valueAbility = if (it.isEmpty()) 0 else it.toInt()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            )
        }

        FloatingActionButton(
            onClick = {
                viewModel.updatePlayer(
                    PlayerParams(
                        playerEdit = player,
                        name = valueName,
                        ability = valueAbility,
                        position = valuePosition
                    )
                )
            },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)

        ) {
            Icon(Icons.Filled.Add, "")
        }
    }

}