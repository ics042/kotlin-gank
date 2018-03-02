package site.tsun.kotingank.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.gank_item.view.*
import site.tsun.kotingank.R
import site.tsun.kotingank.extension.inflate
import site.tsun.kotingank.model.GankDataModel

class GankDataListAdapter(val dataList: ArrayList<GankDataModel>, val itemClick: (GankDataModel) -> Unit) : RecyclerView.Adapter<GankDataListAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViewHolder(dataList[position])
    }

    override fun getItemCount() = dataList.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.gank_item), itemClick)

    class ViewHolder(val view: View, val itemClick: (GankDataModel) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindViewHolder(gankDataModel: GankDataModel) {
            with(gankDataModel) {
                view.tvDescription?.text = "$desc"
                view.tvPublishedAt?.text = "$publishedAt"
                view.setOnClickListener { itemClick(this) }
            }
        }
    }
}