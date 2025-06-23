package com.ulima.hci_project_g2.features.auth.data

import com.ulima.hci_project_g2.features.auth.domain.Usuario

class UsuarioRepository {

    //Lista de usuarios
    private val usuarios = listOf(
        Usuario(usuario = "20220522", contrasena = "ulima123", nombre = "Luis"),
        Usuario(usuario = "20212604", contrasena = "ulima123", nombre = "Hideki")
    )

    fun getName(usuario: String, contrasena: String): String {
        val user = usuarios.firstOrNull { it.usuario == usuario && it.contrasena == contrasena }
        return user?.nombre ?: "Invitado"
    }
    //Verificar si las credenciales son validas
    fun validarLogin(usuario: String, contrasena: String): Boolean {
        return usuarios.any { it.usuario == usuario && it.contrasena == contrasena }
    }
}