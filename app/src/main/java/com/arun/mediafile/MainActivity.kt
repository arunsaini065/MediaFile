package com.arun.mediafile

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.arun.mediafilecaching.CourseContext
import com.arun.mediafilecaching.MediaViewModel
import com.arun.mediafilecaching.UiState
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {


    private val viewModel by lazy { ViewModelProvider(this)[MediaViewModel::class.java] }

    private val startTime by lazy { findViewById<TextView>(R.id.star_time) }
    private val endTime by lazy { findViewById<TextView>(R.id.end_time) }
    private val count by lazy { findViewById<TextView>(R.id.count) }



    override fun onResume() {

        super.onResume()

        viewModel.starVideoSync()

    }


    private fun convertMillisToTime(millis: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault())
        val date = Date(millis)
        return sdf.format(date)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        CourseContext.bindContext(application)


        startTime.text = buildString {

            append("Start: ")

            append(convertMillisToTime(System.currentTimeMillis()))

        }

        lifecycleScope.launch {

            viewModel.videosFiles.collect{


                if (it is UiState.Success) {

                    count.text = it.data.size.toString()

                    endTime.text = buildString {

                        append("End: ")

                        append(convertMillisToTime(System.currentTimeMillis()))

                    }

                }

            }

        }

        lifecycleScope.launch {

            viewModel.videoFolder.collect{


                if (it is UiState.Success) {

                    count.text = count.text.toString()+"   "+it.data.size.toString()

                    endTime.text = buildString {

                        append("End: ")

                        append(convertMillisToTime(System.currentTimeMillis()))

                    }

                }

            }

        }


    }

}