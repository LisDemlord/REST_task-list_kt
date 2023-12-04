import org.springframework.stereotype.Service
import java.time.LocalDate

// Аннотация @Service говорит Spring, что это компонент сервиса
@Service
class TaskService(private val taskRepository: TaskRepository) {

    // Метод для получения отфильтрованного списка задач
    fun getFilteredTasks(today: Boolean?, week: Boolean?, month: Boolean?, completed: Boolean?): List<Task> {
        // Логика фильтрации задач
        val currentDate = LocalDate.now()

        return when {
            today == true -> taskRepository.findByDueDateBetweenAndCompleted(currentDate, currentDate, completed ?: false)
            week == true -> taskRepository.findByDueDateBetweenAndCompleted(currentDate, currentDate.plusDays(6), completed ?: false)
            month == true -> taskRepository.findByDueDateBetweenAndCompleted(currentDate, currentDate.plusMonths(1), completed ?: false)
            else -> taskRepository.findByCompleted(completed ?: false)
        }
    }

    // Метод для создания новой задачи
    fun createTask(task: Task): Task {
        // Логика создания задачи
        return taskRepository.save(task)
    }

    // Метод для обновления существующей задачи
    fun updateTask(taskId: Long, updatedTask: Task): Task {
        // Логика обновления задачи
        val existingTask = taskRepository.findById(taskId)
            .orElseThrow { RuntimeException("Task not found with id: $taskId") }

        existingTask.title = updatedTask.title
        existingTask.description = updatedTask.description
        existingTask.dueDate = updatedTask.dueDate

        return taskRepository.save(existingTask)
    }

    // Метод для установки/снятия метки выполнения задачи
    fun toggleCompletion(taskId: Long): Task {
        // Логика установки/снятия метки выполнения
        val task = taskRepository.findById(taskId)
            .orElseThrow { RuntimeException("Task not found with id: $taskId") }

        task.completed = !task.completed
        return taskRepository.save(task)
    }

    // Метод для удаления задачи
    fun deleteTask(taskId: Long) {
        // Логика удаления задачи
        taskRepository.deleteById(taskId)
    }
}
