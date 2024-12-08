package com.example.kplayerdemo.ui.song_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kplayerdemo.ui.song_list.component.SongListItem
import com.example.kplayerdemo.util.Constants
import com.example.kplayerdemo.util.SortingType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SongListScreen(
    navController: NavController,
    viewModel: SongListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    var title = "Results for \" ${Constants.DEFAULT_ARTIST_NAME} \""
    if (state.isLoading) {
        title = "Fetching results for \" ${Constants.DEFAULT_ARTIST_NAME} \""
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(title)
                        },
                scrollBehavior = scrollBehavior
            )
        }) { innerPadding ->

        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (state.error.isNotBlank()) {
                    Text(
                        text = "You're offline. Results may be limited.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.merge(),
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .padding(horizontal = 20.dp)
                    )
                }
                OutlinedTextField(
                    value = state.queryString,
                    onValueChange = { username -> viewModel.updateQueryString(username) },
                    label = { Text("Search by Song and Album") },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp)
                )
                SortByRadioButton(
                    selectedOption = state.selectedSortingType.displayText,
                    viewModel = viewModel
                )
                LazyColumn(
                    modifier = Modifier.fillMaxSize().weight(1f, false),
                    contentPadding = PaddingValues(20.dp)
                ) {
                    items(state.displaySongs) { song ->
                        SongListItem(song)
                    }
                }
                //Spacer(modifier = Modifier.height(10.dp))
            }

            if (state.displaySongs.isEmpty()) {
                Text(
                    text = "No data",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }

            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun SortByRadioButton(selectedOption: String,
                      viewModel: SongListViewModel) {
    val radioOptions = listOf(SortingType.BY_SONG, SortingType.BY_ALBUM)
    Row(Modifier.selectableGroup()) {
        Text(
            text = "Sort by",
            style = MaterialTheme.typography.bodyMedium.merge(),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 30.dp)
        )
        radioOptions.forEach { option ->
            Row(
                Modifier
                    .width(130.dp)
                    .selectable(
                        selected = (option.displayText == selectedOption),
                        onClick = {
                            viewModel.updateSortingType(option)
                        }
                    )
            ) {
                RadioButton(
                    selected = (option.displayText == selectedOption),
                    onClick = {
                        viewModel.updateSortingType(option)
                    }
                )
                Text(
                    text = option.displayText,
                    style = MaterialTheme.typography.bodyMedium.merge(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}
