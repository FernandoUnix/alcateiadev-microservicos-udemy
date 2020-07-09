package com.br.marcelo.pessoas.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateJsonUtils {
	
	public static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	public static final SimpleDateFormat FORMATTER_JAVASCRIPT = new SimpleDateFormat("MM/dd/yyyy");
	public static final SimpleDateFormat FORMATTER_AMERICANO = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat FORMATTER_DATA_HOME = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	public static final SimpleDateFormat FORMATTER_DATA_HOME_COMPLETA = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

	public static String valueOfDateTime(Date data){
		return valueOf(data, FORMATTER_DATA_HOME);
	}

	public static String valueOf(Date data){
		return valueOf(data, FORMATTER);
	}
	
	public static String valueOf(Date data, SimpleDateFormat formater){
		return formater.format(data);
	}
	
	public static Date valueOf(String data){
		return valueOf(data, FORMATTER);
	}
	
	public static Date valueOf(String data, SimpleDateFormat formater){
		try {
			if( data.contains("-") ){
				return FORMATTER_AMERICANO.parse(data);
			}
			return formater.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}