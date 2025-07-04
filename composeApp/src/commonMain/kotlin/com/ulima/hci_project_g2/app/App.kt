package com.ulima.hci_project_g2.app

import CondicionFisicaScreen
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.ulima.hci_project_g2.features.auth.presentation.intro.IntroScreen
import com.ulima.hci_project_g2.features.auth.presentation.login.LoginScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExerciseCompletedScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExerciseDetailScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExerciseIntructionsScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExercisesViewModel
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.allExercise.AllExerciseDetailScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.allExercise.AllExerciseIntructionsScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.home.RoutineDetailScreen
import com.ulima.hci_project_g2.features.mainApp.presentation.profile.LeaderboardScreen
import com.ulima.hci_project_g2.features.userData.presentation.AlturaScreen
import com.ulima.hci_project_g2.features.userData.presentation.EdadScreen
import com.ulima.hci_project_g2.features.userData.presentation.GeneroScreen
import com.ulima.hci_project_g2.features.userData.presentation.IntroduccionScreen
import com.ulima.hci_project_g2.features.userData.presentation.ObjetivoFitnessScreen
import com.ulima.hci_project_g2.features.userData.presentation.PesoScreen
import com.ulima.hci_project_g2.features.userData.presentation.UserDataStartScreen
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    prefs: DataStore<Preferences>,
    appViewModel: AppViewModel = koinViewModel(),
    exercisesViewModel: ExercisesViewModel = koinViewModel()
) {
    MaterialTheme {
        val navController = rememberNavController()
        val stateApp = appViewModel.state
        val stateExercises = exercisesViewModel.state
        val startDestination = when {
            !stateApp.isLogged -> Route.AuthGraph
            !stateApp.hasCompleteIntro -> Route.UserDataGraph
            else -> Route.MainAppGraph
        }

        NavHost(
            navController = navController,
            startDestination = startDestination
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
                    MainWrapperScreen(
                        navController,
                        exercisesViewModel
                    )
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
                        onBackClick = { navController.popBackStack() },
                        exercisesViewModel = exercisesViewModel
                    )
                }

                composable("allExerciseDetail/{exerciseName}") { backStackEntry ->
                    val exerciseName = backStackEntry.arguments?.getString("exerciseName") ?: ""
                    AllExerciseDetailScreen(
                        exerciseName = exerciseName,
                        onNextClick = {
                            navController.navigate("allExerciseInstructions/$exerciseName")
                        },
                        onBackClick = { navController.popBackStack() },
                        exercisesViewModel = exercisesViewModel
                    )
                }

                composable("routineDetail/{routineName}") { backStackEntry ->
                    val routineName = backStackEntry.arguments?.getString("routineName") ?: return@composable
                    exercisesViewModel.getRoutineExercises(routineName)
                    val exercises = stateExercises.ejerciciosRutina

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
                            navController.navigate("exerciseCompleted/${false}/$routineName")
                        },
                        onBackClick = { navController.popBackStack() },
                        exercisesViewModel = exercisesViewModel
                    )
                }
                composable("allExerciseInstructions/{exerciseName}") { backStackEntry ->
                    val exerciseName = backStackEntry.arguments?.getString("exerciseName") ?: ""
                    AllExerciseIntructionsScreen(
                        exerciseName = exerciseName,
                        onNextClick = {
                            navController.navigate("exerciseCompleted/${true}")
                        },
                        onBackClick = { navController.popBackStack() },
                        exercisesViewModel = exercisesViewModel
                    )
                }
                composable("exerciseCompleted/{dontShowPoints}") { backStackEntry ->
                    val dontShowPoints = backStackEntry.arguments?.getString("dontShowPoints")?.toBoolean() ?: false
                    val state = exercisesViewModel.state
                    val exercise = state.selectedExercise

                    if (exercise != null) {
                        ExerciseCompletedScreen(
                            calories = exercise.calories,
                            duration = exercise.duration,
                            points = exercise.rewardPoints,
                            image = exercise.image,
                            onReturn       = {
                                navController.popBackStack(Route.Home, inclusive = false)
                            },
                            dontShowPoints = dontShowPoints
                        )
                    }
                }
                
                composable("exerciseCompleted/{dontShowPoints}/{routineName}") { backStackEntry ->
                    val dontShowPoints = backStackEntry.arguments?.getString("dontShowPoints")?.toBoolean() ?: false
                    val routineName = backStackEntry.arguments?.getString("routineName") ?: return@composable
                    val state = exercisesViewModel.state
                    val exercise = state.selectedExercise

                    if (exercise != null) {
                        ExerciseCompletedScreen(
                            calories = exercise.calories,
                            duration = exercise.duration,
                            points = exercise.rewardPoints,
                            image = exercise.image,
                            dontShowPoints = dontShowPoints,
                            onReturn = {
                                navController.navigate("routineDetail/$routineName") {
                                    popUpTo("routineDetail/routineName") { inclusive = true }
                                }
                            },
                        )
                    }
                }
                composable<Route.Leaderboard> {
                    LeaderboardScreen (
                        onBackClick = {
                            navController.popBackStack()
                        }
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