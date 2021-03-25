package com.mncgroup.common.repository

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.mncgroup.common.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * an interface UserRepository
 */
interface UserRepository {
    /**
     * return live data of user model
     */
    fun getUserLiveData(): LiveData<List<UserModel>>

    /**
     * Function to update user data
     */
    fun updateUser(userModel: UserModel): LiveData<List<UserModel>>

    /**
     * Function to remove user data from app
     */
    fun clearUser()
}

/**
 * Class implementation of [UserRepository]
 * @param userAccessDAO user access DAO room database
 */
class UserRepositoryImpl(private val userAccessDAO: UserAccessDAO) : UserRepository {

    private val _userLiveData =
        userAccessDAO.getUserData()

    override fun getUserLiveData(): LiveData<List<UserModel>> {
        return _userLiveData
    }

    override fun updateUser(userModel: UserModel): LiveData<List<UserModel>> {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                userAccessDAO.insertDataUser(userModel)
            } catch (e: Exception) {
                //if something happens bad then nuke the table on background thread
                e.printStackTrace()
            }
        }

        return _userLiveData
    }

    override fun clearUser() {
        GlobalScope.launch(Dispatchers.IO) {
            _userLiveData.value?.get(0)?.let { userModel ->
                userAccessDAO.removeUser(userModel)
            }
        }
    }
}

@Dao
interface UserAccessDAO {
    @Delete
    suspend fun removeUser(userModel: UserModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataUser(userModel: UserModel)

    @Query("SELECT * FROM user")
    fun getUserData(): LiveData<List<UserModel>>
}

@Database(entities = [UserModel::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserAccessDAO
}