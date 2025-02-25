package com.example.taskapp.domain.model.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.taskapp.domain.model.database.views.CategoryTaskView
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryTaskDao {
    @Query(
        "SELECT * FROM category_task_view " +
                "ORDER BY " +
                "CASE WHEN :sortDirection = 'ASC' THEN " +
                "(CASE :sortBy " +
                "WHEN 'title' THEN LOWER(categoryTitle) " +
                "WHEN 'note' Then taskCount " +
                "ELSE NULL END) " +
                "END ASC, " +
                "CASE WHEN :sortDirection = 'DESC' THEN " +
                "(CASE :sortBy " +
                "WHEN 'title' THEN LOWER(categoryTitle) " +
                "WHEN 'note' Then taskCount " +
                "ELSE NULL END) " +
                "END DESC"
    )
    fun getCategoryTaskCounts(
        sortBy: String,
        sortDirection: String
    ): Flow<List<CategoryTaskView>>
}