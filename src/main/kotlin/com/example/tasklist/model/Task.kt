import java.time.LocalDate
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

// Аннотация @Entity указывает, что класс является сущностью, которую можно сохранить в базе данных
@Entity
data class Task(
    // Аннотация @Id указывает на поле, которое является идентификатором сущности
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // Идентификатор задачи (автоматически генерируется)

    var title: String, // Заголовок задачи
    var description: String?, // Описание задачи (может быть null)
    var dueDate: LocalDate, // Дата выполнения задачи
    var completed: Boolean = false // Флаг выполнения задачи (по умолчанию false)
)
