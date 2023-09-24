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
import androidx.compose.runtime.DisposableEffect
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
import com.jamirodev.cronosapp.components.MainIconButton
import com.jamirodev.cronosapp.components.MainTextField
import com.jamirodev.cronosapp.components.MainTitle
import com.jamirodev.cronosapp.components.formatTime
import com.jamirodev.cronosapp.model.Cronos
import com.jamirodev.cronosapp.viewModel.ChronometerViewModel
import com.jamirodev.cronosapp.viewModel.CronosViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditView(
    navController: NavController,
    chronosVM: ChronometerViewModel,
    chrVM: CronosViewModel,
    id: Long
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { MainTitle(title = "Edit") },
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
        ContentEditView(it, navController, chronosVM, chrVM, id)
    }
}

@Composable
fun ContentEditView(
    it: PaddingValues,
    navController: NavController,
    chronosVM: ChronometerViewModel,
    chrVM: CronosViewModel,
    id: Long
) {

    val state = chronosVM.state

    LaunchedEffect(state.chronometerActive) {
        chronosVM.chronos()
    }

    LaunchedEffect(Unit) {
        chronosVM.getCronoById(id)
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
        }

        MainTextField(
            value = state.title,
            onValueChange = { chronosVM.onValue(it) },
            label = "Title"
        )
        Button(onClick = {
            chrVM.updateCrono(
                Cronos(
                    id = id,
                    title = state.title,
                    crono = chronosVM.time
                )
            )
            navController.popBackStack()

        }) {
            Text(text = "Save")
        }
        DisposableEffect(Unit) {
            onDispose {
                chronosVM.stop()
            }
        }


    }
}