package de.inovex.kmp_training.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import de.inovex.kmp_training.ui.screens.categories.CategoriesScreen
import de.inovex.kmp_training.ui.screens.taskdetail.TaskDetailScreen
import de.inovex.kmp_training.ui.screens.tasklist.TaskListScreen

@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Route.TaskList,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable<Route.TaskList> {
            TaskListScreen(
                onNavigateToTaskDetail = { taskId ->
                    navController.navigate(Route.TaskDetail(taskId))
                },
                onNavigateToCategories = {
                    navController.navigate(Route.Categories)
                }
            )
        }
        
        composable<Route.TaskDetail> { backStackEntry ->
            val route: Route.TaskDetail = backStackEntry.toRoute()
            TaskDetailScreen(
                taskId = route.taskId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
        
        composable<Route.Categories> {
            CategoriesScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}

