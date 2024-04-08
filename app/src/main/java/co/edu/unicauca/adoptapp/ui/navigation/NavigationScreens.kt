package co.edu.unicauca.adoptapp.ui.navigation

sealed class NavigationScreens (val screen: String) {

    data object Initial : NavigationScreens("initial")
    data object Login : NavigationScreens("login")
    data object Register : NavigationScreens("register")
    data object Publications : NavigationScreens("publications")
    data object Home : NavigationScreens("home")
    data class MyAdoptions(val userId: Int)  : NavigationScreens("my-adoptions/$userId")
    data class MyPosts(val userId: Int) : NavigationScreens("my-posts/$userId")
    data class DetailPost(val postId: Int) : NavigationScreens("detail-post/$postId")
    data class Profile(val userId: Int) : NavigationScreens("profile/$userId")
    data class EditProfile(val userId: Int) : NavigationScreens("edit-profile/$userId")
    data class Favorites(val userId: Int) : NavigationScreens("favorites/$userId")
    data class Categories(val userId: Int) : NavigationScreens("categories/$userId")
    data class MoreServices(val userId: Int) : NavigationScreens("more-services/$userId")
    data class AboutUs(val userId: Int) : NavigationScreens("about-us/$userId")

    data object Settings : NavigationScreens("settings")

}