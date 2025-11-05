package com.hk.board.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hk.board.dtos.EleFileDto;

@Mapper
public interface EleFileMapper {
	
	//파일 정보 추가
	public boolean insertEleFileBoard(EleFileDto dto);
	//파일 정보 조회
	public EleFileDto getEleFileInfo(int elefile_seq);
	
}
