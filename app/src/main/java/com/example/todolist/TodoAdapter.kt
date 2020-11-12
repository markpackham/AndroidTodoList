package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

// contains logic of Todos
class TodoAdapter (
        // just like private in Java
        private val todos: MutableList<Todo>
) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
                // inflate takes the xml from the frontend and lets us work with it in Kotlin
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_todo,
                        parent,
                        false
                )
        )
    }

    fun addTodo (todo: Todo){
        todos.add(todo)
        // put at the end of the list
        notifyItemInserted(todos.size - 1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{ todo ->
            todo.isChecked
        }
        // update our hole list after getting rid of the done todos
        notifyDataSetChanged()
    }

    // Toggle state of striked through todos
    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val currentTodo = todos[position]
        // itemView contains our views
        holder.itemView.apply {
            // these related to fields in our layout of item_todo
            tvTodoTitle.text = currentTodo.title
            cbDone.isChecked = currentTodo.isChecked
            toggleStrikeThrough(tvTodoTitle, currentTodo.isChecked)
            // you can use an _ in Kotlin to show a parameter that isn't needed
            cbDone.setOnCheckedChangeListener{ _, isChecked ->
                toggleStrikeThrough(tvTodoTitle, isChecked)
                // set false to true or true to false depending on current state
                currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }

    // we need to know how many items we have to display so we use this function
    override fun getItemCount(): Int {
        return todos.size
    }
}