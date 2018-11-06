package com.yangbingdong.schedule.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

@Slf4j
public class CronUtils {
	private static final String CRON_FORMAT = "ss mm HH dd MM ? yyyy";
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(CRON_FORMAT);

    public static String formatDateByPattern(Date date) {
		SimpleDateFormat sdf = getSimpleDateFormat();
		Objects.requireNonNull(date);
		return  sdf.format(date);
    }

	private static SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat(CRON_FORMAT);
	}

	public static String getCron(Date date) {
        return formatDateByPattern(date);
    }

	public static String getCron(LocalDateTime localDateTime) {
		Objects.requireNonNull(localDateTime);
		return FORMATTER.format(localDateTime);
	}

    public static Date getDateByCron(String dateCron) {
		SimpleDateFormat sdf = getSimpleDateFormat();
        Date date = null;
        try {
            date = sdf.parse(dateCron);
        }catch (Exception e){
            log.error("转换cron时间失败,源时间为" + dateCron);
        }
        return date;
    }
}
