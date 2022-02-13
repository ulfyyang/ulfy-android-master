package com.ulfy.master.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

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
import com.ulfy.android.system.event.OnTakeVideoEvent;
import com.ulfy.android.system.media_picker.MediaRepository;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.R;
import com.ulfy.master.application.vm.FileChooseVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_file_choose)
public class FileChooseView extends BaseView {
    @ViewById(id = R.id.dialogBT) private Button dialogBT;
    @ViewById(id = R.id.dialogCropBT) private Button dialogCropBT;
    @ViewById(id = R.id.pickBT) private Button pickBT;
    @ViewById(id = R.id.pickCropBT) private Button pickCropBT;
    @ViewById(id = R.id.takeBT) private Button takeBT;
    @ViewById(id = R.id.takeCropBT) private Button takeCropBT;
    @ViewById(id = R.id.takeVideoBT) private Button takeVideoBT;
    @ViewById(id = R.id.cropBT) private Button cropBT;
    @ViewById(id = R.id.mediaPictureBT) private Button mediaPictureBT;
    @ViewById(id = R.id.mediaVideoBT) private Button mediaVideoBT;
    @ViewById(id = R.id.contentIV) private ImageView contentIV;
    private static final int REQUEST_CODE = 100;
    private static final int REQUEST_CODE_PICK_PICTURE = 101;
    private static final int REQUEST_CODE_PICK_VIDEO = 102;
    private static final int REQUEST_CODE_TAKE_VIDEO = 103;
    private CropImageParam cropImageParam = new CropImageParam(100, 150);
    private FileChooseVM vm;

    public FileChooseView(Context context) {
        super(context);
        init(context, null);
    }

    public FileChooseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        vm = (FileChooseVM) model;
    }

    @ViewClick(ids = {R.id.dialogBT, R.id.dialogCropBT})
    private void dialog(View v) {
        switch (v.getId()) {
            case R.id.dialogBT:
                ActivityUtils.showTakePhotoOrPickPictureDialog(REQUEST_CODE);
                break;
            case R.id.dialogCropBT:
                ActivityUtils.showTakePhotoOrPickPictureDialog(REQUEST_CODE, cropImageParam);
                break;
        }
    }

    @ViewClick(ids = {R.id.pickBT, R.id.pickCropBT})
    private void pick(View v) {
        switch (v.getId()) {
            case R.id.pickBT:
                ActivityUtils.pickPicture(REQUEST_CODE);
                break;
            case R.id.pickCropBT:
                ActivityUtils.pickPicture(REQUEST_CODE, cropImageParam);
                break;
        }
    }

    @ViewClick(ids = {R.id.takeBT, R.id.takeCropBT})
    private void take(View v) {
        switch (v.getId()) {
            case R.id.takeBT:
                ActivityUtils.takePhoto(REQUEST_CODE);
                break;
            case R.id.takeCropBT:
                ActivityUtils.takePhoto(REQUEST_CODE, cropImageParam);
                break;
        }
    }

    @ViewClick(ids = R.id.takeVideoBT) private void takeVideoBT(View v) {
        ActivityUtils.takeVideo(REQUEST_CODE_TAKE_VIDEO);
    }

    @ViewClick(ids = R.id.cropBT) private void cropBT(View v) {
        UiUtils.show("参考ActivityUtils.cropImage()方法");
    }

    @ViewClick(ids = {R.id.mediaPictureBT, R.id.mediaVideoBT})
    private void media(View v) {
        switch (v.getId()) {
            case R.id.mediaPictureBT:
                ActivityUtils.pickMedia(REQUEST_CODE_PICK_PICTURE, MediaRepository.SEARCH_TYPE_PICTURE, 10, vm.pictureEntityList);
                break;
            case R.id.mediaVideoBT:
                ActivityUtils.pickMedia(REQUEST_CODE_PICK_VIDEO, MediaRepository.SEARCH_TYPE_VIDEO, 10, vm.videoEntityList);
                break;
        }
    }

    /**
     * 选择图片后会触发
     */
    @Subscribe private void OnPickPictureEvent(OnPickPictureEvent event) {
        // 通常情况只需要订阅OnTakePhotoOrPickPictureEvent即可，因为包含了该事件
    }

    /**
     * 拍照后会触发
     */
    @Subscribe private void OnTackPhotoEvent(OnTakePhotoEvent event) {
        // 通常情况只需要订阅OnTakePhotoOrPickPictureEvent即可，因为包含了该事件
    }

    /**
     * 选图或拍照后会触发
     */
    @Subscribe private void OnTakePhotoOrPickPictureEvent(OnTakePhotoOrPickPictureEvent event) {
        if (event.requestCode == REQUEST_CODE) {
            ImageUtils.loadImage(event.file, contentIV);
        }
    }

    /**
     * 拍摄视频后会触发
     */
    @Subscribe private void OnTakeVideoEvent(OnTakeVideoEvent event) {
        if (event.requestCode == REQUEST_CODE_TAKE_VIDEO) {
            UiUtils.show(String.format("拍摄视频路径：%s，长度：%d", event.file.getAbsolutePath(), event.duration));
        }
    }


    /**
     * 图片裁切后会触发
     */
    @Subscribe private void OnCropPictureEvent(OnCropPictureEvent event) {
        ImageUtils.loadImage(event.file, contentIV);
    }

    /**
     * 选择媒体后会触发
     */
    @Subscribe private void OnPickMediaEvent(OnPickMediaEvent event) {
        if (event.requestCode == REQUEST_CODE_PICK_PICTURE) {
            vm.pictureEntityList = event.entities;
        } else if (event.requestCode == REQUEST_CODE_PICK_VIDEO) {
            vm.videoEntityList = event.entities;
        }
        UiUtils.show(String.format("选择了%d个媒体文件", event.entities.size()));
    }
}