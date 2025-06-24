package com.ulima.hci_project_g2.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object BookGraph: Route

    @Serializable
    data object BookList: Route

    @Serializable
    data class BookDetail(val id: String): Route

    //RUTAS ULIMA FIT

    //Autenticacion

    @Serializable
    data object AuthGraph: Route

    @Serializable
    data object Intro: Route

    @Serializable
    data object Login: Route

    //Recojo de datos

    @Serializable
    data object UserDataGraph: Route

    @Serializable
    data object UserDataStart: Route

    @Serializable
    data object Edad: Route

    @Serializable
    data object Peso: Route

    @Serializable
    data object Altura: Route

    @Serializable
    data object CondicionFisica: Route

    @Serializable
    data object Genero: Route

    @Serializable
    data object Objetivo: Route

    @Serializable
    data object IntroduccionRutina: Route

    @Serializable
    data object Ejercicio: Route

    @Serializable
    data object EjercicioIntrucciones: Route

    //MainApp

    @Serializable
    data object MainAppGraph

    @Serializable
    data object Home

    @Serializable
    data object Profile : Route

    @Serializable
    data object Leaderboard : Route
}