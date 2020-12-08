package com.ulfy.master.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.bus.Subscribe;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.CropImageParam;
import com.ulfy.android.system.event.OnCropPictureEvent;
import com.ulfy.android.system.event.OnPickMediaEvent;
import com.ulfy.android.system.event.OnPickPictureEvent;
import com.ulfy.android.system.event.OnTakePhotoEvent;
import com.ulfy.android.system.event.OnTakePhotoOrPickPictureEvent;
import com.ulfy.android.system.media_picker.MediaEntity;
import com.ulfy.android.system.media_picker.MediaRepository;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.PickImgCM;
import com.ulfy.master.application.vm.FileChooseVM;
import com.ulfy.master.application.vm.ImagePickVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_image_pick)
public class ImagePickView extends BaseView {
    @ViewById(id = R.id.mediaPictureBT)
    private Button mediaPictureBT;
    @ViewById(id = R.id.pickImgRV)
    private RecyclerView pickImgRV;
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

    @Override
    public void bind(IViewModel model) {
        vm = (ImagePickVM) model;
        refreshAdapter();
    }


    @ViewClick(ids = {R.id.mediaPictureBT})
    private void media(View v) {
        switch (v.getId()) {
            case R.id.mediaPictureBT:
                addImage();
                break;
        }
    }

    private void refreshAdapter() {
        pickImgAdapter.setData(vm.pickImgCMList);
        pickImgAdapter.notifyDataSetChanged();
    }

    /**
     * 选择媒体后会触发
     */
    @Subscribe
    private void OnPickMediaEvent(OnPickMediaEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_PICTURE) {
            if (event.entities != null && event.entities.size() > 0) {
                for (MediaEntity entity : event.entities) {
                    if (entity != null) {
                        vm.addImg(entity.id, entity.filePath);
                    }
                }
                refreshAdapter();
            }

        }
    }

    /**
     * 图片适配器中的item点击回调
     *
     * @param event
     */
    @Subscribe
    public void onItemClickEvent(OnItemClickEvent event) {
        switch (event.type) {
            case 1:
                addImage();
                break;
            case 2:
                removeImage(event);
                break;
        }
    }

    /**
     * 添加图片
     */
    private void addImage() {
        if (vm.pickImgCMList.size() <= vm.maxImgCount) {
            ActivityUtils.pickMedia(REQUEST_CODE_PICK_PICTURE, MediaRepository.SEARCH_TYPE_PICTURE, vm.maxImgCount + 1 - vm.pickImgCMList.size(), null);
        }
    }

    /**
     * 删除图片
     *
     * @param event
     */
    private void removeImage(OnItemClickEvent event) {
        vm.removeImg(event.pickImgCM);
        refreshAdapter();
    }


    // 图片item点击回调
    public static final class OnItemClickEvent {
        public int type;        //1.添加图片  2.删除图片
        public PickImgCM pickImgCM;

        public OnItemClickEvent(int type, PickImgCM pickImgCM) {
            this.type = type;
            this.pickImgCM = pickImgCM;
        }
    }
}