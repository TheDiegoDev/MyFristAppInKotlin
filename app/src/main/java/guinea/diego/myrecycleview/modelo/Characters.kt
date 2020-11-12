package guinea.diego.myrecycleview.modelo

import kotlinx.android.parcel.RawValue
import java.io.Serializable


data class CharacterRM(
    var id: Int?,
    var name: String?,
    var species: String?,
    var image: String?,
    var status: String?,
    var gender: String?,
    var type: String?,
    val origin: @RawValue Data?,
    val location: @RawValue Data?) : Serializable

data class Personajes(
    var id: Int?,
    var name: String?,
    var species: String?,
    var image: String?,
    var status: String?,
    var gender: String?,
    var type: String?,
    var origin: String?,
    var location: String?
)

data class InfoRM(
    val count: Int,
    val pages: Int,
    val next: String,
    val prev: String): Serializable

data class UrlOrigin(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String): Serializable


class Characters(val results: List<CharacterRM>) //,val info: @RawValue Data



