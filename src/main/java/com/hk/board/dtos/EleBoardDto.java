package com.hk.board.dtos;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias(value = "EleBoardDto") //mapper.xml에서 사용하는 변수명 설정
public class EleBoardDto {
	private int ele_seq;
    private String id;
    private String title;
    private String content;
    private Date regdate;   // DB의 DATE 타입은 Java의 java.util.Date로 매핑됩니다.
    
    // getBoardList 쿼리에서 SELECT하는 컬럼 (resultMap에는 없었지만 필요함)
    private String delflag;

    // 1:N 관계 (게시글 하나에 파일 여러 개)
    // [핵심]
    // 마지막 매퍼의 <collection property="elefileBoardDto">와
    // 필드(변수) 이름을 정확히 일치시켰습니다.
    private List<EleFileDto> elefileBoardDto;
}
