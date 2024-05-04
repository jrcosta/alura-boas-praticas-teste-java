package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class AbrigoControllerTest {

    @MockBean
    private AbrigoService abrigoService;
    @MockBean
    private PetService petService;
    @Mock
    private Abrigo abrigo;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveDevolver200QuandoListarAbrigos() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                get("/abrigos")).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveDevolver200QuandoCadastrarAbrigo() throws Exception {

        String json = """
                {
                    "nome": "Abrigo 1",
                    "telefone": "(11)1234-5678",
                    "email": "email@email.com"
                }
                """;

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();

        Assertions.assertEquals(200, response.getStatus());
    }

    @Test
    void deveDevolver400QuandoCadastrarAbrigoComDadosInvalidos() throws Exception {

        String json = "{}";

        var response = mockMvc.perform(
                post("/abrigos")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)

        ).andReturn().getResponse();

        Assertions.assertEquals(400, response.getStatus());
    }

    @Test
    void deveDevolver200QuandoListarPetsDoAbrigo() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(
                get("/abrigos/1/pets")).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

}