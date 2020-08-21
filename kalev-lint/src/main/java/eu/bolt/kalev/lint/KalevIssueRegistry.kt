package eu.bolt.kalev.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.Issue

@Suppress("UnstableApiUsage")
class KalevIssueRegistry : IssueRegistry() {
    override val issues: List<Issue>
        get() = listOf(LintDetector.demoIssue)

    override val api: Int = com.android.tools.lint.detector.api.CURRENT_API
}
