package controller.Auth;

import backend.dosiApplication;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = dosiApplication.class)
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public  String token_response;

    public AuthenticationControllerTest() {
    }



    @Test
   @Order(1)
    // test that user cannot logout without a token
    public void userCannotLogoutWithoutToken() throws Exception {
        // send get to /auth/deconnexion
        mockMvc.perform(get("/auth/deconnexion"))
                // assert response status
                .andExpect(status().isUnauthorized())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON+";charset=UTF-8"))
                // assert response body contains 'message/error' key
                .andExpect(jsonPath("$.message").exists());
    }


    @Test
    @Order(2)
    // test that user cannot login with invalid credentials
    public void userCannotLoginWithInvalidCredentials() throws Exception {
        // set up request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("utilisateur", "younesse");
        requestBody.put("mot_de_passe", "mon_mdp");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        // perform HTTP POST request with JSON body
        mockMvc.perform(post("/auth/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                // assert response status
                .andExpect(status().isUnauthorized())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'message/error' key
                .andExpect(jsonPath("$.message").exists())
                .andReturn();
    }

    @Test
    @Order(3)
    // test that user can logout /auth/deconnexion
    public void userCanLogoutAsAdm() throws Exception {
        // send get to /auth/deconnexion
        token_response = null;
        assertTimeoutPreemptively(Duration.ofSeconds(5), this::userCanLoginAsAdmin);
        System.out.println(token_response);

        mockMvc.perform(get("/auth/deconnexion").header("Authorization", "Bearer " + token_response))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'message' key
                .andExpect(jsonPath("$.message").exists());
        token_response = null;
    }

    @Test
    @Order(4)
    public void userCanLogoutAsEns() throws Exception {
        // send get to /auth/deconnexion

        token_response = null;

        userCanLoginAsEns();
        System.out.println("|"+token_response+"|" );
        mockMvc.perform(get("/auth/deconnexion").header("Authorization", "Bearer " + token_response))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'message' key
                .andExpect(jsonPath("$.message").exists());
        token_response = null;
    }

    @Test
   @Order(5)
    public void userCanLoginAsAdmin() throws Exception {
        // set up request body
      //  token_response = null;

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("utilisateur", "Administrateur");
        requestBody.put("mot_de_passe", "dosi");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        // perform HTTP POST request with JSON body
        MvcResult result = mockMvc.perform(post("/auth/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'token' key
                .andExpect(jsonPath("$.token").exists())
                .andReturn();
        // extract token from response
        String response = result.getResponse().getContentAsString();
        token_response = extractToken(response);

        // assert that token is not null
        assertNotNull(token_response);
    }


    @Test
    @Order(6)
    public void userCanLoginAsEns() throws Exception {
        token_response = null;

        // set up request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("utilisateur", "philippe.saliou@univ-brest.fr");
        requestBody.put("mot_de_passe", "dosi");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = objectMapper.writeValueAsString(requestBody);

        // perform HTTP POST request with JSON body
        MvcResult result = mockMvc.perform(post("/auth/connexion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                // assert response status
                .andExpect(status().isOk())
                // assert response content type
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                // assert response body contains 'token' key
                .andExpect(jsonPath("$.token").exists())
                .andReturn();

        // extract token from response
        String response = result.getResponse().getContentAsString();
        token_response = extractToken(response);

        // assert that token is not null
        assertNotNull(token_response);
    }


    public void setMockMvc(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public static String extractToken(String response) {
        // extract token from response body
        // this method will depend on your implementation
        // in this example, the token is returned as a JSON object
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get("token").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}