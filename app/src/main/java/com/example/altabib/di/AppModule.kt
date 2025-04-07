import com.example.altabib.featuers.user.data.source.remote.AuthRepositoryImpl
import com.example.altabib.featuers.user.data.source.remote.FirebaseAuthService
import com.example.altabib.featuers.user.domain.AuthRepository
import com.example.altabib.featuers.user.domain.GoogleSignInUseCase
import com.example.altabib.featuers.user.domain.RegisterUseCase
import com.example.altabib.featuers.user.presentation.auth.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
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
    single { FirebaseAuth.getInstance() }
    singleOf(::FirebaseAuthService)
    single { FirebaseFirestore.getInstance() }
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::GoogleSignInUseCase)
    singleOf(::RegisterUseCase)
    viewModel { AuthViewModel(get(), get()) }
}
