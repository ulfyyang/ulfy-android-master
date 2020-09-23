package com.ulfy.master.ui.cell;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ulfy.android.image.ImageUtils;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.views.ShapeLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.VideoListCM;
import com.ulfy.master.ui.base.BaseCell;

@Layout(id = R.layout.cell_video_list)
public class VideoListCell extends BaseCell {
    @ViewById(id = R.id.coverIV) private ImageView coverIV;
    @ViewById(id = R.id.titleTV) private TextView titleTV;
    @ViewById(id = R.id.countTV) private TextView countTV;
    @ViewById(id = R.id.timeTV) private TextView timeTV;
    @ViewById(id = R.id.avatarIV) private ImageView avatarIV;
    @ViewById(id = R.id.isAuthorSL) private ShapeLayout isAuthorSL;
    @ViewById(id = R.id.nameTV) private TextView nameTV;
    @ViewById(id = R.id.fanCountTV) private TextView fanCountTV;
    @ViewById(id = R.id.commentLL) private LinearLayout commentLL;
    @ViewById(id = R.id.commentTV) private TextView commentTV;
    @ViewById(id = R.id.shareLL) private LinearLayout shareLL;
    private VideoListCM cm;

    public VideoListCell(Context context) {
        super(context);
        init(context, null);
    }

    public VideoListCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

    }

    @Override public void bind(IViewModel model) {
        cm = (VideoListCM) model;
        ImageUtils.loadImage(cm.cover, R.drawable.drawable_loading, R.drawable.drawable_loading_false, coverIV);
        titleTV.setText(cm.title);
        countTV.setText(String.format("%d次播放", cm.count));
        timeTV.setText(cm.time);
        ImageUtils.loadImage(cm.avatar, R.drawable.drawable_loading, R.drawable.drawable_loading_false, avatarIV);
        isAuthorSL.setVisibility(cm.isAuthor ? View.VISIBLE : View.GONE);
        nameTV.setText(cm.name);
        fanCountTV.setText(String.format("%d粉丝", cm.fanCount));
        commentTV.setText(String.valueOf(cm.commentCount));
    }
}