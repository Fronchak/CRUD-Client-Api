package com.fronchak.company.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.company.api.dtos.ClientDTO;
import com.fronchak.company.domain.entities.Client;
import com.fronchak.company.domain.exceptions.ResourceNotFoundException;
import com.fronchak.company.domain.mappers.ClientMapper;
import com.fronchak.company.domain.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ClientMapper mapper;
	
	public Client save(Client client) {
		client = repository.save(client);
		return client;
	}
	
	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Client entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Client not found by ID: " + id));
		return mapper.convertEntityToDTO(entity);
	}
	
	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest request) {
		Page<Client> entities = repository.findAll(request);
		return mapper.convertEntityPageToDTOPage(entities);
	}
}
