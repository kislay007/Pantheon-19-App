package com.kraken.pantheon19.Adapters;
/*
 * created by RahulKraken on 08-09-2019 at 20:23.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.Entities.Sponsor;
import com.kraken.pantheon19.R;

import java.util.List;

public class SponsorsRecyclerViewAdapter extends RecyclerView.Adapter<SponsorsRecyclerViewAdapter.SponsorsRecyclerViewHolder> {
    private static final String TAG = "SpeakerRecyclerViewAda";

    private Context context;
    private List<Sponsor> sponsorList;
    private CustomTabsIntent customTabsIntent;
    boolean isDark;

    public SponsorsRecyclerViewAdapter(Context context, List<Sponsor> sponsorList,boolean isDark) {
        this.context = context;
        this.sponsorList = sponsorList;
        this.isDark=isDark;
        // setup customTabIntent
        customTabsIntent = new CustomTabsIntent.Builder()
                .setShowTitle(true)
                .build();
    }

    @NonNull
    @Override
    public SponsorsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_sponsors, parent, false);
        return new SponsorsRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorsRecyclerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: binding values to views");
        holder.textView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.img.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.sponsorCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));

        // TODO : replace with actual image
        if (sponsorList.get(position).getSponsorName().equals("")) holder.textView.setVisibility(View.GONE);
        else {
            holder.textView.setText(sponsorList.get(position).getSponsorName());
            holder.textView.setVisibility(View.VISIBLE);
        }

        if (sponsorList.get(position).getSponsorImage() == 0) holder.img.setVisibility(View.GONE);
        else {
            holder.img.setImageResource(sponsorList.get(position).getSponsorImage());
            holder.img.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return sponsorList.size();
    }

    class SponsorsRecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView img;
        LinearLayout sponsorCard;

        SponsorsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.sponsor_text);
            img = itemView.findViewById(R.id.item_sponsor);
            sponsorCard=itemView.findViewById(R.id.sponsors_layout);
            if(isDark) setDarkTheme();
            else setLightTheme();
        }

        public void setDarkTheme() {
            textView.setTextColor(Color.parseColor("#ffffff"));
        }

        public void setLightTheme() {
            textView.setTextColor(Color.parseColor("#000000"));
        }
    }
}
