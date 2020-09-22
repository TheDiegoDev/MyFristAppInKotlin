package guinea.diego.myrecycleview.modelo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import retrofit2.Callback
import java.io.Serializable


data class CharacterRM(
    val id: Int,
    val name: String,
    val species: String,
    val image: String,
    val status: String,
    val gender: String,
    val type: String
    /*val origin: @RawValue Data,
    val location: @RawValue Data*/) : Serializable

class Characters(val results: List<CharacterRM>)




