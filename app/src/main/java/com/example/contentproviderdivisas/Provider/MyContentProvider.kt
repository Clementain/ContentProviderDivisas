package com.example.contentproviderdivisas.Provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.example.contentproviderdivisas.BD.DivisaDatabase

private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {

    addURI("com.example.contentproviderdivisas", "monedas", 1)

    addURI("com.example.contentproviderdivisas", "monedas/#", 2)

    addURI("com.example.contentproviderdivisas", "monedas/*", 3)
}

class MyContentProvider : ContentProvider() {

    lateinit var db: DivisaDatabase
    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        p0: Uri, p1: Array<out String>?, p2: String?, p3: Array<out String>?, p4: String?
    ): Cursor? {
        //TODO("Not yet implemented")
        var cursor: Cursor? = null

        when (sUriMatcher.match(p0)) {

            1 -> {
                cursor = db.divisaDao().getAllCursor()

            }
            2 -> {

            }
            3 -> {

            }
            else -> {

            }
        }
        return cursor
    }

    override fun getType(uri: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }
}