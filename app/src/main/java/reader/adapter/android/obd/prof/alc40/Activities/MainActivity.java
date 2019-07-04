package reader.adapter.android.obd.prof.alc40.Activities;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import reader.adapter.android.obd.prof.alc40.Fragments.AgreementFragment;
import reader.adapter.android.obd.prof.alc40.Fragments.ConnectionsFragment;
import reader.adapter.android.obd.prof.alc40.Fragments.HomeFragment;
import reader.adapter.android.obd.prof.alc40.Fragments.MessagesFragment;
import reader.adapter.android.obd.prof.alc40.Fragments.ProfileFragment;
import reader.adapter.android.obd.prof.alc40.Fragments.SettingsFragment;
import reader.adapter.android.obd.prof.alc40.Interface.IMainActivity;
import reader.adapter.android.obd.prof.alc40.R;
import reader.adapter.android.obd.prof.alc40.models.FragmentTag;
import reader.adapter.android.obd.prof.alc40.models.User;
import reader.adapter.android.obd.prof.alc40.util.PreferencesKeys;

public class MainActivity extends AppCompatActivity implements IMainActivity,
BottomNavigationViewEx.OnNavigationItemSelectedListener,
        NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG="MainActivity";
    private static final int HOME_FRAGMENT=0;
    private static final int CONNECTIONS_FRAGMENT=1;
    private static final int MESSAGES_FRAGMENT=2;

    private BottomNavigationViewEx bottomNavigationViewEx;
    private DrawerLayout drawerLayout;

    private ArrayList<String> mFragmentTags=new ArrayList<>();
    private ArrayList<FragmentTag> mFragments=new ArrayList<>();

    private int mExitCount=0;
    private SettingsFragment settingsFragment;
    private HomeFragment homeFragment;
    private ConnectionsFragment connectionsFragment;
    private MessagesFragment messagesFragment;
    private ProfileFragment profileFragment;
    private AgreementFragment agreementFragment;


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            // bottom and drawer navigation are set in the same method
            case R.id.home:
                mFragmentTags.clear();
                mFragmentTags=new ArrayList<>();
                Log.d(TAG, "onNavigationItemSelected: Home  Fragment.");
                if (homeFragment==null){
                    homeFragment=new HomeFragment();
                    loadFragment(homeFragment);
                }else {
                    mFragmentTags.remove(homeFragment.toString());
                    mFragmentTags.add(homeFragment.toString());
                }
                setFragmentVisibilities(homeFragment.toString());
                break;
            case R.id.settings:
                Log.d(TAG, "onNavigationItemSelected: SettingsFragment.");
                if (settingsFragment==null){
                    settingsFragment=new SettingsFragment();
                    loadFragment(settingsFragment);
                }else {
                    mFragmentTags.remove(settingsFragment.toString());
                    mFragmentTags.add(settingsFragment.toString());
                }
                setFragmentVisibilities(settingsFragment.toString());
                break;
            case R.id.agreement:
                Log.d(TAG, "onNavigationItemSelected: AgreementFragment.");
                if (agreementFragment==null){
                    agreementFragment=new AgreementFragment();
                    loadFragment(agreementFragment);
                }else {
                    mFragmentTags.remove(agreementFragment.toString());
                    mFragmentTags.add(agreementFragment.toString());
                }
                setFragmentVisibilities(agreementFragment.toString());
                break;
            case R.id.bottom_nav_home:
                Log.d(TAG, "onNavigationItemSelected: HomeFragment.");

                if (homeFragment==null){
                    homeFragment=new HomeFragment();
                    loadFragment(homeFragment);
                }else {
                    mFragmentTags.remove(homeFragment.toString());
                    mFragmentTags.add(homeFragment.toString());
                }
                setFragmentVisibilities(homeFragment.toString());
                item.setChecked(true);
                break;
            case R.id.bottom_nav_connections:
                Log.d(TAG, "onNavigationItemSelected: ConnectionFragment.");
                if (connectionsFragment==null){
                    connectionsFragment=new ConnectionsFragment();
                    loadFragment(connectionsFragment);
                }else {
                    mFragmentTags.remove(connectionsFragment.toString());
                    mFragmentTags.add(connectionsFragment.toString());
                }
                setFragmentVisibilities(connectionsFragment.toString());

                item.setChecked(true);
                break;
            case R.id.bottom_nav_messages:
                Log.d(TAG, "onNavigationItemSelected: MessagesFragment.");
                if (messagesFragment==null){
                    messagesFragment=new MessagesFragment();
                    loadFragment(messagesFragment);
                }else {
                    mFragmentTags.remove(messagesFragment.toString());
                    mFragmentTags.add(messagesFragment.toString());
                }
                setFragmentVisibilities(messagesFragment.toString());
                item.setChecked(true);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        int backStackCount=mFragmentTags.size();
        if (backStackCount>1){
            //nav Backwards
            String topFragmentTag=mFragmentTags.get(backStackCount-1);

            String newTopFragmentTag=mFragmentTags.get(backStackCount-2);

            setFragmentVisibilities(newTopFragmentTag);

            mFragmentTags.remove(topFragmentTag);

            mExitCount=0;
        }else if (backStackCount==1){
            String topFragment=mFragmentTags.get(backStackCount-1);
            if (topFragment.equals(homeFragment.toString())){
                homeFragment.scrollToTop();
                mExitCount++;
                Toast.makeText(this, "1 more click to Exit", Toast.LENGTH_SHORT).show();
            }else {
                mExitCount++;
                Toast.makeText(this, "1 more click to Exit", Toast.LENGTH_SHORT).show();
            }
        }

        if (mExitCount>=2){
            super.onBackPressed();
        }
    }

    private void setNavigationIcon(String tagName){
        Menu menu=bottomNavigationViewEx.getMenu();
        MenuItem menuItem=null;
        if (tagName.equals(homeFragment.toString())){
            Log.d(TAG, "setNavigationIcon: home Fargment is Visible.");
            menuItem=menu.getItem(HOME_FRAGMENT);
            menuItem.setChecked(true);
        }else if (tagName.equals(connectionsFragment.toString())){
            Log.d(TAG, "setNavigationIcon: Connection Fargment is Visible.");
            menuItem=menu.getItem(CONNECTIONS_FRAGMENT);
            menuItem.setChecked(true);
        }else if (tagName.equals(messagesFragment.toString())){
            Log.d(TAG, "setNavigationIcon: MESSAGES Fargment is Visible.");
            menuItem=menu.getItem(MESSAGES_FRAGMENT);
            menuItem.setChecked(true);
        }
    }

    private void setFragmentVisibilities(String tagName){
        for (int i=0; i<mFragments.size(); i++){
            if (tagName.equals(mFragments.get(i).getTag())){
                //show
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.show(mFragments.get(i).getFragment());
                transaction.commit();
            }else {
                //don't show
                FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.hide(mFragments.get(i).getFragment());
                transaction.commit();
            }
        }
        setNavigationIcon(tagName);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewEx= findViewById(R.id.bottom_nav_view);
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(this);
        drawerLayout=findViewById(R.id.drawer_layout);
        isFirestLogin();
        init();
        initBottomNavigationView();
        setNavigationViewListener();
    }

    private void setNavigationViewListener(){
        Log.d(TAG, "setNavigationViewListener: initializing Navigation Viewer Listener!");
        NavigationView navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void initBottomNavigationView(){
        Log.d(TAG, "initBottomNavigationView: Starting!");
//        bottomNavigationViewEx.enableAnimation(false);
    }

    private void init(){
        if (homeFragment==null){
            homeFragment=new HomeFragment();
            loadFragment(homeFragment);
        }else {
            mFragmentTags.remove(homeFragment.toString());
            mFragmentTags.add(homeFragment.toString());
        }
        setFragmentVisibilities(homeFragment.toString());
    }


    public void loadFragment(Fragment fragment){
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_content_frame, fragment, fragment.toString());
            transaction.commit();
            mFragmentTags.add(fragment.toString());
            mFragments.add(new FragmentTag(fragment, fragment.toString()));
    }


    private void isFirestLogin(){
        Log.d(TAG, "isFirstLogin: checking if this is first login.");

        final SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstLogin=preferences.getBoolean(PreferencesKeys.FIRST_TIME_LOGIN, true);

        if (isFirstLogin){
            Log.d(TAG, "isFirstLogin: launching Alert Dialogue.");

            AlertDialog.Builder alertDialogueBuilder=new AlertDialog.Builder(this);
            alertDialogueBuilder.setMessage(getString(R.string.first_time_user_message));
            alertDialogueBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Log.d(TAG, "onClick: closing Dialogue");

                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putBoolean(PreferencesKeys.FIRST_TIME_LOGIN, false);
                    editor.commit();
                    dialog.dismiss();
                }
            });

            alertDialogueBuilder.setIcon(R.drawable.icon_notification);
            alertDialogueBuilder.setTitle(" ");
            AlertDialog alertDialog=alertDialogueBuilder.create();
            alertDialog.show();

        }
    }

    @Override
    public void inflateProfileView(User user) {
        profileFragment = new ProfileFragment();
        if (profileFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(profileFragment).commitAllowingStateLoss();
        }
        Bundle args = new Bundle();
        args.putParcelable(getString(R.string.profile_frag), user);
        profileFragment.setArguments(args);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_content_frame, profileFragment, getApplicationContext().getResources().getString(R.string.profile_frag));
        transaction.commit();
        mFragmentTags.add(getApplicationContext().getResources().getString(R.string.profile_frag));
        mFragments.add(new FragmentTag(profileFragment, getApplicationContext().getResources().getString(R.string.profile_frag)));

        setFragmentVisibilities(getApplicationContext().getResources().getString(R.string.profile_frag));
    }
}