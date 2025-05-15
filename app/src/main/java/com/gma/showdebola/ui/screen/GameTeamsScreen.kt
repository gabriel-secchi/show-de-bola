package com.gma.showdebola.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.gma.infrastructure.model.Game
import com.gma.infrastructure.model.Player
import com.gma.showdebola.ui.theme.Gray40

@Preview(
    showBackground = true
)
@Composable
fun GameTeamsScreenPreview() {
    GameTeamsScreen()
}

@Composable
fun GameTeamsScreen(
    gameTeams: Game? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                color = Gray40,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TeamColumn(
                title = "Time 1",
                players = gameTeams?.firstTeam,
                modifier = Modifier
                    .weight(1f)
            )
            TeamColumn(
                title = "Time 2",
                players = gameTeams?.secondTeam,
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Composable
fun TeamColumn(
    title: String,
    players: List<Player>?,
    modifier: Modifier = Modifier
    ) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = title,
            fontSize = TextUnit(18f, TextUnitType.Sp),
            fontWeight = FontWeight.Bold
        )
        players?.forEach { player ->
            Text(
                text = player.name,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}