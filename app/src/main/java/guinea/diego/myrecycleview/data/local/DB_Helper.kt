package guinea.diego.myrecycleview.data.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import guinea.diego.myrecycleview.data.modelo.*

class DB_Helper(context: Context):SQLiteOpenHelper(context,
    dbName,
    factory,
    versionDB
) {
    private var characters: ArrayList<CharacterRM> = arrayListOf()
    private var id: Int? = null

    companion object{
        internal val dbName = "RickMortyDB"
        internal val factory = null
        internal val versionDB = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(LLamadaDB)
        db?.execSQL(tabla2)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }


    fun importDataUrl(response: UrlOrigin){
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        val exist = response.id?.let { compararUrlData(it) }

        if (exist == true){
            values.put("id", response.id)
            values.put("name", response.name)
            values.put("type",response.type)
            values.put("dimension", response.dimension)
            db.insert("url", null, values)
            Log.d("baseDiego" ,"$values")
        }
        db.close()
    }
    private fun compararUrlData(id: Int):Boolean{
        val db = this.readableDatabase
        val query = "Select id from url WHERE id == $id"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            return false
        }
        return true
    }
    fun readUrlData(name: String): UrlOrigin {
        val dataUrl = UrlOrigin(null, null,null,null)
        val db = this.readableDatabase
        val query = "Select * from url WHERE name == \"$name\""
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {

                dataUrl.id =   result.getInt(result.getColumnIndex("id"))
                dataUrl.name = result.getString(result.getColumnIndex("name"))
                dataUrl.type = result.getString(result.getColumnIndex("type"))
                dataUrl.dimension  = result.getString(result.getColumnIndex("dimension"))
        }
        return dataUrl
    }
    fun importDataCharacters(response: Characters){
        var cont = 0
        characters.clear()
        characters.addAll(response.results)
        for (i in characters.indices){
            insertCharacterstDB(response.results[cont])
            cont = cont +1
        }

    }

    private fun compararCharactersData(id: Int):Boolean{
        val db = this.readableDatabase
        val query = "Select id from persons WHERE id == $id"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            return false
        }
    return true
    }

    private fun insertCharacterstDB (response: CharacterRM){
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()
        val exist = response.id?.let { compararCharactersData(it) }

        if (exist == true){
            values.put("id", response.id)
            values.put("name", response.name)
            values.put("species", response.species)
            values.put("image", response.image)
            values.put("status", response.status)
            values.put("gender", response.gender)
            values.put("type", response.type)
            values.put("location",response.location?.name)
            values.put("origen", response.origin?.name)

            db.insert("persons", null, values)
        }
        db.close()
    }


    fun readCharactersData(): ArrayList<CharacterRM> {

        val list: ArrayList<CharacterRM> = arrayListOf()
        list.clear()
        val db = this.readableDatabase
        val query = "Select * from persons"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val caracteres = CharacterRM(null, null,null,null, null,null,null,
                    Data(null,null),Data(null,null))
                caracteres.id =   result.getInt(result.getColumnIndex("id"))
                caracteres.name = result.getString(result.getColumnIndex("name"))
                caracteres.species = result.getString(result.getColumnIndex("species"))
                caracteres.gender  = result.getString(result.getColumnIndex("gender"))
                caracteres.image = result.getString(result.getColumnIndex("image"))
                caracteres.location?.name= result.getString(result.getColumnIndex("location"))
                caracteres.origin?.name = result.getString(result.getColumnIndex("origen"))
                caracteres.status = result.getString(result.getColumnIndex("status"))
                caracteres.type = result.getString(result.getColumnIndex("type"))
                list.add(caracteres)
            }
            while (result.moveToNext())
        }
        return list
    }
}