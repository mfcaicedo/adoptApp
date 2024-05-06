package co.edu.unicauca.adoptapp.ui.adoptions

interface AdoptionEvent {
    data class SetDate(val date: String): AdoptionEvent
    data class SetDescription(val description: String): AdoptionEvent
    data class SetPostUserId(val adoptionUserId: Int): AdoptionEvent
    data class SetAdoptionPostId(val adoptionPostId: Int): AdoptionEvent

    object Register: AdoptionEvent
    object isLoading: AdoptionEvent
    object registerSuccess: AdoptionEvent
    object AllAdoption: AdoptionEvent

}