package org.zerock.domain;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class Criteria {

	private int PageNum;
	private int amount;
	
	public Criteria()
	{
		this(1,10);
	}
	
	public Criteria(int pagenum, int amount)
	{
		this.PageNum = pagenum;
		this.amount =amount;
	}
	
}
