package com.devjunior.projetoIntensivao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjunior.projetoIntensivao.entities.GameList;

public interface GameListRepository extends JpaRepository<GameList, Long> {

}
