package guinea.diego.myrecycleview.modelo

import java.io.Serializable

class CharacterRM(
    val name: String,
    val species: String,
    val image: String,
    val status: String,
    val gender: String,
    val type: String
    //val origin: List<String>,
    /*val location: List<String>*/) : Serializable

class Characters(val results: List<CharacterRM>)
