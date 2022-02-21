package demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
    runApplication<DemoApplication>(*args) {
        addInitializers()
    }
}

@RestController
class MessageController(private val messageService: MessageService) {
    @GetMapping
    fun findMessages(): List<MessageDto> = messageService.findMessages()

    @PostMapping
    fun addMessage(@RequestBody command: AddMessage) = messageService.addMessage(command)
}

@Service
class MessageService(private val repository: MessageRepository) {
    fun findMessages(): List<MessageDto> {
        return repository.findMessages()
            .map { it.toDto() }
    }

    fun addMessage(command: AddMessage) {
        repository.save(command.toEntity())
    }
}

interface MessageRepository: CrudRepository<Message, String> {
    @Query("SELECT * FROM messages")
    fun findMessages(): List<Message>
}

data class AddMessage(val text: String)
data class MessageDto(val id: String, val text: String)

// region Extensions
fun AddMessage.toEntity(): Message = Message(null, this.text)
fun Message.toDto(): MessageDto = MessageDto(this.id!!, this.text)
// endregion

@Table("MESSAGES")
data class Message(@Id val id: String?, val text: String)