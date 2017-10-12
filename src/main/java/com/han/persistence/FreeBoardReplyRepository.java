package com.han.persistence;

import com.han.domain.FreeBoard;
import com.han.domain.FreeBoardReply;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FreeBoardReplyRepository extends CrudRepository<FreeBoardReply, Long> {

}
