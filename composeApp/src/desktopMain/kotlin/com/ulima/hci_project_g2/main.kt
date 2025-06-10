package com.ulima.hci_project_g2

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ulima.hci_project_g2.app.App
import com.ulima.hci_project_g2.core.data.DATA_STORE_FILE_NAME
import com.ulima.hci_project_g2.core.data.createDataStore
import com.ulima.hci_project_g2.di.initKoin

fun main() {
    val prefs = createDataStore {
        DATA_STORE_FILE_NAME
    }
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "CMP-Bookpedia",
        ) {
            App(
                prefs = remember {
                    prefs
                }
            )
        }
    }
}