package com.jess.myrealtrip.common.util

object StringUtils {
    /**
     * 특수문자 여부
     *
     * @param text
     * @return
     */
    fun hasSpecialText(text: String?): Boolean {
        text?.let {
            return !text.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*".toRegex())
        } ?: return false
    }
}