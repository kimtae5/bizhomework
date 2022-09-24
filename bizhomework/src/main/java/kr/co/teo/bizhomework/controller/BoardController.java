package kr.co.teo.bizhomework.controller;

import javax.servlet.http.HttpSession;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.teo.bizhomework.boarddto.BoardDTO;
import kr.co.teo.bizhomework.boarddto.PageRequestDTO;
import kr.co.teo.bizhomework.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequiredArgsConstructor
@EnableJpaAuditing
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/")
	public String main() {
		return "redirect:/board/list1";
	}
	
	//목록보기 요청을 처리
	@GetMapping("/board/list1")
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("목록 보기...");
		model.addAttribute("result", boardService.getList(pageRequestDTO));
	}
	
	//삽입 화면으로 이동하는 요청을 처리
	@GetMapping("/board/register")
	public void register() {
		log.info("데이터 삽입 화면으로 이동");
	}

	//데이터 삽입 처리
	@PostMapping("/board/register")
	public String register(BoardDTO dto, Model model, HttpSession session, 
			RedirectAttributes rAttr) {
		//여기가 제대로 출력이 안되면 요청 URL 과 View 이름을 확인하고
		//form의 경우라면 입력 요소의 name을 확인
		log.info("파라미터:", dto);

		//삽입
		Long gno = boardService.insertBoard(dto);
		
		//model에 msg를 저장
		//model.addAttribute("msg", gno + " 삽입 성공");
		
		//session.setAttribute("msg", gno + " 삽입 성공");
		
		//리다이렉트 할 때 데이터를 전달
		rAttr.addFlashAttribute("msg", gno + " 삽입 성공");

		return "redirect:/board/list1";
	}

	//상세보기 처리를 위한 메서드
	@GetMapping({"/board/read", "/board/modify"})
	public void read(long gno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		
		BoardDTO board = boardService.read(gno);
		model.addAttribute("dto", board);
		
	}
	
	@PostMapping("/board/modify")
	public String modify(BoardDTO board, @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
			RedirectAttributes rattr) {
		log.info("dto:" + board);
		
		boardService.modify(board);
		
		//상세보기로 이동할 때 필요한 gno 값 과 page 값을 설정
		rattr.addAttribute("gno", board.getGno());
		rattr.addAttribute("page", requestDTO.getPage());
		
		return "redirect:/board/read";
	}
	
	
	@PostMapping("/board/remove")
	public String remove(long gno, RedirectAttributes rattr) {
		log.info("gno:" + gno);
		
		boardService.remove(gno);
		
		//상세보기로 이동할 때 필요한 gno 값 과 page 값을 설정
		rattr.addAttribute("msg", gno + " 삭제");
	
		return "redirect:/board/list1";
	}
	
	
}
