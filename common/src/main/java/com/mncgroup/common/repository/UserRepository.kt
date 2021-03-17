package com.mncgroup.common.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
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
    fun getUserLiveData(): LiveData<UserModel>

    /**
     * get user data
     */
    val userData: UserModel?

    /**
     * to check token of user
     * @return boolean value, true if already logged in or false if not logged in
     */
    fun isLoggedIn(): Boolean

    /**
     * Function to update user data
     */
    fun updateUser(userModel: UserModel): UserModel

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

    private var _user: UserModel? = userAccessDAO.getUserData().value
    private val _userLiveData: MutableLiveData<UserModel> =
        MutableLiveData<UserModel>().apply { _user }


    override fun getUserLiveData(): LiveData<UserModel> {
        _userLiveData.value = _user

        return _userLiveData
    }

    override val userData: UserModel?
        get() = _user

    override fun isLoggedIn(): Boolean {
        return !_user?.token.isNullOrEmpty()
    }

    override fun updateUser(userModel: UserModel): UserModel {
        GlobalScope.launch(Dispatchers.Main) {
            userAccessDAO.insertDataUser(userModel)
        }

        _user = userModel
        return _user ?: userModel
    }

    override fun clearUser() {
        _user = null
        userAccessDAO.removeUser()
        _userLiveData.value = _user
    }
}

@Dao
interface UserAccessDAO {
    @Query("DELETE FROM user")
    fun removeUser()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataUser(loginTableModel: UserModel)

    @Query("SELECT * FROM user LIMIT 1")
    fun getUserData(): LiveData<UserModel>

}

@Database(entities = [UserModel::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserAccessDAO
}