package com.ulfy.master.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulfy.android.adapter.RecyclerAdapter
import com.ulfy.android.mvvm.IView
import com.ulfy.android.mvvm.IViewModel
import com.ulfy.android.system.ActivityUtils
import com.ulfy.android.task.LoadListPageUiTask.DEFAULT_START_PAGE
import com.ulfy.android.task.TaskUtils
import com.ulfy.android.task_transponder.ContentDataLoader
import com.ulfy.android.ui_injection.Layout
import com.ulfy.android.ui_injection.ViewById
import com.ulfy.android.utils.RecyclerViewUtils
import com.ulfy.android.utils.UiUtils
import com.ulfy.extra.*
import com.ulfy.master.R
import com.ulfy.master.application.base.BaseCM
import com.ulfy.master.application.base.BaseVM
import com.ulfy.master.databinding.CellList1Binding
import com.ulfy.master.databinding.ViewList1Binding
import com.ulfy.master.ui.base.BaseCell
import com.ulfy.master.ui.base.BaseView
import com.ulfy.master.ui.base.TitleContentActivity

class List1KotlinActivity : TitleContentActivity() {
    private lateinit var contentVM: List1VM
    private lateinit var contentView: List1View

    companion object {
        @JvmStatic fun startActivity() = ActivityUtils.startActivity(List1KotlinActivity::class.java)
    }

    /**
     * 初始化方法
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initModel(savedInstanceState)
        initContent(savedInstanceState)
        initActivity(savedInstanceState)
    }

    /**
     * 初始化模型和界面
     */
    private fun initModel(savedInstanceState: Bundle?) {
        contentVM = List1VM()
    }

    /**
     * 初始化界面的数据
     */
    private fun initContent(savedInstanceState: Bundle?) {
        TaskUtils.loadData(context, contentVM.contentListPage, contentVM.loadContentDataPerPageOnExe(),
            object : ContentDataLoader(contentFL, contentVM, false) {
                override fun onCreateView(loader: ContentDataLoader, createdView: View) {
                    contentView = createdView as List1View
                }
            }.setOnReloadListener { initContent(savedInstanceState) }
        )
    }

    /**
     * 初始化Activity的数据
     */
    private fun initActivity(savedInstanceState: Bundle?) {
        titleTV.text = "列表一"
    }
}


class List1View(context: Context) : BaseView(context) {
    private val binding = ViewList1Binding
        .inflate(LayoutInflater.from(context), this, true)
    private val contentAdapter = RecyclerAdapter<List1CM>()
    private lateinit var vm: List1VM

    init {
        with(binding) {
            // RecyclerAdapter 可以设置 header 和 footer，如果设置了 header、footer 则 dividerXX 方法中要填写对应的 header、footer 数量
            // RecyclerViewPageLoader 上拉加载分页内部通过 RecyclerAdapter 设置了 footer，因此需要配置 footer 为 1
            contentRV.linearLayout().vertical()
                .dividerDp(resources.getColor(R.color.line), 0.5f, 0, 1)
//                .dividerDp(getResources().getColor(R.color.line), 0.5f, 0, 1, 20, 20);
            contentRV.adapter = contentAdapter
        }
        contentAdapter.setOnItemClickListener { parent, view, position, model -> UiUtils.show(String.format("点击了：%d", position)) }
        contentAdapter.setOnItemLongClickListener { parent, view, position, model -> UiUtils.show(String.format("长按了：%d", position)) }
        with(binding) {
            contentSRL.initRefresher { bind(vm) }
            contentRV.initLoader {  }
        }
    }

    override fun bind(model: IViewModel<*>) {
        vm = model as List1VM
        // 配置下拉刷新、上拉加载内部的执行任务
        with(binding) {
            contentSRL.bindRefresher(vm.contentListPage, vm.loadContentDataPerPageOnExe())
            contentRV.bindLoader(vm.contentListPage, vm.loadContentDataPerPageOnExe())
        }
        // 设置数据源并更新列表页
        contentAdapter.setData(vm.contentCMList)
        contentAdapter.notifyDataSetChanged()
        // 该方法用于通知上拉加载器更新页面，这样在首页为空的情况下有更好的页面显示
        binding.contentRV.updateLoader()
    }
}

class List1VM : BaseVM() {
    var contentCMList = mutableListOf<List1CM>()    // 存放列表数据的模型列表
    var contentListPage = ListPage(contentCMList)   // 用于跟踪分页数据的信息，如果不需要分页可以删除该成员

    /**
     * 分页的方式加载列表数据，加载出来的数据必须要放到参数中的临时列表中，最终的数据合并将会在框架内完成
     */
    fun loadContentDataPerPageOnExe()= onLoadSimplePage { task, modelList, tempList, page, pageSize ->
        Thread.sleep(1000)
        // 在第一页时可以额外加载一些其它的数据
        if (page == DEFAULT_START_PAGE) {
        }
        // 正常的按照分页来了加载数据
        for (i in 0..29) {
            tempList.add(List1CM((page - 1) * 30 + i))
        }
    }

    override fun getViewClass() = List1View::class.java
}


class List1Cell(context: Context) : BaseCell(context) {
    private val binding = CellList1Binding
        .inflate(LayoutInflater.from(context), this, true)
    private lateinit var cm: List1CM

    override fun bind(model: IViewModel<*>?) {
        cm = model as List1CM
        binding.contentTV.text = "编号：${cm.index}"
    }
}

class List1CM(var index: Int) : BaseCM() {
    override fun getViewClass() = List1Cell::class.java
}