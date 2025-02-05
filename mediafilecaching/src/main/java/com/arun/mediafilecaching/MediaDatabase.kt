package com.arun.mediafilecaching

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MediaFile::class], version = 1, exportSchema = false)
abstract class MediaDatabase : RoomDatabase() {
    abstract fun mediaDao(): MediaDao

    companion object {
        @Volatile
        private var INSTANCE: MediaDatabase? = null

        fun getDatabase(context: Context): MediaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MediaDatabase::class.java,
                    "media_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
