package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoThumbnailCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_video_thumbnail)
public class VideoThumbnailCell extends BaseCell {
    @ViewById(id = R.id.thumbnailIV) private ImageView thumbnailIV;
    private VideoThumbnailCM cm;

    public VideoThumbnailCell(Context context) {
        super(context);
        init(context, null);
    }

    public VideoThumbnailCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (VideoThumbnailCM) model;
        ImageUtils.loadImage(cm.thumbnail, R.drawable.drawable_loading, thumbnailIV);
    }
}