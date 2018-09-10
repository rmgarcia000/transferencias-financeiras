package br.com.transferenciasfinanceirasapi.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	/**
	 * Valida Datas desconsiderandos Time
	 * 
	 * @param dataInicial Data comparada por
	 * @param dataFinal Data a ser comparada com
	 * @return -1 < | 0 = | 1 >
	 */
	public static int comparaData(Date dataInicial, Date dataFinal) {
		Calendar dataInicialCal = trancateCalendar(dataInicial);
		Calendar dataFinalCal = trancateCalendar(dataFinal);
		
		return dataFinalCal.compareTo(dataInicialCal);
	}
	
	public static Calendar trancateCalendar(Date data) {
		Calendar cal = Calendar.getInstance(); // locale-specific
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}
	
	public static Date trancateDate(Date data) {
		return trancateCalendar(data).getTime();
	}
	
}
