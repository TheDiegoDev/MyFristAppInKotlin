package guinea.diego.myrecycleview.modelo

<<<<<<< Updated upstream
class CharacterRM(val name: String, val species: String, val image: String)
class Characters(val results: List<CharacterRM>)
=======
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

class Characters(val results: List<CharacterRM>)




>>>>>>> Stashed changes
