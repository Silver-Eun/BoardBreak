package com.board.boardBreak.repository;

import com.board.boardBreak.entity.Board;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {
}
