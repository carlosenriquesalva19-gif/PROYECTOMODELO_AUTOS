package com.tuplataforma.myproyectoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MarcaDao {
    @Query("SELECT * FROM marcas")
    fun getAllMarcas(): Flow<List<Marca>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMarca(marca: Marca)

    @Update
    suspend fun updateMarca(marca: Marca)

    @Delete
    suspend fun deleteMarca(marca: Marca)
}

@Dao
interface ModeloDao {
    @Query("SELECT * FROM modelos WHERE marcaId = :marcaId")
    fun getModelosByMarca(marcaId: Int): Flow<List<Modelo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModelo(modelo: Modelo)

    @Update
    suspend fun updateModelo(modelo: Modelo)

    @Delete
    suspend fun deleteModelo(modelo: Modelo)
}
