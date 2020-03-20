package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVo {

	private Long bno;
	private String title;
	private String Content;
	private String Writer;
	private Date  Regdate;
	private Date  Updatedate;
	
}
