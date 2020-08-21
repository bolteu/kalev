package eu.bolt.kalev.lint

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression

@Suppress("UnstableApiUsage")
class LintDetector : Detector(), Detector.UastScanner {

    override fun getApplicableMethodNames(): List<String> {
        return listOf("tag",  "v", "d", "i", "w", "e")
    }

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        super.visitMethodCall(context, node, method)
        val methodName = node.methodName

        context.report(demoIssue, method, context.getLocation(node), "Visited method. $methodName")
    }

    companion object {

        val demoIssue = Issue.create(
            "demo-issue",
            "Brief",
            "Explanation",
            Category.MESSAGES,
            9,
            Severity.WARNING,
            Implementation(LintDetector::class.java, Scope.JAVA_FILE_SCOPE)
        )

    }

}