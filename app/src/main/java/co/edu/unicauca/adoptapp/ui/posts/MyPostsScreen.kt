package co.edu.unicauca.adoptapp.ui.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    navigationController.navigate(NavigationScreens.CreatePost.passId(userId ?: "0"))
                }
            ) {
                Text("Crear publicación")
            }
        }
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Mis publicaciones")
        }
    }
}