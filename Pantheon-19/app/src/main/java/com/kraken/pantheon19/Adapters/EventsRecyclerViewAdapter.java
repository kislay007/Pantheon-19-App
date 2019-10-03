package com.kraken.pantheon19.Adapters;
/*
 * created by RahulKraken on 09-09-2019 at 04:13.
 */

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.kraken.pantheon19.Activities.EventDetailActivity;
import com.kraken.pantheon19.Activities.EventsActivity;
import com.kraken.pantheon19.Entities.Event;
import com.kraken.pantheon19.R;
import com.kraken.pantheon19.Utils.Services;
import com.kraken.pantheon19.Utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.EventsViewHolder> {
    private static final String TAG = "EventsRecyclerViewAdapt";
    private Context context;
    private List<Event> events = new ArrayList<>();
    boolean isDark;

    public EventsRecyclerViewAdapter(Context context,boolean isDark) {
        this.context = context;
        this.isDark=isDark;
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_row, parent, false);
        return new EventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.imageView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition_animation));
        holder.eventCard.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_scale_animation));
        holder.title.setText(events.get(position).getEventName());
        holder.venue.setText(events.get(position).getVenue());
        holder.time.setText(events.get(position).getTiming());
        holder.time.setTextColor(context.getResources().getColor(Services.getRandomColor()));
        holder.imageView.setImageResource(Services.getImgResourceId(context, StringUtils.getImageResourceName(events.get(position).getEventName())));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircleImageView imageView;
        TextView title;
        TextView venue;
        TextView time;
        LinearLayout eventCard;
        ConstraintLayout eventRow;

        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.circle_image_view);
            title = itemView.findViewById(R.id.tv_event_title);
            venue = itemView.findViewById(R.id.tv_event_venue);
            time = itemView.findViewById(R.id.tv_event_time);
            eventCard=itemView.findViewById(R.id.container);
            eventRow=itemView.findViewById(R.id.events_row_layout);
            itemView.setOnClickListener(this);
            if(isDark) setDarkTheme();
            else setLightTheme();
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: clicked");
            Intent intent = new Intent(context, EventDetailActivity.class);
            ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation((Activity) context,imageView,
                    ViewCompat.getTransitionName(imageView));

            intent.putExtra(context.getResources().getString(R.string.event_intent_pass_key), events.get(getAdapterPosition()));
            context.startActivity(intent,options.toBundle());
        }

        public void setDarkTheme() {
            eventRow.setBackgroundColor(Color.parseColor("#000000"));
            title.setTextColor(Color.parseColor("#ffffff"));
            venue.setTextColor(Color.parseColor("#ffffff"));
            eventCard.setBackgroundResource(R.drawable.card_events_dark);
        }

        public void setLightTheme() {
            eventRow.setBackgroundColor(Color.parseColor("#ffffff"));
            title.setTextColor(Color.parseColor("#000000"));
            venue.setTextColor(Color.parseColor("#000000"));
            eventCard.setBackgroundResource(R.drawable.card_events_white);
        }
    }
}
