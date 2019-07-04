package reader.adapter.android.obd.prof.alc40.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import reader.adapter.android.obd.prof.alc40.Interface.IMainActivity;
import reader.adapter.android.obd.prof.alc40.R;
import reader.adapter.android.obd.prof.alc40.models.User;

/**
 * Created by Prof-Mohamed Atef on 19/06/2019.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static final String TAG = "RecyclerAdapter";

    private final Context mContext;
    private final ArrayList<User> feedItemList;

    private IMainActivity interfaceMainActivity;

    public RecyclerAdapter(Context context, ArrayList<User> feedItemList){
        this.mContext=context;
        this.feedItemList=feedItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from( viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewholder: called.");

        RequestOptions requestOptions=new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background);

        Glide.with(mContext)
                .load(feedItemList.get(position).getPROFILE_IMAGE())
                .apply(requestOptions)
                .into(holder.image);

        holder.name.setText(feedItemList.get(position).getNAME());
        holder.interestedId.setText(feedItemList.get(position).getINTERESTED_IN());
        holder.Status.setText(feedItemList.get(position).getSTATUS());

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked:"+feedItemList.get(position).getNAME());

                interfaceMainActivity.inflateProfileView(feedItemList.get(position));
            }
        });
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        interfaceMainActivity=(IMainActivity) mContext;
    }

    @Override
    public int getItemCount() {
        return feedItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final CircleImageView image;
        private final TextView name;
        private final TextView gender;
        private final TextView interestedId;
        private final TextView Status;
        private final CardView card_view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image=(CircleImageView) itemView.findViewById(R.id.profile_img);
            name=(TextView)itemView.findViewById(R.id.name);
            gender=(TextView)itemView.findViewById(R.id.gender);
            interestedId=(TextView)itemView.findViewById(R.id.interestedIn);
            Status=(TextView)itemView.findViewById(R.id.status);
            card_view=(CardView)itemView.findViewById(R.id.card_view);
        }
    }
}