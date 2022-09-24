package kr.co.teo.bizhomework.service;

import kr.co.teo.bizhomework.boarddto.BoardDTO;
import kr.co.teo.bizhomework.boarddto.PageRequestDTO;
import kr.co.teo.bizhomework.boarddto.PageResponseDTO;
import kr.co.teo.bizhomework.model.Board;

public interface BoardService {
	//DTO 객체를 Entity 로 변환해주는 메서드
	//이 메서드는 클라이언트 정보를 가지고 데이터베이스에 변환을 수행하기 위해서 사용
	default Board dtoToEntity(BoardDTO dto) {
		Board board = Board.builder()
				.gno(dto.getGno())
				.title(dto.getTitle())
				.content(dto.getContent())
				.writer(dto.getWriter())
				.build();
		return board;
	}
	
	//Entity 객체를 DTO 객체로 변환해주는 메서드
	//조회를 할 때 사용
	default BoardDTO entityToDto(Board board) {
		BoardDTO dto = BoardDTO.builder()
				.gno(board.getGno())
				.title(board.getTitle())
				.content(board.getContent())
				.writer(board.getWriter())
				.regDate(board.getRegDate())
				.modDate(board.getModDate())
				.build();
		return dto;
	}
	
	//데이터 삽입을 위한 메서드
	//삽입된 메모의 gno 값을 리턴
	public Long insertBoard(BoardDTO dto);
	
	//목록 보기를 위한 메서드
	public PageResponseDTO<BoardDTO, Board> getList (PageRequestDTO requestDTO);
	
	//상세 보기를 위한 메서드
	public BoardDTO read(Long gno);
	
	//수정을 처리하는 메서드
	public void modify(BoardDTO dto);
	
	//삭제를 처리하는 메서드
	public void remove(Long gno);


	
}