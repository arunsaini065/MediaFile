package com.arun.mediafilecaching

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MediaViewModel : ViewModel() {
    private val mediaRepository = MediaRepository()

    val videosFiles = mediaRepository.videosFiles

    val audiosFiles = mediaRepository.audioFiles

    val imageFiles = mediaRepository.imageFiles
    
    val allMediaFiles = mediaRepository.allMediaFiles

    val videoFolder = mediaRepository.videoFolder

    val audioFolder = mediaRepository.audioFolder

    val imageFolder = mediaRepository.imageFolder

    val allMediaFolder = mediaRepository.allMediaFolder

    fun starVideoSync() {

        viewModelScope.launch {

            mediaRepository.getVideos()
        }

        viewModelScope.launch {

            mediaRepository.updateVideos()

        }
    }

    fun starAudioSync() {

        viewModelScope.launch {

            mediaRepository.getAudios()

        }

        viewModelScope.launch {

            mediaRepository.updateAudios()

        }
    }

    fun starImagesSync() {

        viewModelScope.launch {

            mediaRepository.getImages()

        }

        viewModelScope.launch {

            mediaRepository.updateImages()

        }
    }


    fun starAllMediaFileSync() {

        viewModelScope.launch {

            mediaRepository.getAllMediaFile()

        }

        viewModelScope.launch {

            mediaRepository.updateAllMediaFile()

        }
    }


}