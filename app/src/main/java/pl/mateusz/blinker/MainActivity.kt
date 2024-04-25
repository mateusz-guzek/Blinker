package pl.mateusz.blinker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.mateusz.blinker.modules.navigation.NavigationRoutes
import pl.mateusz.blinker.ui.screens.LoginScreen
import pl.mateusz.blinker.ui.screens.MainScreen
import pl.mateusz.blinker.ui.theme.BlinkerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlinkerTheme {
                val navController = rememberNavController()

                // change startDestination later
                NavHost(navController = navController, startDestination = NavigationRoutes.Outer.LoginScreen) {
                    composable(NavigationRoutes.Outer.LoginScreen) {
                        LoginScreen(
                            onLoginNav = {
                                navController.navigate(NavigationRoutes.Outer.MainScreen)
                            }
                        )
                    }

                    composable(NavigationRoutes.Outer.MainScreen) {
                        // disable return button
                        BackHandler(true) {

                        }
                        MainScreen()
                    }
                }
            }
        }
    }
}
