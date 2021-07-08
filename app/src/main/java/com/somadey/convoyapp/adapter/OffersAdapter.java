package com.somadey.convoyapp.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.somadey.convoyapp.R;

import com.somadey.convoyapp.model.Offer;
import com.somadey.convoyapp.views.OfferBrowser.OfferBrowserActivity;

import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.MyViewHolder> {

    private OfferBrowserActivity offerBrowserActivity;
    private List<Offer> offerList;


    public OffersAdapter(OfferBrowserActivity offerBrowserActivity, List<Offer> offerList) {
        this.offerBrowserActivity = offerBrowserActivity;
        this.offerList = offerList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Offer offer = offerList.get(position);
        holder.showOffer(offer);
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView miles;
        public TextView price;
        public TextView origin;
        public TextView origin_timeslot;
        public TextView destination;
        public TextView destination_timeslot;

        public MyViewHolder(View itemView) {
            super(itemView);
            miles = itemView.findViewById(R.id.miles);
            price = itemView.findViewById(R.id.price);
            origin = itemView.findViewById(R.id.origin);
            origin_timeslot = itemView.findViewById(R.id.origin_timeslot);
            destination = itemView.findViewById(R.id.destination);
            destination_timeslot = itemView.findViewById(R.id.destination_timeslot);
        }

        public void showOffer(Offer offer){
            offer.log();
            miles.setText(offer.getMiles()+" miles");
            price.setText("$"+offer.getOffer());
            origin.setText(offer.getOrigin().getCity()+", "+offer.getOrigin().getState());
            destination.setText(offer.getDestination().getCity()+", "+offer.getDestination().getState());
            origin_timeslot.setText(offer.getOrigin().getPickup().getTimeDescription());
            destination_timeslot.setText(offer.getDestination().getDropoff().getTimeDescription());
        }
    }
}
