package com.jamirodev.cronosapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamirodev.cronosapp.R
import com.jamirodev.cronosapp.components.CircleButton
import com.jamirodev.cronosapp.components.FloatButton
import com.jamirodev.cronosapp.components.MainIconButton
import com.jamirodev.cronosapp.components.MainTextField
import com.jamirodev.cronosapp.components.MainTitle
import com.jamirodev.cronosapp.components.formatTime
import com.jamirodev.cronosapp.model.Cronos
import com.jamirodev.cronosapp.viewModel.ChronometerViewModel
import com.jamirodev.cronosapp.viewModel.CronosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddView(navController: NavController, chronosVM: ChronometerViewModel, chrVM: CronosViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = "ADD") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentAddView(it, navController, chronosVM, chrVM)
    }
}

@Composable
fun ContentAddView(
    it: PaddingValues,
    navController: NavController,
    chronosVM: ChronometerViewModel,
    chrVM: CronosViewModel
) {

    val state = chronosVM.state

    LaunchedEffect(state.chronometerActive) {
        chronosVM.chronos()
    }
    Column(
        modifier = Modifier
            .padding(it)
            .padding(top = 30.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatTime(chronosVM.time),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            //INIT
            CircleButton(
                icon = painterResource(id = R.drawable.ic_play),
                enabled = !state.chronometerActive
            ) {
                chronosVM.init()
            }
            //PAUSE
            CircleButton(
                icon = painterResource(id = R.drawable.ic_pause),
                enabled = state.chronometerActive
            ) {
                chronosVM.pause()
            }
            //STOP
            CircleButton(
                icon = painterResource(id = R.drawable.ic_stop),
                enabled = !state.chronometerActive
            ) {
                chronosVM.stop()
            }

            //SAVE AND SHOW
            CircleButton(
                icon = painterResource(id = R.drawable.ic_save),
                enabled = state.showSaveButton
            ) {
                chronosVM.showTextField()
            }
        }

        if (state.showTextField) {
            MainTextField(
                value = state.title,
                onValueChange = { chronosVM.onValue(it) },
                label = "Title"
            )
            Button(onClick = {
                chrVM.addCrono(
                    Cronos(
                        title = state.title,
                        crono = chronosVM.time
                    )
                )
                chronosVM.stop()
                navController.popBackStack()
                
            }) {
                Text(text = "Save")
            }
        }

    }
}