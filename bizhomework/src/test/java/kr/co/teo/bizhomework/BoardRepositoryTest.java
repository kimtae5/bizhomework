package kr.co.teo.bizhomework;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.teo.bizhomework.model.Board;
import kr.co.teo.bizhomework.persistence.BoardRepository;

@SpringBootTest
public class BoardRepositoryTest{
	
	@Autowired
	private BoardRepository boardRepository;

	//데이터 삽입 테스트
	//@Test
	public void insertBoard() {
		//300개의 정수 모임을 생성하고 순회
		IntStream.rangeClosed(1, 100).forEach(i -> {
			//데이터 생성
			Board board = Board.builder()
					.title("title..." + i)
					.content("contents..." + i)
					.writer("user..." + i)
					.build();
			//데이터 삽입
			boardRepository.save(board);
		});
	}
	
	//데이터 수정 테스트
	//@Test
	public void updateBoard() {
		//수정할 데이터 가져오기
		Optional<Board> result = boardRepository.findById(300L);
		if(result.isPresent()) {
			Board board = result.get();
			board.changeTitle("제목 변경");
			board.changeContent("내용 변경");
			boardRepository.save(board);
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}
	
	//데이터 삭제 테스트
	//@Test
	public void deleteBoard() {
		//삭제 데이터 가져오기
		Optional<Board> result = boardRepository.findById(300L);
		if(result.isPresent()) {
			Board board = result.get();
			boardRepository.delete(board);
		}else {
			System.out.println("데이터가 존재하지 않습니다.");
		}
	}


}

