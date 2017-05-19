package com.smart.commom;

import java.math.BigDecimal;
import java.text.NumberFormat;

/**
 * Function: 金额转换工具类<br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2016年12月6日 下午9:22:20 <br/>
 * 
 * @author gaowenming
 * @version
 * @since JDK 1.6
 * @see
 */
public class AmountUtils {
	/**
	 * 金额为分的格式
	 */
	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

	/**
	 * 将分为单位的转换为元 （除100）
	 *
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal fen2Yuan(String amount) {
		if (!amount.matches(CURRENCY_FEN_REGEX)) {
			throw new RuntimeException("金额格式错误|" + amount);
		}
		return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100));
	}

	/**
	 * 将分为单位的转换为元 （除100）
	 *
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static BigDecimal fen2Yuan(int amount) {
		if (!String.valueOf(amount).matches(CURRENCY_FEN_REGEX)) {
			throw new RuntimeException("金额格式错误|" + amount);
		}
		return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100));
	}

	/**
	 * 
	 * (这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author gaowenming
	 * @param amount
	 * @return
	 * @since JDK 1.8
	 */

	public static BigDecimal fen2Yuan(Long amount) {
		if (!String.valueOf(amount).matches(CURRENCY_FEN_REGEX)) {
			throw new RuntimeException("金额格式错误|" + amount);
		}
		return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100));
	}

	/**
	 * 
	 * (这里用一句话描述这个方法的作用). <br/>
	 *
	 * @author gaowenming
	 * @param amount
	 * @return
	 * @since JDK 1.8
	 */
	public static BigDecimal fen2Yuan(BigDecimal amount) {
		if (!String.valueOf(amount).matches(CURRENCY_FEN_REGEX)) {
			throw new RuntimeException("金额格式错误|" + amount);
		}
		return amount.divide(new BigDecimal(100));
	}

	/**
	 * 
	 * (分转元，格式化输出金额的格式). <br/>
	 *
	 * @author gaowenming
	 * @param amount
	 * @return
	 * @since JDK 1.8
	 */
	public static String formatNumber4fen(BigDecimal amount) {
		if (!String.valueOf(amount).matches(CURRENCY_FEN_REGEX)) {
			throw new RuntimeException("金额格式错误|" + amount);
		}
		BigDecimal amountYuan = amount.divide(new BigDecimal(100));
		// 获取货币数值格式
		NumberFormat currency = NumberFormat.getNumberInstance();
		currency.setMinimumFractionDigits(2);// 设置数的小数部分所允许的最小位数(如果不足后面补0)
		return currency.format(amountYuan);
	}

	/**
	 * 
	 * (获取百分比). <br/>
	 *
	 * @author gaowenming
	 * @param decimal
	 * @return
	 * @since JDK 1.8
	 */
	public static String formatPercent(BigDecimal decimal) {
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMinimumFractionDigits(0);// 设置数的小数部分所允许的最小位数(如果不足后面补0)
		percent.setMaximumFractionDigits(0);// 设置数的小数部分所允许的最大位数(如果超过会四舍五入)
		return percent.format(decimal);
	}

	/**
	 * 
	 * (获取百分比). <br/>
	 *
	 * @author gaowenming
	 * @param decimalTotal
	 *            总数
	 * @param decimal
	 *            除数
	 * @return
	 * @since JDK 1.8
	 */
	public static String formatPercent(BigDecimal decimalTotal, BigDecimal decimal) {
		NumberFormat percent = NumberFormat.getPercentInstance();
		percent.setMinimumFractionDigits(0);// 设置数的小数部分所允许的最小位数(如果不足后面补0)
		percent.setMaximumFractionDigits(0);// 设置数的小数部分所允许的最大位数(如果超过会四舍五入)
		return percent.format(decimal.divide(decimalTotal, 2, BigDecimal.ROUND_HALF_UP));
	}

	/**
	 * 
	 * (元转分). <br/>
	 *
	 * @author gaowenming
	 * @param amount
	 * @return
	 * @since JDK 1.8
	 */
	public static String yuan2Fen(String amount) {
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0l;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
		}
		return amLong.toString();
	}

	public static void main(String[] args) {
		//System.out.println(formatNumber4fen(new BigDecimal(53500000)));
		// System.out.println(formatPercent(new BigDecimal(53500000), new
		// BigDecimal(5500000)));
		System.out.println(yuan2Fen("400000"));
	}

}
