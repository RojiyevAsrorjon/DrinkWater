package uz.gita.drinkwater.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "water_table")
data class WaterClass(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var volume: Int,
    var drunkTime : Long,
    var drunkDay : String
)