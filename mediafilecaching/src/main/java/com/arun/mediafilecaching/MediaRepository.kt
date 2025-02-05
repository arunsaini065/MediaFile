package com.arun.mediafilecaching

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext

class MediaRepository {

     private val _videosFiles =  MutableStateFlow<UiState<MutableList<MediaFile>>>(UiState.Loading)

     val videosFiles = _videosFiles.asStateFlow()


    private val _audioFiles =  MutableStateFlow<UiState<MutableList<MediaFile>>>(UiState.Loading)

    val audioFiles = _audioFiles.asStateFlow()

    private val _imageFiles =  MutableStateFlow<UiState<MutableList<MediaFile>>>(UiState.Loading)

    val imageFiles = _imageFiles.asStateFlow()


    private val _allMediaFiles =  MutableStateFlow<UiState<MutableList<MediaFile>>>(UiState.Loading)

    val allMediaFiles = _allMediaFiles.asStateFlow()

    private val _videoFolder =  MutableStateFlow<UiState<Map<String,List<MediaFile>>>>(UiState.Loading)

    val videoFolder = _videoFolder.asStateFlow()


    suspend fun getVideos() = withContext(Dispatchers.Default) {

        val context = CourseContext.context ?: return@withContext

        MediaDatabase.getDatabase(context).mediaDao().getVideos().collect { videos ->

            _videosFiles.value = UiState.Success(videos)

            _videoFolder.value = UiState.Success(videos.groupBy { it.parent })

        }

    }

    suspend fun updateVideos() = withContext(Dispatchers.Default){

        val context = CourseContext.context ?: return@withContext

        val listOfVideos = fetchMediaFiles(MediaType.VIDEO)

        _videoFolder.value = UiState.Success(listOfVideos.groupBy { it.path })

        MediaDatabase.getDatabase(context).mediaDao().clearAllVideos()

        MediaDatabase.getDatabase(context).mediaDao().insertMediaFiles(listOfVideos)



    }

    suspend fun getAudios() = withContext(Dispatchers.Default) {

        val context = CourseContext.context ?: return@withContext

        MediaDatabase.getDatabase(context).mediaDao().getAudio().collect { videos ->

            _audioFiles.value = UiState.Success(videos)

        }

    }

    suspend fun updateAudios() = withContext(Dispatchers.Default){

        val context = CourseContext.context ?: return@withContext

        val listOfAudios = fetchMediaFiles(MediaType.AUDIO)

        MediaDatabase.getDatabase(context).mediaDao().clearAllAudios()

        MediaDatabase.getDatabase(context).mediaDao().insertMediaFiles(listOfAudios)

    }


    suspend fun getImages() = withContext(Dispatchers.Default) {

        val context = CourseContext.context ?: return@withContext

        MediaDatabase.getDatabase(context).mediaDao().getImages().collect { videos ->

            _imageFiles.value = UiState.Success(videos)

        }

    }

    suspend fun updateImages() = withContext(Dispatchers.Default){

        val context = CourseContext.context ?: return@withContext

        val listOfImages = fetchMediaFiles(MediaType.IMAGE)

        MediaDatabase.getDatabase(context).mediaDao().clearAllImage()

        MediaDatabase.getDatabase(context).mediaDao().insertMediaFiles(listOfImages)

    }


    suspend fun getAllMediaFile() = withContext(Dispatchers.Default) {

        val context = CourseContext.context ?: return@withContext

        MediaDatabase.getDatabase(context).mediaDao().getAllMediaFiles().collect { videos ->

            _allMediaFiles.value = UiState.Success(videos)

        }

    }

    suspend fun updateAllMediaFile() = withContext(Dispatchers.Default){

        val context = CourseContext.context ?: return@withContext

        val listOf = fetchMediaFiles(MediaType.ALL)

        MediaDatabase.getDatabase(context).mediaDao().clearAll()

        MediaDatabase.getDatabase(context).mediaDao().insertMediaFiles(listOf)

    }

}