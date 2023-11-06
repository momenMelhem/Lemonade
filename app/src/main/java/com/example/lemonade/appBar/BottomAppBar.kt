package com.example.lemonade.appBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun BottomBar(navController :NavHostController= rememberNavController()) {

    val screens = listOf(
        BottomBarScreens.Home,
        BottomBarScreens.Slide
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}
@Composable
fun RowScope.AddItem(
    screen: BottomBarScreens,
    currentDestination: NavDestination?,
    navController: NavHostController= rememberNavController()
) {
    BottomNavigationItem(
        label = { Text(text = screen.title) },
        icon = { Icon(imageVector = screen.icon, contentDescription = "Navigation Icon") },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.rout
        } == true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.rout) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        },
    )
}