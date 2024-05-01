package co.edu.unicauca.adoptapp.data.post

import kotlinx.coroutines.flow.Flow

interface PostRepository {

        fun getAllPostsStream(): Flow<List<Post>>

        fun getPostStream(id: Int): Flow<Post>

        suspend fun insertPost(post: Post)

        suspend fun updatePost(post: Post)

        suspend fun deletePost(post: Post)

}

