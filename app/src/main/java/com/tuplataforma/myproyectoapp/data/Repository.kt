package com.tuplataforma.myproyectoapp.data

import kotlinx.coroutines.flow.Flow

class CarRepository(private val marcaDao: MarcaDao, private val modeloDao: ModeloDao) {
    val allMarcas: Flow<List<Marca>> = marcaDao.getAllMarcas()

    suspend fun insertMarca(marca: Marca) = marcaDao.insertMarca(marca)
    suspend fun updateMarca(marca: Marca) = marcaDao.updateMarca(marca)
    suspend fun deleteMarca(marca: Marca) = marcaDao.deleteMarca(marca)

    fun getModelosForMarca(marcaId: Int): Flow<List<Modelo>> = modeloDao.getModelosByMarca(marcaId)
    suspend fun insertModelo(modelo: Modelo) = modeloDao.insertModelo(modelo)
    suspend fun updateModelo(modelo: Modelo) = modeloDao.updateModelo(modelo)
    suspend fun deleteModelo(modelo: Modelo) = modeloDao.deleteModelo(modelo)
}
