package com.tuplataforma.myproyectoapp.ui

import android.app.Application
import androidx.lifecycle.*
import com.tuplataforma.myproyectoapp.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CarRepository
    val allMarcas: LiveData<List<Marca>>

    init {
        val database = AppDatabase.getDatabase(application)
        repository = CarRepository(database.marcaDao(), database.modeloDao())
        allMarcas = repository.allMarcas.asLiveData()
    }

    // CRUD Marca
    fun insertMarca(marca: Marca) = viewModelScope.launch { repository.insertMarca(marca) }
    fun updateMarca(marca: Marca) = viewModelScope.launch { repository.updateMarca(marca) }
    fun deleteMarca(marca: Marca) = viewModelScope.launch { repository.deleteMarca(marca) }

    // CRUD Modelo
    fun getModelosForMarca(marcaId: Int): LiveData<List<Modelo>> = 
        repository.getModelosForMarca(marcaId).asLiveData()
    
    fun insertModelo(modelo: Modelo) = viewModelScope.launch { repository.insertModelo(modelo) }
    fun updateModelo(modelo: Modelo) = viewModelScope.launch { repository.updateModelo(modelo) }
    fun deleteModelo(modelo: Modelo) = viewModelScope.launch { repository.deleteModelo(modelo) }
}
