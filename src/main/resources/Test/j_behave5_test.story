
Scenario: Quick scenario should pass
When short wait
Then something

Scenario: slow and failing test
When slow wait
Then something

Scenario: Another quick scenario should also report this
When short wait
Then something
