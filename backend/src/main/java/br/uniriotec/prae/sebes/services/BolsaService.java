package br.uniriotec.prae.sebes.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.uniriotec.prae.sebes.dto.BolsaDTO;
import br.uniriotec.prae.sebes.entity.Bolsa;
import br.uniriotec.prae.sebes.repository.BolsaRepository;

@Service
public class BolsaService {

    @Autowired
    private BolsaRepository bolsaRepository;

    public List<BolsaDTO> listarTodas() {
        return bolsaRepository.findAll().stream()
                .map(bolsa -> entityToDTO(bolsa))
                .collect(Collectors.toList());
    }

    public BolsaDTO buscarPorId(Integer id) {
        Bolsa bolsa = bolsaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bolsa não encontrada"));

        return entityToDTO(bolsa);
    }

    public BolsaDTO cadastrar(BolsaDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("Dados da bolsa são obrigatórios.");
        }

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome da bolsa é obrigatório.");
        }

        if (dto.getValor() == null || dto.getValor().doubleValue() <= 0) {
            throw new IllegalArgumentException("O valor da bolsa deve ser maior que zero.");
        }

        if (dto.getPeriodo() == null || dto.getPeriodo() <= 0) {
            throw new IllegalArgumentException("O período da bolsa deve ser informado e maior que zero.");
        }

        if (dto.getDescricao() == null || dto.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descrição da bolsa é obrigatória.");
        }

        Bolsa bolsa = new Bolsa();
        bolsa.setNome(dto.getNome());
        bolsa.setDescricao(dto.getDescricao());
        bolsa.setValor(dto.getValor());
        bolsa.setPeriodo(dto.getPeriodo());

        Bolsa bolsaSalva = bolsaRepository.save(bolsa);

        return entityToDTO(bolsaSalva);
    }

    public BolsaDTO atualizarParcial(Integer id, BolsaDTO dto) {
        Bolsa bolsa = bolsaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bolsa não encontrada"));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            bolsa.setNome(dto.getNome());
        }

        if (dto.getDescricao() != null && !dto.getDescricao().isBlank()) {
            bolsa.setDescricao(dto.getDescricao());
        }

        if (dto.getValor() != null && dto.getValor().compareTo(BigDecimal.ZERO) > 0) {
            bolsa.setValor(dto.getValor());
        }

        if (dto.getPeriodo() != null && dto.getPeriodo() > 0) {
            bolsa.setPeriodo(dto.getPeriodo());
        }

        Bolsa bolsaAtualizada = bolsaRepository.save(bolsa);

        return entityToDTO(bolsaAtualizada);
    }

    public void deletar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID da bolsa não pode ser nulo.");
        }

        if (!bolsaRepository.existsById(id)) {
            throw new RuntimeException("Bolsa não encontrada.");
        }

        bolsaRepository.deleteById(id);
    }

    private BolsaDTO entityToDTO(Bolsa bolsa) {
        BolsaDTO dto = new BolsaDTO();

        dto.setId(bolsa.getId());
        dto.setNome(bolsa.getNome());
        dto.setDescricao(bolsa.getDescricao());
        dto.setValor(bolsa.getValor());
        dto.setPeriodo(bolsa.getPeriodo());

        return dto;
    }
}
