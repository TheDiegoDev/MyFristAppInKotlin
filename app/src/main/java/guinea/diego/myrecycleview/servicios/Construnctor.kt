import guinea.diego.myrecycleview.local.DB_Helper
import guinea.diego.myrecycleview.Repositorio.CharacterRepository

object Single{
    val characterRepository: CharacterRepository = CharacterRepository()
    fun Repository(): CharacterRepository {return characterRepository}
}
object bd{
    lateinit var db_helper: DB_Helper
}