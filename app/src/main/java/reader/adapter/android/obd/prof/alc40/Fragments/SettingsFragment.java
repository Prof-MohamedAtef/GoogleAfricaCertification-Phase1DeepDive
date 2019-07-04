package reader.adapter.android.obd.prof.alc40.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import reader.adapter.android.obd.prof.alc40.Interface.IMainActivity;
import reader.adapter.android.obd.prof.alc40.R;
import reader.adapter.android.obd.prof.alc40.models.User;

public class SettingsFragment extends Fragment implements View.OnClickListener{

    IMainActivity iMainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    @Override
    public void onClick(View v) {
//        if (v.getId()==R.id.){
//            iMainActivity.onBackPressed();
//        }
    }
}