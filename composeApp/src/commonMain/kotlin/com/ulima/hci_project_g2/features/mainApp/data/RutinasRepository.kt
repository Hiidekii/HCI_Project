package com.ulima.hci_project_g2.features.mainApp.data

import com.ulima.hci_project_g2.features.exercise.domain.*
import com.ulima.hci_project_g2.features.mainApp.domain.Rutina
import hci_project.composeapp.generated.resources.Res
import hci_project.composeapp.generated.resources.*

class RutinasRepository {

    fun obtenerRutinas(): List<Rutina> {
        return listOf(
            Rutina(
                nombre = "Fuerza superior 1",
                duracion = "55min",
                calorias = "412kcal",
                imagen = Res.drawable.img_introduccionRutina,
                ejercicios = listOf(
                    Exercise(
                        name = "Máquina de remo alto",
                        duration = 5,
                        calories = 40,
                        sets = "3x10",
                        muscleGroups = listOf(MuscleGroup.BACK),
                        rewardPoints = 25,
                        instructions = listOf("Siéntate frente a la máquina mirando hacia adelante, con el pecho recto y pegado al apoyo.", "Asegura una buena postura: espalda recta y pies bien apoyados.\n" +
                                "Sujeta las empuñaduras con ambas manos."),
                        image = Res.drawable.img_Maquina_Remo_Alto,
                        gif = Res.drawable.gif_remo_alto,
                    ),
                    Exercise(
                        name = "Remo con mancuernas",
                        duration = 5,
                        calories = 35,
                        sets = "3x12",
                        muscleGroups = listOf(MuscleGroup.BACK, MuscleGroup.ARMS),
                        rewardPoints = 20,
                        instructions = listOf("Inclina el torso", "Remar con ambos brazos"),
                        image = Res.drawable.img_Remo_Con_Mancuernas,
                        gif = Res.drawable.gif_remo_alto,
                    ),
                    Exercise(
                        name = "Press de banca",
                        duration = 6,
                        calories = 45,
                        sets = "3x8",
                        muscleGroups = listOf(MuscleGroup.CHEST, MuscleGroup.ARMS),
                        rewardPoints = 20,
                        instructions = listOf("Baja lentamente", "Empuja fuerte hacia arriba"),
                        image = Res.drawable.img_Press_De_Banca,
                        gif = Res.drawable.gif_remo_alto,
                    ),
                    Exercise(
                        name = "Push-ups",
                        duration = 4,
                        calories = 30,
                        sets = "3x15",
                        muscleGroups = listOf(MuscleGroup.CHEST, MuscleGroup.ARMS),
                        rewardPoints = 15,
                        instructions = listOf("Mantén la espalda recta", "Baja y sube controladamente"),
                        image = Res.drawable.img_Push_Ups,
                        gif = Res.drawable.gif_remo_alto,
                    ),
                    Exercise(
                        name = "Curl de bíceps con mancuernas",
                        duration = 4,
                        calories = 25,
                        sets = "3x10",
                        muscleGroups = listOf(MuscleGroup.ARMS),
                        rewardPoints = 15,
                        instructions = listOf("Codo pegado al cuerpo", "Sube y baja sin impulso"),
                        image = Res.drawable.img_Curl_Biceps_Mancuernas,
                        gif = Res.drawable.gif_remo_alto,
                    )
                )
            ),
            Rutina(
                nombre = "Fuerza inferior 1",
                duracion = "45min",
                calorias = "370kcal",
                imagen = Res.drawable.img_introduccionRutina,
                ejercicios = listOf(
                    Exercise(
                        name = "Sentadillas con peso",
                        duration = 6,
                        calories = 50,
                        sets = "3x10",
                        muscleGroups = listOf(MuscleGroup.LEGS),
                        rewardPoints = 30,
                        instructions = listOf("Espalda recta", "Baja lento, sube fuerte"),
                        image = Res.drawable.img_introduccionRutina,
                        gif = Res.drawable.gif_remo_alto,

                    )
                )
            ),
            Rutina(
                nombre = "Cardio explosivo",
                duracion = "25min",
                calorias = "450kcal",
                imagen = Res.drawable.img_introduccionRutina,
                ejercicios = listOf(
                    Exercise(
                        name = "Burpees",
                        duration = 4,
                        calories = 60,
                        sets = "4x12",
                        muscleGroups = listOf(MuscleGroup.FULL_BODY),
                        rewardPoints = 40,
                        instructions = listOf("Salta alto", "Controla la bajada"),
                        image = Res.drawable.img_introduccionRutina,
                        gif = Res.drawable.gif_remo_alto,
                    )
                )
            )
        )
    }

    fun obtenerEjerciciosPorRutina(nombreRutina: String): List<Exercise> {
        return obtenerRutinas().find { it.nombre == nombreRutina }?.ejercicios ?: emptyList()
    }
}
