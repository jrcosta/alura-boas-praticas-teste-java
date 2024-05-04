package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoSolicitacaoAdocao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdocaoServiceTest {

    @InjectMocks
    private AdocaoService service;
    @Mock
    private AdocaoRepository repository;
    @Mock
    private PetRepository petRepository;
    @Mock
    private TutorRepository tutorRepository;
    @Mock
    private EmailService emailService;
    @Spy
    private List<ValidacaoSolicitacaoAdocao> validacoes = new ArrayList<>();
    @Mock
    private ValidacaoSolicitacaoAdocao validador1;
    @Mock
    private ValidacaoSolicitacaoAdocao validador2;
    @Mock
    private Pet pet;
    @Mock
    private Tutor tutor;
    @Mock
    private Abrigo abrigo;

    private SolicitacaoAdocaoDto dto;

    @Captor
    private ArgumentCaptor<Adocao> adocaoCaptor;

    @Test
    void deveSalvarAdocaoSolicitar(){

        this.dto = new SolicitacaoAdocaoDto(1L, 1L, "motivo");

        given(petRepository.getReferenceById(any())).willReturn(pet);
        given(tutorRepository.getReferenceById(any())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);


        service.solicitar(dto);

        then(repository).should().save(adocaoCaptor.capture());
        Adocao adocaoSalva = adocaoCaptor.getValue();

        assertEquals(adocaoSalva.getPet(), pet);
        assertEquals(adocaoSalva.getTutor(), tutor);
        assertEquals(adocaoSalva.getMotivo(), dto.motivo());
    }

    @Test
    void deveChamarValidadoresAdocao(){

        this.dto = new SolicitacaoAdocaoDto(1L, 1L, "motivo");

        given(petRepository.getReferenceById(any())).willReturn(pet);
        given(tutorRepository.getReferenceById(any())).willReturn(tutor);
        given(pet.getAbrigo()).willReturn(abrigo);

        validacoes.add(validador1);
        validacoes.add(validador2);

        service.solicitar(dto);

        verify(validador1).validar(dto);
        verify(validador2).validar(dto);
    }

}