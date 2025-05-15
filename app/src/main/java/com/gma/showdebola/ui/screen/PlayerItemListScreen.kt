package com.gma.showdebola.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gma.infrastructure.model.Player
import com.gma.showdebola.config.LocalNavController
import com.gma.showdebola.viewmodel.state.PlayerInGameState
import com.gma.showdebola.viewmodel.state.PlayerInGameState.AddPlayer
import com.gma.showdebola.viewmodel.state.PlayerInGameState.RemovePlayer
import com.gma.showdebola.viewmodel.state.SharedDataState
import org.koin.compose.koinInject

@Preview(
    showBackground = true
)
@Composable
fun PlayerItemListScreenPreview() {
    PlayerItemListScreen(
        Player(
            teamId = "",
            name = "Gabriel",
            mainPosition = "Meio",
            ability = 5
        )
    ) {}
}

@Composable
fun PlayerItemListScreen(
    player: Player,
    sharedTeamState: SharedDataState = koinInject(),
    checkCallback: (state: PlayerInGameState) -> Unit
) {
    val navController = LocalNavController.current
    var selected by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 16.dp
            )
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Checkbox(
                checked = selected,
                onCheckedChange = { isChecked ->
                    selected = isChecked

                    val state = when (isChecked) {
                        true -> AddPlayer(player)
                        else -> RemovePlayer(player)
                    }

                    checkCallback(state)
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(text = player.name)
                Text(text = player.mainPosition)
                Text(text = "habilidade: ${player.ability}")
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                IconButton(
                    onClick = {
                        sharedTeamState.updateSelectedPlayer(player)
                        navController.navigate("player_details")
                    }
                ) {
                    Icon(Icons.Filled.Edit, "")
                }
            }
        }
    }
}