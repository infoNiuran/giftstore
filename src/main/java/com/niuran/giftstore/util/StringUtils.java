package com.niuran.giftstore.util;

import com.xiaoleilu.hutool.util.RandomUtil;

import java.util.Calendar;
import java.util.UUID;
import java.util.regex.Pattern;

import static com.xiaoleilu.hutool.util.StrUtil.isEmpty;

/**
 * @Author MrD on 2018/8/2.
 */
public class StringUtils {
    /**
     * 礼品编号生成
     * @return
     */
    public static String randomGiftCode(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    public static String generateShortUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }


    public static String getMonthDaysStr(){
        Calendar c = Calendar.getInstance();
        //获取月份，0表示1月份
        String month = c.get(Calendar.MONTH) + 1>9?c.get(Calendar.MONTH) + 1+ "":"0"+(c.get(Calendar.MONTH) + 1);
        //获取当前天数
        String day = c.get(Calendar.DAY_OF_MONTH)>9?c.get(Calendar.DAY_OF_MONTH)+"":"0"+c.get(Calendar.DAY_OF_MONTH);
        String birthStr = month + day ;
        System.out.println(birthStr);
        return birthStr;
    }

    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
