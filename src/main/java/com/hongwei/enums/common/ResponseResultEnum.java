package com.hongwei.enums.common;

import java.util.TreeMap;

/**
 * Package: com.jd.bdp.ai.domain.common
 * User: 何芊芊
 * Email: heqianqian1@jd.com
 * Date: 2018/9/18
 * Time: 11:05
 * Description:
 */
public enum ResponseResultEnum {
    SUCCESS(0, "success", "调用成功"),
    FAILED(1, "failed", "基础服务平台调用失败"),

    PARAM_NULL(2, "param_null", "参数为空"),
    PARAM_WRONG(3, "param_wrong", "参数有误"),

    MOBILE_PLATFORM_WRONG(4, "mobile_platform_wrong", "组件平台调用失败"),

    HINT_MSG(5, "hint_msg", "提示信息");

    private Integer code;
    private String name;
    private String description;

    /**
     * @param description 中文描述
     */
    private ResponseResultEnum(String description) {
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param description 中文描述
     */
    private ResponseResultEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private ResponseResultEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * @param code        数字编码
     * @param name        英文编码名称
     * @param description 中文描述
     */
    private ResponseResultEnum(Integer code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }


    /**
     * 获取枚举类型数值编码
     */
    public Integer toCode() {
        return this.code == null ? this.ordinal() : this.code;
    }

    /**
     * 获取枚举类型英文编码名称
     */
    public String toName() {
        return this.name == null ? this.name() : this.name;
    }

    /**
     * 获取枚举类型中文描述
     */
    public String toDescription() {
        return this.description;
    }

    /**
     * 获取枚举类型中文描述
     */
    public String toString() {
        return this.description;
    }

    /**
     * 按数值获取对应的枚举类型
     *
     * @param code 数值
     * @return 枚举类型
     */
    public static ResponseResultEnum enumValueOf(Integer code) {
        ResponseResultEnum[] values = ResponseResultEnum.values();
        ResponseResultEnum v = null;
        for (int i = 0; i < values.length; i++) {
            if (values[i].toCode().equals(code)) {
                v = values[i];
                break;
            }
        }
        return v;
    }

    /**
     * 按英文编码获取对应的枚举类型
     *
     * @param name 英文编码
     * @return 枚举类型
     */
    public static ResponseResultEnum enumValueOf(String name) {
        ResponseResultEnum[] values = ResponseResultEnum.values();
        ResponseResultEnum v = null;
        for (int i = 0; i < values.length; i++) {
            if (values[i].toName().equalsIgnoreCase(name)) {
                v = values[i];
                break;
            }
        }
        return v;
    }

    /**
     * 获取枚举类型的所有<数字编码,中文描述>对
     *
     * @return
     */
    public static TreeMap<Integer, String> toCodeDescriptionMap() {
        TreeMap<Integer, String> map = new TreeMap<Integer, String>();
        for (int i = 0; i < ResponseResultEnum.values().length; i++) {
            map.put(ResponseResultEnum.values()[i].toCode(), ResponseResultEnum.values()[i].toDescription());
        }
        return map;
    }

    /**
     * 获取枚举类型的所有<英文编码名称,中文描述>对
     *
     * @return
     */
    public static TreeMap<String, String> toNameDescriptionMap() {
        TreeMap<String, String> map = new TreeMap<String, String>();
        for (int i = 0; i < ResponseResultEnum.values().length; i++) {
            map.put(ResponseResultEnum.values()[i].toName(), ResponseResultEnum.values()[i].toDescription());
        }
        return map;
    }
}
