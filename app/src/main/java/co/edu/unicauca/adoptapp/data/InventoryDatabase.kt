package co.edu.unicauca.adoptapp.data

import co.edu.unicauca.adoptapp.data.user.User
import co.edu.unicauca.adoptapp.data.user.UserDao


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import co.edu.unicauca.adoptapp.data.adoption_request.AdoptionRequest
import co.edu.unicauca.adoptapp.data.animal.Animal
import co.edu.unicauca.adoptapp.data.post.Post

//@Database(entities = [User::class], version = 1, exportSchema = false)
@Database(entities = [User::class, Post::class, Animal::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: InventoryDatabase? = null

        fun getDatabase(context: Context): InventoryDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "all_database")
                    /**
                     * Setting this option in your app's database builder means that Room
                     * permanently deletes all data from the tables in your database when it
                     * attempts to perform a migration with no defined migration path.
                     */
                    //.fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}


