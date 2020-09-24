package module16.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.skillboxkotlin.R
import kotlinx.android.synthetic.main.fragment_withoutpermission.*
import module16.OpenFragment

class FragmentWithoutPermission: Fragment(R.layout.fragment_withoutpermission)  {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_fragmentWithoutPer_resolvePer.setOnClickListener {
            requestLocationPermission()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }){
            (activity as OpenFragment).openFragment()
        } else {
            Toast.makeText(requireContext(), "Невозможно получить локацию без разрешения", Toast.LENGTH_SHORT).show()
        }
    }

    private fun requestLocationPermission(){
        requestPermissions(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST)
    }

    companion object{
        private const val PERMISSION_REQUEST = 123
    }
}