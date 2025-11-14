package com.hk.board.elecommand;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DelEleBoardCommand {
	
	@NotEmpty(message = "최소 하나 이상 체크해야 합니다.")
	private String[] ele_seq;
	
}
