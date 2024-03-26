package com.example.Projet_Shinka


import android.content.Context
import com.google.gson.Gson
import java.util.*


@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var title: String,
    var type: TaskType,
    var timeCategory: TimeCategory
)

@Dao
interface TaskDao {
    @Insert
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM Task")
    suspend fun getTasks(): List<Task>

    // Autres méthodes CRUD
}


@Database(entities = [Task::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        // Singleton pour empêcher plusieurs instances de la base de données
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

class TaskManager(context: Context) {
    private val taskDao: TaskDao = AppDatabase.getDatabase(context).taskDao()

    suspend fun addTask(task: Task) {
        taskDao.addTask(task)
    }

    suspend fun getTasks(): List<Task> {
        return taskDao.getTasks()
    }

    // Implémentez d'autres méthodes pour mettre à jour et supprimer les tâches
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



/*
enum class TaskType {
    SPORT, KNOWLEDGE, WELLBEING, HELP
}

enum class TimeCategory {
    DAILY, WEEKLY, EXCEPTIONAL
}

class TaskManager(private val context: Context) {
    private val tasks = mutableListOf<Task>()

    fun addTask(title: String, type: TaskType, timeCategory: TimeCategory) {
        tasks.add(Task(title = title, type = type, timeCategory = timeCategory))
    }

    fun getTasks(): List<Task> = tasks

    fun getTasksByType(type: TaskType): List<Task> = tasks.filter { it.type == type }

    fun getTasksByTimeCategory(timeCategory: TimeCategory): List<Task> = tasks.filter { it.timeCategory == timeCategory }

    fun removeTask(taskId: UUID) {
        tasks.removeAll { it.id == taskId }
    }

    fun updateTask(taskId: UUID, newTitle: String, newType: TaskType, newTimeCategory: TimeCategory) {
        tasks.find { it.id == taskId }?.apply {
            title = newTitle
            type = newType
            timeCategory = newTimeCategory
        }
    }

    fun saveTasks() {
        val sharedPreferences = context.getSharedPreferences("TaskManager", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val tasksJson = gson.toJson(tasks)
        editor.putString("tasks", tasksJson)
        editor.apply()
    }

    fun loadTasks() {
        val sharedPreferences = context.getSharedPreferences("TaskManager", Context.MODE_PRIVATE)
        val tasksJson = sharedPreferences.getString("tasks", null)
        tasksJson?.let {
            val gson = Gson()
            val type = object : com.google.gson.reflect.TypeToken<List<Task>>() {}.type
            val loadedTasks: List<Task> = gson.fromJson(/* json = */ it, /* typeOfT = */ type)
            tasks.clear()
            tasks.addAll(loadedTasks)
        }
    }
}*/

