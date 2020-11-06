package guinea.diego.myrecycleview.modelo

import kotlinx.android.parcel.RawValue
import java.io.Serializable


data class CharacterRM(
    val id: Int,
    val name: String,
    val species: String,
    val image: String,
    val status: String,
    val gender: String,
    val type: String,
    val origin: @RawValue Data,
    val location: @RawValue Data) : Serializable

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



