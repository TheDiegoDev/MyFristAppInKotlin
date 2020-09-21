package guinea.diego.myrecycleview.modelo

import java.io.Serializable

class CharacterRM(
    val id: Int,
    val name: String,
    val species: String,
    val image: String,
    val status: String,
    val gender: String,
    val type: String
    /*val origin: OriginRM,
    val location: LocationRM*/) : Serializable

class Characters(val results: List<CharacterRM>)

class OriginRM(val name: String)


class LocationRM(val name: String)


