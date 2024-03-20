package com.example.testproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.presentation.base.BaseScreen
import com.example.presentation.base.BaseViewModel
import com.example.presentation.detail.DetailScreen
import com.example.presentation.detail.DetailViewModel
import com.example.testproject.ui.theme.TestProjectTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val baseViewModel: BaseViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            TestProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "BaseScreen"
                    ) {
                        composable(route = "BaseScreen") {
                            BaseScreen(vm = baseViewModel, navController)
                        }
                        composable(route = "DetailScreen/{id}",
                            arguments = listOf(
                                navArgument(name = "id") {
                                    type = NavType.IntType
                                }
                            )) { id ->
                            DetailScreen(
                                vm = detailViewModel,
                                productID = id.arguments!!.getInt("id")
                            )
                        }
                    }
                }
            }
        }
    }
}
