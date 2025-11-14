package com.hk.board.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hk.board.dtos.CalDto;

@Component
public class Util {

	//문자열을 2자리로 변환해주는 메서드
	public String isTwo(String s) {
		return s.length()<2?"0"+s:s;
	}
	
	//mdate: 날짜 형식으로 변환
	public String toDates(String mdate, String p) {
		//Date 형식으로 만들기: "yyyy-mm-dd hh:mm:ss"
		String m=mdate.substring(0,4) + "-"
				+mdate.substring(4,6) + "-"
				+mdate.substring(6,8) + " "
				+mdate.substring(8,10) + ":"
				+mdate.substring(10) + ":00";
		
		Timestamp tm=Timestamp.valueOf(m); //String->Date로 변환
		
		//java의 SimpleDateFormat 객체 
		SimpleDateFormat sdf = new SimpleDateFormat(p);
		
		return sdf.format(tm);
	}
	
	//일일별 일정 목록을 출력해주는 기능
	//calendar.html에서 날짜 출력할 때 사용된 변수 i
	public String getCalViewList(int i,List<CalDto> clist) {
		
		String d=isTwo(i+""); //1자리를 2자리로 바꾸기
		String calList=""; //<p>일정 제목</p> 이런식으로 뽑아낼 것임
		
		for (int j = 0; j < clist.size(); j++) {
			if(clist.get(j).getMdate().substring(6,8).equals(d)) { //날짜의 date만 뽑아내서 비교하기
				String ctitle=clist.get(j).getTitle();
				
				//ctitle이 7자리보다 크면 7자까지만 출력하고 아니면 ctitle 그대로 출력하기
				calList+="<p>"+(ctitle.length()>7?ctitle.substring(0,7)+"..":ctitle)+"</p>";
			}
		}
		
		return calList;
	}
}