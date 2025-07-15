package br.uniriotec.prae.sebes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.uniriotec.prae.sebes.entity.LoginCode;

@Repository
public interface LoginCodeRepository extends JpaRepository<LoginCode, Integer> {
    Optional<LoginCode> findTopByEmailOrderByExpirationDesc(String email);
}