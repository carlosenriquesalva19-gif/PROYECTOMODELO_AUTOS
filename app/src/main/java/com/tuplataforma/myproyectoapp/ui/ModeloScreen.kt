package com.tuplataforma.myproyectoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tuplataforma.myproyectoapp.data.Modelo
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModeloScreen(
    viewModel: CarViewModel,
    marcaId: Int,
    onBack: () -> Unit
) {
    val modelos by viewModel.getModelosForMarca(marcaId).observeAsState(emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var nombre by remember { mutableStateOf("") }
    var año by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { 
                    Text(
                        "Modelos", 
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Nuevo Modelo")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).fillMaxSize().background(MaterialTheme.colorScheme.surface)) {
            if (modelos.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay modelos para esta marca", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(modelos) { modelo ->
                        ModeloItem(
                            modelo = modelo,
                            onDelete = { viewModel.deleteModelo(modelo) }
                        )
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Registrar Modelo", fontWeight = FontWeight.Bold) },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = nombre, 
                            onValueChange = { nombre = it }, 
                            label = { Text("Nombre del Modelo") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = año, 
                            onValueChange = { año = it }, 
                            label = { Text("Año") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = precio, 
                            onValueChange = { precio = it }, 
                            label = { Text("Precio Sugerido") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            val a = año.toIntOrNull() ?: 0
                            val p = precio.toDoubleOrNull() ?: 0.0
                            if (nombre.isNotBlank() && a > 0) {
                                viewModel.insertModelo(Modelo(marcaId = marcaId, nombre = nombre, año = a, precio = p))
                                nombre = ""
                                año = ""
                                precio = ""
                                showDialog = false
                            }
                        },
                        shape = RoundedCornerShape(8.dp)
                    ) { Text("Guardar") }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
                }
            )
        }
    }
}

@Composable
fun ModeloItem(modelo: Modelo, onDelete: () -> Unit) {
    val currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US)
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = modelo.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    SuggestionChip(
                        onClick = {},
                        label = { Text("Año ${modelo.año}") },
                        shape = RoundedCornerShape(8.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = currencyFormatter.format(modelo.precio),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            IconButton(
                onClick = onDelete,
                colors = IconButtonDefaults.iconButtonColors(contentColor = MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    }
}
