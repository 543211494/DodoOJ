package com.lzy.oj.enums;

public enum LanguageEnum {

    JAVA("java"),
    PYTHON("python");

    private String value;

    LanguageEnum(String language) {
        this.value = language;
    }

    public static LanguageEnum getEnumByRole(String language){
        for(LanguageEnum languageEnum : LanguageEnum.values()){
            if(languageEnum.value.equals(language)){
                return languageEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }
}
