package com.kraken.pantheon19.Adapters;
/*
 * created by RahulKraken on 23-08-2019 at 15:12.
 */

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;

import com.kraken.pantheon19.Activities.EventDetailActivity;
import com.kraken.pantheon19.Entities.Flagship;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Utils.Constants;
import com.kraken.pantheon19.Utils.Services;

import java.util.Arrays;
import java.util.List;

public class FlagshipRecyclerViewAdapter extends RecyclerView.Adapter<FlagshipRecyclerViewAdapter.FlagshipRecyclerViewHolder> {
    private static final String TAG = "FlagshipRecyclerViewAda";

    private Context context;
    private List<Flagship> eventList;

    public FlagshipRecyclerViewAdapter(Context context) {
        this.context = context;
        eventList = Arrays.asList(Constants.FLAGSHIPS);
    }

    @NonNull
    @Override
    public FlagshipRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.flagship_rv_item, parent, false);
        return new FlagshipRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlagshipRecyclerViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: binding values to views");
        holder.title.setText(context.getResources().getString(eventList.get(position).getTitle()));
        holder.venue.setText(context.getResources().getString(eventList.get(position).getVenue()));
        holder.time.setText(context.getResources().getString(eventList.get(position).getTime()));

        holder.img.setImageResource(eventList.get(position).getImageId());
        holder.constraintLayout.setBackgroundColor(context.getResources().getColor(eventList.get(position).getColorId()));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class FlagshipRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, venue, time;
        ConstraintLayout constraintLayout;
        ImageView img;
        Button viewBtn;
        CardView cardView;

        FlagshipRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.flagship_card_title);
            venue = itemView.findViewById(R.id.flagship_card_venue);
            time = itemView.findViewById(R.id.flagship_card_time);
            img = itemView.findViewById(R.id.flagship_card_featured_image);
            viewBtn = itemView.findViewById(R.id.flagship_card_view_btn);
            cardView = itemView.findViewById(R.id.container_card);

            constraintLayout = itemView.findViewById(R.id.container);

            viewBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: view btn clicked!!");
            Intent intent = new Intent(context, EventDetailActivity.class);
            intent.putExtra(context.getResources().getString(R.string.flagship_intent_pass_key), eventList.get(getAdapterPosition()));
            context.startActivity(intent);
        }
    }
}
