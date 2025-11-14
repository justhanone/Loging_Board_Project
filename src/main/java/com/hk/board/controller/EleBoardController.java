package com.hk.board.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.dtos.EleBoardDto;
import com.hk.board.dtos.EleFileDto;
import com.hk.board.elecommand.DelEleBoardCommand;
import com.hk.board.elecommand.InsertEleBoardCommand;
import com.hk.board.elecommand.UpdateEleBoardCommand;
import com.hk.board.service.BoardService;
import com.hk.board.service.EleBoardService;
import com.hk.board.service.EleFileService;
import com.hk.board.util.Paging;

import groovy.lang.Binding;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(value = "/eleboard")
public class EleBoardController {

    private final BoardService boardService;

    private final BoardController boardController;
	
	@Autowired
	private EleBoardService eleboardService;
	
	@Autowired
	private EleFileService elefileService;

    EleBoardController(BoardController boardController, BoardService boardService) {
        this.boardController = boardController;
        this.boardService = boardService;
    }
	
	@GetMapping(value = "/eleboardList")
	public String eleboardList(Model model, 
			@RequestParam(defaultValue = "1") String pNum) {
		
		List<EleBoardDto> list = eleboardService.getEleBoardList(pNum);
		
		model.addAttribute("list",list);
		model.addAttribute("delEleBoardCommand", new DelEleBoardCommand());
		
		//전체 페이지 수 가져오기
		int pcount = eleboardService.getPcount();
		Map<String, Integer> map = Paging.pagingValue(pcount, pNum, 5);
		model.addAttribute("paging", map);
		
		//pcount 넘겨주기
		model.addAttribute("pcount", pcount);		
		
		return "eleboard/eleboardList";
	}
	
	@GetMapping(value = "/eleboardInsert")
	public String eleboardInsertForm(Model model) {
		model.addAttribute("insertEleBoardCommand", new InsertEleBoardCommand());
		
		return "eleboard/eleboardInsertForm";
	}
	
	@PostMapping(value = "/eleboardInsert")
	public String eleboardInsert(@Validated InsertEleBoardCommand insertEleBoardCommand
								,BindingResult result
								,MultipartRequest multiparRequest
								,HttpServletRequest request
								,Model model) throws IllegalStateException, IOException {
		
		if(result.hasErrors()) {
			System.out.println("글을 모두 입력하세요");
			return "eleboard/eleboardInsertForm";
		}
		
		eleboardService.insertBoard(insertEleBoardCommand, multiparRequest, request);
		
		return "redirect:/eleboard/eleboardList";
	}
	
	//상세보기
	@GetMapping(value = "/eleboardDetail")
	public String eleboardDetail(int ele_seq, Model model) {
		
		EleBoardDto dto = eleboardService.getEleBoard(ele_seq);
		
		//유효값 처리용
		model.addAttribute("updateEleBoardCommand", new UpdateEleBoardCommand());
		
		//출력용
		model.addAttribute("dto", dto);
		System.out.println(dto);
		
		return "/eleboard/eleboardDetail";
	}
	
	//수정하기
	@PostMapping(value = "/eleboardUpdate")
	public String eleboardUpdate(@Validated UpdateEleBoardCommand updateEleBoardCommand
								,BindingResult result
								,Model model) {
		
		if(result.hasErrors()) {
			System.out.println("수정 내용을 모두 입력하세요");
			
			EleBoardDto dto = eleboardService.getEleBoard(updateEleBoardCommand.getEle_seq());
			model.addAttribute("dto", dto);
			
			return "eleboard/eleboardDetail";
		}
		
		eleboardService.updateEleBoard(updateEleBoardCommand);
		
		return "redirect:/eleboard/eleboardDetail?ele_seq="
				+ updateEleBoardCommand.getEle_seq();
	}
	
	//파일 다운로드
	@GetMapping(value = "/eledownload")
	public void eledownload(int elefile_seq, HttpServletRequest request
							, HttpServletResponse response) throws UnsupportedEncodingException {
		
		EleFileDto fdto = elefileService.getEleFileInfo(elefile_seq); //파일 정보 가져오기
		
		elefileService.elefileDownload(fdto.getEle_origin_filename()
										, fdto.getEle_stored_filename()
										, request
										, response);
		
	}
	
	//게시글 삭제
	@RequestMapping(value = "elemulDel", method = {RequestMethod.POST, RequestMethod.GET})
	public String elemulDel(@Validated DelEleBoardCommand delEleBoardCommand
							, BindingResult result
							, Model model) {
		
		if(result.hasErrors()) {
			System.out.println("최소 하나 체크하기");
			List<EleBoardDto> list = eleboardService.getAllList();
			model.addAttribute("list", list);
			
			return "eleboard/eleboardList";
		}
		
		eleboardService.elemulDel(delEleBoardCommand.getEle_seq());
		System.out.println("글 삭제함");
		
		return "redirect:/eleboard/eleboardList";
	}
	
	
	
}
