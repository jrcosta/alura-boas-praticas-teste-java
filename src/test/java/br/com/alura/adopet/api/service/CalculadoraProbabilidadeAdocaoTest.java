package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class CalculadoraProbabilidadeAdocaoTest {

    @Test
    @DisplayName("Probabilidade alta para gatos com 4 anos e peso 4kg.")
    void deve_Retornar_Prob_Alta_Gato(){
        //idade 4 anos e 4kg - ALTA

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
            "Abrigo Feliz",
            "979488484848",
            "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                4,
                "Cinza",
                4.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculador = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculador.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidade);
    }

    @Test
    @DisplayName("Probabilidade média para gatos com 15 anos e peso 4kg.")
    void deve_Retornar_Prob_Media_Gato(){
        //idade 15 anos e 4kg - MEDIA

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo Feliz",
                "979488484848",
                "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                15,
                "Cinza",
                4.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculador = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculador.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidade);
    }

    @Test
    @DisplayName("Probabilidade baixa para gatos com 15 anos e peso 11kg.")
    void deve_Retornar_Prob_Baixa_Gato(){
        //idade 15 anos e 11kg - ALTA

        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto(
                "Abrigo Feliz",
                "979488484848",
                "abrigofeliz@email.com.br"
        ));
        Pet pet = new Pet(new CadastroPetDto(
                TipoPet.GATO,
                "Miau",
                "Siames",
                15,
                "Cinza",
                11.0f
        ), abrigo);

        CalculadoraProbabilidadeAdocao calculador = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidade = calculador.calcular(pet);

        Assertions.assertEquals(ProbabilidadeAdocao.BAIXA, probabilidade);
    }
}