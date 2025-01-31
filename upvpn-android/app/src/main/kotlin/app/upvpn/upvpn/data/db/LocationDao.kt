package app.upvpn.upvpn.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LocationDao {

    @Query("SELECT * FROM location")
    suspend fun getLocations(): List<Location>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(locations: List<Location>)

    @Query("DELETE FROM location WHERE code NOT IN (:notInLocationCodes)")
    suspend fun deleteNotIn(notInLocationCodes: List<String>);

    @Query("UPDATE location SET lastAccess = :lastAccess WHERE code = :code")
    suspend fun updateLastAccess(code: String, lastAccess: Long)

    @Query("SELECT * FROM location WHERE lastAccess > 0 ORDER BY lastAccess ASC LIMIT :limit")
    suspend fun recentLocations(limit: Int): List<Location>
}
