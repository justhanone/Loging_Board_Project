package com.hk.board.elecommand;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertEleBoardCommand {
	
	private String id;
	@NotBlank(message = "제목을 입력하세요.")
	private String title;
	@NotBlank(message = "내용을 입력하세요")
	private String content;
}
