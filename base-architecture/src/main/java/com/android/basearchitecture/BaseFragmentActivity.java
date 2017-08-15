package com.android.basearchitecture;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public abstract class BaseFragmentActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_fragment);
        if (getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container) == null) {
            Fragment fragment = getFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    protected abstract Fragment getFragment();

    protected Fragment getAlreadyAddedFragment() {
        return getSupportFragmentManager()
                .findFragmentById(R.id.fragment_container);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager()
                .getBackStackEntryCount() <= 1)
            finish();
        else
            super.onBackPressed();
    }
}
