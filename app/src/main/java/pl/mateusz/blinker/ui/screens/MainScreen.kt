package pl.mateusz.blinker.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.mateusz.blinker.modules.navigation.NavigationRoutes
import pl.mateusz.blinker.ui.screens.pages.OrdersPage
import pl.mateusz.blinker.ui.screens.pages.SettingsPage
import pl.mateusz.blinker.ui.screens.pages.StartPage
import pl.mateusz.blinker.ui.screens.pages.StatsPage
import pl.mateusz.blinker.ui.theme.BlinkerTheme


@Composable
fun MainScreen(
    context: Context = LocalContext.current
) {
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState)},
        bottomBar = {
                    BottomNavBar(
                        navCon = navController
                    )
        },
        content = { padding ->

            NavHost(
                navController = navController,
                startDestination = NavigationRoutes.Inner.HomePage,
                modifier = Modifier.padding(padding)) {
                composable(NavigationRoutes.Inner.HomePage) {
                    StartPage()
                }
                composable(NavigationRoutes.Inner.OrdersPage) {
                    OrdersPage()
                }
                composable(NavigationRoutes.Inner.StatsPage) {
                    StatsPage()
                }
                composable(NavigationRoutes.Inner.SettingsPage) {
                    SettingsPage()
                }

            }
        }

    )

}


@Composable
fun BottomNavBar(
    navCon: NavHostController
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.primary
    ) {
        // Ikony na bottomBarze, myślę że self explainatory

        // jakies wiadomosci moze albo wiadomosc powitalna
        CategoryButton(
            navCon = navCon,
            destination = NavigationRoutes.Inner.HomePage,
            icon = Icons.Default.Home,
            description = "Home"
        )
        // zamowienia z kazdego orderStatusa do wyboru z zakladkami
        CategoryButton(
            navCon = navCon,
            destination = NavigationRoutes.Inner.OrdersPage,
            icon = Icons.Default.ShoppingCart,
            description = "Orders"
        )
        // statystyki zamowien ze strony baselinker wziete z web scrapa
        CategoryButton(
            navCon = navCon,
            destination = NavigationRoutes.Inner.StatsPage,
            icon = Icons.Default.Info,
            description = "Stats"
        )
        // ustawienia aplikacji jakies kolory, czy cos takiego
        CategoryButton(
            navCon = navCon,
            destination = NavigationRoutes.Inner.SettingsPage,
            icon = Icons.Default.Settings,
            description = "Settings"
        )
        CategoryButton(
            navCon = navCon,
            destination = NavigationRoutes.Inner.AccountPage,
            icon = Icons.Default.AccountBox,
            description = "Account")
    }
}


@Composable
fun CategoryButton(
    navCon: NavHostController,
    destination: String,
    icon: ImageVector,
    description: String
) {

    // TODO make an active button have a different tint
    val selected = navCon.currentBackStackEntry?.destination?.route == destination

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(10.dp)
            .width(50.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                Log.d("AHA", selected.toString())
                if (!selected) {
                    navCon.navigate(destination)
                }
            }
    ) {
        Icon(icon,
            contentDescription = description,
            tint = Color.White
        )
        Text(
            fontSize = 2.5.em,
            text = description,
            color = Color.White
        )
    }
}

@Preview(device = "id:pixel_5", showSystemUi = true)
@Composable
fun MainScreenPreview() {
    BlinkerTheme {
        MainScreen()
    }
}



