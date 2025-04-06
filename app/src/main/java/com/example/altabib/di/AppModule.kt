import com.example.altabib.featuers.user.data.source.remote.AuthRepositoryImpl
import com.example.altabib.featuers.user.data.source.remote.FirebaseAuthService
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.GoogleSignInUseCase
import com.example.altabib.featuers.user.presentation.auth.AuthViewModel
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
    singleOf(::FirebaseAuthService)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::GoogleSignInUseCase)
    viewModel { AuthViewModel(get()) }
}
