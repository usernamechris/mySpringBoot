package com.han.persistence;

import com.han.domain.FreeBoard;
import org.springframework.data.repository.CrudRepository;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {
}
