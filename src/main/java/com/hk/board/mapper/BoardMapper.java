package com.hk.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hk.board.dtos.BoardDto;

@Mapper
public interface BoardMapper {

	//글목록
	public List<BoardDto> getAllList();
	//페이징
	public int getPcount(); //select ceil로 페이지 수 가져옴
	//현재 페이지의 목록 가져오기
	public List<BoardDto> getBoardList(@Param("offset") int offset);
	//글상세조회
	public BoardDto getBoard(int board_seq);
	//글추가
	public boolean insertBoard(BoardDto dto);
	//글 수정
	public boolean updateBoard(BoardDto dto);
	//글 삭제
	public boolean mulDel(String[] seqs);
	
}







