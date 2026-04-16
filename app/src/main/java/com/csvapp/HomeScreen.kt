package com.csvapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: UserViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(searchQuery) {
        viewModel.setSearchQuery(searchQuery)
    }

    val users = viewModel.users.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Voter Search") }
            )
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search by EPIC Number or Voter Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            LazyColumn {
                items(
                    count = users.itemCount,
                    key = users.itemKey { it.SR_NO }
                ) { index ->
                    val user = users[index]
                    user?.let {
                        UserItem(user = it, onClick = {
                            navController.navigate("detail/${it.SR_NO}")
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Name: ${user.Voter_Name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "EPIC: ${user.EPIC_Number}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Age: ${user.Age}, Gender: ${user.Gender}", style = MaterialTheme.typography.bodySmall)
        }
    }
}