package co.edu.unicauca.adoptapp.ui.posts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MyPostsScreen(userId: Int) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Text(text = "Publicaciones del usuario con id: $userId")
    }
}