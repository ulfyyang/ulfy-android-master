package com.ulfy.extra

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.ulfy.android.adapter.RecyclerAdapter
import com.ulfy.android.task.LoadDataUiTask
import com.ulfy.android.task.LoadListPageUiTask
import com.ulfy.android.task_transponder.RecyclerViewPageLoader
import com.ulfy.android.task_transponder_smart.SmartRefresher
import com.ulfy.android.utils.RecyclerViewUtils


/*
    数据加载相关补丁
 */


// RecyclerView 布局快速设置
fun RecyclerView.linearLayout() = RecyclerViewUtils.linearLayout(this)!!
fun RecyclerView.gridLayout() = RecyclerViewUtils.gridLayout(this)!!
fun RecyclerView.staggeredLayout() = RecyclerViewUtils.staggeredLayout(this)!!
fun RecyclerView.viewPagerLayout() = RecyclerViewUtils.viewPagerLayout(this)!!


typealias ListPage<D> = LoadListPageUiTask.LoadListPageUiTaskInfo<D>   // 定义别名，简化代码
/**
 * 简化创建列表加载体的代码
 */
fun onLoadSimplePage(
    executeBody: (task: LoadListPageUiTask, modelList: List<Any>, tempList: MutableList<Any>, page: Int, pageSize: Int) -> Unit
): LoadListPageUiTask.OnLoadListPage {
    return object : LoadListPageUiTask.OnLoadSimpleListPage() {
        override fun loadSimplePage(task: LoadListPageUiTask, modelList: List<Any>, tempList: MutableList<Any>, page: Int, pageSize: Int) {
            executeBody(task, modelList, tempList, page, pageSize)
        }
    }
}


private const val KEY_SMART_REFRESHER = 1
private const val KEY_RECYCLER_LOADER = 2

// SmartRefreshLayout 便捷方法
fun SmartRefreshLayout.initRefresher(recreate: Boolean = false, onSuccess: (refresher: SmartRefresher) -> Unit) {
    getTargetFromTagMap(KEY_SMART_REFRESHER, recreate) {
        SmartRefresher(this) { onSuccess(it) }
    }
}
fun SmartRefreshLayout.bindRefresher(executeBody: LoadDataUiTask.OnExecute) {
    getTargetFromTagMap<SmartRefresher>(KEY_SMART_REFRESHER, false, null)
        .updateExecuteBody(executeBody)
}
fun SmartRefreshLayout.bindRefresher(listPage: ListPage<*>, executeBody: LoadListPageUiTask.OnLoadListPage) {
    getTargetFromTagMap<SmartRefresher>(KEY_SMART_REFRESHER, false, null)
        .updateExecuteBody(listPage, executeBody)
}

// RecyclerView 上拉加载编辑方法
fun RecyclerView.initLoader(recreate: Boolean = false, onSuccess: (loader: RecyclerViewPageLoader) -> Unit) {
    getTargetFromTagMap(KEY_RECYCLER_LOADER, recreate) {
        RecyclerViewPageLoader(this, adapter as RecyclerAdapter<*>) { onSuccess(it) }
    }
}
fun RecyclerView.bindLoader(listPage: ListPage<*>, executeBody: LoadListPageUiTask.OnLoadListPage) {
    getTargetFromTagMap<RecyclerViewPageLoader>(KEY_RECYCLER_LOADER, false, null)
        .updateExecuteBody(listPage, executeBody)
}
fun RecyclerView.updateLoader() {
    getTargetFromTagMap<RecyclerViewPageLoader>(KEY_RECYCLER_LOADER, false, null)
        .notifyDataSetChanged()
}


/**
 * 从View中找到关联的目标，如果没有则创建
 * @param key 用于定位目标的Key
 * @param recreate 是否重新创建(覆盖已存在的)
 * @param compute 用于生成目标的块，为空则不创建
 */
@Suppress("UNCHECKED_CAST")
private fun <T> View.getTargetFromTagMap(key: Any, recreate: Boolean, compute: (() -> T)?): T {
    val targetMap: MutableMap<Any, Any> =
        if (tag == null) {
            val map = mutableMapOf<Any, Any>()
            tag = map; map
        } else {
            tag as MutableMap<Any, Any>
        }
    val target: T =
        if (!recreate && targetMap.containsKey(key)) {
            targetMap[key] as T
        } else {
            compute?.let { targetMap[key] = compute()!! }
            targetMap[key] as T
        }
    return target
}