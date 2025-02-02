package com.example.lab9mysql_updatedelete

sealed class Screen(val route: String, val name: String) {
    data object Home: Screen(route = "home_screen", name = "Home")
    data object Insert: Screen(route = "insert_screen", name = "Insert")
    data object EditDelete: Screen(route = "edit_delete_screen", name = "EditDelete")
}

