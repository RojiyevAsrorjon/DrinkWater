package uz.gita.drinkwater.data.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.drinkwater.data.model.WaterClass

@Dao
interface WaterDao {
    @Query("SELECT * FROM water_table")
    fun getAllData(): Flow<List<WaterClass>>

    @Query("SELECT * FROM water_table WHERE drunkDay=:drunkDay")
    fun getTodayDrunkWater(drunkDay : String) : Flow<List<WaterClass>>

    @Insert
    suspend fun insert(waterClass: WaterClass)


    @Update
    suspend fun update(waterClass: WaterClass)

    @Delete
    suspend fun delete(waterClass: WaterClass)

    @Query("DELETE from water_table")
    fun deleteAll()
}