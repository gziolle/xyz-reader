package com.example.xyzreader.remote;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.xyzreader.R;
import com.example.xyzreader.data.UpdaterService;

public class NetworkConnectionReceiver extends BroadcastReceiver {

    private NetworkConnectionInterface mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED){
            mListener.showSnackBar(context.getString(R.string.no_internet));
        } else{
            mListener.dismissSnackBar();
            context.startService(new Intent(context, UpdaterService.class));
        }

    }

    public void registerReceiver(NetworkConnectionInterface listener){
        this.mListener = listener;
    }

    public interface NetworkConnectionInterface {
        void showSnackBar(String text);

        void dismissSnackBar();
    }
}
