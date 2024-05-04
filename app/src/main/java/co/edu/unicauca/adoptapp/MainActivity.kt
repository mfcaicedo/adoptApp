package co.edu.unicauca.adoptapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.room.Room
import co.edu.unicauca.adoptapp.data.user.UserDatabase

import co.edu.unicauca.adoptapp.ui.navigation.LearnNavDrawer
import co.edu.unicauca.adoptapp.ui.publications.PostEvent
import co.edu.unicauca.adoptapp.ui.publications.PublicationFormViewModel
import co.edu.unicauca.adoptapp.ui.register_user.UserEntryViewModel

import co.edu.unicauca.adoptapp.ui.theme.AdoptAppTheme


class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "adoptapp.db",
        )
            .fallbackToDestructiveMigration() //-> cada vez que se cambie la db
            .build()
    }
    private val viewModel by viewModels<UserEntryViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    return UserEntryViewModel(db.daoUser) as T
                }
            }
        }
    )
    private val viewModelPost by viewModels<PublicationFormViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>,
                    extras: CreationExtras
                ): T {
                    return PublicationFormViewModel(db.daoPost) as T
                }
            }
        }
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdoptAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val state by viewModel.state.collectAsState()
                    val statePost by viewModelPost.state.collectAsState()
                    LearnNavDrawer(
                        state = state,
                        onEvent = viewModel::onEvent,
                        statePost = statePost,
                        onEventPost = viewModelPost::onEvent
                    )
                }
            }
        }
    }
}







