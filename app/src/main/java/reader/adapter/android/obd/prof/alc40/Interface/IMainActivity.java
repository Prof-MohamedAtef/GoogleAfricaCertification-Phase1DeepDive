package reader.adapter.android.obd.prof.alc40.Interface;

import reader.adapter.android.obd.prof.alc40.models.User;

/**
 * Created by Prof-Mohamed Atef on 20/06/2019.
 */

public interface IMainActivity {
    public void inflateProfileView(User user);

    void onBackPressed();
}
