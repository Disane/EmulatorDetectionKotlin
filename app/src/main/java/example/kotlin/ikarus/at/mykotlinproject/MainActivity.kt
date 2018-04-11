package example.kotlin.ikarus.at.mykotlinproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val strEnvironment = when(DetectHardware.isEmulatedEnvironement(applicationContext))
        {
            true -> "Executing on Emulator"
            false -> "Executing on a Real Device"
        }
        // Example of a call to a native method
        val tv = findViewById(R.id.sample_text) as TextView
        //tv.text = stringFromJNI()
       tv.text = strEnvironment
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String
    companion object
    {
        // Used to load the 'native-lib' library on application startup.
        init
        {
            System.loadLibrary("native-lib")
        }
    }
}
