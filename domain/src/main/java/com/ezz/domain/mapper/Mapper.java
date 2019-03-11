package com.ezz.domain.mapper;

/**
 * Created by Ezz Waleed on 11,March,2019
 */
public interface Mapper<FROMTYPE, TOTYPE> {
	TOTYPE map(FROMTYPE fromtype);
}
