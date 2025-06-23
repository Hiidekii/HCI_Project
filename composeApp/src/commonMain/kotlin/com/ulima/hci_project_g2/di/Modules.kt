package com.ulima.hci_project_g2.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ulima.hci_project_g2.features.auth.data.UsuarioRepository
import com.ulima.hci_project_g2.features.auth.presentation.login.LoginViewModel
import com.ulima.hci_project_g2.book.data.database.DatabaseFactory
import com.ulima.hci_project_g2.book.data.database.FavoriteBookDatabase
import com.ulima.hci_project_g2.book.data.network.KtorRemoteBookDataSource
import com.ulima.hci_project_g2.book.data.network.RemoteBookDataSource
import com.ulima.hci_project_g2.book.data.repository.DefaultBookRepository
import com.ulima.hci_project_g2.book.domain.BookRepository
import com.ulima.hci_project_g2.book.presentation.SelectedBookViewModel
import com.ulima.hci_project_g2.book.presentation.book_detail.BookDetailViewModel
import com.ulima.hci_project_g2.book.presentation.book_list.BookListViewModel
import com.ulima.hci_project_g2.core.data.HttpClientFactory
import com.ulima.hci_project_g2.features.mainApp.data.ExerciseRepository
import com.ulima.hci_project_g2.features.mainApp.data.RutinasRepository
import com.ulima.hci_project_g2.features.mainApp.presentation.exercise.ExercisesViewModel
import com.ulima.hci_project_g2.features.mainApp.presentation.home.HomeViewModel
import com.ulima.hci_project_g2.features.userData.presentation.UserDataViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)

    //ViewModels de UlimaFit
    viewModelOf(::LoginViewModel)
    viewModelOf(::UserDataViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::ExercisesViewModel)

    //Repositories de UlimaFit
    singleOf(::UsuarioRepository)
    singleOf(::ExerciseRepository)
    singleOf(::RutinasRepository)
}