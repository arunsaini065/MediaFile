package com.arun.mediafile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.arun.mediafile.databinding.ActivityMainBinding
import com.arun.mediafilecaching.CourseContext
import com.arun.mediafilecaching.MediaViewModel
import com.arun.mediafilecaching.UiState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {


    private val viewModel by lazy { ViewModelProvider(this)[MediaViewModel::class.java] }


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onResume() {
        viewModel.starVideoSync()
        viewModel.starAudioSync()
        viewModel.starImagesSync()
        viewModel.starAllMediaFileSync()
        super.onResume()


    }


    private fun convertMillisToTime(millis: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val date = Date(millis)
        return sdf.format(date)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        CourseContext.bindContext(application)


        binding.startTime.text = buildString {

            append("Start: ")

            append(convertMillisToTime(System.currentTimeMillis()))

        }

        lifecycleScope.launch {

            viewModel.videosFiles.collect{


                if (it is UiState.Success) {

                    binding.videoFile.text = buildString {
                        append("Video file: ")
                        append(it.data.size.toString())
                    }

                   timeUpDate()

                }

            }

        }

        lifecycleScope.launch {

            viewModel.videoFolder.collect{


                if (it is UiState.Success) {

                    binding.videoFolder.text = buildString {
                        append("Video folder: ")
                        append(it.data.size.toString())
                    }

                    timeUpDate()

                }

            }

        }


        lifecycleScope.launch {

            viewModel.audiosFiles.collect{


                if (it is UiState.Success) {

                    binding.audioFile.text = buildString {
                        append("Audio file: ")
                        append(it.data.size.toString())
                    }

                    timeUpDate()

                }

            }

        }

        lifecycleScope.launch {

            viewModel.audioFolder.collect{


                if (it is UiState.Success) {

                    binding.audioFolder.text = buildString {
                        append("Audio folder: ")
                        append(it.data.size.toString())
                    }

                    timeUpDate()

                }

            }

        }


        lifecycleScope.launch {

            viewModel.imageFiles.collect{


                if (it is UiState.Success) {

                    binding.imageFile.text = buildString {
                        append("Image file: ")
                        append(it.data.size.toString())
                    }

                    timeUpDate()

                }

            }

        }

        lifecycleScope.launch {

            viewModel.imageFolder.collect{


                if (it is UiState.Success) {

                    binding.imageFolder.text = buildString {
                        append("Image file: ")
                        append(it.data.size.toString())
                    }

                    timeUpDate()

                }

            }

        }



        lifecycleScope.launch {

            viewModel.allMediaFolder.collect{


                if (it is UiState.Success) {

                    binding.allFolder.text = buildString {
                        append("All media folder: ")
                        append(it.data.size.toString())
                    }

                    timeUpDate()

                }

            }

        }

        lifecycleScope.launch {

            viewModel.allMediaFiles.collect{


                if (it is UiState.Success) {

                    binding.allFile.text = buildString {
                        append("All media file: ")
                        append(it.data.size.toString())
                    }

                    timeUpDate()

                }

            }

        }


    }

    private fun timeUpDate() {

        binding.endTime.text = buildString {

            append("End: ")

            append(convertMillisToTime(System.currentTimeMillis()))

        }
    }

}