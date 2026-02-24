package com.tuplataforma.myproyectoapp.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "marcas")
data class Marca(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val pais: String
)

@Entity(
    tableName = "modelos",
    foreignKeys = [
        ForeignKey(
            entity = Marca::class,
            parentColumns = ["id"],
            childColumns = ["marcaId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Modelo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val marcaId: Int,
    val nombre: String,
    val año: Int,
    val precio: Double
)
