Feature: Products controller tests

    Scenario Outline: Products make POST call
        Given products make POST call with "<description>" and "<deliveryStatus>"
        Then products receives POST status code <status> with data "<description>"

        Examples:
            | status |   description   | deliveryStatus |
            |  201   | testDescription |   DISPATCHED   |
            |  201   |   description2  |   DELIVERED   |

    Scenario Outline: Products make GET call
        Given products make GET call with "<deliveryStatus>"
        Then products receives GET status code <status> and the amount is <size>

        Examples:
            | status |   deliveryStatus  | size |
            |  200   |         null      |  3   |
            |  200   |     DISPATCHED    |  2   |
            |  200   |     DELIVERED     |  1   |

    Scenario Outline: Products make GET call with ID
        Given products make GET call with ID <id>
        Then products receives GET status code <status> with description "<description>"

        Examples:
            | status |   description   | id |
            |  200   | testDescription |  1 |
            |  200   |   description2  |  2 |

    Scenario Outline: Products make PUT call
        Given products make PUT call with ID <id> and "<description>"
        Then products receives PUT status code <status> with description "<description>"

        Examples:
            | status |   description   | id |
            |  200   |  newDescription |  1 |
            |  200   |  newDescription |  2 |