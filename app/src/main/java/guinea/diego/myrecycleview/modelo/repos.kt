package guinea.diego.myrecycleview.modelo


const val PrincipalRepo = "https://rickandmortyapi.com/api/"

const val LLamadaDB =
    "CREATE TABLE persons(id int(4)," + "name varchar(100),"+
            "species varchar(100)," + "image varchar(100)," + "status varchar(100),"+ "gender varchar(100),"+
            "type varchar(100),"+ "location varchar(100),"+ "origen varchar(100))"
const val compareId = "SELECT id FROM persons WHERE"

