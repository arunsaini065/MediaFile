package com.arun.mediafilecaching

import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore


fun fetchMediaFiles(mediaType: MediaType): MutableList<MediaFile> {

    val mediaList = mutableListOf<MediaFile>()

    when (mediaType) {
        MediaType.IMAGE -> {
            mediaList.addAll(
                getMediaFiles(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "Image"
                )
            )
        }
        MediaType.VIDEO -> {
            mediaList.addAll(
                getMediaFiles(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    "Video"
                )
            )
        }
        MediaType.AUDIO -> {
            mediaList.addAll(
                getMediaFiles(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    "Audio"
                )
            )
        }
        else -> {
            mediaList.addAll(
                getMediaFiles(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    "Image"
                )
            )
            mediaList.addAll(
                getMediaFiles(
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                    "Video"
                )
            )
            mediaList.addAll(
               getMediaFiles(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    "Audio"
                )
            )
        }
    }

     return mediaList

}



private fun getMediaFiles(uri: Uri, mediaType: String): List<MediaFile> {
    val mediaList = mutableListOf<MediaFile>()

    val projection = arrayOf(
        MediaStore.MediaColumns._ID,
        MediaStore.MediaColumns.DISPLAY_NAME,
        MediaStore.MediaColumns.DATA,
        MediaStore.MediaColumns.SIZE,
        MediaStore.MediaColumns.DATE_ADDED,
        if (mediaType != "Image") MediaStore.MediaColumns.DURATION else null
    ).filterNotNull().toTypedArray()

    val cursor: Cursor? = CourseContext.context?.contentResolver?.query(uri, projection, null, null, MediaStore.MediaColumns.DATE_ADDED + " DESC")

    cursor?.use {
        val idIndex = it.getColumnIndexOrThrow(MediaStore.MediaColumns._ID)
        val nameIndex = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DISPLAY_NAME)
        val pathIndex = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        val sizeIndex = it.getColumnIndexOrThrow(MediaStore.MediaColumns.SIZE)
        val dateAddedIndex = it.getColumnIndexOrThrow(MediaStore.MediaColumns.DATE_ADDED)
        val durationIndex = if (mediaType != "Image") it.getColumnIndexOrThrow(MediaStore.MediaColumns.DURATION) else -1

        while (it.moveToNext()) {
            val id = it.getLong(idIndex)
            val name = it.getString(nameIndex)
            val path = it.getString(pathIndex)
            val size = it.getLong(sizeIndex)
            val dateAdded = it.getLong(dateAddedIndex)
            val duration = if (durationIndex != -1) it.getLong(durationIndex) else null

            if (size>0) {

                mediaList.add(MediaFile(id, name, path,
                    getParentPathUsingString(path)?:"", mediaType, size, dateAdded, duration))

            }
        }
    }

    cursor?.close()
    return mediaList
}
fun getParentPathUsingString(path: String): String? {
    return if (path.contains("/")) path.substringBeforeLast("/") else null
}

