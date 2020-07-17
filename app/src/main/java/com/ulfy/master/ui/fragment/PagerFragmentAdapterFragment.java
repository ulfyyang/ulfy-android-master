package com.ulfy.master.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.ui.base.BaseFragment;

@Layout(id = R.layout.fragment_pager_fragment_adapter)
public class PagerFragmentAdapterFragment extends BaseFragment {
    @ViewById(id = R.id.contentTV) private TextView contentTV;

    public static PagerFragmentAdapterFragment getInstance(String pageNumberString) {
        PagerFragmentAdapterFragment fragment = new PagerFragmentAdapterFragment();
        Bundle data = new Bundle();
        data.putString("pageNumberString", pageNumberString);
        fragment.setArguments(data);
        return fragment;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        initContent(savedInstanceState);
    }

    private void initContent(final Bundle savedInstanceState) {
        contentTV.setText(String.format("页面%s", getArguments().getString("pageNumberString")));
    }
}