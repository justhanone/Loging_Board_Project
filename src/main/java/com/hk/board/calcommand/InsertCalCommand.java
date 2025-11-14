package com.hk.board.calcommand;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//client: parameter 보냄 ---------> command 객체가 받음
//controller --> service --> DTO 값을 받음 --> DB
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InsertCalCommand {
	
	private int seq;
	@NotBlank(message = "아이디를 입력하세요")
	private String id;
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	
	//mdate 컬럼에 저장할 값: 12자리로 변환해서 나중에 DTO에 전달할 것임
	@NotNull(message = "년도를 입력하세요")
	private int year;
	@NotNull(message = "월을 입력하세요")
	private int month;
	@NotNull(message = "일을 입력하세요")
	private int date;
	@NotNull(message = "시간를 입력하세요")
	private int hour;
	@NotNull(message = "분을 입력하세요")
	private int min;
	
}
