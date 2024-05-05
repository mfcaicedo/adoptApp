package co.edu.unicauca.adoptapp.ui.publications

data class PostState (
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
    )