package com.ulima.hci_project_g2.app

import CondicionFisicaScreen
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.ulima.hci_project_g2.book.presentation.SelectedBookViewModel
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailAction
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailScreenRoot
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailViewModel
import com.ulima.hci_project_g2.book.presentation.book_list.BookListScreenRoot
import com.ulima.hci_project_g2.book.presentation.book_list.BookListViewModel
import com.ulima.hci_project_g2.core.presentation.PrimaryOrange
import com.ulima.hci_project_g2.features.auth.presentation.intro.IntroScreen
import com.ulima.hci_project_g2.features.auth.presentation.login.LoginScreen
import com.ulima.hci_project_g2.features.exercise.domain.Exercise
import com.ulima.hci_project_g2.features.exercise.domain.MuscleGroup
import com.ulima.hci_project_g2.features.exercise.presentation.ExerciseDetailScreen
import com.ulima.hci_project_g2.features.exercise.presentation.ExerciseIntructionsScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.MainWrapperScreen
import com.ulima.hci_project_g2.features.userData.presentation.AlturaScreen
import com.ulima.hci_project_g2.features.userData.presentation.EdadScreen
import com.ulima.hci_project_g2.features.userData.presentation.GeneroScreen
import com.ulima.hci_project_g2.features.userData.presentation.IntroduccionScreen
import com.ulima.hci_project_g2.features.userData.presentation.ObjetivoFitnessScreen
import com.ulima.hci_project_g2.features.userData.presentation.PesoScreen
import com.ulima.hci_project_g2.features.userData.presentation.UserDataStartScreen
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.ejercicio
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun App(
    appViewModel: AppViewModel = koinViewModel(),
    prefs: DataStore<Preferences>
) {
    MaterialTheme {
        val navController = rememberNavController()
        val state = appViewModel.state
        val startDestination = when {
            !state.isLogged -> Route.AuthGraph
            !state.hasCompleteIntro -> Route.UserDataGraph
            else -> Route.MainAppGraph
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = PrimaryOrange
                )
            }
        } else {
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {

                navigation<Route.AuthGraph>(
                    startDestination = Route.Intro
                ) {
                    composable<Route.Intro> {
                        IntroScreen(
                            onStartClick = {
                                navController.navigate(Route.Login)
                            }
                        )
                    }
                    composable<Route.Login> {
                        LoginScreen(
                            onLoginClick = {
                                navController.navigate(Route.UserDataGraph) {
                                    popUpTo(Route.AuthGraph) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                }

                navigation<Route.UserDataGraph>(
                    startDestination = Route.UserDataStart
                ) {
                    composable<Route.UserDataStart> {
                        UserDataStartScreen(
                            onNextClick = {
                                navController.navigate(Route.Edad)
                            }
                        )
                    }

                    composable<Route.Edad> {
                        EdadScreen(
                            onReturnClick = {
                                navController.popBackStack()
                            },
                            onNextClick = {
                                navController.navigate(Route.Peso)
                            }
                        )
                    }

                    composable<Route.Peso> {
                        PesoScreen(
                            onNextClick = {
                                navController.navigate(Route.Altura)
                            },
                            onReturnClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Route.Altura> {
                        AlturaScreen(
                            onNextClick = {
                                navController.navigate(Route.CondicionFisica)
                            },
                            onReturnClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Route.CondicionFisica> {
                        CondicionFisicaScreen(
                            onNextClick = {
                                navController.navigate(Route.Genero)
                            },
                            onReturnClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Route.Genero> {
                        GeneroScreen(
                            onNextClick = {
                                navController.navigate(Route.Objetivo)
                            },
                            onReturnClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Route.Objetivo> {
                        ObjetivoFitnessScreen(
                            onReturnClick = {
                                navController.popBackStack()
                            },
                            onNextClick = {
                                navController.navigate(Route.IntroduccionRutina)
                            }
                        )
                    }

                    composable<Route.IntroduccionRutina> {
                        IntroduccionScreen(
                            onNextClick = {
                                navController.navigate(Route.MainAppGraph) {
                                    popUpTo(Route.UserDataGraph) {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    val ejercicio1 = Exercise(
                        name = "Máquina de remo alto",
                        duration = 9,            // 9 minutos
                        calories = 45,                // 45 kcal
                        sets = "3 × 15",
                        muscleGroups = listOf(
                            MuscleGroup.BACK,
                            MuscleGroup.ARMS,
                            MuscleGroup.CORE
                        ),
                        rewardPoints = 25,
                        instructions = listOf(
                            "Ajusta el asiento y la resistencia según tu nivel.",
                            "Siéntate con la espalda recta y pies firmes en los reposapiés.",
                            "Agarra el agarre con las dos manos, extiende los brazos y tira hacia el pecho exhalando.",
                            "Vuelve despacio a la posición inicial inhalando.",
                            "Repite el movimiento de forma controlada."
                        ),
                        image = Res.drawable.ejercicio,
                        gif = "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExYms2c2J4amUwY2R2Zzg2amhqNDdxYndyZnNhbW1lbDV4dG1wbjh0eSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/qt7bBGJ8x7ZRu/giphy.gif"
                    )

                    composable<Route.Ejercicio> {
                        ExerciseDetailScreen(
                            onNextClick = {
                                navController.navigate(Route.EjercicioIntrucciones)
                            },
                            exercise = ejercicio1,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }

                    composable<Route.EjercicioIntrucciones> {
                        ExerciseIntructionsScreen(
                            onNextClick = {
                                navController.navigate(Route.Ejercicio)
                            },
                            exercise = ejercicio1,
                            onBackClick = {
                                navController.popBackStack()
                            }
                        )
                    }
                }

                navigation<Route.MainAppGraph>(
                    startDestination = Route.Home
                ) {
                    composable<Route.Home> {
                        MainWrapperScreen()
                    }
                }

                navigation<Route.BookGraph>(
                    startDestination = Route.BookList
                ) {
                    composable<Route.BookList>(
                        exitTransition = { slideOutHorizontally() },
                        popEnterTransition = { slideInHorizontally() }
                    ) {
                        val viewModel = koinViewModel<BookListViewModel>()
                        val selectedBookViewModel =
                            it.sharedKoinViewModel<SelectedBookViewModel>(navController)

                        LaunchedEffect(true) {
                            selectedBookViewModel.onSelectBook(null)
                        }

                        BookListScreenRoot(
                            viewModel = viewModel,
                            onBookClick = { book ->
                                selectedBookViewModel.onSelectBook(book)
                                navController.navigate(
                                    Route.BookDetail(book.id)
                                )
                            }
                        )
                    }
                    composable<Route.BookDetail>(
                        enterTransition = {
                            slideInHorizontally { initialOffset ->
                                initialOffset
                            }
                        },
                        exitTransition = {
                            slideOutHorizontally { initialOffset ->
                                initialOffset
                            }
                        }
                    ) {
                        val selectedBookViewModel =
                            it.sharedKoinViewModel<SelectedBookViewModel>(navController)
                        val viewModel = koinViewModel<BookDetailViewModel>()
                        val selectedBook by selectedBookViewModel.selectedBook.collectAsStateWithLifecycle()

                        LaunchedEffect(selectedBook) {
                            selectedBook?.let {
                                viewModel.onAction(BookDetailAction.OnSelectedBookChange(it))
                            }
                        }

                        BookDetailScreenRoot(
                            viewModel = viewModel,
                            onBackClick = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private inline fun <reified T : ViewModel> NavBackStackEntry.sharedKoinViewModel(
    navController: NavController
): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel<T>()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return koinViewModel(
        viewModelStoreOwner = parentEntry
    )
}