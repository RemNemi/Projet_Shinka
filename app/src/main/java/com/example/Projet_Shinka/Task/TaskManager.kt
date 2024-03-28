package com.example.Projet_Shinka.Task

import android.content.Context
import androidx.room.*

// Définition d'une entité Task pour la base de données Room
@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var type: TaskType,
    var timeCategory: TimeCategory
)

// Énumérations pour catégoriser les tâches
enum class TaskType { SPORT, KNOWLEDGE, WELLBEING, HELP }
enum class TimeCategory { DAILY, WEEKLY, EXCEPTIONAL }

// DAO avec les opérations de base de données pour Task
@Dao
interface TaskDao {
    @Insert suspend fun addTask(task: Task)
    @Query("SELECT * FROM Task") suspend fun getTasks(): List<Task>
    @Delete suspend fun deleteTask(task: Task)
    @Update suspend fun updateTask(task: Task)
}

// Base de données Room pour les tâches
@Database(entities = [Task::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    // Singleton pour la base de données
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "task_database").build().also { INSTANCE = it }
        }
    }
}

// Gestionnaire de tâches pour effectuer des opérations de base de données
class TaskManager(private val context: Context) {
    private val taskDao: TaskDao = AppDatabase.getDatabase(context).taskDao()
    suspend fun addTask(task: Task) = taskDao.addTask(task)
    suspend fun getTasks(): List<Task> = taskDao.getTasks()
    suspend fun deleteTask(task: Task) = taskDao.deleteTask(task)
    suspend fun updateTask(task: Task) = taskDao.updateTask(task)
}

// Convertisseurs pour les énumérations utilisées dans Room
class Converters {
    @TypeConverter fun fromTaskType(value: TaskType): String = value.name
    @TypeConverter fun toTaskType(value: String): TaskType = enumValueOf(value)
    @TypeConverter fun fromTimeCategory(value: TimeCategory): String = value.name
    @TypeConverter fun toTimeCategory(value: String): TimeCategory = enumValueOf(value)
}

