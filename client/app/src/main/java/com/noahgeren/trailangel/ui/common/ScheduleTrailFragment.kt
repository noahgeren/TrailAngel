package com.noahgeren.trailangel.ui.common

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.models.Hike
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

class ScheduleTrailFragment: Fragment(R.layout.fragment_schedule_trail) {

    private val args: ScheduleTrailFragmentArgs by navArgs()

    private lateinit var trailName: EditText
    private lateinit var latitude: EditText
    private lateinit var longitude: EditText
    private lateinit var length: EditText
    private lateinit var startTime: EditText
    private lateinit var editStartTime: Button
    private lateinit var endTime: EditText
    private lateinit var editEndTime: Button
    private lateinit var submit: Button

    private lateinit var hike: Hike

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        trailName = view.findViewById(R.id.schedule_trail_trail_name)
        latitude = view.findViewById(R.id.schedule_trail_latitude)
        longitude = view.findViewById(R.id.schedule_trail_longitude)
        length = view.findViewById(R.id.schedule_trail_trail_length)
        startTime = view.findViewById(R.id.schedule_trail_start)
        editStartTime = view.findViewById(R.id.schedule_trail_edit_start)
        endTime = view.findViewById(R.id.schedule_trail_end)
        editEndTime = view.findViewById(R.id.schedule_trail_edit_end)
        submit = view.findViewById(R.id.schedule_trail_submit)

        hike = args.hike

        editStartTime.setOnClickListener {
            getDateTimeInput(hike.startTime)
        }
        editEndTime.setOnClickListener {
            getDateTimeInput(hike.endTime)
        }

        updateUI()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateUI() {
        hike.trailName?.let {
            trailName.setText(it)
        }
        hike.latitude?.let {
            latitude.setText("" + it)
        }
        hike.longitude?.let {
            longitude.setText("" + it)
        }
        hike.length?.let {
            length.setText("" + it)
        }
        hike.startTime?.let {
            startTime.setText(it.format(formatter))
        }
        hike.endTime?.let {
            endTime.setText(it.format(formatter))
        }
    }

    private fun getDateTimeInput(dateTime: LocalDateTime?) {
        val c: Calendar = Calendar.getInstance()

        val mHour = c.get(Calendar.HOUR_OF_DAY)
        val mMinute = c.get(Calendar.MINUTE)

        TimePickerDialog(
            requireContext(),
            { view, hourOfDay, minute -> /* TODO */},
            mHour,
            mMinute,
            false
        ).show()

        val mYear = c.get(Calendar.YEAR)
        val mMonth = c.get(Calendar.MONTH)
        val mDay = c.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(
            requireContext(),
            { view, year, monthOfYear, dayOfMonth -> /* TODO */ },
            mYear,
            mMonth,
            mDay
        ).show()

    }
}