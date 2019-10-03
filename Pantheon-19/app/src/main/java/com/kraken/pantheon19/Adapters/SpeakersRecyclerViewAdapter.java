package com.kraken.pantheon19.Adapters;
/*
 * created by RahulKraken on 08-09-2019 at 19:43.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kraken.pantheon19.Entities.Speaker;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Utils.Services;

import java.util.List;

public class SpeakersRecyclerViewAdapter extends RecyclerView.Adapter<SpeakersRecyclerViewAdapter.SpeakersRecyclerViewHolder> {
    private static final String TAG = "SpeakerRecyclerViewAda";

    private Context context;
    private List<Speaker> speakersList;
    boolean isDark;

    public SpeakersRecyclerViewAdapter(Context context, List<Speaker> speakersList,boolean isDark) {
        this.context = context;
        this.speakersList = speakersList;
        this.isDark=isDark;
    }

    @NonNull
    @Override
    public SpeakersRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_speakers, parent, false);
        return new SpeakersRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpeakersRecyclerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: binding values to views");
        holder.img.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.speakerCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.name.setText(speakersList.get(position).getName());
        holder.venue.setText(speakersList.get(position).getVenue());
        holder.stime.setText(speakersList.get(position).getTime());
        holder.stime.setTextColor(context.getResources().getColor(Services.getRandomColor()));
        holder.day.setText(speakersList.get(position).getDay());
        holder.des.setText(speakersList.get(position).getDetail());
        holder.view.setBackgroundColor(context.getResources().getColor(Services.getRandomColor()));
        // TODO : replace with actual image
        holder.img.setImageResource(speakersList.get(position).getImageId());
        //holder.img.setImageBitmap(ImageNicer.decodeSampledBitmapFromResource(context.getResources(),
        // speakersList.get(position).getImage(),100,100));
    }

    @Override
    public int getItemCount() {
        return speakersList.size();
    }

    class SpeakersRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView name, venue, des, stime, day;
        ImageView img;
        View view;
        LinearLayout speakerCard;

        SpeakersRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.speaker_name);
            venue = itemView.findViewById(R.id.speakers_venue);
            des = itemView.findViewById(R.id.speaker_description);
            stime= itemView.findViewById(R.id.speakers_start_time);
            day= itemView.findViewById(R.id.speakers_day);
            img = itemView.findViewById(R.id.circleView);
            view=itemView.findViewById(R.id.underline);
            speakerCard=itemView.findViewById(R.id.speaker_layout);
            if(isDark) setDarkTheme();
            else setLightTheme();
        }

        public void setDarkTheme() {
            speakerCard.setBackgroundResource(R.drawable.card_events_dark);
            name.setTextColor(Color.parseColor("#ffffff"));
            venue.setTextColor(Color.parseColor("#ffffff"));
            des.setTextColor(Color.parseColor("#ffffff"));
            stime.setTextColor(Color.parseColor("#ffffff"));
            day.setTextColor(Color.parseColor("#ffffff"));
        }

        public void setLightTheme() {
            speakerCard.setBackgroundResource(R.drawable.card_events_white);
            name.setTextColor(Color.parseColor("#000000"));
            venue.setTextColor(Color.parseColor("#000000"));
            des.setTextColor(Color.parseColor("#000000"));
            stime.setTextColor(Color.parseColor("#000000"));
            day.setTextColor(Color.parseColor("#000000"));
        }
    }
}
