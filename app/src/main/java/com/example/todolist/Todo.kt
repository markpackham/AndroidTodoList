package com.example.todolist

// a data class in Kotlin just holds data, not logic
data class Todo(
        val title: String,
        // needs to be a var since we change it in a toggle
        var isChecked: Boolean = false
)