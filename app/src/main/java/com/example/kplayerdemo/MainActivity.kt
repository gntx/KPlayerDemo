package com.example.kplayerdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kplayerdemo.ui.song_list.SongListScreen
import com.example.kplayerdemo.ui.theme.KPlayerDemoTheme
import com.example.kplayerdemo.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KPlayerDemoTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController,
                        startDestination = Constants.DEFAULT_ARTIST_NAME,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        composable(route = Constants.DEFAULT_ARTIST_NAME) {
                            SongListScreen(navController)
                        }
                    }
                }
            }
        }
    }
}