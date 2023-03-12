package controller.Promotion;

import backend.dosiApplication;
import controller.Auth.AuthenticationControllerTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = dosiApplication.class)
@AutoConfigureMockMvc
public class PromotionControllerTest {
    @Autowired
    private  MockMvc mockMvc;

    private static AuthenticationControllerTest authTest;

    static String admin_token;
    static String ens_token;
    static boolean get_tokens_only_once = true;

    @BeforeEach
    public void init() throws Exception {
        if(get_tokens_only_once) {
            authTest = new AuthenticationControllerTest();
            authTest.setMockMvc(mockMvc);
            authTest.userCanLoginAsAdmin();
            admin_token = authTest.token_response;
            authTest.userCanLoginAsEns();
            ens_token = authTest.token_response;
            get_tokens_only_once = false;
        }
    }
    @AfterAll
    @DirtiesContext
    public static void cleanup() {

    }

    //  test admin can get all promotions
    @Test
    @Order(1)
    public void adminCanGetAllPromotions() throws Exception {
        mockMvc.perform(get("/promotions")
                .header("Authorization", "Bearer " + admin_token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$", hasSize(2)))
                // expect output has data nad total
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.total").exists());
    }
    // test to  create a promotion
    @Test
    @Order(2)
    public void adminCanCreatePromotion() throws Exception {
        String json = "{\n" +
                "    \"code_formation\": \"M2DOSI\",\n" +
                "    \"annee_universitaire\": \"2020-2021\",\n" +
                "    \"no_enseignant\": 1,\n" +
                "    \"sigle_promotion\": \"M\",\n" +
                "    \"nb_max_etudiant\": 40,\n" +
                "    \"date_reponse_lp\": \"2021-07-31\",\n" +
                "    \"date_reponse_lalp\": \"2021-07-31\",\n" +
                "    \"date_rentree\": \"2021-07-31\",\n" +
                "    \"lieu_rentree\": \"Brest\",\n" +
                "    \"processus_stage\": \"RECH\",\n" +

                "    \"commentaire\":  \"rien\"\n" +

                "}";
        mockMvc.perform(post("/promotions")
                .header("Authorization", "Bearer " + admin_token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code_formation").value("M2DOSI"))
                .andExpect(jsonPath("$.annee_universitaire").value("2020-2021"));
    }
    // test to update promo
    @Test
    @Order(3)
    public void adminCanUpdatePromotion() throws Exception {
        String json = "{\n" +
                "    \"code_formation\": \"M2DOSI\",\n" +
                "    \"annee_universitaire\": \"2020-2021\",\n" +
                "    \"no_enseignant\": 1,\n" +
                "    \"sigle_promotion\": \"M\",\n" +
                "    \"nb_max_etudiant\": 40,\n" +
                "    \"date_reponse_lp\": \"2021-07-31\",\n" +
                "    \"date_reponse_lalp\": \"2021-07-31\",\n" +
                "    \"date_rentree\": \"2021-07-31\",\n" +
                "    \"lieu_rentree\": \"Re\",\n" +
                "    \"processus_stage\": \"RECH\",\n" +

                "    \"commentaire\":  \"rien\"\n" +

                "}";
        mockMvc.perform(patch("/promotions")
                .header("Authorization", "Bearer " + admin_token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.code_formation").value("M2DOSI"))
                .andExpect(jsonPath("$.annee_universitaire").value("2020-2021"))
                 .andExpect(jsonPath("$.lieu_rentree").value("Re"));
    }

}
