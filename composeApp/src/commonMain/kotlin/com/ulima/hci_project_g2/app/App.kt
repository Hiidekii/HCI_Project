package com.ulima.hci_project_g2.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
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
import com.ulima.hci_project_g2.book.presentation.SelectedBookViewModel
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailAction
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailScreenRoot
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailViewModel
import com.ulima.hci_project_g2.book.presentation.book_list.BookListScreenRoot
import com.ulima.hci_project_g2.book.presentation.book_list.BookListViewModel
import com.ulima.hci_project_g2.features.userData.presentation.EdadScreen
import com.ulima.hci_project_g2.features.userData.presentation.PesoScreen
import com.ulima.hci_project_g2.features.userData.presentation.UserDataStartScreen
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App(
    prefs: DataStore<Preferences>
) {
    /*val counter by prefs
        .data
        .map {
            val counterKey = intPreferencesKey("counter")
            it[counterKey] ?: 0
        }
        .collectAsState(0)
    val scope = rememberCoroutineScope()*/
    MaterialTheme {
        /*Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = counter.toString(),
                textAlign = TextAlign.Center,
                fontSize = 50.sp
            )
            Button(onClick = {
                scope.launch {
                    prefs.edit { dataStore ->
                        val counterKey = intPreferencesKey("counter")
                        dataStore[counterKey] = counter + 1
                    }
                }
            }){
                Text(text = "Increment")
            }
        }*/
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.UserDataGraph
        ) {

            navigation<Route.AuthGraph>(
                startDestination = Route.Intro
            ){
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
                            navController.navigate(Route.UserDataGraph){
                                popUpTo(Route.AuthGraph){
                                    inclusive = true
                                }
                            }
                        }
                    )
                }
            }

            navigation<Route.UserDataGraph>(
                startDestination = Route.UserDataStart
            ){
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

                        },
                        onReturnClick = {

                        }
                    )
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
                    enterTransition = { slideInHorizontally { initialOffset ->
                        initialOffset
                    } },
                    exitTransition = { slideOutHorizontally { initialOffset ->
                        initialOffset
                    } }
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

@Composable
private inline fun <reified T: ViewModel> NavBackStackEntry.sharedKoinViewModel(
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