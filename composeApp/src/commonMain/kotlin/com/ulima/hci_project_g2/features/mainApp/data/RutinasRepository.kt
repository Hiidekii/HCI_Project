package com.ulima.hci_project_g2.features.mainApp.data

import com.ulima.hci_project_g2.features.mainApp.domain.Exercise
import com.ulima.hci_project_g2.features.mainApp.domain.MuscleGroup
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
                imagen = Res.drawable.img_fuerza_superior1,
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
                        gif = Res.drawable.gif_remo_mancuernas,
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
                        gif = Res.drawable.gif_press_banca,
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
                        gif = Res.drawable.gif_push_ups,
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
                        gif = Res.drawable.gif_curl_biceps,
                    )
                )
            ),
            Rutina(
                nombre = "Fuerza inferior 1",
                duracion = "45min",
                calorias = "370kcal",
                imagen = Res.drawable.img_fuerza_inferior1,
                ejercicios = listOf(
                    Exercise(
                        name = "Sentadillas con peso",
                        duration = 6,
                        calories = 50,
                        sets = "3x10",
                        muscleGroups = listOf(MuscleGroup.LEGS, MuscleGroup.CORE),
                        rewardPoints = 30,
                        instructions = listOf("Espalda recta", "Baja lento, sube fuerte"),
                        image = Res.drawable.img_introduccionRutina,
                        gif = Res.drawable.gif_remo_alto,

                    ),
                    Exercise(
                        name = "Prensa de piernas",
                        duration = 5,
                        calories = 45,
                        sets = "3x12",
                        muscleGroups = listOf(MuscleGroup.LEGS),
                        rewardPoints = 25,
                        instructions = listOf(
                            "Apoya pies en plataforma.",
                            "Empuja sin bloquear rodillas."
                        ),
                        image = Res.drawable.img_prensa_piernas,
                        gif = Res.drawable.gif_prensa_piernas
                    ),
                    Exercise(
                        name = "Peso muerto rumano",
                        duration = 5,
                        calories = 45,
                        sets = "3x8",
                        muscleGroups = listOf(MuscleGroup.LEGS, MuscleGroup.BACK),
                        rewardPoints = 25,
                        instructions = listOf(
                            "Barra junto a tibias.",
                            "Extiende caderas al subir."
                        ),
                        image = Res.drawable.img_peso_muerto,
                        gif = Res.drawable.gif_peso_muerto
                    ),
                    Exercise(
                        name = "Zancadas con mancuernas",
                        duration = 5,
                        calories = 40,
                        sets = "3x10 cada pierna",
                        muscleGroups = listOf(MuscleGroup.LEGS, MuscleGroup.GLUTES),
                        rewardPoints = 25,
                        instructions = listOf(
                            "Da paso largo hacia adelante.",
                            "Mantén rodilla alineada."
                        ),
                        image = Res.drawable.img_zancadas,
                        gif = Res.drawable.gif_zancadas
                    ),
                    Exercise(
                        name = "Elevaciones de gemelos",
                        duration = 4,
                        calories = 30,
                        sets = "4x15",
                        muscleGroups = listOf(MuscleGroup.LEGS),
                        rewardPoints = 20,
                        instructions = listOf(
                            "Sube a la punta de los pies.",
                            "Baja lentamente."
                        ),
                        image = Res.drawable.img_gemelos,
                        gif = Res.drawable.gif_gemelos
                    )
                )
            ),
            Rutina(
                nombre = "Cardio 1",
                duracion = "25min",
                calorias = "450kcal",
                imagen = Res.drawable.img_cardio1,
                ejercicios = listOf(
                    Exercise(
                        name = "Burpees",
                        duration = 4,
                        calories = 60,
                        sets = "4x12",
                        muscleGroups = listOf(MuscleGroup.FULL_BODY),
                        rewardPoints = 40,
                        instructions = listOf("Salta alto", "Controla la bajada"),
                        image = Res.drawable.img_burpees,
                        gif = Res.drawable.gif_burpees,
                    ),
                    Exercise(
                        name = "Mountain Climbers",
                        duration = 3,
                        calories = 50,
                        sets = "4x30",
                        muscleGroups = listOf(MuscleGroup.CORE, MuscleGroup.LEGS),
                        rewardPoints = 30,
                        instructions = listOf(
                            "Cadera estable.",
                            "Alterna rodillas rápido."
                        ),
                        image = Res.drawable.img_mountain_climbers,
                        gif = Res.drawable.gif_mountain_climbers
                    ),
                    Exercise(
                        name = "Jumping Jacks",
                        duration = 3,
                        calories = 45,
                        sets = "3x30",
                        muscleGroups = listOf(MuscleGroup.FULL_BODY),
                        rewardPoints = 30,
                        instructions = listOf(
                            "Separa brazos y piernas.",
                            "Vuelve al centro."
                        ),
                        image = Res.drawable.img_jumping_jacks,
                        gif = Res.drawable.gif_jumping_jacks
                    ),
                    Exercise(
                        name = "High Knees",
                        duration = 3,
                        calories = 40,
                        sets = "3x45",
                        muscleGroups = listOf(MuscleGroup.LEGS, MuscleGroup.CORE),
                        rewardPoints = 25,
                        instructions = listOf(
                            "Eleva rodillas.",
                            "Impulsa en puntas de pie."
                        ),
                        image = Res.drawable.img_high_knees,
                        gif = Res.drawable.gif_high_knees
                    ),
                    Exercise(
                        name = "Mountain Climbers",
                        duration = 2,
                        calories = 30,
                        sets = "2x30",
                        muscleGroups = listOf(MuscleGroup.CORE, MuscleGroup.LEGS),
                        rewardPoints = 20,
                        instructions = listOf(
                            "Repite con intensidad.",
                            "Mantén postura."
                        ),
                        image = Res.drawable.img_mountain_climbers,
                        gif = Res.drawable.gif_mountain_climbers
                    )
                )
            )
        )
    }

    fun obtenerEjerciciosPorRutina(nombreRutina: String): List<Exercise> {
        return obtenerRutinas().find { it.nombre == nombreRutina }?.ejercicios ?: emptyList()
    }
}
