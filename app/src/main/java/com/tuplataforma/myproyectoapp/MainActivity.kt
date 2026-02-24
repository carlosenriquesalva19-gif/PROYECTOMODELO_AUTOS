package com.tuplataforma.myproyectoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tuplataforma.myproyectoapp.ui.CarViewModel
import com.tuplataforma.myproyectoapp.ui.MarcaScreen
import com.tuplataforma.myproyectoapp.ui.ModeloScreen
import com.tuplataforma.myproyectoapp.ui.theme.MyproyectoappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyproyectoappTheme {
                val navController = rememberNavController()
                val viewModel: CarViewModel = viewModel()

                NavHost(navController = navController, startDestination = "marcas") {
                    composable("marcas") {
                        MarcaScreen(
                            viewModel = viewModel,
                            onMarcaClick = { marcaId ->
                                navController.navigate("modelos/$marcaId")
                            }
                        )
                    }
                    composable(
                        route = "modelos/{marcaId}",
                        arguments = listOf(navArgument("marcaId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        val marcaId = backStackEntry.arguments?.getInt("marcaId") ?: 0
                        ModeloScreen(
                            viewModel = viewModel,
                            marcaId = marcaId,
                            onBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}
