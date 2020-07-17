package com.ulfy.master.ui.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ulfy.android.adapter.ListAdapter;
import com.ulfy.android.adapter.RecyclerAdapter;
import com.ulfy.android.mvvm.IViewModel;
import com.ulfy.android.system.ActivityUtils;
import com.ulfy.android.system.AppUtils;
import com.ulfy.android.task.TaskUtils;
import com.ulfy.android.task_transponder.DialogProcesser;
import com.ulfy.android.task_transponder.RecyclerViewPageLoader;
import com.ulfy.android.task_transponder_smart.SmartRefresher;
import com.ulfy.android.ui_injection.Layout;
import com.ulfy.android.ui_injection.ViewById;
import com.ulfy.android.ui_injection.ViewClick;
import com.ulfy.android.ui_linkage.EditTextClearLinkage;
import com.ulfy.android.utils.RecyclerViewUtils;
import com.ulfy.android.utils.StringUtils;
import com.ulfy.android.utils.UiUtils;
import com.ulfy.android.views.FlowLayout;
import com.ulfy.master.R;
import com.ulfy.master.application.cm.ContentSearchCM;
import com.ulfy.master.application.cm.ContentSearchHistoryCM;
import com.ulfy.master.application.vm.ContentSearchVM;
import com.ulfy.master.ui.base.BaseView;

@Layout(id = R.layout.view_content_search)
public class ContentSearchView extends BaseView {
    @ViewById(id = R.id.searchET) private EditText searchET;
    @ViewById(id = R.id.searchClearIV) private ImageView searchClearIV;
    @ViewById(id = R.id.searchCancelTV) private TextView searchCancelTV;
    @ViewById(id = R.id.searchResultLL) private LinearLayout searchResultLL;
    @ViewById(id = R.id.searchHistoryLL) private LinearLayout searchHistoryLL;
    @ViewById(id = R.id.searchHistoryClearIV) private ImageView searchHistoryClearIV;
    @ViewById(id = R.id.searchHistoryFL) private FlowLayout searchHistoryFL;
    @ViewById(id = R.id.searchResultSRL) private SmartRefreshLayout searchResultSRL;
    @ViewById(id = R.id.searchResultRV) private RecyclerView searchResultRV;
    @ViewById(id = R.id.searchResultEmptyFL) private FrameLayout searchResultEmptyFL;
    private ListAdapter<ContentSearchHistoryCM> searchHistoryAdapter = new ListAdapter<>();
    private RecyclerAdapter<ContentSearchCM> searchResultAdapter = new RecyclerAdapter<>();
    private SmartRefresher searchResultRefresher;
    private RecyclerViewPageLoader searchResultLoader;
    private ContentSearchVM vm;

    /*
        测试用例：
            1. 首次进入页面显示正常：显示顶部搜索栏、历史记录，但是没有具体的搜索结果
        有数据时：
            1. 正常搜索：增加历史记录，隐藏历史记录，显示搜索结果，下拉刷新、上拉加载正常，输入法关闭
            2. 清空输入框：显示历史记录，隐藏搜索结果
            3. 清空输入框后继续搜索：显示正常搜索的结果
            4. 重复搜索：不增加历史记录
            5. 点击搜索记录：输入框自动输入并弹出输入框
            6. 点击清空历史记录可以清空
        无数据时：
            1. 正常搜索：增加历史记录，隐藏历史记录，显示暂无搜索结果页面
     */

    public ContentSearchView(Context context) {
        super(context);
        init(context, null);
    }

    public ContentSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initTopSearchBar();
        initSearchHistory();
        initSearchResult();
    }

    /**
     * 初始化顶部搜索条
     */
    private void initTopSearchBar() {
        new EditTextClearLinkage(searchET, searchClearIV).setOnClearListener(new EditTextClearLinkage.OnClearListener() {
            @Override public void onClear(EditTextClearLinkage linkage) {
                updateSearchUIWhenClearSearch();
            }
        });
        searchET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    loadSearchResultThenUpdateUI(UiUtils.toString(searchET));
                    return true;
                }
                return false;
            }
        });
    }

    /**
     * 初始化搜索历史记录
     */
    private void initSearchHistory() {
        searchHistoryFL.setAdapter(searchHistoryAdapter);
        searchHistoryFL.setOnItemClickListener(new FlowLayout.OnItemClickListener() {
            @Override public void onItemClick(FlowLayout parent, View view, int position, Object item, long itemId) {
                UiUtils.setText(searchET, ((ContentSearchHistoryCM)item).text);
                AppUtils.showSoftInput(searchET);
            }
        });
    }

    /**
     * 初始化搜索结果
     */
    private void initSearchResult() {
        RecyclerViewUtils.linearLayout(searchResultRV).vertical().dividerDp(getResources().getColor(R.color.line), 0.5f, 0, 1);
        searchResultRV.setAdapter(searchResultAdapter);
        searchResultAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener<ContentSearchCM>() {
            @Override public void onItemClick(ViewGroup parent, View view, int position, ContentSearchCM model) {
                UiUtils.show("点击了搜索结果：" + position);
            }
        });
        searchResultRefresher = new SmartRefresher(searchResultSRL, new SmartRefresher.OnRefreshSuccessListener() {
            @Override public void onRefreshSuccess(SmartRefresher smartRefresher) {
                searchResultAdapter.notifyDataSetChanged();
            }
        });
        searchResultLoader = new RecyclerViewPageLoader(searchResultRV, searchResultAdapter, null);
    }

    @Override public void bind(IViewModel model) {
        vm = (ContentSearchVM) model;
        updateSearchHistoryUI();
        updateSearchResultUI();
        updateUIWhenInit();
    }

    /**
     * 更新搜索历史页面
     */
    private void updateSearchHistoryUI() {
        searchHistoryAdapter.setData(vm.searchHistoryCMList);
        searchHistoryAdapter.notifyDataSetChanged();
    }

    /**
     * 更新搜索结果页面
     */
    private void updateSearchResultUI() {
        searchResultRefresher.updateExecuteBody(vm.searchResultTaskInfo, vm.loadSearchResultDataPerPageOnExe());
        searchResultLoader.updateExecuteBody(vm.searchResultTaskInfo, vm.loadSearchResultDataPerPageOnExe());
        searchResultAdapter.setData(vm.searchResultCMList);
        searchResultAdapter.notifyDataSetChanged();
    }

    /**
     * 首次进入页面的显示更新
     */
    private void updateUIWhenInit() {
        searchHistoryLL.setVisibility(View.VISIBLE);
        searchResultLL.setVisibility(View.GONE);
        searchResultEmptyFL.setVisibility(View.GONE);
    }

    /**
     * 当清空输入框时更新搜索页面
     */
    private void updateSearchUIWhenClearSearch() {
        vm.searchResultCMList.clear();
        searchResultAdapter.notifyDataSetChanged();
        searchHistoryLL.setVisibility(View.VISIBLE);
        searchResultLL.setVisibility(GONE);
        searchResultEmptyFL.setVisibility(GONE);
    }

    /**
     * 当搜索结束后根据搜索结果更新搜索页面
     */
    private void updateSearchUIAfterSearch() {
        searchHistoryLL.setVisibility(View.GONE);
        searchResultLL.setVisibility(vm.searchResultCMList.size() == 0 ? View.GONE : View.VISIBLE);
        searchResultEmptyFL.setVisibility(vm.searchResultCMList.size() == 0 ? View.VISIBLE : View.GONE);
        searchResultAdapter.notifyDataSetChanged();
    }

    /**
     * 清空搜索历史记录
     */
    @ViewClick(ids = R.id.searchHistoryClearIV) private void searchHistoryClearIV(View v) {
        vm.clearSearchHistory();
        searchHistoryAdapter.notifyDataSetChanged();
    }

    /**
     * 点击取消按钮
     */
    @ViewClick(ids = R.id.searchCancelTV) private void searchCancelTV(View v) {
        ActivityUtils.closeTopActivity();
    }

    /**
     * 加载搜索结果然后更新页面
     */
    private void loadSearchResultThenUpdateUI(String content) {
        // 处理历史记录
        if (!StringUtils.isEmpty(content)) {
            vm.addSearchHistory(content);
            updateSearchHistoryUI();
        }
        // 加载搜索结果
        vm.keyword = content;
        vm.searchResultTaskInfo.loadStartPage();
        TaskUtils.loadData(getContext(), vm.searchResultTaskInfo, vm.loadSearchResultDataPerPageOnExe(), new DialogProcesser(getContext()) {
                    @Override public void onSuccess(Object tipData) {
                        updateSearchUIAfterSearch();
                    }
                }
        );
    }
}