package com.devjunior.projetoIntensivao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjunior.projetoIntensivao.dto.GameListDTO;

import com.devjunior.projetoIntensivao.entities.GameList;
import com.devjunior.projetoIntensivao.projections.GameMinProjection;
import com.devjunior.projetoIntensivao.repositories.GameListRepository;
import com.devjunior.projetoIntensivao.repositories.GameRepository;

@Service
public class GameListService {

	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameListDTO> findAll() {
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		List<GameMinProjection> list = gameRepository.searchByList(listId); //pega a lista
		
		GameMinProjection obj = list.remove(sourceIndex); //remove o elemento da lista especificado pelo seu índice
		list.add(destinationIndex, obj); //adiciona o elemento removido ao índice especificado
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex; /*descobre se a posição de origem é menor ou maior
		que a posição de destino e guarda o número do índice na variável*/
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex ;/*se sourceIndex for menor(<) que 
		destinationIndex, então(?) destinationIndex é maior que sourceIndex, caso contrário(:), sourceIndex é maior.*/
		
		for(int i = min; i <= max; i++) { //será trabalhado somente com o intervalos dos índices entre o menor e o maior
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);			
		}
		
		
	}
}
