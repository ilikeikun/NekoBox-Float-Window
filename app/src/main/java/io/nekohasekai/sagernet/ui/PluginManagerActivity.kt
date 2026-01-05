package io.nekohasekai.sagernet.ui

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import io.nekohasekai.sagernet.R
import io.nekohasekai.sagernet.databinding.LayoutAppsItemBinding
import io.nekohasekai.sagernet.databinding.LayoutSimpleListBinding

class PluginManagerActivity : ThemedActivity() {

    private data class PluginApp(
        val name: CharSequence,
        val packageName: String,
        val icon: Drawable,
    )

    private inner class PluginViewHolder(val binding: LayoutAppsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PluginApp) {
            binding.itemicon.setImageDrawable(item.icon)
            binding.title.text = item.name
            binding.desc.text = item.packageName
            // 插件列表暂时不提供开关，隐藏右侧 Switch
            binding.itemcheck.visibility = View.GONE

            binding.root.setOnClickListener {
                try {
                    val intent = Intent("io.nekohasekai.sagernet.action.PLUGIN_APP").apply {
                        `package` = item.packageName.toString()
                    }
                    binding.root.context.startActivity(intent)
                } catch (_: Exception) {
                }
            }
        }
    }

    private inner class PluginAdapter(
        private var items: List<PluginApp>
    ) : RecyclerView.Adapter<PluginViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PluginViewHolder {
            val binding = LayoutAppsItemBinding.inflate(layoutInflater, parent, false)
            return PluginViewHolder(binding)
        }

        override fun onBindViewHolder(holder: PluginViewHolder, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size

        fun submitList(newItems: List<PluginApp>) {
            items = newItems
            notifyDataSetChanged()
        }
    }

    private lateinit var binding: LayoutSimpleListBinding
    private val adapter = PluginAdapter(emptyList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayoutSimpleListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: MaterialToolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            setTitle(R.string.plugin_manager_title)
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_navigation_close)
        }

        binding.list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.list.adapter = adapter

        loadPlugins()
    }

    private fun loadPlugins() {
        val pm = packageManager
        val apps = pm.getInstalledApplications(PackageManager.GET_META_DATA)
            .filter { it.packageName.contains("kwnet", ignoreCase = true) }
            .sortedBy { it.loadLabel(pm).toString().lowercase() }
            .map { info: ApplicationInfo ->
                PluginApp(
                    name = info.loadLabel(pm),
                    packageName = info.packageName,
                    icon = info.loadIcon(pm),
                )
            }

        adapter.submitList(apps)
        binding.placeholder.visibility = if (apps.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
