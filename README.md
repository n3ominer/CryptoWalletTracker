# CryptoTracker 📱

Application Android de suivi de cryptomonnaies en temps réel, développée dans le cadre du cours de **développement Android pour les étudiants de 4ème année à l'ESGI (promotion 2025-2026)**.

> ⚠️ Ce projet est un support pédagogique. Il est conçu pour être construit progressivement en cours, en introduisant les concepts un par un.

---

## 📋 Description

CryptoTracker permet de :
- Consulter la liste des cryptomonnaies les plus capitalisées (via l'API CoinGecko)
- Voir le prix en temps réel, la fluctuation sur 24h, et le logo de chaque crypto
- Naviguer vers l'écran de détail d'une cryptomonnaie
- Visualiser son portefeuille (wallet) avec les holdings
- Profiter d'une UI moderne avec thème clair/sombre et dégradé violet

---

## 🏗️ Architecture

Le projet suit une architecture **Clean Architecture** en 3 couches :

```
📦 cryptotracker
 ┣ 📂 data                    → Couche données
 ┃ ┣ 📂 remote                → API REST (Retrofit + CoinGecko)
 ┃ ┃ ┣ CoinGeckoApi.kt        → Interface Retrofit (endpoints)
 ┃ ┃ ┣ CoinGeckoApiClient.kt  → Configuration OkHttp + Retrofit (Singleton object)
 ┃ ┃ ┗ 📂 dto                 → Data Transfer Objects (CryptoDto, CryptoDetailDto)
 ┃ ┣ 📂 local                 → Base de données Room
 ┃ ┃ ┣ AppDataBase.kt         → Singleton Room Database
 ┃ ┃ ┣ CryptoDao.kt           → DAO (Data Access Object) avec @Query et @Insert
 ┃ ┃ ┗ CryptoEntity.kt        → Entité Room + fonctions de mapping (toDomainModel / toEntity)
 ┃ ┣ 📂 datasource
 ┃ ┃ ┣ 📂 remote              → RemoteCryptoDataSource (retry + exponential backoff)
 ┃ ┃ ┣ 📂 local               → LocalDataSource (données mockées)
 ┃ ┃ ┗ 📂 mocks               → CryptoDataMocks (données de test)
 ┃ ┗ 📂 repository
 ┃   ┗ WalletRepositoryImpl.kt → Implémentation du repository (remote + local)
 ┣ 📂 domain                  → Couche métier (indépendante du framework)
 ┃ ┣ 📂 model                 → Modèles domaine (Crypto, Wallet, Holding, CryptoDetail)
 ┃ ┣ 📂 repository
 ┃ ┃ ┗ WalletRepository.kt    → Interface du repository (contrat)
 ┃ ┗ 📂 usecase               → Cas d'usage (logique métier)
 ┃   ┣ GetWalletCryptosUseCase.kt
 ┃   ┣ GetCryptoDetailUseCase.kt
 ┃   ┗ 📂 state               → États UI (WalletStateUi, CryptoDetailStateUi)
 ┗ 📂 presentation            → Couche présentation (UI)
   ┣ 📂 viewmodel
   ┃ ┗ WalletViewModel.kt     → ViewModel avec StateFlow
   ┗ 📂 ui
     ┣ 📂 screen
     ┃ ┣ HomeScreen.kt        → Écran principal (liste du wallet)
     ┃ ┣ 📂 detail            → DetailScreen.kt (détail d'une crypto)
     ┃ ┣ 📂 home/composables  → Composants de l'écran home (Header, ButtonRow, etc.)
     ┃ ┗ WalletScreen.kt      → Écran wallet dédié
     ┣ 📂 components          → Composants réutilisables (CryptoItem, CryptoList, EmptyState…)
     ┣ 📂 navigation
     ┃ ┣ Navigation.kt        → NavHost + routes de navigation
     ┃ ┣ Screens.kt           → Sealed class des routes
     ┃ ┗ 📂 composables       → BottomNavigationBar
     ┗ 📂 theme               → Thème Material3 (Color.kt, Theme.kt, Type.kt)
```

---

## 🧑‍🏫 Concepts pédagogiques abordés

### 1. 🎨 Jetpack Compose — UI déclarative
- Composables (`@Composable`), recomposition, `@Preview`
- Layouts : `Column`, `Row`, `Box`, `LazyColumn`
- Modificateurs : `Modifier.fillMaxSize()`, `.padding()`, `.clip()`, `.offset()`, `.clickable()`
- Formes : `CircleShape`, `RoundedCornerShape`
- Dégradés : `Brush.verticalGradient`
- Gestion du thème clair/sombre avec `MaterialTheme` et `isSystemInDarkTheme()`
- Ressources de couleur via `colorResource(R.color.xxx)`
- Chargement d'images asynchrones avec **Coil** (`AsyncImage`)

### 2. 🧱 Architecture MVVM
- **ViewModel** (`WalletViewModel`) avec `viewModelScope` pour les coroutines
- **StateFlow** exposé en lecture seule (`asStateFlow()`) et collecté dans les composables (`collectAsState()`)
- Séparation claire des responsabilités : UI ne connaît pas la couche data

### 3. 🔁 Gestion des états UI
- **Sealed class** pour modéliser les états : `Loading`, `Success`, `Error`
  ```kotlin
  sealed class WalletStateUi {
      data object Loading : WalletStateUi()
      data class Success(val crypto: Wallet) : WalletStateUi()
      data class Error(val message: String) : WalletStateUi()
  }
  ```
- Gestion dans le composable avec `when(uiState) { ... }`

### 4. 🌐 Appels réseau avec Retrofit
- Interface `CoinGeckoTokensService` avec annotations `@GET`, `@Query`
- Configuration du client HTTP avec **OkHttp** (timeouts)
- Désérialisation JSON avec **Gson** (`@SerializedName`, `GsonConverterFactory`)
- Patron `object` Kotlin pour le singleton `CoinGeckoApiClient`
- Gestion des erreurs HTTP (code 429) avec **retry** et **exponential backoff**

### 5. 🗃️ Persistance locale avec Room
- Annotation `@Entity`, `@PrimaryKey`, `@ColumnInfo`
- Interface `@Dao` avec `@Query` et `@Insert`
- `@Database` avec singleton via companion object
- Mapping entre `CryptoEntity` ↔ `Crypto` (domaine) avec des fonctions d'extension

### 6. 🧩 Clean Architecture & Use Cases
- Le domaine est indépendant du framework Android
- Les Use Cases (`GetWalletCryptosUseCase`, `GetCryptoDetailUseCase`) encapsulent la logique métier
- L'interface `WalletRepository` découple le domaine de l'implémentation data
- `WalletRepositoryImpl` orchestre remote + local

### 7. 🗺️ Navigation Compose
- `NavHost` + `composable(route = ...)` pour définir les destinations
- `Sealed class Screen` pour typer les routes
- Passage de paramètres de navigation (`navArgument`, `NavType.StringType`, `backstackEntry.arguments`)
- `navController.navigate(...)` et `navController.popBackStack()`
- `BottomNavigationBar` personnalisée avec bouton central décalé (`offset`)

### 8. ⚡ Coroutines Kotlin
- `suspend fun` pour les appels réseau et BDD
- `viewModelScope.launch { }` pour lancer des coroutines depuis le ViewModel
- `operator fun invoke()` pour rendre les Use Cases appelables comme des fonctions

### 9. 💉 Injection de dépendances manuelle
- Instanciation et câblage manuel dans `MainActivity.injectDependencies()`
- Passage des dépendances par constructeur (Constructor Injection)
- Les commentaires TODO indiquent les zones à refactoriser avec Hilt

### 10. 🖼️ Composants réutilisables
- Composants atomiques : `ButtonNavItem`, `HomeQuickActionButton`, `CryptoItem`, `WalletHomeHeader`
- Composition de composants pour construire des écrans complets
- Séparation en fichiers dédiés (`composables/`)

---

## 🛠️ Stack technique

| Librairie | Rôle |
|---|---|
| **Jetpack Compose** | UI déclarative |
| **Material3** | Design system (thème, composants) |
| **Navigation Compose** | Navigation entre écrans |
| **Retrofit 3** | Client HTTP / API REST |
| **OkHttp** | Intercepteur HTTP, timeouts |
| **Gson** | Sérialisation/Désérialisation JSON |
| **Room** | Base de données locale SQLite |
| **Coil** | Chargement d'images asynchrone |
| **Kotlin Coroutines** | Programmation asynchrone |
| **Kotlin StateFlow** | Gestion d'état réactif |

---

## 🌐 API utilisée

**CoinGecko API v3** (publique, sans clé) :
- `GET /coins/markets` → Liste des cryptos par capitalisation
- `GET /simple/price` → Détail d'une crypto (prix, volume 24h, variation 24h)

Base URL : `https://api.coingecko.com/api/v3/`

---

## 📂 Structure des packages

```
com.example.cryptotracker
├── Counter.kt                  # Composable d'exercice (compteur)
├── MainActivity.kt             # Point d'entrée, injection manuelle
├── custom_keyboard/            # Clavier personnalisé (exercice)
├── data/
│   ├── datasource/
│   ├── local/                  # Room DB
│   ├── remote/                 # Retrofit
│   └── repository/
├── domain/
│   ├── model/
│   ├── repository/
│   └── usecase/
├── presentation/
│   ├── ui/
│   └── viewmodel/
└── util/
```

---

## 🚀 Lancer le projet

1. Cloner le dépôt
2. Ouvrir dans **Android Studio Hedgehog** ou supérieur
3. Laisser Gradle synchroniser les dépendances
4. Lancer sur un émulateur ou un appareil physique (API 21+)

> 💡 L'API CoinGecko a une limite de taux (rate limit). En cas d'erreur 429, l'app retante automatiquement jusqu'à 3 fois avec un délai exponentiel.

---

*Projet réalisé pour le cours de développement Android — 4ème année ESGI, promotion 2025-2026.*
