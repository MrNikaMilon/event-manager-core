package com.nikamilon.api.repository;

import com.nikamilon.api.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, UUID> {
  Optional<TokenEntity> findByToken(String jwtToken);

  List<TokenEntity> findAllValidTokenByUserId(Long UseId);
}