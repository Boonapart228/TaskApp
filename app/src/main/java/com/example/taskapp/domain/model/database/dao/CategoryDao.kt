package com.example.taskapp.domain.model.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.taskapp.domain.model.database.entity.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun create(categoryEntity: CategoryEntity)

    @Update
    suspend fun update(categoryEntity: CategoryEntity)

    @Delete
    suspend fun delete(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category_table WHERE id = :categoryId")
    fun getCategoryById(categoryId: Long): CategoryEntity?

    @Query("SELECT * from category_table")
    fun getAllCategories(): Flow<List<CategoryEntity>>

    @Query("SELECT title FROM category_table WHERE id = :categoryId")
    fun getCategoryTitleById(categoryId: Long): String?

}