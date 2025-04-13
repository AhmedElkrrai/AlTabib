package com.example.altabib.featuers.favorites.data.source

import com.example.altabib.core.domain.util.DataError
import com.example.altabib.core.domain.util.Result
import com.example.altabib.featuers.dashboard.domain.entities.Doctor
import com.example.altabib.featuers.favorites.data.source.local.FavoritesDao
import com.example.altabib.featuers.favorites.data.source.local.mappers.toDoctor
import com.example.altabib.featuers.favorites.data.source.local.mappers.toEntity
import com.example.altabib.featuers.favorites.domain.FavoritesRepository

class FavoritesRepositoryImpl(
    private val dao: FavoritesDao
) : FavoritesRepository {

    override suspend fun isFavorite(doctorId: String): Boolean {
        return dao.isFavorite(doctorId)
    }

    override suspend fun getFavorites(): Result<List<Doctor>, DataError> {
        return try {
            val favorites = dao.getFavorites().map { it.toDoctor() }
            Result.Success(favorites)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError)
        }
    }

    override suspend fun addFavorite(doctor: Doctor): Result<Unit, DataError> {
        return try {
            dao.addFavorite(doctor.toEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError)
        }
    }

    override suspend fun removeFavorite(doctor: Doctor): Result<Unit, DataError> {
        return try {
            dao.removeFavorite(doctor.toEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.LocalError)
        }
    }
}
