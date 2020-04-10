package network.piction.example.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import network.piction.example.api.requests.CreateUserDto
import org.hamcrest.core.Is
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@WebAppConfiguration
@SpringBootTest(classes = [ApiApplication::class])
class ApiTest {
    @Autowired
    lateinit var wac: WebApplicationContext

    lateinit var mockMvc: MockMvc

    private val objectMapper = ObjectMapper().registerModule(KotlinModule())

    @Before
    fun setup() {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .alwaysDo<DefaultMockMvcBuilder>(MockMvcResultHandlers.print())
            .build()
    }

    @Test
    fun 유저_저장_테스트() {
        val request = CreateUserDto(
            "tester",
            "test@piction.network",
            "username",
            "password"
        )

        mockMvc.perform(post("/users")
            .content(objectMapper.writeValueAsBytes(request))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.wallet").isNotEmpty)
    }

    @Test
    fun 유저_조회_테스트() {
        mockMvc.perform(get("/users/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.username", Is.`is`("username")))
            .andExpect(jsonPath("$.wallet").isNotEmpty)
    }
}