package module16

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.skillboxkotlin.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil.getErrorDialog
import com.google.android.gms.common.GooglePlayServicesUtil.isGooglePlayServicesAvailable
import module16.fragments.FragmentWithoutPermission
import module16.fragments.FragmentWithLocation


class MainActivity: AppCompatActivity(R.layout.activity_main), OpenFragment {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val isPermissionLocationGranted = ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        if (isPermissionLocationGranted){
//            addFragmentWithLocation()
            addFragmentTest()
        } else {
            addFragmentWithoutPermission()
        }
    }

    override fun onResume() {
        super.onResume()
        val status =
            isGooglePlayServicesAvailable(applicationContext)
        if (status != ConnectionResult.SUCCESS) {
            when(status){
                ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED -> getErrorDialog(ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED, this, 0)
                ConnectionResult.SERVICE_MISSING -> getErrorDialog(ConnectionResult.SERVICE_MISSING, this, 0)
            }
        }
    }

    private fun addFragmentWithoutPermission(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_mainAct,
                FragmentWithoutPermission()
            )
            .commit()
    }

    private fun addFragmentWithLocation(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_mainAct, FragmentWithLocation())
            .commit()
    }

    private fun addFragmentTest(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_mainAct, FragmentWithLocation())
            .commit()
    }

//    override fun openFragment() {
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.container_mainAct, FragmentWithLocation())
//            .commit()
//    }

    override fun openFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_mainAct, FragmentWithLocation())
            .commit()
    }

    private fun makeToast(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}