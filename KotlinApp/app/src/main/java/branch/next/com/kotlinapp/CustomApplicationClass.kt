package branch.next.com.kotlinapp

import android.app.Application

import io.branch.referral.Branch

/**
 * Created by adeshmukh on 8/22/17.
 */

class CustomApplicationClass : Application() {

    override fun onCreate() {
        super.onCreate()
        // initialize the Branch object
        //        Branch.enableTestMode();

        Branch.enableLogging()
        Branch.getAutoInstance(this)
    }
}
