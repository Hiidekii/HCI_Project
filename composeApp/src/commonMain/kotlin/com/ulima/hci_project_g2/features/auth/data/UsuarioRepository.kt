package com.ulima.hci_project_g2.features.auth.data

import com.ulima.hci_project_g2.features.auth.domain.Usuario

class UsuarioRepository {

    //Lista de usuarios
    private val usuarios = listOf(
        Usuario(usuario = "20220522",
            contrasena = "ulima123",
            nombre = "Luis",
            apellidos = "Martinez Rueda",
            carrera = "Ingeniería de Sistemas",
            puntos = 908
        ),
        Usuario(usuario = "20212604",
            contrasena = "ulima123",
            nombre = "Hideki",
            apellidos = "Sotero Huaroto",
            carrera = "Ingeniería de Sistemas",
            puntos = 1050
        ),
        Usuario(
            usuario    = "20221234",
            contrasena = "ulima123",
            nombre     = "Tiara",
            apellidos  = "Herrera Díaz",
            carrera    = "Comunicación",
            puntos     = 1047
        ),
        Usuario(
            usuario    = "20222345",
            contrasena = "ulima123",
            nombre     = "Valeria",
            apellidos  = "Mendoza Ríos",
            carrera    = "Ingeniería Industrial",
            puntos     = 964
        )
    )

    fun login(usuario: String, contrasena: String): Usuario? =
        usuarios.firstOrNull { it.usuario == usuario && it.contrasena == contrasena }

    //Verificar si las credenciales son validas
    fun validarLogin(usuario: String, contrasena: String): Boolean {
        return usuarios.any { it.usuario == usuario && it.contrasena == contrasena }
    }

    fun obtenerRanking(): List<Usuario> {
        return this.usuarios
    }
}