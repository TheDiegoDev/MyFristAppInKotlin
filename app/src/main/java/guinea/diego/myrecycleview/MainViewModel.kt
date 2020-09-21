package guinea.diego.myrecycleview

class MainViewModel {

    lateinit var characterRepository:CharacterRepository

    fun getCharactersVM(){
        characterRepository.getCharacters()

    }

}