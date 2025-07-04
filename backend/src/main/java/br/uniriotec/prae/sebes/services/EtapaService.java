package br.uniriotec.prae.sebes.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.EtapaDTO;
import br.uniriotec.prae.sebes.entity.Etapa;
import br.uniriotec.prae.sebes.entity.ProcessoSeletivo;
import br.uniriotec.prae.sebes.repository.EtapaRepository;
import br.uniriotec.prae.sebes.repository.ProcessoSeletivoRepository;

@Service
public class EtapaService {

    @Autowired
    private EtapaRepository etapaRepository;

    @Autowired
    private ProcessoSeletivoRepository processoRepository;

    public EtapaDTO criar(EtapaDTO dto) {
        if (dto == null)
            throw new IllegalArgumentException("Dados obrigatórios não informados.");

        if (dto.getIdProcessoSeletivo() == null || dto.getIdProcessoSeletivo().isBlank()) {
            throw new IllegalArgumentException("ID do processo seletivo é obrigatório.");
        }

        Optional<ProcessoSeletivo> processoOpt = processoRepository.findById(dto.getIdProcessoSeletivo());
        if (!processoOpt.isPresent()) {
            throw new RuntimeException("Processo seletivo não encontrado.");
        }

        boolean tipoJaExiste = etapaRepository
                .findAllByProcessoSeletivoId(dto.getIdProcessoSeletivo())
                .stream()
                .anyMatch(e -> e.getTipoEtapa().equalsIgnoreCase(dto.getTipoEtapa()));

        if (tipoJaExiste) {
            throw new RuntimeException("Já existe uma etapa com o tipo '" + dto.getTipoEtapa() + "' para esse processo seletivo.");
        }

        Etapa etapa = new Etapa();
        etapa.setTipoEtapa(dto.getTipoEtapa());
        etapa.setDataInicio(dto.getDataInicio());
        etapa.setDataEncerramento(dto.getDataEncerramento());
        etapa.setStatus(dto.getStatus());
        etapa.setProcessoSeletivo(processoOpt.get());

        Etapa salva = etapaRepository.save(etapa);

        return entityToDTO(salva);
    }

    public EtapaDTO buscarPorId(String id) {
        Etapa etapa = etapaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etapa não encontrada."));
        return entityToDTO(etapa);
    }

    public EtapaDTO atualizarParcial(String id, EtapaDTO dto) {
        Etapa etapa = etapaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Etapa não encontrada."));

        if (dto.getDataInicio() != null) {
            etapa.setDataInicio(dto.getDataInicio());
        }

        if (dto.getDataEncerramento() != null) {
            etapa.setDataEncerramento(dto.getDataEncerramento());
        }

        if (dto.getStatus() != null) {
            etapa.setStatus(dto.getStatus());
        }

        Etapa atualizada = etapaRepository.save(etapa);
        return entityToDTO(atualizada);
    }

    public void deletar(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("ID da etapa é obrigatório.");
        }

        if (!etapaRepository.existsById(id)) {
            throw new RuntimeException("Etapa não encontrada para exclusão.");
        }

        etapaRepository.deleteById(id);
    }

    public List<EtapaDTO> listarTodasDoProcesso(String idProcesso) {
        return etapaRepository.findAllByProcessoSeletivoId(idProcesso)
                .stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    private EtapaDTO entityToDTO(Etapa etapa) {
        EtapaDTO dto = new EtapaDTO();
        dto.setTipoEtapa(etapa.getTipoEtapa());
        dto.setDataInicio(etapa.getDataInicio());
        dto.setDataEncerramento(etapa.getDataEncerramento());
        dto.setStatus(etapa.getStatus());
        dto.setIdProcessoSeletivo(etapa.getProcessoSeletivo().getId());
        return dto;
    }
}
