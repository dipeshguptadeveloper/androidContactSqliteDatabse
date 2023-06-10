package com.dkgtech.contactsqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        val DATABASE_NAME = "contact.db"
        val DATABASE_VERSION = 1

        //        contact table
        val TABLE_NAME = "contact"
        val COLUMN_CONTACT_ID = "contact_id"
        val COLUMN_CONTACT_NAME = "contact_name"
        val COLUMN_CONTACT_NUMBER = "contact_no"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            db.execSQL(
                "create table $TABLE_NAME ($COLUMN_CONTACT_ID integer primary key autoincrement," +
                        "$COLUMN_CONTACT_NAME text," +
                        "$COLUMN_CONTACT_NUMBER text)"
            )
        }
    }

    fun addContact(contactName: String, contactNumber: String) {
        val myDb = writableDatabase
        val cv = ContentValues()

        cv.put(COLUMN_CONTACT_NAME, contactName)
        cv.put(COLUMN_CONTACT_NUMBER, contactNumber)

        myDb.insert(TABLE_NAME, null, cv)

    }

    fun getAllContacts(): ArrayList<ContactModel> {
        val myDb = writableDatabase

        val cursor = myDb.rawQuery("select * from $TABLE_NAME", null)

        val arrContacts = ArrayList<ContactModel>()

        while (cursor.moveToNext()) {
            val contactId = cursor.getInt(0)
            val contactName = cursor.getString(1)
            val contactNumber = cursor.getString(2)

            val contactModel = ContactModel(contactId, contactName, contactNumber)
            arrContacts.add(contactModel)
        }

        return arrContacts
    }

    fun deleteContact(contactId: Int) {
        val myDb = writableDatabase
        myDb.delete(TABLE_NAME, "$COLUMN_CONTACT_ID = ?", arrayOf(contactId.toString()))
    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}