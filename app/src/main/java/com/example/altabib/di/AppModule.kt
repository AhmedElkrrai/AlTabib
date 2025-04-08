import com.example.altabib.di.provideGoogleSignInClient
import com.example.altabib.featuers.settings.presentation.SettingsViewModel
import com.example.altabib.featuers.user.data.source.remote.AuthRepositoryImpl
import com.example.altabib.featuers.user.data.source.remote.AuthenticationService
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.usecases.GoogleSignInUseCase
import com.example.altabib.featuers.user.domain.usecases.RegisterUseCase
import com.example.altabib.featuers.user.domain.usecases.LogoutUseCase
import com.example.altabib.featuers.user.presentation.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Koin module for dependency injection in the application.
 *
 * This module defines how various dependencies are created and provided throughout the application.
 * It uses the Koin dependency injection framework to manage the creation and lifecycle of objects.
 */
val appModule = module {
    single { provideGoogleSignInClient(androidContext()) }
    single { FirebaseAuth.getInstance() }
    singleOf(::AuthenticationService)
    single { FirebaseFirestore.getInstance() }
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::GoogleSignInUseCase)
    singleOf(::RegisterUseCase)
    singleOf(::LogoutUseCase)
    viewModel { AuthViewModel(get(), get()) }
    viewModel { SettingsViewModel(get()) }
}
