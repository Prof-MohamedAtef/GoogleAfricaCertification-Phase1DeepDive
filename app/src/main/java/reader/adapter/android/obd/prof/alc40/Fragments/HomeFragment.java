package reader.adapter.android.obd.prof.alc40.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import reader.adapter.android.obd.prof.alc40.Adapter.RecyclerAdapter;
import reader.adapter.android.obd.prof.alc40.R;
import reader.adapter.android.obd.prof.alc40.models.User;
import reader.adapter.android.obd.prof.alc40.util.Users;

public class HomeFragment extends Fragment {

    public static final String TAG="HomeFragment";
    private RecyclerView recyclerView;
    private ArrayList<User> usersArrList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "HomeFragment: Created");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "HomeFragment: Started");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container , false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        Log.d(TAG, "HomeFragment: Viewed");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Users users=new Users();
        usersArrList=new ArrayList<User>();
        for (User user: users.USERS){
            usersArrList.add(user);
        }
        populateRecycler(usersArrList);
    }

    public void populateRecycler(ArrayList<User> usersArrList){
        RecyclerAdapter adapter=new RecyclerAdapter(getActivity(), usersArrList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager1);
        adapter.notifyDataSetChanged();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    public void scrollToTop(){
        recyclerView.smoothScrollToPosition(0);
    }
}