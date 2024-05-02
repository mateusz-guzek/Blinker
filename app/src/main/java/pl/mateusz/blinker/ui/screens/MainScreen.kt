package pl.mateusz.blinker.ui.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import pl.mateusz.blinker.modules.navigation.NavigationRoutes
import pl.mateusz.blinker.modules.storage.DataStorage
import pl.mateusz.blinker.ui.screens.pages.AccountPage
import pl.mateusz.blinker.ui.screens.pages.HomePage
import pl.mateusz.blinker.ui.screens.pages.OrdersPage
import pl.mateusz.blinker.ui.screens.pages.SettingsPage
import pl.mateusz.blinker.ui.screens.pages.StatsPage


@Composable
fun MainScreen(
    navBackToLogin: () -> Unit,
    context: Context = LocalContext.current
) {
    val navController = rememberNavController()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    SideEffect {
        Thread { // tu jest inny thread
            val accountsAmount = DataStorage.getInstance().db.accounts().getAllAccounts().size
            if(accountsAmount <=0) {

                scope.launch {// a tu juz jest glowny thread
                    navBackToLogin()          // polak to zawsze wymysli
                }

            }
        }.start()

    }


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
                startDestination = NavigationRoutes.Inner.OrdersPage, //TODO change later to homePage
                modifier = Modifier.padding(padding)) {
                composable(NavigationRoutes.Inner.HomePage) {
                    HomePage()
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
                composable(NavigationRoutes.Inner.AccountPage) {
                    AccountPage(navigateToLoginPage = navBackToLogin)
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
            description = "Accounts")
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

    // BUG lepiej zebym mial na to oko bo to sie chyba często psuje
    val currentBackStackEntry by navCon.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route.toString()
    val selected = currentRoute == destination

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(10.dp)
            .wrapContentWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                //Log.d("AHA", "$destination $currentRoute")
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




