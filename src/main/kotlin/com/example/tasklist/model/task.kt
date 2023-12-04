import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

// Аннотация @Entity указывает, что класс является сущностью, которую можно сохранить в базе данных
@Entity
data class Task(
    // Аннотация @Id указывает на поле, которое является идентификатором сущности
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null, // Идентификатор задачи (автоматически генерируется)

    val title: String, // Заголовок задачи
    val description: String?, // Описание задачи (может быть null)
    val dueDate: LocalDate, // Дата выполнения задачи
    val completed: Boolean = false // Флаг выполнения задачи (по умолчанию false)
)
