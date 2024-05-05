package co.edu.unicauca.adoptapp.ui.publications

interface PostEvent {
    data class SetTitle(val title: String): PostEvent
    data class SetDate(val date: String): PostEvent
    data class SetPetName(val petName: String): PostEvent
    data class SetPetAge(val petAge: String): PostEvent
    data class SetPetBreed(val petBreed: String): PostEvent
    data class SetPetDescription(val petDescription: String): PostEvent
    data class SetPetColor(val petColor: String): PostEvent
    data class SetPetSex(val petSex: String): PostEvent
    data class SetImageId(val imageId: String): PostEvent
    data class SetPostUserId(val postUserId: Int): PostEvent
    object Register: PostEvent
    object isLoading: PostEvent
}