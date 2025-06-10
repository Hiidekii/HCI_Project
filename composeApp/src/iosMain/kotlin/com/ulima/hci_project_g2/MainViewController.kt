package com.ulima.hci_project_g2

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.ulima.hci_project_g2.app.App
import com.ulima.hci_project_g2.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App(
        prefs = remember {
            createDataStore()
        }
    )
}