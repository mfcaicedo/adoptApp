package co.edu.unicauca.adoptapp.ui.adoptions

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AdoptPetScreen(navigationController: NavController, prueba: String?) {

    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Text(text = "Adoptar mascota $prueba")
    }
}