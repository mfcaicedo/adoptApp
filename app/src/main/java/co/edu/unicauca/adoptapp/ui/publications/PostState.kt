package co.edu.unicauca.adoptapp.ui.publications

import co.edu.unicauca.adoptapp.data.post.Post
import co.edu.unicauca.adoptapp.data.user.User

data class PostState(
    val title: String = "",
    val date: String = "",
    val petName: String = "",
    val petAge: String = "",
    val petBreed: String = "",
    val petDescription: String = "",
    val petColor: String = "",
    val petSex: String = "",
    val imageId: String = "",
    val postUserId: Int = 0, //llave foranea
    val registerEnable: Boolean = false,
    val isLoading: Boolean = false,
    val registerSuccess: Boolean = false,
    val posts: List<Post> = emptyList(),
    val postsAndUser: Map<User, List<Post>> = emptyMap(),
    val post: Post? = null
    )