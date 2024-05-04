package co.edu.unicauca.adoptapp.ui.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import co.edu.unicauca.adoptapp.ui.navigation.NavigationScreens

@Composable
fun MyPostsScreen(userId: String?, navigationController: NavController) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Column (
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    navigationController.navigate(NavigationScreens.CreatePost.passId(userId ?: "0"))
                }
            ) {
                Text("Crear publicación")
            }
        }
        Text(text = "Publicaciones del usuario con id: $userId")
    }
}