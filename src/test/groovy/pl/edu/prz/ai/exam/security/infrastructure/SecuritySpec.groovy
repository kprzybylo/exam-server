package pl.edu.prz.ai.exam.security.infrastructure

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Ignore
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class SecuritySpec extends Specification {
    @Autowired
    private MockMvc mockMvc

    @Ignore
    def "successful login should respond with JWT token"() {
        expect: "When correct credentials are provided then response status is 200 and contains JWT"
        mockMvc.perform(
                post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "username": "jkowalski@test.pl",
                        "password": "jkowalski"
                    }
                """)
        ).andExpect(status().isOk())
        .andReturn()
        .response
        .contentAsString.contains("token")
    }
}
