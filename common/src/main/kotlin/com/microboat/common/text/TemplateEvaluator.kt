package com.microboat.common.text

import java.util.LinkedList

interface TemplateEvaluator {

    fun eval(str: String): Any?

    companion object {
        fun from(supplier: (String) -> Any?): TemplateEvaluator {
            return TemplateEvaluatorFromSupplier(supplier)
        }

        fun from(context: Map<String?, Any?>): TemplateEvaluator {
            return TemplateEvaluatorFromMap(context)
        }

        fun nop(): TemplateEvaluator {
            return NopTemplateEvaluator.INSTANCE
        }
    }

}


/**
 * NOP TemplateEvaluator
 *
 * @author zhouwei
 * */
private class NopTemplateEvaluator : TemplateEvaluator {
    override fun eval(str: String): Any {
        return str
    }

    companion object {
        val INSTANCE = NopTemplateEvaluator()
    }
}

fun TemplateEvaluator.union(candidate: TemplateEvaluator): TemplateEvaluator {
    return if (this is ComponentEvaluator || candidate is ComponentEvaluator) {
        val evaluators = LinkedList<TemplateEvaluator>()
        this.flat { evaluators.add(it) }
        candidate.flat { evaluators.add(it) }
        MultipleTemplateEvaluator(evaluators.toTypedArray())
    } else {
        UnionTemplateEvaluator(this, candidate)
    }
}

fun TemplateEvaluator.flat(handle: (TemplateEvaluator) -> Unit) {
    if (this is ComponentEvaluator) {
        for (component in this.components()) {
            component.flat(handle)
        }
    } else {
        handle(this)
    }
}

/**
 * 来自 Supplier 的 TemplateEvaluator
 *
 * @author zhouwei
 * */
private class TemplateEvaluatorFromSupplier(private val supplier: (String) -> Any?) : TemplateEvaluator {
    override fun eval(str: String): Any? {
        return supplier(str)
    }
}

/**
 * 来自 Map 的 TemplateEvaluator
 *
 * @author zhouwei
 * */
private class TemplateEvaluatorFromMap(private val context: Map<String?, Any?>) : TemplateEvaluator {
    override fun eval(str: String): Any? {
        return context[str]
    }
}

/**
 * ComponentEvaluator
 *
 * @author zhouwei
 * */
private interface ComponentEvaluator : TemplateEvaluator {
    fun components(): Array<TemplateEvaluator>
}

/**
 * UnionTemplateEvaluator
 *
 * @author zhouwei
 * */
private class UnionTemplateEvaluator(
    private val templateEvaluator: TemplateEvaluator,
    private val candidate: TemplateEvaluator
) : ComponentEvaluator {
    override fun components(): Array<TemplateEvaluator> {
        return arrayOf(templateEvaluator, candidate)
    }

    override fun eval(str: String): Any? {
        return templateEvaluator.eval(str) ?: candidate.eval(str)
    }
}

/**
 * MultipleTemplateEvaluator
 *
 * @author zhouwei
 * */
private class MultipleTemplateEvaluator(private val evaluators: Array<TemplateEvaluator>) : ComponentEvaluator {
    override fun components(): Array<TemplateEvaluator> {
        return evaluators
    }

    override fun eval(str: String): Any? {
        for (evaluator in evaluators) {
            evaluator.eval(str)?.let { return it }
        }
        return null
    }
}
