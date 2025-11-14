package com.hk.board.calcommand;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//command 역할: client와 controller 간에 데이터를 주고받게 하는 중간 역할
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteCalCommand {

	//seq의 값이 null값이면 하나이상 체크하지 않고 삭제 요청을 함
	// 에러 메시지: "삭제하려면 최소 하나이상 체크하세요"
	@NotEmpty(message = "삭제하려면 최소 하나 이상 체크하세요")
	private String[] seq;
	
}