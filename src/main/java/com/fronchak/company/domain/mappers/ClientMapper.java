package com.fronchak.company.domain.mappers;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.fronchak.company.api.dtos.ClientDTO;
import com.fronchak.company.domain.entities.Client;

@Service
public class ClientMapper {

	public ClientDTO convertEntityToDTO(Client entity) {
		ClientDTO dto = new ClientDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCpf(entity.getCpf());
		dto.setIncome(entity.getIncome());
		dto.setBirthDate(entity.getBirthDate());
		dto.setChildren(entity.getChildren());
		return dto;
	}
	
	public Page<ClientDTO> convertEntityPageToDTOPage(Page<Client> entities) {
		return entities.map(entity -> convertEntityToDTO(entity));
	}
	
	public void copyDTOToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setCpf(dto.getCpf());
		entity.setIncome(dto.getIncome());
		entity.setBirthDate(dto.getBirthDate());
		entity.setChildren(dto.getChildren());
	}
	
}
