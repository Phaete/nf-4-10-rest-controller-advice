package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAnimalSpecies_shouldReturnDog() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/dog"))
                .andExpect(status().isOk())
                .andExpect(content().string("dog"));
    }

    @Test
    void getAnimalSpecies_throws() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/cat"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        """
                            {"message":"Only 'dog' is allowed"}
                        """
                ));
    }

    @Test
    void getAllAnimals_shouldThrowNoMatterWhat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(
                        """
                            {"message":"No Animals found"}
                        """
                ));
    }
}
