package com.devjunior.projetoIntensivao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devjunior.projetoIntensivao.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{

}
