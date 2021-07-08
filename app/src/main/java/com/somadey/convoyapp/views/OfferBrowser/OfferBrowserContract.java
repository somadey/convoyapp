package com.somadey.convoyapp.views.OfferBrowser;

import com.somadey.convoyapp.model.Offer;
import com.somadey.convoyapp.utils.Constants;

import java.util.List;

public interface OfferBrowserContract {

    interface Model {
        interface OnFinishedListener {
            void onFinished(List<Offer> offerArrayList);
            void onFailure(Throwable t);
        }
        void getOfferList(OnFinishedListener onFinishedListener, int pageNo );
        void updateResultsOrder(Constants.OfferSort offerSort);
    }

    interface View {
        void showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<Offer> offerArrayList);
        void onResponseFailure(Throwable throwable);
    }

    interface Presenter {
        void onDestroy();
        void getMoreData();
        void requestDataFromServer();
        void increasePageNo();
        public void updateSortMethod(Constants.OfferSort offerSort);
    }
}
