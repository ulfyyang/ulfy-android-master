package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.event.OnPickMediaEvent;
import com.ulfy.android.system.media_picker.MediaRepository;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.PickImgCM;
import com.ulfy.master.application.vm.ImagePickVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_image_pick)
public class ImagePickView extends BaseView {
    @ViewById(id = R.id.mediaPictureBT) private Button mediaPictureBT;
    @ViewById(id = R.id.pickImgRV) private RecyclerView pickImgRV;
    private static final int REQUEST_CODE_PICK_PICTURE = 101;
    private RecyclerAdapter<PickImgCM> pickImgAdapter = new RecyclerAdapter<>();
    private ImagePickVM vm;

    public ImagePickView(Context context) {
        super(context);
        init(context, null);
    }

    public ImagePickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        RecyclerViewUtils.gridLayout(pickImgRV).vertical(3).dividerDp(Color.TRANSPARENT, 15, 15, 0, 0);
        pickImgRV.setAdapter(pickImgAdapter);
    }

    @Override public void bind(IViewModel model) {
        vm = (ImagePickVM) model;
        pickImgAdapter.setData(vm.pickImgCMList);
        pickImgAdapter.notifyDataSetChanged();
    }

    @ViewClick(ids = R.id.mediaPictureBT) private void mediaPictureBT(View v) {
        goPickImageActivity();
    }

    /**
     * 图片适配器中的item点击回调
     */
    @Subscribe public void onItemClickEvent(OnItemClickEvent event) {
        if (event.type == 1) {
            goPickImageActivity();
        } else if (event.type == 2) {
            vm.removeImage(event.cm);
            pickImgAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 选择媒体后会触发
     */
    @Subscribe private void OnPickMediaEvent(OnPickMediaEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_PICTURE) {
            if (event.entities != null && event.entities.size() > 0) {
                vm.addImage(event.entities);
                pickImgAdapter.notifyDataSetChanged();
            }
        }
    }

    /**
     * 添加图片
     */
    private void goPickImageActivity() {
        int emptyCount = vm.caculateEmptyCount();
        if (emptyCount > 0) {
            ActivityUtils.pickMedia(REQUEST_CODE_PICK_PICTURE,
                    MediaRepository.SEARCH_TYPE_PICTURE, emptyCount, null);
        }
    }

    // 图片item点击回调
    public static final class OnItemClickEvent {
        public int type;        // 1.添加图片  2.删除图片
        public PickImgCM cm;

        public OnItemClickEvent(int type, PickImgCM cm) {
            this.type = type;
            this.cm = cm;
        }
    }
}