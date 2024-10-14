package fpl.md07.beeslearn.data

import fpl.md07.beeslearn.models.ConnectingWordQuestionModel

// Fake data for connecting word questions
val connectingWordQuestions = listOf(
    ConnectingWordQuestionModel(
        columnA = listOf(
            "1. We visited Hyde Park,",
            "2. The police needed details",
            "3. Mr. Smith,",
            "4. I'd like to take you to a café",
            "5. The skirt,"
        ),
        columnB = listOf(
            "a. which serves excellent coffee",
            "b. which is close to Buckingham Palace.",
            "c. that could help identify the robber.",
            "d. who works with me, has invited me to a party.",
            "e. which is a lovely dark blue colour, only cost £10"
        )
    )
)
