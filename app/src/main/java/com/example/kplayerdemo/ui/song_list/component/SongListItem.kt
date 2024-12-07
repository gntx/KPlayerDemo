package com.example.kplayerdemo.ui.song_list.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.kplayerdemo.domain.model.Song

@Composable
fun SongListItem(song: Song) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = song.artworkUrl100,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier.fillMaxSize()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = song.trackName,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxWidth()
            )
            //Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = song.collectionName,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
    HorizontalDivider()
}