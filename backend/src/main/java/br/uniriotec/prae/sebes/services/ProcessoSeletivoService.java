package br.uniriotec.prae.sebes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.ProcessoSeletivoDTO;
import br.uniriotec.prae.sebes.entity.Bolsa;
import br.uniriotec.prae.sebes.entity.ProcessoSeletivo;
import br.uniriotec.prae.sebes.repository.BolsaRepository;
import br.uniriotec.prae.sebes.repository.ProcessoSeletivoRepository;

@Service
public class ProcessoSeletivoService {

    @Autowired
    private ProcessoSeletivoRepository processoRepository;

    @Autowired
    private BolsaRepository bolsaRepository;

    public ProcessoSeletivoDTO criar(ProcessoSeletivoDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Dados obrigatórios não informados.");

        if (dto.getIdBolsa() == null) {
            throw new IllegalArgumentException("O ID da bolsa deve ser informado.");
        }

        Optional<Bolsa> bolsaOpt = bolsaRepository.findById(dto.getIdBolsa());
        if (!bolsaOpt.isPresent()) {
            throw new RuntimeException("Bolsa informada não existe.");
        }

        ProcessoSeletivo processo = new ProcessoSeletivo();
        processo.setDataInicio(dto.getDataInicio());
        processo.setDataEncerramento(dto.getDataEncerramento());
        processo.setStatus(dto.getStatus());
        processo.setBolsa(bolsaOpt.get());

        ProcessoSeletivo salvo = processoRepository.save(processo);
        return entityToDTO(salvo);
    }

    public ProcessoSeletivoDTO buscarPorId(String id) {
        ProcessoSeletivo processo = processoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo seletivo não encontrado."));
        return entityToDTO(processo);
    }

    public List<ProcessoSeletivoDTO> buscarPorStatus(String status) {
        return processoRepository.findAllByStatus(status).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<ProcessoSeletivoDTO> buscarPorBolsa(Integer idBolsa) {
        return processoRepository.findAllByBolsa_Id(idBolsa).stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public List<ProcessoSeletivoDTO> listarTodos() {
        return processoRepository.findAll().stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public ProcessoSeletivoDTO atualizarParcial(String id, ProcessoSeletivoDTO dto) {
        ProcessoSeletivo processo = processoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo seletivo não encontrado."));

        if (dto.getDataInicio() != null) {
            processo.setDataInicio(dto.getDataInicio());
        }

        if (dto.getDataEncerramento() != null) {
            processo.setDataEncerramento(dto.getDataEncerramento());
        }

        if (dto.getStatus() != null) {
            processo.setStatus(dto.getStatus());
        }

        ProcessoSeletivo atualizado = processoRepository.save(processo);
        return entityToDTO(atualizado);
    }

    public void deletar(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID é obrigatório.");
        }

        if (!processoRepository.existsById(id)) {
            throw new RuntimeException("Processo seletivo não encontrado.");
        }

        processoRepository.deleteById(id);
    }

    private ProcessoSeletivoDTO entityToDTO(ProcessoSeletivo processo) {
        ProcessoSeletivoDTO dto = new ProcessoSeletivoDTO();
        dto.setDataInicio(processo.getDataInicio());
        dto.setDataEncerramento(processo.getDataEncerramento());
        dto.setStatus(processo.getStatus());
        dto.setIdBolsa(processo.getBolsa().getId());
        return dto;
    }
}
