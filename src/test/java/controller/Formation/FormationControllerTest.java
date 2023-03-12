package controller.Formation;

import backend.dosiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.Auth.AuthenticationControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = dosiApplication.class)
@AutoConfigureMockMvc
public class FormationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private static AuthenticationControllerTest authTest;
    @BeforeEach
    public void init() {
        authTest = new AuthenticationControllerTest();
        authTest.setMockMvc(mockMvc);
    }

    @Test
    // test to get all formations from endoint /formations
    public void AdmCanGetFormations()  throws Exception
    {
        authTest.userCanLoginAsAdmin();
        System.out.println("token: " + authTest.token_response);
        mockMvc.perform(get("/formations").header("Authorization", "Bearer " + authTest.token_response))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    // get formation from /fromations with cde_formation as pathparam
    @Test
    public void AdmCanGetFormation()  throws Exception
    {
        String code_formation = "M2DOSI";
        authTest.userCanLoginAsAdmin();
        System.out.println("token: " + authTest.token_response);
        mockMvc.perform(get("/formations/" + code_formation).header("Authorization", "Bearer " + authTest.token_response))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void AdmCanGetFormationIgnoreCase()  throws Exception
    {
        String code_formation = "m2DoSI";
        authTest.userCanLoginAsAdmin();
        System.out.println("token: " + authTest.token_response);
        mockMvc.perform(get("/formations/" + code_formation).header("Authorization", "Bearer " + authTest.token_response))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void userCannotFindFormationNotFound()  throws Exception
    {
        String code_formation = "SH520";
        authTest.userCanLoginAsAdmin();
        System.out.println("token: " + authTest.token_response);
        mockMvc.perform(get("/formations/" + code_formation).header("Authorization", "Bearer " + authTest.token_response))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    // Test to create formation with post to /formations
    public void AdmCanCreateFormation()  throws Exception
    {


        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code_formation", "M2TESTX");
        requestBody.put("nom_formation", "M2 TEST");
        requestBody.put("diplome", "M2");
        requestBody.put("no_annee", 2);
        requestBody.put("double_diplome", "O");
        requestBody.put("debut_accreditation", "2023-02-02");
        requestBody.put("fin_accreditation", "2023-02-02");

        ObjectMapper objectMapper = new ObjectMapper();
        String formation = objectMapper.writeValueAsString(requestBody);

        authTest.userCanLoginAsAdmin();
        System.out.println("token: " + authTest.token_response);
        mockMvc.perform(post("/formations").header("Authorization", "Bearer " + authTest.token_response).contentType(MediaType.APPLICATION_JSON).content(formation))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
                // assert response body contains 'total' and 'data' key
               /* .andExpect(jsonPath("$.codeFormation").exists())
                .andExpect(jsonPath("$.intituleFormation").exists())
                .andExpect(jsonPath("$.diplome").exists())
                .andExpect(jsonPath("$.doubleDiplome").exists())
                .andExpect(jsonPath("$.nature").exists())
                .andExpect(jsonPath("$.parcours").exists())
                .andExpect(jsonPath("$.departement").exists())
                .andExpect(jsonPath("$.responsable").exists());

                */
    }

}
