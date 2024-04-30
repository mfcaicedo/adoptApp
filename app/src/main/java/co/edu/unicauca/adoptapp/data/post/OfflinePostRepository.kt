package co.edu.unicauca.adoptapp.data.post
import kotlinx.coroutines.flow.Flow

class OfflinePostRepository(private val postDao: PostDao): PostRepository {

    override fun getAllPostsStream(): Flow<List<Post>> = postDao.getAllPosts()

    override fun getPostStream(id: Int): Flow<Post> = postDao.getPost(id)

    override suspend fun insertPost(post: Post) = postDao.insert(post)

    override suspend fun updatePost(post: Post) = postDao.update(post)

    override suspend fun deletePost(post: Post) = postDao.delete(post)
}
