package com.niuran.giftstore.util;

import com.niuran.giftstore.enume.CommonEnum;

/**
 * 枚举工具类
 * @Author MrD on 2018/7/6.
 */


public class EnumUtil {

    /**
     * 返回指定编码的'枚举'
     * @param code
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends CommonEnum> T getEnumBycode(Class<T> clazz, int code) {
        for(T _enum : clazz.getEnumConstants()){
            if(code == _enum.getCode()){
                return _enum;
            }
        }
        return null;
    }

    /**
     * 返回指定信息的'枚举'
     * @param message
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends CommonEnum> T getEnumByName(Class<T> clazz, String message) {
        for(T _enum : clazz.getEnumConstants()){
            if(_enum.getMessage().equals(message)){
                return _enum;
            }
        }
        return null;
    }

    /**
     * 返回指定描述的'枚举'
     * @param desc
     * @return SharedObjTypeEnum
     * @throws
     */
    public static <T extends CommonEnum> T getEnumByDesc(Class<T> clazz, String desc) {
        for(T _enum : clazz.getEnumConstants()){
            if(_enum.getDesc().equals(desc)){
                return _enum;
            }
        }
        return null;
    }
}

