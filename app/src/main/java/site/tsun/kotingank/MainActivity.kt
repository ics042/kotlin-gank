package site.tsun.kotingank

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import site.tsun.kotingank.adapter.GankDataListAdapter
import site.tsun.kotingank.http.GankRequest
import site.tsun.kotingank.model.GankResult
import site.tsun.kotingank.ui.WebViewActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvGankDataList.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(this,
                layoutManager.orientation)
        rvGankDataList.addItemDecoration(dividerItemDecoration)
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() = async(UI) {
        val result = bg { GankRequest().getGankData("Android", 1) }
        updateUI(result.await())
    }

    private fun updateUI(gankResult: GankResult) {
        val adapter = GankDataListAdapter(gankResult.results) {
            startActivity(intentFor<WebViewActivity>("url" to it.url))
        }
        rvGankDataList.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
