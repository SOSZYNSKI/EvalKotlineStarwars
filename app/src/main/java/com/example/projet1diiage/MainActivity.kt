package com.example.projet1diiage


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.projet1diiage.StarWars.ui.StarWarsNavHost
import com.example.projet1diiage.core.ui.theme.Projet1DIIAGETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projet1DIIAGETheme {
                val navController = rememberNavController()
                StarWarsNavHost(nav = navController)
            }
        }
    }
}
