package com.example.speed_racer.data.data_source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GamePreferencesDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : GamePreferencesDataSource {

    override fun getIntValue(key: String): Flow<Int?> {
        val prefKey = intPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[prefKey]
        }
    }

    override suspend fun saveIntValue(key: String, value: Int) {
        val prefKey = intPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }
}