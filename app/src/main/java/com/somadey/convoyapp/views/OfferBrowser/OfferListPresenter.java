package com.somadey.convoyapp.views.OfferBrowser;

import com.somadey.convoyapp.model.Offer;
import com.somadey.convoyapp.utils.Constants;
import com.somadey.convoyapp.views.OfferBrowser.OfferBrowserContract.View;
import com.somadey.convoyapp.views.OfferBrowser.OfferBrowserContract.Model;
import com.somadey.convoyapp.views.OfferBrowser.OfferBrowserContract.Presenter;
import com.somadey.convoyapp.views.OfferBrowser.OfferBrowserContract.Model.OnFinishedListener;

import java.util.List;

public class OfferListPresenter implements Presenter, OnFinishedListener {

    private View offerListView;
    private Model offerListModel;
    private int pageNo = 1;

    public OfferListPresenter(OfferBrowserContract.View offerListView) {
        this.offerListView = offerListView;
        offerListModel = new OfferBrowserModel();
    }

    @Override
    public void onDestroy() {
        this.offerListView = null;
    }

    @Override
    public void getMoreData( ) {

        if (offerListView != null) {
            offerListView.showProgress();
        }
        offerListModel.getOfferList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {

        if (offerListView != null) {
            offerListView.showProgress();
        }
        offerListModel.getOfferList(this, 1);
    }

    @Override
    public void onFinished(List<Offer> offerArrayList) {
        offerListView.setDataToRecyclerView(offerArrayList);
        if (offerListView != null) {
            offerListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        offerListView.onResponseFailure(t);
        if (offerListView != null) {
            offerListView.hideProgress();
        }
    }

    @Override
    public void increasePageNo(){
        pageNo++;
    }

    @Override
    public void updateSortMethod(Constants.OfferSort offerSort){
        offerListModel.updateResultsOrder(offerSort);
    }
}
