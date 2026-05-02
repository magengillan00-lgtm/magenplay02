package com.magen.play02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.magen.play02.ui.editor.VideoEditorScreen
import com.magen.play02.ui.mp3converter.MP3ConverterScreen
import com.magen.play02.ui.player.PlayerScreen
import com.magen.play02.ui.theme.AnimeTheme

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MagenPlay02App()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagenPlay02App() {
    val navController = rememberNavController()
    
    AnimeTheme {
        Scaffold(
            bottomBar = {
                AnimeBottomNavigation(navController = navController)
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                AppNavHost(navController = navController)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "player"
    ) {
        composable("player") {
            PlayerScreen()
        }
        composable("editor") {
            VideoEditorScreen()
        }
        composable("converter") {
            MP3ConverterScreen()
        }
    }
}

val bottomNavItems = listOf(
    BottomNavItem(
        label = "مشغل",
        icon = Icons.Default.Home,
        route = "player"
    ),
    BottomNavItem(
        label = "محرر",
        icon = Icons.Default.Edit,
        route = "editor"
    ),
    BottomNavItem(
        label = "MP3",
        icon = Icons.Default.MusicNote,
        route = "converter"
    )
)

@Composable
fun AnimeBottomNavigation(navController: NavHostController) {
    val selectedItem by navController.currentBackStackEntryAsState().observeAsState {
        navController.currentBackStackEntry
    }?.let { entry ->
        entry?.destination?.route
    }?.let { route ->
        route
    } ?: "player"

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                label = {
                    Text(text = item.label)
                },
                selected = selectedItem == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                )
            )
        }
    }
}