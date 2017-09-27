package com.han;

import com.han.domain.Board;
import com.han.persistence.BoardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTests {

    @Autowired
    private BoardRepository boardRepo;

    @Test
    public void inspect() {
        // 실제 객체 클래스 이름
        Class<?> clz = boardRepo.getClass();

        System.out.println("실제 객체 클래스 이름: " + clz.getName());

        // 클래스가 구현하고 있는 인터페이스 목록
        Class<?>[] interfaces = clz.getInterfaces();

        Stream.of(interfaces).forEach(inter -> System.out.println("인터페이스 목록 " + inter.getName()));

        // 클래스의 부모 클래스
        Class<?> superClasses = clz.getSuperclass();

        System.out.println("부모 클래스: " + superClasses.getName());

    }

    @Test
    public void testInsert() {
        Board board = new Board();
        board.setTitle("test title");
        board.setContent("test contents....");
        board.setWriter("test writer");

        boardRepo.save(board);
    }

    @Test
    public void testRead() {
        Optional<Board> board = boardRepo.findById(1L);
        System.out.println(board);

    }

    @Test
    public void testUpdate() {
        System.out.println("Read First............................");
        Board board = boardRepo.findById(1L).get();

        System.out.println("Update Title..........................");
        board.setTitle("updated title");

        System.out.println("Call Save()...........................");
        boardRepo.save(board);
    }

    @Test
    public void testDelete() {
        System.out.println("Delete entity");
        boardRepo.deleteById(1L);
    }



}
