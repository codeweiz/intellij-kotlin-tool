package com.microboat.common.text

/**
 * 模板引擎
 *
 * @author zhouwei
 * */
interface TemplateEngine {

    /**
     * 渲染
     *
     * @param msg 消息
     * @param placeholder 占位符
     * @param templateEvaluator 模板计算器
     * @return 渲染结果
     * */
    fun render(msg: String, placeholder: CharArray, templateEvaluator: TemplateEvaluator): String?

}