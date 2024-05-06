package co.edu.unicauca.adoptapp.data.user

import androidx.room.Database
import androidx.room.RoomDatabase
import co.edu.unicauca.adoptapp.data.adoption_request.AdoptionRequest
import co.edu.unicauca.adoptapp.data.adoption_request.AdoptionRequestDao
import co.edu.unicauca.adoptapp.data.post.Post
import co.edu.unicauca.adoptapp.data.post.PostDao

@Database(
    entities = [User::class, Post::class, AdoptionRequest::class],
    version = 3
)

abstract class UserDatabase: RoomDatabase() {
    abstract val daoUser: UserDao
    abstract val daoPost: PostDao
    abstract val daoAdoptionRequest: AdoptionRequestDao
}



