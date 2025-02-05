package com.arun.mediafilecaching

import android.view.ViewParent
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "media_files")
data class MediaFile(
    val id: Long,
    val name: String,
    @PrimaryKey
    val path: String,
    val parent: String,
    val type: String, // Image, Video, or Audio
    val size: Long,
    val dateAdded: Long,
    val duration: Long? = null // Only for audio/video
)