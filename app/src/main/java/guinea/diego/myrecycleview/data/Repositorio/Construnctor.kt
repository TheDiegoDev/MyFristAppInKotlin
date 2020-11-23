
import guinea.diego.myrecycleview.data.Repositorio.CharacterRepository

object Single{
    val characterRepository: CharacterRepository = CharacterRepository()
    fun Repository(): CharacterRepository {return characterRepository}
}
