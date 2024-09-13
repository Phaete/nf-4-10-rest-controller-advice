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
public class CarControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getCarBrandTest_shouldReturnCarBrand() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/porsche"))
                .andExpect(status().isOk())
                .andExpect(content().string("porsche"));
    }

    @Test
    void getCarBrandTest_shouldThrowUp() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/volkswagen"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(
                        """
                            {"message":"Only 'porsche' allowed"}
                        """
                ));
    }

    @Test
    void getAllCars_shouldThrowNoMatterWhat() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(
                        """
                            {"message":"No Cars found"}
                        """
                ));
    }
}
