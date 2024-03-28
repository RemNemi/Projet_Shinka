package com.example.Projet_Shinka.Entrainement

data class Entrainement(
    val nom: String,
    val description: String,
    val type: TypeEntrainement // Enum pour définir le type d'entrainement (Physique, Mental, Intelect)
)

enum class TypeEntrainement {
    PHYSIQUE, MENTAL , INTELECT
}

