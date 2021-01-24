package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerGroupAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ContactBookCM;
import com.ulfy.master.application.cm.GroupContactBookCM;
import com.ulfy.master.application.vm.ContactBookVM;
import com.ulfy.master.ui.base.BaseView;

import cc.solart.wave.WaveSideBarView;

@Layout(id = R.layout.view_contact_book)
public class ContactBookView extends BaseView {
    @ViewById(id = R.id.contactRV) private RecyclerView contactRV;
    @ViewById(id = R.id.contactSB) private WaveSideBarView contactSB;
    private RecyclerGroupAdapter<GroupContactBookCM, ContactBookCM> contactAdapter = new RecyclerGroupAdapter<>();
    private ContactBookVM vm;

    public ContactBookView(Context context) {
        super(context);
        init(context, null);
    }

    public ContactBookView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    /*
    依赖于一下三方依赖 在项目根目录下的 wave
     */

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.linearLayout(contactRV).vertical().dividerDp(getResources().getColor(R.color.line), 0.5f, 0, 0);
        contactRV.setAdapter(contactAdapter);
        contactAdapter.setOnGroupItemClickListener(new RecyclerGroupAdapter.OnGroupItemClickListener<GroupContactBookCM, ContactBookCM>() {
            @Override public void onGroupItemClick(ViewGroup parent, View view, int position, int groupPosition, GroupContactBookCM model) {

            }
            @Override public void onChildItemClick(ViewGroup parent, View view, int position, int groupPosition, int childPosition, ContactBookCM model) {
                UiUtils.show(model.peopleName);
            }
        });

        contactSB.setOnTouchLetterChangeListener(new WaveSideBarView.OnTouchLetterChangeListener() {
            @Override public void onLetterChange(String letter) {
                int position = vm.getGroupIndexByLetter(letter);
                if (position != -1) {
                    ((LinearLayoutManager) contactRV.getLayoutManager()).scrollToPositionWithOffset(position, 0);
                }
            }
        });
    }

    @Override public void bind(IViewModel model) {
        vm = (ContactBookVM) model;
        contactAdapter.setData(vm.groupContactBookCMList);
        contactAdapter.notifyDataSetChanged();
    }
}