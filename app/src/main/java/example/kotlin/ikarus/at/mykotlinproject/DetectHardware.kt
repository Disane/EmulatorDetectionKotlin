package example.kotlin.ikarus.at.mykotlinproject

import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log

/**
 * Created by elias.t on 20.10.2017.
 */
class DetectHardware
{
        companion object
        {
            fun isEmulatedEnvironement(ctx: Context) : Boolean
            {
                val telephonyManager = ctx.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                val deviceId : String = when(telephonyManager.getDeviceId()){null -> "" else -> telephonyManager.getDeviceId()}
                Log.d(DetectHardware.javaClass.name, "deviceId is: " + deviceId)
                if(Build.VERSION.RELEASE.equals("O") ||
                    deviceId.equals("000000000000000") ||
                    deviceId.equals("012345678912345")||
                    deviceId.equals("004999010640000") ||
                    Build.MODEL.contains("google_sdk") ||
                    Build.MODEL.contains("Emulator") ||
                    Build.MODEL.contains("Android SDK built for x86") ||

                    Build.MANUFACTURER.contains("Genymotion") ||
                    "google_sdk".equals(Build.PRODUCT) ||
                    "sdk".equals(Build.PRODUCT) ||
                    "sdk_x86".equals(Build.PRODUCT) ||
                    "vbox86p".equals(Build.PRODUCT))
                {
                    return true
                }
                val simOperatorName = telephonyManager.networkOperatorName
                for (carriers in "android|emergency calls only|fakecarrier".split("\\|"))
                {
                    if(simOperatorName.toLowerCase().equals(carriers))
                    {
                        return true;
                    }
                }
                return false
            }
        }
}