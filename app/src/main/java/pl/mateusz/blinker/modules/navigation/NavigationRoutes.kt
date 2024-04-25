package pl.mateusz.blinker.modules.navigation


class NavigationRoutes {

    class Outer {
        companion object {
            public val LoginScreen = "o_login_screen"
            public val MainScreen = "o_main_screen"
        }

    }
    class Inner {
        companion object {
            public val HomePage = "i_home_page"
            public val OrdersPage = "i_orders_page"
            public val StatsPage = "i_stats_page"
            public val SettingsPage = "i_settings_page"

            public val AccountPage = "i_account_page" // to be used

        }

    }
}

