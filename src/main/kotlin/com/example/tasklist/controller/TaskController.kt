import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

// Аннотация @RestController говорит Spring, что это контроллер, обрабатывающий HTTP-запросы
@RestController
@RequestMapping("/api/tasks") // Базовый путь для всех эндпоинтов в этом контроллере
class TaskController(private val taskService: TaskService) {

    // Эндпоинт для получения списка задач с учетом фильтров
    @GetMapping
    fun getTasks(
        @RequestParam(required = false) today: Boolean?,
        @RequestParam(required = false) week: Boolean?,
        @RequestParam(required = false) month: Boolean?,
        @RequestParam(required = false) completed: Boolean?
    ): ResponseEntity<List<Task>> {
        // Логика получения и фильтрации задач
        val filteredTasks = taskService.getFilteredTasks(today, week, month, completed)
        return ResponseEntity.ok(filteredTasks)
    }

    // Эндпоинт для создания новой задачи
    @PostMapping
    fun createTask(@RequestBody task: Task): ResponseEntity<Task> {
        // Логика создания задачи
        val createdTask = taskService.createTask(task)
        return ResponseEntity.ok(createdTask)
    }

    // Эндпоинт для обновления существующей задачи
    @PutMapping("/{taskId}")
    fun updateTask(@PathVariable taskId: Long, @RequestBody updatedTask: Task): ResponseEntity<Task> {
        // Логика обновления задачи
        val updated = taskService.updateTask(taskId, updatedTask)
        return ResponseEntity.ok(updated)
    }

    // Эндпоинт для установки/снятия метки выполнения задачи
    @PatchMapping("/{taskId}/complete")
    fun toggleCompletion(@PathVariable taskId: Long): ResponseEntity<Task> {
        // Логика установки/снятия метки выполнения
        val toggledTask = taskService.toggleCompletion(taskId)
        return ResponseEntity.ok(toggledTask)
    }

    // Эндпоинт для удаления задачи
    @DeleteMapping("/{taskId}")
    fun deleteTask(@PathVariable taskId: Long): ResponseEntity<Void> {
        // Логика удаления задачи
        taskService.deleteTask(taskId)
        return ResponseEntity.noContent().build()
    }
}
