package reader.adapter.android.obd.prof.alc40.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import reader.adapter.android.obd.prof.alc40.R;
import reader.adapter.android.obd.prof.alc40.models.User;


public class ProfileFragment extends Fragment {

    private static final String TAG="ProfileFragment";

    private User mUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=this.getArguments();
        if (bundle!=null){
            mUser=bundle.getParcelable(getString(R.string.profile_frag));
            Log.d(TAG, "onCreate: received Parcelable Bundle Successfully: " + mUser.getNAME());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}