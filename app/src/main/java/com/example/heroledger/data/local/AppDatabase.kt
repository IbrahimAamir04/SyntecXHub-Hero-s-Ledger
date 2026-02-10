
package com.example.heroledger.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.heroledger.data.local.dao.TransactionDao
import com.example.heroledger.data.local.model.Transaction
import com.example.heroledger.data.local.model.Converters

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}
