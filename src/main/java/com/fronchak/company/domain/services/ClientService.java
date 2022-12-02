package com.fronchak.company.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fronchak.company.domain.entities.Client;
import com.fronchak.company.domain.exceptions.ResourceNotFoundException;
import com.fronchak.company.domain.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public Client save(Client client) {
		client = repository.save(client);
		return client;
	}
	
	public Client findById(Long id) {
		Client entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found by ID: " + id));
		return entity;
	}
}
