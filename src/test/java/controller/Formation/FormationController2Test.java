package controller.Formation;


import backend.dosiApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.Auth.AuthenticationControllerTest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = dosiApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FormationController2Test {
    @Autowired
    private MockMvc mockMvc;

    private static AuthenticationControllerTest authTest;
    static String admin_token;
    static String ens_token;
    static boolean get_tokens_only_once = true;

    @AfterAll
    @DirtiesContext
    public static void cleanup() {
        // Do any cleanup here
    }
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

    @Test
    @Order(1)
    // test to get all formations by adm from endpoint /formations
    public void AdmCanGetFormations()  throws Exception
    {
        System.out.println("token: " + admin_token);
        mockMvc.perform(get("/formations").header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test to get formations by ens with post to /formations
    public void EnsCanGetFormations()  throws Exception
    {
        System.out.println("token: " + ens_token);
        mockMvc.perform(get("/formations").header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }



    @Test
    @Order(1)
    // Test to create formation by adm with post to /formations
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

        System.out.println("token: " + admin_token);
        mockMvc.perform(post("/formations").header("Authorization", "Bearer " + admin_token).contentType(MediaType.APPLICATION_JSON).content(formation))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test cannot create formation by ens with post to /formations
    public void EnsCannotCreateFormation()  throws Exception
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

        System.out.println("token: " + ens_token);
        mockMvc.perform(post("/formations").header("Authorization", "Bearer " + ens_token).contentType(MediaType.APPLICATION_JSON).content(formation))
                // assert response status
                .andExpect(status().isForbidden())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(1)
    // Test to get ECs by adm with get to /formations/{code_formation}/ue/{code_ue}/ec
    public void AdmCanGetECs()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        System.out.println("token: " + admin_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test to get ECs Ignore Case by adm with get to /formations/{code_formation}/ue/{code_ue}/ec
    public void AdmCanGetECsIgnoreCase()  throws Exception
    {
        String code_formation = "M2doSi";
        String code_ue = "Pco";
        System.out.println("token: " + admin_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test to get ECs by adm with get to /formations/{code_formation}/ue/{code_ue}/ec
    public void AdmCannotGetECsNotFound()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "TESTX";
        System.out.println("token: " + admin_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(1)
    // Test to get ECs by ens with get to /formations/{code_formation}/ue/{code_ue}/ec
    public void EnsCanGetECs()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        System.out.println("token: " + ens_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test to get ECs by adm with get to /formations/{code_formation}/ue/{code_ue}/ec
    public void EnsCannotGetECsNotFound()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "TESTX";
        System.out.println("token: " + ens_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(1)
    // Test to create EC by adm with post to /formations/{code_formation}/ue/{code_ue}/ec
    public void AdmCanCreateEC()  throws Exception
    {

        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code_formation", "M2DOSI");
        requestBody.put("code_ue", "PCO");
        requestBody.put("code_ec", "TEST111");
        requestBody.put("no_enseignant", 2);
        requestBody.put("designation", "TEST1");
        requestBody.put("description", "TEST2");
        requestBody.put("nbh_cm", 2);
        requestBody.put("nbh_td", 2);
        requestBody.put("nbh_tp", 2);

        ObjectMapper objectMapper = new ObjectMapper();
        String ec = objectMapper.writeValueAsString(requestBody);

        System.out.println("token: " + admin_token);
        mockMvc.perform(post("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + admin_token).contentType(MediaType.APPLICATION_JSON).content(ec))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test cannot create EC not found by adm with post to /formations/{code_formation}/ue/{code_ue}/ec
    public void AdmCannotCreateECNotFound()  throws Exception
    {

        String code_formation = "M2DOSI55";
        String code_ue = "TESTX";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code_formation", "M2DOSI2222");
        requestBody.put("code_ue", "PCO");
        requestBody.put("code_ec", "test123");
        requestBody.put("no_enseignant", 2);
        requestBody.put("designation", "TEST1");
        requestBody.put("description", "TEST2");
        requestBody.put("nbh_cm", 2);
        requestBody.put("nbh_td", 2);
        requestBody.put("nbh_tp", 2);

        ObjectMapper objectMapper = new ObjectMapper();
        String ec = objectMapper.writeValueAsString(requestBody);

        System.out.println("token: " + admin_token);
        mockMvc.perform(post("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + admin_token).contentType(MediaType.APPLICATION_JSON).content(ec))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(1)
    // Test cannot create EC by ens with post to /formations/{code_formation}/ue/{code_ue}/ec
    public void EnsCannotCreateEC() throws Exception
    {

        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code_formation", "M2DOSI");
        requestBody.put("code_ue", "PCO");
        requestBody.put("code_ec", "test");
        requestBody.put("no_enseignant", 2);
        requestBody.put("designation", "TEST1");
        requestBody.put("description", "TEST2");
        requestBody.put("nbh_cm", 2);
        requestBody.put("nbh_td", 2);
        requestBody.put("nbh_tp", 2);

        ObjectMapper objectMapper = new ObjectMapper();
        String ec = objectMapper.writeValueAsString(requestBody);

        System.out.println("token: " + ens_token);
        mockMvc.perform(post("/formations/"+code_formation+"/ue/"+code_ue+"/ec").header("Authorization", "Bearer " + ens_token).contentType(MediaType.APPLICATION_JSON).content(ec))
                // assert response status
                .andExpect(status().isForbidden())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(1)
    // Test to get EC by adm with get to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void AdmCanGetECByCode()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "SFM";
        System.out.println("token: " + admin_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @Order(1)
    // Test to get EC Ignore Case by adm with get to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void AdmCanGetECByCodeIgnoreCase()  throws Exception
    {
        String code_formation = "M2DOsi";
        String code_ue = "pcO";
        String code_ec = "sFm";
        System.out.println("token: " + admin_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test cannot find EC by adm with get to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void AdmCannotGetECByCodeNotFound()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "TESTX";
        System.out.println("token: " + admin_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec+"1").header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(1)
    // Test to get EC by ens with get to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void EnsCanGetECByCode()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "SFM";
        System.out.println("token: " + ens_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(1)
    // Test cannot find EC by adm with get to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void EnsCannotGetECByCodeNotFound()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "TESTX";
        System.out.println("token: " + ens_token);
        mockMvc.perform(get("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec+"1").header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(2)
    // Test to update EC by adm with put to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void AdmCanUpdateEC()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "J2EE";
        String code_ec = "CPR";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code_formation", "M2DOSI");
        requestBody.put("code_ue", "J2EE");
        requestBody.put("code_ec", "CPR");
        requestBody.put("no_enseignant", 2);
        requestBody.put("designation", "fzfzfz");
        requestBody.put("description", "TESTzfz2");
        requestBody.put("nbh_cm", 2);
        requestBody.put("nbh_td", 2);
        requestBody.put("nbh_tp", 2);

        ObjectMapper objectMapper = new ObjectMapper();
        String ec = objectMapper.writeValueAsString(requestBody);

        System.out.println("token: " + admin_token);
        mockMvc.perform(patch("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + admin_token).contentType(MediaType.APPLICATION_JSON).content(ec))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(2)
    // Test cannot update EC not found by adm with put to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void AdmCannotUpdateECNotFound()  throws Exception
    {
        String code_formation = "M2DOSI55";
        String code_ue = "PCO";
        String code_ec = "TEESTXxx";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code_formation", "M2DOSI55");
        requestBody.put("code_ue", "PCO");
        requestBody.put("code_ec", "TEESTXxx");
        requestBody.put("no_enseignant", 2);
        requestBody.put("designation", "TEST1");
        requestBody.put("description", "TEST2");
        requestBody.put("nbh_cm", 2);
        requestBody.put("nbh_td", 2);
        requestBody.put("nbh_tp", 2);

        ObjectMapper objectMapper = new ObjectMapper();
        String ec = objectMapper.writeValueAsString(requestBody);

        System.out.println("token: " + admin_token);
        mockMvc.perform(patch("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + admin_token).contentType(MediaType.APPLICATION_JSON).content(ec))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }



    @Test
    @Order(3)
    // Test to delete EC by adm with delete to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void AdmCanDeleteEC()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "SFM";

        System.out.println("token: " + admin_token);
        mockMvc.perform(delete("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @Order(3)
    // Test cannot delete EC not found by adm with delete to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void AdmCannotDeleteECNotFound()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "TEESTX";

        System.out.println("token: " + admin_token);
        mockMvc.perform(delete("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + admin_token))
                // assert response status
                .andExpect(status().isNotFound())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }



    @Test
    @Order(3)
    // Test cannot delete EC by ens with delete to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void EnsCannotDeleteEC()  throws Exception {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "ERP";

        System.out.println("token: " + ens_token);
        mockMvc.perform(delete("/formations/" + code_formation + "/ue/" + code_ue + "/ec/" + code_ec).header("Authorization", "Bearer " + ens_token))
                // assert response status
                .andExpect(status().isForbidden())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    @Order(2)
    // Test cannot update EC by ens with put to /formations/{code_formation}/ue/{code_ue}/ec/{code_ec}
    public void EnsCannotUpdateEC()  throws Exception
    {
        String code_formation = "M2DOSI";
        String code_ue = "PCO";
        String code_ec = "SFM";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("code_formation", "M2DOSI");
        requestBody.put("code_ue", "PCO");
        requestBody.put("code_ec", "SFM");
        requestBody.put("no_enseignant", 2);
        requestBody.put("designation", "TEST1");
        requestBody.put("description", "TEST2");
        requestBody.put("nbh_cm", 2);
        requestBody.put("nbh_td", 2);
        requestBody.put("nbh_tp", 2);

        ObjectMapper objectMapper = new ObjectMapper();
        String ec = objectMapper.writeValueAsString(requestBody);

        System.out.println("token: " + ens_token);
        mockMvc.perform(patch("/formations/"+code_formation+"/ue/"+code_ue+"/ec/"+code_ec).header("Authorization", "Bearer " + ens_token).contentType(MediaType.APPLICATION_JSON).content(ec))
                // assert response status
                .andExpect(status().isForbidden())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // check if response body contains error message
                .andExpect(jsonPath("$.message").exists());
    }












}