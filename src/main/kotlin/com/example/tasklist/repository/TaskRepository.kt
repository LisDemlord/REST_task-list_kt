import org.springframework.data.repository.CrudRepository
import java.time.LocalDate

// Интерфейс расширяет CrudRepository, предоставляя основные операции CRUD (Create, Read, Update, Delete) для сущности
interface TaskRepository : CrudRepository<Task, Long> {

    // Дополнительный метод для поиска задач по дате выполнения и статусу выполнения
    fun findByDueDateBetweenAndCompleted(dueDateStart: LocalDate, dueDateEnd: LocalDate, completed: Boolean): List<Task>
}
