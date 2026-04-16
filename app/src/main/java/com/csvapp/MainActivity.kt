package com.csvapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(UserRepository(UserDao(VoterDatabaseHelper.getInstance(this))))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    VoterApp(viewModel)
                }
            }
        }
    }
}

@Composable
fun VoterApp(viewModel: UserViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(viewModel, navController)
        }
        composable("detail/{srNo}") { backStackEntry ->
            val srNo = backStackEntry.arguments?.getString("srNo")?.toIntOrNull()
            if (srNo != null) {
                DetailScreen(viewModel, srNo, navController)
            }
        }
    }
}