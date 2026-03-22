import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cryptotracker.presentation.ui.navigation.Screen
import com.example.cryptotracker.presentation.ui.screen.detail.CryptoDetailScreen
import com.example.cryptotracker.presentation.ui.screen.home.HomeScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    homeVm: WalletViewModel = WalletViewModel()
) {

    NavHost(
        // NavController
        // Start destination
        navController = navController,
        startDestination = Screen.Home.route /* .../route */
    ) {
        // Cet écran est accessible, sur la route 'route'
        composable(route = Screen.Home.route) {
            HomeScreen(
                viewModel = homeVm,
                { cryptoName ->
                    navController.navigate("${Screen.Detail.route}/$cryptoName")
                }
            )
        }

        composable(
            route = "${Screen.Detail.route}/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )
        ) { backstackEntry ->
            val nameParam = backstackEntry.arguments?.getString("name") ?: return@composable
            CryptoDetailScreen(
                nameParam,
                {
                    navController.popBackStack()
                }
            )
        }
    }
}

