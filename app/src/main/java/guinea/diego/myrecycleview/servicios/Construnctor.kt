import guinea.diego.myrecycleview.modelo.CharacterRM
import guinea.diego.myrecycleview.remote.CharacterRepository

object Single{
    val characterRepository: CharacterRepository = CharacterRepository()
    fun Repository(): CharacterRepository {return characterRepository}
}