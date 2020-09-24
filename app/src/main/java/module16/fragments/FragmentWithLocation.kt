package module16.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skillboxkotlin.R
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.fragment_withlocation.btn_fragmentWithLocation_getLocation
import kotlinx.android.synthetic.main.fragment_withlocation.rv_fragmentWithLocation
import kotlinx.android.synthetic.main.fragment_withlocation.tv_fragmentWithLocation_listEmpty
import module16.AdapterLocation
import module16.data.Data
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneId
import kotlin.random.Random

class FragmentWithLocation : Fragment(R.layout.fragment_withlocation) {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var dataList = mutableListOf<Data>()
    private var adapterLocation: AdapterLocation? = null
    private var selectedInstant: org.threeten.bp.Instant? = null
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireContext())
        setLocationRequest()
        setLocationCallback()
        initList()

        btn_fragmentWithLocation_getLocation.setOnClickListener {
            addItem()
            startGettingUpdates()
            Log.d("test_onActivityCreated", "dataList.size = ${dataList.size}")
        }
        updateAdapterLocation()
    }

    private fun setLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                if (p0 != null) {
                    updateUIValues(p0.lastLocation)
                } else {
                    makeToast("location information is not available")
                }
            }
        }
    }

    private fun setLocationRequest() {
        locationRequest = LocationRequest().apply {
            interval = 60000
        }
    }

    private fun updateUIValues(location: Location) {
        val newDataList: MutableList<Data> = mutableListOf(
            Data(
                id = Random.nextLong(),
                currentTime = selectedInstant ?: org.threeten.bp.Instant.now(),
                lat = location.latitude,
                lng = location.longitude,
                speed = location.speed,
                accuracy = location.accuracy,
                imageLink = "https://i.picsum.photos/id/1029/4887/2759.jpg?hmac=uMSExsgG8_PWwP9he9Y0LQ4bFDLlij7voa9lU9KMXDE"
            )
        )
        dataList = (newDataList + dataList).toMutableList()
        updateAdapterLocation()
        rv_fragmentWithLocation.scrollToPosition(0)
    }

    private fun initList() {
        adapterLocation = AdapterLocation { position -> initTimePicker(position) }
        with(rv_fragmentWithLocation) {
            adapter = adapterLocation
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun requestPermission() {
        requestPermissions(
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_LOCATION_UPDATES_CODE
        )
    }

    private fun startGettingUpdates() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        } else {
            requestPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                null
            )
        }
    }

    private fun addItem() {
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {
                updateUIValues(it)
            }
    }

    private fun makeToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun updateAdapterLocation() {
        adapterLocation?.updateAdapter(dataList)
        tv_fragmentWithLocation_listEmpty.isVisible = dataList.size == 0
    }

    private fun initTimePicker(position: Int) {

        val currentDateTime = LocalDateTime.now()
        val itemId = dataList[position].id

        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
                TimePickerDialog(
                    requireContext(),
                    { _, hourOfDay, minute ->
                        val zonedDateTime =
                            LocalDateTime.of(year, month + 1, dayOfMonth, hourOfDay, minute)
                                .atZone(ZoneId.systemDefault())
                        Toast.makeText(
                            requireContext(),
                            "Выбрано время: $zonedDateTime",
                            Toast.LENGTH_SHORT
                        ).show()
                        setUpItemCurrentTime(itemId, zonedDateTime.toInstant())
                        Log.d("DataPicker", "currentTime = ${dataList[position].currentTime}")
                    },
                    currentDateTime.hour,
                    currentDateTime.minute,
                    true
                ).show()
            },
            currentDateTime.year, currentDateTime.month.value - 1, currentDateTime.dayOfMonth
        )
            .show()
    }

    private fun setUpItemCurrentTime(id: Long, newTime: Instant) {
        for (item in dataList) {
            if (item.id == id) {
                item.currentTime = newTime
            }
        }
    }

    companion object {
        private const val REQUEST_LOCATION_UPDATES_CODE = 1
        private const val LAST_LOCATION = 2
    }
}