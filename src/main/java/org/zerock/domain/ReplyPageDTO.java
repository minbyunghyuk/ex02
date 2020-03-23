package org.zerock.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class ReplyPageDTO {

	//댓글페이지 처리 class
	//0323 javascript에서도 동일하게 사용하니까 주의하시오...
	private int replyCnt;
	private List<ReplyVo> list;
	
}
