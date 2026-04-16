package com.csvapp

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(viewModel: UserViewModel, srNo: Int, navController: NavController) {
    val user by viewModel.selectedUser.collectAsStateWithLifecycle(initialValue = null)

    LaunchedEffect(srNo) {
        viewModel.loadUserDetails(srNo)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Voter Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        user?.let {
            Column(modifier = Modifier.padding(padding).padding(16.dp)) {
                Text("SR_NO: ${it.SR_NO}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("EPIC_Number: ${it.EPIC_Number}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Voter_Name: ${it.Voter_Name}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Relative_Name: ${it.Relative_Name}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Relation: ${it.Relation}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("House_Number: ${it.House_Number}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Age: ${it.Age}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Gender: ${it.Gender}", style = MaterialTheme.typography.bodyLarge)
            }
        } ?: Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
    }
}
