package com.ulima.hci_project_g2.app

import CondicionFisicaScreen
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.*
import com.ulima.hci_project_g2.book.presentation.SelectedBookViewModel
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailAction
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailScreenRoot
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailViewModel
import com.ulima.hci_project_g2.book.presentation.book_list.BookListScreenRoot
import com.ulima.hci_project_g2.book.presentation.book_list.BookListViewModel
import com.ulima.hci_project_g2.features.auth.presentation.intro.IntroScreen
import com.ulima.hci_project_g2.features.auth.presentation.login.LoginScreen
import com.ulima.hci_project_g2.features.profile.presentation.ProfileScreen
import com.ulima.hci_project_g2.features.exercise.presentation.ExerciseDetailScreen
import com.ulima.hci_project_g2.features.exercise.presentation.ExerciseIntructionsScreen
import com.ulima.hci_project_g2.features.exercise.presentation.ExerciseCompletedScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.MainWrapperScreen
import com.ulima.hci_project_g2.features.userData.presentation.*
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import com.ulima.hci_project_g2.features.mainApp.data.RutinasRepository
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.*

@Composable
@Preview
fun App(prefs: DataStore<Preferences>) {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Route.MainAppGraph
        ) {
            // --- AUTH FLOW ---
            navigation<Route.AuthGraph>(startDestination = Route.Intro) {
                composable<Route.Intro> {
                    IntroScreen(onStartClick = {
                        navController.navigate(Route.Login)
                    })
                }
                composable<Route.Login> {
                    LoginScreen(onLoginClick = {
                        navController.navigate(Route.UserDataGraph) {
                            popUpTo(Route.AuthGraph) { inclusive = true }
                        }
                    })
                }
            }

            // --- USER DATA FLOW ---
            navigation<Route.UserDataGraph>(startDestination = Route.UserDataStart) {
                composable<Route.UserDataStart> {
                    UserDataStartScreen(onNextClick = { navController.navigate(Route.Edad) })
                }
                composable<Route.Edad> {
                    EdadScreen(
                        onReturnClick = { navController.popBackStack() },
                        onNextClick = { navController.navigate(Route.Peso) }
                    )
                }
                composable<Route.Peso> {
                    PesoScreen(
                        onNextClick = { navController.navigate(Route.Altura) },
                        onReturnClick = { navController.popBackStack() }
                    )
                }
                composable<Route.Altura> {
                    AlturaScreen(
                        onNextClick = { navController.navigate(Route.CondicionFisica) },
                        onReturnClick = { navController.popBackStack() }
                    )
                }
                composable<Route.CondicionFisica> {
                    CondicionFisicaScreen(
                        onNextClick = { navController.navigate(Route.Genero) },
                        onReturnClick = { navController.popBackStack() }
                    )
                }
                composable<Route.Genero> {
                    GeneroScreen(
                        onNextClick = { navController.navigate(Route.Objetivo) },
                        onReturnClick = { navController.popBackStack() }
                    )
                }
                composable<Route.Objetivo> {
                    ObjetivoFitnessScreen(
                        onReturnClick = { navController.popBackStack() },
                        onNextClick = { navController.navigate(Route.IntroduccionRutina) }
                    )
                }
                composable<Route.IntroduccionRutina> {
                    IntroduccionScreen(onNextClick = {
                        navController.navigate(Route.MainAppGraph) {
                            popUpTo(Route.UserDataGraph) { inclusive = true }
                        }
                    })
                }
            }

            // --- MAIN APP FLOW ---
            navigation<Route.MainAppGraph>(startDestination = Route.Home) {
                composable<Route.Home> {
                    MainWrapperScreen(navController)
                }
                composable("exerciseDetail/{routineName}/{exerciseIndex}") { backStackEntry ->
                    val routineName = backStackEntry.arguments?.getString("routineName") ?: ""
                    val index = backStackEntry.arguments?.getString("exerciseIndex")?.toIntOrNull() ?: 0
                    ExerciseDetailScreen(
                        routineName = routineName,
                        exerciseIndex = index,
                        onNextClick = {
                            navController.navigate("exerciseInstructions/$routineName/$index")
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }
                composable("routineDetail/{routineName}") { backStackEntry ->
                    val routineName = backStackEntry.arguments?.getString("routineName") ?: return@composable
                    val exercises = RutinasRepository().obtenerEjerciciosPorRutina(routineName)

                    RoutineDetailScreen(
                        routineName = routineName,
                        exercises = exercises,
                        onBackClick = { navController.popBackStack() },
                        onNavigateToExerciseDetail = { exercise ->
                            val index = exercises.indexOf(exercise)
                            if (index != -1) {
                                navController.navigate("exerciseDetail/$routineName/$index")
                            }
                        }
                    )
                }
                composable("exerciseInstructions/{routineName}/{exerciseIndex}") { backStackEntry ->
                    val routineName = backStackEntry.arguments?.getString("routineName") ?: ""
                    val index = backStackEntry.arguments?.getString("exerciseIndex")?.toIntOrNull() ?: 0
                    ExerciseIntructionsScreen(
                        routineName = routineName,
                        exerciseIndex = index,
                        onNextClick = {
                            navController.navigate("exerciseCompleted/$routineName/$index")
                        },
                        onBackClick = { navController.popBackStack() }
                    )
                }
                composable("exerciseCompleted/{routineName}/{exerciseIndex}") { backStackEntry ->
                    val routineName = backStackEntry.arguments?.getString("routineName") ?: ""
                    val index = backStackEntry.arguments?.getString("exerciseIndex")?.toIntOrNull() ?: 0
                    val exercise = RutinasRepository().obtenerEjerciciosPorRutina(routineName).getOrNull(index)

                    if (exercise != null) {
                        ExerciseCompletedScreen(
                            calories = exercise.calories,
                            duration = exercise.duration,
                            points = exercise.rewardPoints,
                            image = exercise.image,
                            onReturnHome = {
                                navController.navigate(Route.Home) {
                                    popUpTo(Route.MainAppGraph) { inclusive = false }
                                }
                            }
                        )
                    }
                }
            }

            // --- BOOK FLOW ---
            navigation<Route.BookGraph>(startDestination = Route.BookList) {
                composable<Route.BookList>(
                    exitTransition = { slideOutHorizontally() },
                    popEnterTransition = { slideInHorizontally() }
                ) {
                    val viewModel = koinViewModel<BookListViewModel>()
                    val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                    LaunchedEffect(true) {
                        selectedBookViewModel.onSelectBook(null)
                    }

                    BookListScreenRoot(
                        viewModel = viewModel,
                        onBookClick = { book ->
                            selectedBookViewModel.onSelectBook(book)
                            navController.navigate(Route.BookDetail(book.id))
                        }
                    )
                }
                composable<Route.BookDetail>(
                    enterTransition = { slideInHorizontally { it } },
                    exitTransition = { slideOutHorizontally { it } }
                ) {
                    val selectedBookViewModel = it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                    val viewModel = koinViewModel<BookDetailViewModel>()
                    val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                    LaunchedEffect(selectedBook) {
                        selectedBook?.let { viewModel.onAction(BookDetailAction.OnSelectedBookChange(it)) }
                    }

                    BookDetailScreenRoot(
                        viewModel = viewModel,
                        onBackClick = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(viewModelStoreOwner = parentEntry)
}