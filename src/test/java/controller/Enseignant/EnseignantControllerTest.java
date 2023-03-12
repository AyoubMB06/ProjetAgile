package controller.Enseignant;

import backend.dosiApplication;
import controller.Auth.AuthenticationControllerTest;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static oracle.net.aso.b.i;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import in all your tests


//@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = dosiApplication.class)
@AutoConfigureMockMvc
//@Import(AuthenticationControllerTest.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class EnseignantControllerTest {
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
        // Do any cleanup here
    }
    @Test
    // test that user can get // enseignant/enseignants
    @Order(1)
    public void AdmCanGetEnseignants()  throws Exception
    {

        mockMvc.perform(get("/enseignants").header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'total' and 'data' key
                .andExpect(jsonPath("$.total").exists())
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    @Order(2)
    public void EnsCanGetHisInfo()  throws Exception
    {

        Integer noEnseignant = 1;
        mockMvc.perform(get("/enseignants/" + noEnseignant).header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'total' and 'data' key
                .andExpect(jsonPath("$.no_enseignant").value(1))
              .andExpect(jsonPath("$.email_perso").exists());
    }
    @Test
    @Order(3)
    public void EnsCanGetOtherEnsWithSamePromo()  throws Exception
    {
     //   authTest.userCanLoginAsEns();
        Integer noEnseignant = 2;
        mockMvc.perform(get("/enseignants/" + noEnseignant).header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'total' and 'data' key
                .andExpect(jsonPath("$.no_enseignant").value(2))
                .andExpect(jsonPath("$.email_ubo").exists());
    }
    // Test ens can't get other ens info
    @Test
    @Order(4)
    public void EnsCanNotGetOtherEnsInfoNotSamePromo()  throws Exception
    {
     //   authTest.userCanLoginAsEns();
        Integer noEnseignant = 3;
        mockMvc.perform(get("/enseignants/" + noEnseignant).header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isForbidden());
    }

    // Test to create a new enseignant
    @Test
    @Order(5)
    public void AdmCanCreateNewEns()  throws Exception
    {
        String json = "{\n" +
                "  \"no_enseignant\": 0,\n" +
                "  \"type\": \"FR\",\n" +
                "  \"sexe\": \"M\",\n" +
                "  \"nom\": \"YN\",\n" +
                "  \"prenom\": \"MN\",\n" +
                "  \"adresse\": \"BR AD 1\",\n" +
                "  \"code_postal\": \"29200\",\n" +
                "  \"ville\": \"Brest\",\n" +
                "  \"pays\": \"FR\",\n" +
                "  \"mobile\": \"07.18.19.18.17\",\n" +
                "  \"telephone\": \"06.18.18.18\",\n" +
                "  \"email_ubo\": \"ubo@email.com\",\n" +
                "  \"email_perso\": \"perso@email.com\"\n" +

                "}";
        mockMvc.perform(post("/enseignants").header("Authorization", "Bearer " + admin_token).contentType(MediaType.APPLICATION_JSON).content(json))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'total' and 'data' key
                .andExpect(jsonPath("$.no_enseignant").exists())
                .andExpect(jsonPath("$.nom").exists());
    }
    // test to delete an enseignant
    @Test
    @Transactional
    @Rollback
    @Order(7)
    public void AdmCanDeleteEns()  throws Exception
    {
        Integer noEnseignant = 1000;
        mockMvc.perform(delete("/enseignants/" + noEnseignant).header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'total' and 'data' key
                .andExpect(jsonPath("$.no_enseignant").value(1000));
    }

    // test to update an enseignant
    @Test
    @Transactional
    @Rollback
    @Order(6)
    public void AdmCanUpdateEns()  throws Exception {
        String json = "{\n" +
                "  \"no_enseignant\": 1000,\n" +
                "  \"type\": \"MCF\",\n" +
                "  \"sexe\": \"H\",\n" +
                "  \"nom\": \"YN\",\n" +
                "  \"prenom\": \"MN\",\n" +
                "  \"adresse\": \"BR\",\n" +
                "  \"code_postal\": \"29200\",\n" +
                "  \"ville\": \"Brest\",\n" +
                "  \"pays\": \"FR\",\n" +
                "  \"mobile\": \"07.01.02.03.04\",\n" +
                "  \"telephone\": \"06.01.01.01.01\",\n" +
                "  \"email_ubo\": \"lo@email.com\",\n" +
                "  \"email_perso\": \"perso@email.com\"\n" +

                "}";
        Integer noEnseignant = 1000;
        mockMvc.perform(patch("/enseignants/" + noEnseignant).header("Authorization", "Bearer " + admin_token).content(json).contentType(MediaType.APPLICATION_JSON))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'total' and 'data' key
                .andExpect(jsonPath("$.no_enseignant").value(1000));

    }
}
