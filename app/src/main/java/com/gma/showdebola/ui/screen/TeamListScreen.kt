package com.gma.showdebola.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gma.showdebola.R
import com.gma.showdebola.config.LocalNavController
import com.gma.showdebola.viewmodel.TeamViewModel
import com.gma.showdebola.viewmodel.state.SharedDataState
import com.gma.showdebola.viewmodel.state.UiState
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Preview()
@Composable
fun TeamListScreenPreview() {
    TeamListScreen()
}

@Composable
fun TeamListScreen(
    viewModel: TeamViewModel = koinViewModel(),
    sharedTeamState: SharedDataState = koinInject()
) {
    LaunchedEffect(Unit) {
        viewModel.searchTeams()
    }

    val navController = LocalNavController.current
    var search by rememberSaveable { mutableStateOf("") }
    val uiState by viewModel.uiTeamListState.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = stringResource(R.string.team_list_title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(16.dp)
            )

            TextField(
                value = search,
                label = { Text(stringResource(R.string.team_list_search_placeholder)) },
                onValueChange = { search = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp)
            )

            if (uiState is UiState.Success) {
                (uiState as UiState.Success).data?.let { teamList ->
                    LazyColumn {
                        items(teamList.size) { index ->
                            TeamItemListScreen(teamList[index])
                        }
                    }
                }
            }

        }

        when (uiState) {
            is UiState.Loading,
            is UiState.Initial -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.Center)
                )
            }

            is UiState.Error -> {
                val errorMessage = (uiState as UiState.Error).errorMessage
                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.Center)
                )
            }

            is UiState.Success -> {
                val teamList = (uiState as UiState.Success).data
                if (teamList.isNullOrEmpty()) {
                    Text(
                        text = stringResource(R.string.team_list_empty_list),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.Center)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = {
                sharedTeamState.updateSelectedTeam(null)
                navController.navigate("team_details")
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