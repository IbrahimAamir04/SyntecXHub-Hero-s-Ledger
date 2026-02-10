
package com.example.heroledger.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.heroledger.domain.model.TransactionType
import java.util.Date

@Entity(tableName = "transactions")
data class Transaction( 
    @PrimaryKey
    val id: String,
    val title: String,
    val amount: Double,
    val category: String,
    val date: Date,
    val type: TransactionType,
    val description: String? = null,
    val userId: String
)

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTransactionType(value: String) = enumValueOf<TransactionType>(value)

    @TypeConverter
    fun toTransactionType(type: TransactionType) = type.name
}
