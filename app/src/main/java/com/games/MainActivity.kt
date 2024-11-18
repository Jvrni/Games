package com.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.core.designsystem.theme.GamesTheme
import com.games.navigation.Navigator
import com.wygo.navigation.RouterImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GamesTheme {
                Navigator(RouterImpl(rememberNavController()))
            }
        }
    }
}