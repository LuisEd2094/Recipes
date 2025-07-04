package com.example.recipe.models
import androidx.room.*


@Dao
interface MeasurementDao {
    @Insert
    suspend fun insert(measurement: Measurement): Long
}