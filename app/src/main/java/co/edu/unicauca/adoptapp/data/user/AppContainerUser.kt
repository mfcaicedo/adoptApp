package co.edu.unicauca.adoptapp.data.user

import android.content.Context
import co.edu.unicauca.adoptapp.data.InventoryDatabase

interface AppContainer {
    val userRepository: UserRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainerUser(private val context: Context) : AppContainer {
    /**
     * Implementation for [ItemsRepository]
     */
    override val userRepository: UserRepository by lazy {
        OfflineUsersRepository(InventoryDatabase.getDatabase(context).userDao())
    }
}