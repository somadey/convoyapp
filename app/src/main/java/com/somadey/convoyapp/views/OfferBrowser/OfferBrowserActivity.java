package com.somadey.convoyapp.views.OfferBrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.somadey.convoyapp.R;
import com.somadey.convoyapp.adapter.OffersAdapter;
import com.somadey.convoyapp.model.Offer;
import com.somadey.convoyapp.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class OfferBrowserActivity extends AppCompatActivity implements OfferBrowserContract.View{

    private static final String TAG = "OfferBrowserActivity";
    private OfferListPresenter offerListPresenter;
    private RecyclerView offerListRecyclerView;
    private List<Offer> offerList;
    private OffersAdapter offersAdapter;
    private ProgressBar pbLoading;
    private TextView emptyView;
    private FloatingActionButton searchButton;


    //Constants for load more
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.offer_list_layout);
        initUI();
        setListeners();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        offerListPresenter = new OfferListPresenter(this);
        offerListPresenter.requestDataFromServer();
    }

    private void initUI() {

        offerListRecyclerView = findViewById(R.id.offer_list);
        offerList = new ArrayList<>();
        offersAdapter = new OffersAdapter(this, offerList);

        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                false);
        offerListRecyclerView.setLayoutManager(mLayoutManager);
        offerListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        offerListRecyclerView.setAdapter(offersAdapter);

        pbLoading = findViewById(R.id.pb_loading);
        emptyView = findViewById(R.id.empty_view_tv);
    }

    private void setListeners() {

        offerListRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = offerListRecyclerView.getChildCount();
                totalItemCount = mLayoutManager.getItemCount();
                firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    offerListPresenter.getMoreData();
                    loading = true;
                }

//                if (dy > 0 && searchButton.getVisibility() == View.VISIBLE) {
//                    searchButton.hide();
//                } else if (dy < 0 && searchButton.getVisibility() != View.VISIBLE) {
//                    searchButton.show();
//                }
            }
        });

    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Offer> offerArrayList) {
        offerList.addAll(offerArrayList);
        offersAdapter.notifyDataSetChanged();
        offerListPresenter.increasePageNo();
    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e(TAG, throwable.getMessage());
        Toast.makeText(this, getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        offerListPresenter.onDestroy();
    }

    public void showEmptyView() {
        offerListRecyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    public void hideEmptyView() {
        offerListRecyclerView.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Constants.OfferSort offerSort = Constants.OfferSort.PICKUPDATE;
        switch (id){
            case R.id.miles_menu:offerSort = Constants.OfferSort.MILES;break;
            case R.id.price_menu:offerSort = Constants.OfferSort.PRICE;break;
            case R.id.pickup:offerSort = Constants.OfferSort.PICKUPDATE;break;
            case R.id.dropoff:offerSort = Constants.OfferSort.DROPOFFDATE;break;
            case R.id.origin_menu:offerSort = Constants.OfferSort.ORIGIN;break;
            case R.id.destination_menu:offerSort = Constants.OfferSort.DESTINATION;break;
        }

        initUI();
        setListeners();
        offerListPresenter = new OfferListPresenter(this);
        offerListPresenter.updateSortMethod(offerSort);
        offerListPresenter.requestDataFromServer();
        return true;
    }

}