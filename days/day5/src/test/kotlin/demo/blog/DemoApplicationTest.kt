package demo.blog

import com.ninjasquad.springmockk.MockkBean
import demo.MessageDto
import demo.MessageService
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import io.mockk.every
import io.mockk.verify
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest
internal class DemoApplicationTest(@Autowired val mockMvc: MockMvc){

 @MockBean
   private lateinit var service: MessageService


    @MockkBean
    private lateinit var serviceMockk: MessageService

    @Test
    fun `find messages with mockito`() {
        Mockito.`when`(service.findMessages()).thenReturn(listOf(MessageDto("1", "First"), MessageDto("2", "Second")))

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].id").value("1"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[0].text").value("First"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].id").value("2"))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.[1].text").value("Second"))

        Mockito.verify(service).findMessages()
    }

    @Test
    fun `find messages with mockk`() {
        every { serviceMockk.findMessages() } returns listOf(
            MessageDto("1", "First"),
            MessageDto("2", "Second")
        )

        mockMvc.get("/")
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect {
                jsonPath("\$.[0].id", "") { value("1") }
                jsonPath("\$.[0].text") { value("First") }
                jsonPath("\$.[1].id") { value("2") }
                jsonPath("\$.[1].text") { value("Second") }
            }

        verify(exactly = 1) { serviceMockk.findMessages() }
    }
}
