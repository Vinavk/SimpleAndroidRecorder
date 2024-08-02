package com.example.jpanimated.Room

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [AudioNote::class], version = 2)
abstract class AudioDataBases : RoomDatabase() {

    companion object{
        var INSTANCE : AudioDataBases? = null

        val MIGRATION_1_2 = object: Migration(1,2){
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE AudioNotes ADD COLUMN name TEXT NOT NULL DEFAULT ''")
            }
        }

        fun getinstances(context : Context) : AudioDataBases{
            if(INSTANCE == null){
                synchronized(AudioDataBases){
                    INSTANCE = Room.databaseBuilder(context,AudioDataBases::class.java,"AudioDataBases")
                        .addMigrations(MIGRATION_1_2)
                        .build()
                }
            }
            return INSTANCE!!
        }

    }


    abstract  fun getdao() : AudioDao
}