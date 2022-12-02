package com.fronchak.company.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fronchak.company.domain.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {}
