package com.hk.board.mapper;

import java.util.List;
import java.util.Map; 
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hk.board.dtos.EleBoardDto;

@Mapper
public interface EleBoardMapper {
	
	//전체 글목록
	public List<EleBoardDto> getAllList();
	//페이징
	public int getPcount();
	//현재 페이지 번호에 맞는 목록 가져오기
	public List<EleBoardDto> getBoardList(@Param("offset") int offset);
	//글 상세조회
	public EleBoardDto getBoard(int ele_seq);
	//글  추가
	public boolean insertBoard(EleBoardDto dto);
	//글 수정
	public boolean updateBoard(EleBoardDto dto);
	//글 삭제
	public boolean elemulDel(String[] seqs);
}







