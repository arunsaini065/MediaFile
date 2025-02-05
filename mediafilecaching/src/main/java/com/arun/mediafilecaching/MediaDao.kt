package com.arun.mediafilecaching

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMediaFiles(mediaFiles: List<MediaFile>)

    @Query("SELECT * FROM media_files ORDER BY dateAdded DESC")
    fun getAllMediaFiles(): Flow<MutableList<MediaFile>>

    @Query("SELECT * FROM media_files WHERE type = 'Video' ORDER BY dateAdded DESC")
    fun getVideos():Flow<MutableList<MediaFile>>

    @Query("SELECT * FROM media_files WHERE type = 'Image' ORDER BY dateAdded DESC")
    fun getImages():Flow<MutableList<MediaFile>>

    @Query("SELECT * FROM media_files WHERE type = 'Audio' ORDER BY dateAdded DESC")
    fun getAudio():Flow<MutableList<MediaFile>>


    @Query("DELETE FROM media_files WHERE type = 'Video'")
    fun clearAllVideos()

    @Query("DELETE FROM media_files WHERE type = 'Audio'")
    fun clearAllAudios()

    @Query("DELETE FROM media_files WHERE type = 'Image'")
    fun clearAllImage()

    @Query("DELETE FROM media_files")
    fun clearAll()

}
