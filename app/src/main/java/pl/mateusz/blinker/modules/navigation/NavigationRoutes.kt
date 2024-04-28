package pl.mateusz.blinker.modules.navigation


class NavigationRoutes {

    class Outer {
        companion object {
            const val LoginScreen = "o_login_screen"
            const val MainScreen = "o_main_screen"
        }

    }
    class Inner {
        companion object {
            const val HomePage = "i_home_page"
            const val OrdersPage = "i_orders_page"
            const val StatsPage = "i_stats_page"
            const val SettingsPage = "i_settings_page"

            const val AccountPage = "i_account_page" // to be used

        }

    }
}

