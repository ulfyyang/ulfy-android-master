package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dueeeke.videoplayer.player.VideoView;
import com.ulfy.android.dialog.DialogUtils;
import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.master.BuildConfig;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.DouyinCM;
import com.ulfy.master.ui.base.BaseCell;
import com.ulfy.master.ui.custom_dkplayer.TikTokController;
import com.ulfy.master.ui.custom_dkplayer.VideoViewRepository;
import com.ulfy.master.ui.custom_dkplayer.cache.PreloadManager;
import com.ulfy.master.ui.view.VideoCommentContentView;
import com.ulfy.master.ui.view.VideoCommentView;

@Layout(id = R.layout.cell_douyin)
public class DouyinCell extends BaseCell {
    @ViewById(id = R.id.douyinVV) private VideoView douyinVV;
    @ViewById(id = R.id.messageLL) private LinearLayout messageLL;
    @ViewById(id = R.id.messageTV) private TextView messageTV;
    private TikTokController tikTokController;
    private DouyinCM cm;

    public DouyinCell(Context context) {
        super(context);
        init(context, null);
    }

    public DouyinCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        tikTokController = new TikTokController(getContext());
        tikTokController.getThumbIV().setScaleType(ImageView.ScaleType.FIT_CENTER);
        tikTokController.setEnableDoubleEffect(true);
        tikTokController.setOnDoubleClickListener(new TikTokController.OnDoubleClickListener() {
            @Override public void onDoubleClick() {
                UiUtils.show("双击了");
            }
        });

//        videoView.setScreenScaleType(VideoView.SCREEN_SCALE_CENTER_CROP);
        douyinVV.setVideoController(tikTokController);
        VideoViewRepository.getInstance().addVideoView(douyinVV);
    }

    @Override public void bind(IViewModel model) {
        cm = (DouyinCM) model;
        ImageUtils.loadImage(cm.imageUrl, android.R.color.black, tikTokController.getThumbIV());
        messageTV.setText(String.valueOf(200));
    }

    public void onItemSelected() {
        if (BuildConfig.VIDEO_PRE_LOAD) {
            douyinVV.setUrl(PreloadManager.getInstance(getContext()).getPlayUrl(cm.videoUrl));
        } else {
            douyinVV.setUrl(cm.videoUrl);
        }
        VideoViewRepository.getInstance().releaseVideoView(getContext(), false);
        douyinVV.start();
    }

    public String getPlayUrl() {
        return cm.videoUrl;
    }

    public int getIndex() {
        return cm.index;
    }

    @ViewClick(ids = R.id.messageLL) private void comment(View view) {
        DialogUtils.showBottomSheetDialog(getContext(), VideoCommentView.DIALOG_ID,
                new VideoCommentContentView(getContext()), true)
                .ignoreSoftInputMethod();
    }
}