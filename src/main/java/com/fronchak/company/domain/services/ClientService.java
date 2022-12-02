package com.fronchak.company.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fronchak.company.api.dtos.ClientDTO;
import com.fronchak.company.domain.entities.Client;
import com.fronchak.company.domain.exceptions.DatabaseException;
import com.fronchak.company.domain.exceptions.ResourceNotFoundException;
import com.fronchak.company.domain.mappers.ClientMapper;
import com.fronchak.company.domain.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ClientMapper mapper;
	
	@Transactional
	public ClientDTO save(ClientDTO dto) {
		Client entity = new Client();
		mapper.copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return mapper.convertEntityToDTO(entity);
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
	
	@Transactional
	public ClientDTO update(ClientDTO dto, Long id) {
		try {
			Client entity = repository.getReferenceById(id);
			mapper.copyDTOToEntity(dto, entity);
			entity = repository.save(entity);
			return mapper.convertEntityToDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Client not found by ID: " + id);
		}
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Client not found by ID: " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Database integrity violation");
		}
	}
}
