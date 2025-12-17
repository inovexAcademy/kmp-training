package de.inovex.kmp_training.core.data.repository

import de.inovex.kmp_training.core.database.dao.CategoryDao
import de.inovex.kmp_training.core.database.entity.CategoryEntity
import de.inovex.kmp_training.core.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao
) : CategoryRepository {
    
    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getCategoryById(id: Long): Category? {
        return categoryDao.getCategoryById(id)?.toDomain()
    }
    
    override suspend fun insertCategory(category: Category): Long {
        return categoryDao.insertCategory(CategoryEntity.fromDomain(category))
    }
    
    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(CategoryEntity.fromDomain(category))
    }
    
    override suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(CategoryEntity.fromDomain(category))
    }
    
    override suspend fun deleteCategoryById(id: Long) {
        categoryDao.deleteCategoryById(id)
    }
}

