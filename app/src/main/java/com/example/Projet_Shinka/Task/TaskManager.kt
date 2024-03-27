package com.example.Projet_Shinka.Task

import android.content.Context
import androidx.room.*

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var type: TaskType,
    var timeCategory: TimeCategory
)

enum class TaskType {
    SPORT, KNOWLEDGE, WELLBEING, HELP
}

enum class TimeCategory {
    DAILY, WEEKLY, EXCEPTIONAL
}

@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM Task")
    suspend fun getTasks(): List<Task>

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)
}

@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                )
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}



class TaskManager(private val context: Context) {
    private val taskDao: TaskDao = AppDatabase.getDatabase(context).taskDao()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun getTasks(): List<Task> {
        return taskDao.getTasks()
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

}

class Converters {
    @TypeConverter
    fun fromTaskType(value: TaskType): String = value.name

    @TypeConverter
    fun toTaskType(value: String): TaskType = enumValueOf(value)

    @TypeConverter
    fun fromTimeCategory(value: TimeCategory): String = value.name

    @TypeConverter
    fun toTimeCategory(value: String): TimeCategory = enumValueOf(value)
}

