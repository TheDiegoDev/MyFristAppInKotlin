package guinea.diego.myrecycleview.local

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import guinea.diego.myrecycleview.modelo.*

class DB_Helper(context: Context):SQLiteOpenHelper(context, dbName, factory, versionDB) {
    private var characters: ArrayList<CharacterRM> = arrayListOf()
    private var id: Int? = null

    companion object{
        internal val dbName = "RickMortyDB"
        internal val factory = null
        internal val versionDB = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(LLamadaDB)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun importData(response: Characters){
        var cont = 0
        characters.clear()
        characters.addAll(response.results)
        for (i in characters.indices){
            insertDB(response.results[cont])
            cont = cont +1
        }

    }

    private fun insertDB (response: CharacterRM){
        val db: SQLiteDatabase = writableDatabase
        val values = ContentValues()

        values.put("id", response.id)
        values.put("name", response.name)
        values.put("species", response.species)
        values.put("image", response.image)
        values.put("status", response.status)
        values.put("gender", response.gender)
        values.put("type", response.type)
        values.put("location",response.location!!.name)
        values.put("origen", response.origin!!.name)

        db.insert("persons", null, values)
        Log.i("baseDiego", "$values")
        db.close()
    }


    fun readData(): ArrayList<CharacterRM> {
        val list: ArrayList<CharacterRM> = arrayListOf()
        list.clear()
        val db = this.readableDatabase
        val query = "Select * from persons"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val caracteres = CharacterRM(null, null,null,null, null,null,null,null,null)
                caracteres.id =   result.getInt(result.getColumnIndex("id"))
                caracteres.name = result.getString(result.getColumnIndex("name"))
                caracteres.species = result.getString(result.getColumnIndex("species"))
                caracteres.gender  = result.getString(result.getColumnIndex("gender"))
                caracteres.image = result.getString(result.getColumnIndex("image"))
//                caracteres.location!!.name= result.getString(result.getColumnIndex("location"))
//                caracteres.origin!!.name = result.getString(result.getColumnIndex("origen"))
                caracteres.status = result.getString(result.getColumnIndex("status"))
                caracteres.type = result.getString(result.getColumnIndex("type"))
                list.add(caracteres)
            }
            while (result.moveToNext())
        }
        return list
    }
}