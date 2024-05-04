package co.edu.unicauca.adoptapp.ui.navigation

sealed class NavigationScreens (val screen: String) {

    private val userId: String = ""

    data object Initial : NavigationScreens("initial")
    data object Login : NavigationScreens("login")
    data object Register : NavigationScreens("register")
    data object Publications : NavigationScreens("publications")

    data object Home : NavigationScreens("home/{userId}") {
        var userId: String = ""

        fun passId(id: String): String {
            this.userId = id
            return this.screen.replace(oldValue = "{userId}", newValue = id)
        }

        fun retrieveUserId(): String {
            return this.userId
        }
    }

    data class MyAdoptions(val userId: Int)  : NavigationScreens("my-adoptions/$userId")
    data class AdoptPet(val userId: Int, val postId: Int) : NavigationScreens("adopt-pet/$userId/$postId")
    data object MyPosts: NavigationScreens("my-posts/{userId}") {
        fun passId(id: String): String {
            return this.screen.replace(oldValue = "{userId}", newValue = id)
        }
    }
    data object DetailPost: NavigationScreens("detail-post/{postId}") {
        fun passId(id: String): String {
            return this.screen.replace(oldValue = "{postId}", newValue = id)
        }
    }
    data object CreatePost: NavigationScreens("create-post/{userId}") {
        fun passId(id: String): String {
            return this.screen.replace(oldValue = "{userId}", newValue = id)
        }
    }

    data class Profile(val userId: Int) : NavigationScreens("profile/$userId")
    data class EditProfile(val userId: Int) : NavigationScreens("edit-profile/$userId")
    data class Favorites(val userId: Int) : NavigationScreens("favorites/$userId")
    data class Categories(val userId: Int) : NavigationScreens("categories/$userId")
    data class MoreServices(val userId: Int) : NavigationScreens("more-services/$userId")
    data class AboutUs(val userId: Int) : NavigationScreens("about-us/$userId")

    data object Settings : NavigationScreens("settings")

}