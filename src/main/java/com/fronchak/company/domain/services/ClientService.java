package com.fronchak.company.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronchak.company.domain.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
}
