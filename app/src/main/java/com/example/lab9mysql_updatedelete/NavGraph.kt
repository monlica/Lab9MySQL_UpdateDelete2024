package com.example.lab9mysql_updatedelete

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(
            route = Screen.Home.route
        ) {
            HomeScreen(navController)
        }
        composable(
            route = Screen.Insert.route
        ) {
            InsertScreen(navController)
        }
        composable(
            route = Screen.EditDelete.route
        ) {
            EditDeleteScreen(navController)
        }

    }
}