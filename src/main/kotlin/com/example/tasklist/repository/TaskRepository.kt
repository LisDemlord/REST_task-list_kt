// Импорты необходимых библиотек
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

// Интерфейс расширяет JpaRepository, предоставляя операции CRUD и дополнительные возможности
interface TaskRepository : JpaRepository<Task, Long> {

    // Дополнительный метод для поиска задач по дате выполнения и статусу выполнения
    fun findByDueDateBetweenAndCompleted(dueDateStart: LocalDate, dueDateEnd: LocalDate, completed: Boolean): List<Task>

    // Метод для поиска задач по статусу выполнения
    fun findByCompleted(completed: Boolean): List<Task>
}
