package com.hk.board.status;

public enum RoleStatus {
	ADMIN, USER
}



/**

// com.hk.board.controller.BoardController.java

import com.hk.board.dtos.BoardDto; // List<BoardDto> 사용 위해 import
import com.hk.board.dtos.DelBoardCommand; // DelBoardCommand 사용 위해 import
import java.util.List; // List import
import java.util.Map; // Map import

// (생략)...

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private FileService fileService;
	
	@GetMapping(value = "/boardList")
	public String boardList(Model model, 
							@RequestParam(defaultValue = "1") String pNum) {
		System.out.println("글목록 보기: " + pNum + "페이지");
		
		// ▼ [수정] : getAllList() 대신 getBoardList(pNum) 호출
		// List<BoardDto> list=boardService.getAllList();
		List<BoardDto> list = boardService.getBoardList(pNum); 
		
		model.addAttribute("list", list);
		model.addAttribute("delBoardCommand", new DelBoardCommand());
		
		//전체 페이지 수 가져오기
		int pcount = boardService.getPcount();
		Map<String, Integer> map = Paging.pagingValue(pcount, pNum, 5); //5개씩
		model.addAttribute("paging", map);
		
		return "board/boardList";
	}

	// ... (다른 메서드들) ...
}

**/