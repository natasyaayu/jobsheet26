package com.example.natasya26

import Siswa
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {


override fun onCreate(db: SQLiteDatabase) {
    val query = ("CREATE TABLE " + TABLE_NAME + " ("
            + ID_COL + " INTEGER PRIMARY KEY, " +
            NAME_COL + " TEXT, " +
            NIS_COL + " TEXT " + ")")

    db.execSQL(query)
}

override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
    onCreate(db)
}

fun addSiswa(name : String, nis : String ){
    val values = ContentValues()
    val db = this.writableDatabase

    values.put(NAME_COL, name)
    values.put(NIS_COL, nis)

    db.insert(TABLE_NAME, null, values)
    db.close()
}

fun getSiswa():ArrayList<Siswa> {
    val list = ArrayList<Siswa>()
    val db = this.readableDatabase
    val cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    if (cursor.moveToFirst()) {
        do {
            list.add(Siswa(cursor.getString(0), cursor.getString(1), cursor.getString(2)))
        } while (cursor.moveToNext());
    }
    cursor.close()
    return list;
}

fun deleteSiswa(id: String): Int {
    val db = this.readableDatabase
    val status = db.delete(TABLE_NAME, "id="+id, null)
    db.close()

    return status
}

fun updateSiswa(siswa: Siswa): Int {
    val db = this.writableDatabase
    val contentValues = ContentValues()
    contentValues.put(ID_COL, siswa.id)
    contentValues.put(NAME_COL, siswa.nama)
    contentValues.put(NIS_COL, siswa.nis)

    val succes = db.update(TABLE_NAME, contentValues, "id="+siswa.id, null)
    db.close()
    return succes
}

companion object {
    private val DATABASE_NAME = "siswa"
    private val DATABASE_VERSION = 2
    val TABLE_NAME = "data_siswa"
    val ID_COL = "id"
    val NAME_COL = "nama"
    val NIS_COL = "nis"

}

}