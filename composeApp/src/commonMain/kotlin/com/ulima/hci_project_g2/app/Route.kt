package com.ulima.hci_project_g2.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object BookGraph: Route

    @Serializable
    data object BookList: Route

    @Serializable
    data class BookDetail(val id: String): Route

    @Serializable
    data object AuthGraph: Route

    @Serializable
    data object Intro: Route

    @Serializable
    data object Login: Route
}