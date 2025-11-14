package com.hk.board.elecommand;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateEleBoardCommand {
	
	private int ele_seq;
	
	@NotBlank(message = "제목을 입력하세요")
	private String title;
	
	@NotBlank(message = "내용을 입력하세요")
	private String content;
	
}
