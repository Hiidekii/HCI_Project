package com.ulima.hci_project_g2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ulima.hci_project_g2.app.App
import com.ulima.hci_project_g2.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Bookpedia",
        ) {
            App()
        }
    }
}