package org.zerock.domain;

import lombok.Data;

@Data
public class PageDTO {
	
	private int startPage;
	private int endPage;
	private boolean prev, next;
	private int total;
	private Criteria cri;
	
	public PageDTO(Criteria cri ,int total)
	{
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0))*10; //사용자가 어디를보고있냐에따라서 Endpage가바뀜
		this.startPage = this.endPage -9;
		
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getAmount())); 
		//먼저 데이터전체의수를ㄹ 이용행서 진짜 끝페이지가 몇번까지되는지를 계산해야함.
		if(realEnd < this.endPage) {
			this.endPage=realEnd;
			// 완전 끝페이지가 구해둔 끝번호보다 작다면  endpage는 완전끝페이지가되야됨으로 
		}
		this.prev= this.startPage>1; //1페이지보다 커야만존재한다.
		this.next = this.endPage < realEnd; // 완전끝번호가 끝번호보다 큰경우 에만존재한다.
		//System.out.println(cri.getAmount());	
		
	}
	
	

}
