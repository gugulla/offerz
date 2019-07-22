package cn.fanrunqi.materiallogin.volley;

import android.app.Application;

/**
 * Created by priya on 26-04-2018.
 */

public class    MyApplication extends Application {
    // Gloabl declaration of variable to use in whole app

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }
}
