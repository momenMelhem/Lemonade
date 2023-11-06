@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.lemonade.utils.navDrawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun NavDrawer(){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutine = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val items = listOf(
        NavigationItem(
            title = "All",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationItem(
            title = "About",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
        ),
        NavigationItem(
            title = "Settings",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
    )
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier =Modifier.height(16.dp))
                items.forEachIndexed { index, navigationItem ->
                    NavigationDrawerItem(
                        label = { Text(text = navigationItem.title) },
                        selected = index==selectedItemIndex,
                        onClick = {
                            selectedItemIndex=index
                            coroutine.launch {
                                drawerState.close()
                            }

                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex){
                                           navigationItem.selectedIcon
                                      }else navigationItem.unselectedIcon,

                                contentDescription = navigationItem.title
                            )
                        },
                        badge = {},
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                }
                Text(text = "MOMEN MELHEM")
            }
        },
        drawerState=drawerState
    ) {
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text(text = "Lemonade")},
                    navigationIcon = {
                    IconButton(onClick = {
                        coroutine.launch { drawerState.open() }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                    }
                })
            }
        ){
    Modifier.padding(it)
        }
    }
}