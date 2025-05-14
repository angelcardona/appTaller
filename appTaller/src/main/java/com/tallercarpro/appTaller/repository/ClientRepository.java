package com.tallercarpro.appTaller.repository;

import com.tallercarpro.appTaller.models.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository  extends JpaRepository <Client,String> {
    boolean existsByIdentification(String identification);
    Optional<Client> findByIdentification(String identification);

}
