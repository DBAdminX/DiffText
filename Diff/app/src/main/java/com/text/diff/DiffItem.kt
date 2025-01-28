package com.text.diff

data class DiffItem(
    val text: String,
    val type: DiffType
)

enum class DiffType {
    ADDED, REMOVED, UNCHANGED
}
