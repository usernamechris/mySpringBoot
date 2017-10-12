package com.han;

import com.han.domain.FreeBoard;
import com.han.persistence.FreeBoardReplyRepository;
import com.han.persistence.FreeBoardRepository;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class FreeBoardTests {

    @Autowired
    FreeBoardRepository boardRepo;

    @Autowired
    FreeBoardReplyRepository replyRepo;

    @Test
    public void insertDummy() {
        IntStream.range(1, 200).forEach(i -> {
            FreeBoard board = new FreeBoard();
            board.setTitle("Free Board... " + i);
            board.setContent("Free Content..." + i);
            board.setWriter("user" + i%10);

            boardRepo.save(board);
        });
    }
}
