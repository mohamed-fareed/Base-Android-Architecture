package com.android.basearchitecture;

/**
 * Created by Mohamed Farid on 8/15/2017.
 */

public interface BaseContract {
    interface BaseView {

    }

    interface BasePresenter<T extends BaseContract.BaseView> {
        void registerView(T view);

        void unregisterView();
    }

    interface BaseInteractor {

    }
}
