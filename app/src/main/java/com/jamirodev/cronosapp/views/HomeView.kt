package com.jamirodev.cronosapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.jamirodev.cronosapp.components.FloatButton
import com.jamirodev.cronosapp.components.MainTitle
import com.jamirodev.cronosapp.components.formatTime
import com.jamirodev.cronosapp.viewModel.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, chrVM: CronosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = "CRONO APP") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            FloatButton {
                navController.navigate("AddView")
            }
        }
    ) {
        ContentHomeView(it, navController, chrVM)
    }
}

@Composable
fun ContentHomeView(it: PaddingValues, navController: NavController, chrVM: CronosViewModel) {
    Column(
        modifier = Modifier
            .padding(it)
    ) {
        val cronosList by chrVM.cronosList.collectAsState()
        LazyColumn{
            items(cronosList) {item ->
                Text(text = formatTime(item.crono))
            }
        }
    }
}












