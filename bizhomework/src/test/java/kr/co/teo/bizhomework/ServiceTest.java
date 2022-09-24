package kr.co.teo.bizhomework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.teo.bizhomework.boarddto.BoardDTO;
import kr.co.teo.bizhomework.boarddto.PageRequestDTO;
import kr.co.teo.bizhomework.boarddto.PageResponseDTO;
import kr.co.teo.bizhomework.model.Board;
import kr.co.teo.bizhomework.service.BoardService;

@SpringBootTest
public class ServiceTest {
	@Autowired
	private BoardService boardService;
	
	//@Test
	public void testInsert() {
		BoardDTO dto = BoardDTO.builder().title("데이터 삽입 테스트")
				.content("삽입 성공?").writer("태오").build();
		System.out.println(boardService.insertBoard(dto));
	}
	
	//목록 보기 테스트
	//@Test
	public void testList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).size(10).build();
		PageResponseDTO <BoardDTO, Board> resultDTO = boardService.getList(pageRequestDTO);
		for(BoardDTO boardDTO : resultDTO.getDtoList()) {
			System.out.println(boardDTO);
		}
	}
	
	//목록 보기 테스트 - 목록 과 페이지 번호 테스트
	@Test
	public void testPageList() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(11).size(5).build();
		PageResponseDTO <BoardDTO, Board> resultDTO = boardService.getList(pageRequestDTO);
		for(BoardDTO boardDTO : resultDTO.getDtoList()) {
			System.out.println(boardDTO);
		}
		//이전 과 다음 페이지 존재 여부
		System.out.println("이전:" + resultDTO.isPrev());
		System.out.println("다음:" + resultDTO.isNext());
		//전체 페이지 개수
		System.out.println("전체 페이지 개수:" + resultDTO.getTotalPage());
		//페이지 번호 목록
		System.out.println(resultDTO.getPageList());
		
	}
	
}
