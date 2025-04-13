package com.example.altabib.featuers.favorites.data.source

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

    override suspend fun getFavorites(): List<Doctor> {
        return dao.getFavorites().map { it.toDoctor() }
    }

    override suspend fun addFavorite(doctor: Doctor) {
        dao.addFavorite(doctor.toEntity())
    }

    override suspend fun removeFavorite(doctor: Doctor) {
        dao.removeFavorite(doctor.toEntity())
    }
}
