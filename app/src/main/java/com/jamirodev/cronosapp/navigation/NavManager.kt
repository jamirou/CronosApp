package com.jamirodev.cronosapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jamirodev.cronosapp.viewModel.ChronometerViewModel
import com.jamirodev.cronosapp.viewModel.CronosViewModel
import com.jamirodev.cronosapp.views.AddView
import com.jamirodev.cronosapp.views.EditView
import com.jamirodev.cronosapp.views.HomeView


@Composable
fun NavManager(chronosVM: ChronometerViewModel, chrVM: CronosViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, chrVM)
        }
        composable("AddView"){
            AddView(navController, chronosVM, chrVM)
        }
        composable("EditView/{id}", arguments = listOf(
            navArgument("id") { type = NavType.LongType }
        )){
            val id = it.arguments?.getLong("id") ?: 0
            EditView(navController, chronosVM, chrVM, id)
        }
    }
}