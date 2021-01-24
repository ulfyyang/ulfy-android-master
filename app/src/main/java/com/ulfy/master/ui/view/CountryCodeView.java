package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.quicksidebar.QuickSideBarTipsView;
import com.bigkoo.quicksidebar.QuickSideBarView;
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener;
import com.ulfy.android.adapter.RecyclerGroupAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.CountryCodeCM;
import com.ulfy.master.application.cm.GroupCountryCodeCM;
import com.ulfy.master.application.vm.CountryCodeVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_country_code)
public class CountryCodeView extends BaseView {
    @ViewById(id = R.id.countryRV) private RecyclerView countryRV;
    @ViewById(id = R.id.pickSB) private QuickSideBarView pickSB;
    @ViewById(id = R.id.tipsSBT) private QuickSideBarTipsView tipsSBT;
    private RecyclerGroupAdapter<GroupCountryCodeCM, CountryCodeCM> countryAdapter = new RecyclerGroupAdapter<>();
    private CountryCodeVM vm;

    public CountryCodeView(Context context) {
        super(context);
        init(context, null);
    }

    public CountryCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
    依赖于一下三方依赖
            implementation externalQuickSlideBar                        // 列表索引列表
     */

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(countryRV).vertical().dividerDp(getResources().getColor(R.color.line), 0.5f, 0, 0);
        countryRV.setAdapter(countryAdapter);
        countryAdapter.setOnGroupItemClickListener(new RecyclerGroupAdapter.OnGroupItemClickListener<GroupCountryCodeCM, CountryCodeCM>() {
            @Override public void onGroupItemClick(ViewGroup parent, View view, int position, int groupPosition, GroupCountryCodeCM model) {

            }
            @Override public void onChildItemClick(ViewGroup parent, View view, int position, int groupPosition, int childPosition, CountryCodeCM model) {
                UiUtils.show(model.countryCode.countryName);
            }
        });

        pickSB.setOnQuickSideBarTouchListener(new OnQuickSideBarTouchListener() {
            @Override public void onLetterChanged(String letter, int position, float y) {
                tipsSBT.setText(letter, position, y);
                position = vm.getGroupIndexByLetter(letter);
                if (position != -1) {
                    ((LinearLayoutManager) countryRV.getLayoutManager()).scrollToPositionWithOffset(position, 0);
                }
            }
            @Override public void onLetterTouching(boolean touching) {
                tipsSBT.setVisibility(touching ? View.VISIBLE : View.INVISIBLE);
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (CountryCodeVM) model;
        countryAdapter.setData(vm.groupCountryCodeCMList);
        countryAdapter.notifyDataSetChanged();
        pickSB.setLetters(vm.groupNameList);
    }
}