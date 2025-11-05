package com.hk.board.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EleFileDto {
	private int elefile_seq;
	private int ele_seq;
	private String ele_origin_filename;
	private String ele_stored_filename;
}
