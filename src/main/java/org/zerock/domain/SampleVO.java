package org.zerock.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor //비어있는생성자 
@AllArgsConstructor // 모든속성을 사용하는 생성자 
public class SampleVO {

	private Integer mno;
	private String firstName;
	private String lastName;
	
}
