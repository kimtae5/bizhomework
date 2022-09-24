package kr.co.teo.bizhomework.service;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.co.teo.bizhomework.boarddto.BoardDTO;
import kr.co.teo.bizhomework.boarddto.PageRequestDTO;
import kr.co.teo.bizhomework.boarddto.PageResponseDTO;
import kr.co.teo.bizhomework.model.Board;
import kr.co.teo.bizhomework.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

//로그 기록을 위한 어노테이션 - log.레벨(메시지)를 이용해서 로그를 출력 하는 것이 가능
@Log4j2
@Service
//생성자를 이용해서 주입받기 위한 어노테이션
//Autowired를 이용해서 주입받으면 주입받는 시점을 예측하기 어렵기 때문에 비추천
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	//주입받기 위한 Repository - 생성자에서 주입받기 위해서는 final로 만들어져야 합니다.
		private final BoardRepository boardRepository;

		@Override
		public Long insertBoard(BoardDTO dto) {
			log.info("=========데이터 삽입=================");
			log.info(dto);
			
			//DTO를 Entity로 변환
			Board board = dtoToEntity(dto);
			
			//데이터 저장하고 저장한 내용을 memo에 다시 기록
			boardRepository.save(board);
			
			return board.getGno();
		}

		@Override
		public PageResponseDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO) {
			//정렬 조건을 적용해서 페이징 객체를 생성
			Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());
			Page<Board> result = boardRepository.findAll(pageable);
			
			//Entity를 DTO로 변환해주는 함수 설정
			Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));
			//결과 리턴
			return new PageResponseDTO <>(result, fn);
		}

		@Override
		public BoardDTO read(Long gno) {
			//기본키를 이용해서 데이터 찾아오기
			Optional<Board> board = boardRepository.findById(gno);
			return board.isPresent() ? entityToDto(board.get()) : null;
		}

		@Override
		public void modify(BoardDTO dto) {
			//데이터 찾아오기
			Optional<Board> result = boardRepository.findById(dto.getGno());
			if(result.isPresent()) {
				Board board = result.get();
				board.changeTitle(dto.getTitle());
				board.changeContent(dto.getContent());
				boardRepository.save(board);
			}
			
		}

		@Override
		public void remove(Long gno) {
			//데이터 찾아오기
			Optional<Board> result = boardRepository.findById(gno);
			if(result.isPresent()) {
				boardRepository.deleteById(gno);
			}
			
		}
}
