package com.microboat.common.text

interface TemplateEvaluatorDecorator {
    fun decorate(templateEvaluator: TemplateEvaluator): TemplateEvaluator
}