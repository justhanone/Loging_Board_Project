package com.hk.board.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import com.hk.board.dtos.EleBoardDto;
import com.hk.board.elecommand.InsertEleBoardCommand;
import com.hk.board.mapper.EleBoardMapper;
import com.hk.board.mapper.EleFileMapper;
import com.hk.board.util.Paging;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EleBoardService {
	
	@Autowired
	private EleBoardMapper eleboardMapper;
	
	@Autowired
	private EleFileMapper elefileMapper;
	
	@Autowired
	private EleFileService elefileService;
	
	
	//글목록 조회
	public List<EleBoardDto> getAllList() {
		return eleboardMapper.getAllList();
	}
	
	//페이징
	public int getPcount() {
		return eleboardMapper.getPcount();
	}
	
	//페이지 번호에 맞는 그 목록 가져오기
	public List<EleBoardDto> getEleBoardList(String pNum) {
		// pNum(페이지번호)을 offset(DB조회 시작번호)으로 변환
    	int pNumber = Integer.parseInt(pNum);
    	// (페이지번호 - 1) * 10 
    	int offset = (pNumber - 1) * 10;
    	
    	// 매퍼에 offset 값을 전달하여 10개씩 데이터를 가져옴
    	return eleboardMapper.getBoardList(offset);
	}
	
	//글 상세조회
	public EleBoardDto getEleBoard(int ele_seq) {
		return eleboardMapper.getBoard(ele_seq);
	}
	
	//글 추가
	public void insertBoard(InsertEleBoardCommand insertEleBoardCommand, // [수정] Command 변경
            MultipartRequest multipartRequest,
            HttpServletRequest request) 
            throws IllegalStateException, IOException {
		
		EleBoardDto eleBoardDto = new EleBoardDto();
		eleBoardDto.setId(insertEleBoardCommand.getId());
		eleBoardDto.setTitle(insertEleBoardCommand.getTitle());
		eleBoardDto.setContent(insertEleBoardCommand.getContent());
	
		eleboardMapper.insertBoard(eleBoardDto);
		
		if(!multipartRequest.getFiles("filename").get(0).isEmpty()) {
			String filepath = System.getProperty("user.dir") + "/upload/";
			File dir = new File(filepath);
			if (!dir.exists()) dir.mkdirs();
			
			// FileService.uploadFiles는 EleFileDto를 반환하도록 수정되었거나,
			// FileBoardDto를 반환한다면 EleFileDto로 변환하는 과정이 필요합니다.
			// (여기서는 FileService가 EleFileDto를 반환한다고 가정)
			List<EleFileDto> uploadFileList = fileService.uploadFiles(filepath, multipartRequest);
			
			// [수정] 파일 정보를 elefileinfo 테이블에 저장
			for (EleFileDto fDto : uploadFileList) {
				// [수정] EleFileMapper의 insert 메서드 호출 (새로 만들어야 함)
				eleFileMapper.insertEleFileBoard(
				 new EleFileDto(0, eleBoardDto.getEle_seq(), // [수정] 증가된 ele_seq 값 사용
						             fDto.getEle_origin_filename(),
						 			 fDto.getEle_stored_filename())
				                          );
			}
		}
		
	}
}















