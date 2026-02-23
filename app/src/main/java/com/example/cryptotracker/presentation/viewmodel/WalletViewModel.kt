import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.cryptotracker.domain.usecase.WalletStateUi
import com.example.cryptotracker.domain.usecase.GetWalletCryptosUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WalletViewModel(
    // TODO: Remplacer par de l'injection de dépendenances
    val getWalletCryptosUseCase: GetWalletCryptosUseCase = GetWalletCryptosUseCase(WalletRepositoryImpl())
): ViewModel() {

    // UI State privé R+W
    private val _walletUiState = MutableStateFlow<WalletStateUi>(WalletStateUi.Loading)

    // UI State public Read only
    val walletUiState: StateFlow<WalletStateUi> = _walletUiState.asStateFlow()

    // Fonction init
    init {
        // Fonction qui se lance dès que le ViewModel est instancié
    }

    // Fonction de chargement du wallet
    private fun loadWallet() {
        // Mettre à jour le state de la vue
        _walletUiState.value = WalletStateUi.Loading

        // Récupère le resultat
        val result: WalletStateUi = getWalletCryptosUseCase()

        // Mettre à jour l'état à nouveau avec le nouveau state
        _walletUiState.value = result
    }




    // Fonctions de modif du wallet (Add, Remove, etc.)

}