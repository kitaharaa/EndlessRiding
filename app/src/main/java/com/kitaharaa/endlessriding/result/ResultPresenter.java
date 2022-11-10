package com.kitaharaa.endlessriding.result;

public class ResultPresenter implements ResultContract.Presenter{
    ResultContract.View view;

    public ResultPresenter(ResultContract.View view) {
        this.view = view;
    }

    @Override
    public int extractIntentData() {
        return 0;
    }
}
