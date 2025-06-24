package com.ulima.hci_project_g2.features.auth.domain

data class Usuario(
    val usuario: String,
    val contrasena: String,
    val nombre: String,
    val apellidos: String,
    val carrera: String,
    val puntos: Int
)