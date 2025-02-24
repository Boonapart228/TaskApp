package com.example.taskapp.domain.model.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.taskapp.domain.model.database.views.CategoryTaskView
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryTaskDao {
    @Query("SELECT * FROM category_task_view")
    fun getCategoryTaskCounts(): Flow<List<CategoryTaskView>>
}