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
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.noahgeren.trailangel.R
import com.noahgeren.trailangel.database.HikeRepository
import com.noahgeren.trailangel.models.Hike
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

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
            getDateTimeInput(true)
        }
        editEndTime.setOnClickListener {
            getDateTimeInput(false)
        }

        submit.setOnClickListener {
            HikeRepository.get().saveHike(Hike(
                hike.id,
                trailName.text.toString(),
                latitude.text.toString().toDoubleOrNull(),
                longitude.text.toString().toDoubleOrNull(),
                length.text.toString().toDoubleOrNull(),
                hike.startTime,
                hike.endTime,
                hike.user
            ))
            findNavController().navigate(R.id.navigation_schedule)
        }

        trailName.setText(hike.trailName)
        hike.latitude?.let {
            latitude.setText("" + it)
        }
        hike.longitude?.let {
            longitude.setText("" + it)
        }
        hike.length?.let {
            length.setText("" + it)
        }
        updateUI()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateUI() {
        hike.startTime.let {
            startTime.setText(it.format(formatter))
        }
        hike.endTime.let {
            endTime.setText(it.format(formatter))
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDateTimeInput(isStartTime: Boolean) {

        val dateTime: LocalDateTime = if(isStartTime) hike.startTime else hike.endTime

        TimePickerDialog(
            requireContext(),
            { _, hourOfDay, minute ->
                if(isStartTime) {
                    hike.startTime = hike.startTime.withHour(hourOfDay)
                    hike.startTime = hike.startTime.withMinute(minute)
                    if(hike.startTime.isAfter(hike.endTime)) {
                        hike.endTime = hike.startTime.plusHours(1)
                    }
                } else {
                    hike.endTime = hike.endTime.withHour(hourOfDay)
                    hike.endTime = hike.endTime.withMinute(minute)
                }
                updateUI()
            },
            dateTime.hour,
            dateTime.minute,
            false
        ).show()

        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                if(isStartTime) {
                    hike.startTime = hike.startTime.withYear(year)
                    hike.startTime = hike.startTime.withMonth(month)
                    hike.startTime = hike.startTime.withDayOfMonth(dayOfMonth)
                    if(hike.startTime.isAfter(hike.endTime)) {
                        hike.endTime = hike.startTime.plusHours(1)
                    }
                } else {
                    hike.endTime = hike.endTime.withYear(year)
                    hike.endTime = hike.endTime.withMonth(month)
                    hike.endTime = hike.endTime.withDayOfMonth(dayOfMonth)
                }
                updateUI()
            },
            dateTime.year,
            dateTime.monthValue,
            dateTime.dayOfMonth
        ).show()

    }
}