package com.text.diff

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvDiff)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = DiffAdapter()
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btnCompare).setOnClickListener {
            val oldText = findViewById<EditText>(R.id.etOldText).text.toString()
            val newText = findViewById<EditText>(R.id.etNewText).text.toString()
            showDiff(oldText, newText, adapter)
        }
    }

    private fun showDiff(oldText: String, newText: String, adapter: DiffAdapter) {
        val oldLines = oldText.split("\n")
        val newLines = newText.split("\n")

        val diffItems = mutableListOf<DiffItem>()

        // Simplified diff logic (you can replace this with a proper diff algorithm)
        oldLines.forEach { line ->
            if (!newLines.contains(line)) {
                diffItems.add(DiffItem(line, DiffType.REMOVED))
            }
        }
        newLines.forEach { line ->
            if (!oldLines.contains(line)) {
                diffItems.add(DiffItem(line, DiffType.ADDED))
            }
        }

        adapter.updateList(diffItems)
    }
}