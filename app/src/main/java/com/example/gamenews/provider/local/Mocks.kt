package com.example.gamenews.provider.local

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.example.gamenews.model.GameNewsDTO
import com.example.gamenews.model.GameNewsState

val listOfNews: List<GameNewsState> = mutableListOf(
    GameNewsState(
        title = "Gremlins: Secrets Of The Mogwai Trailer Hints At Gizmo’s Magical Origins",
        date = "Sun, 14 May 2023 02:00:53 +0000",
        description = LoremIpsum(words = 50).values.first(),
        image = "https://www.kotaku.com.au/wp-content/uploads/sites/3/2023/05/12/08933e4a622ddb89d721158f62ce8060.png?quality=80=1280,720",
        link = "https://www.kotaku.com.au/2023/05/gremlins-secrets-of-the-mogwai-trailer-hints-at-gizmos-magical-origins/",
    ),
    GameNewsState(

        title = "Gamer's Grandma Reaches Absurd Milestone in Animal Crossing: New Horizons",
        date = "Sun, 14 May 2023 01:20:18 GMT",
        description = "A gamer online shares their grandma's impressive achievement of reaching " +
            "an absurd Nook Miles milestone in Animal Crossing: New Horizons.",
        image = "https://asdf/static0.gamerantimages" +
            ".com/wordpress/wp-content/uploads/2023/05/animal-crossing-nook-miles-grandma.jpg",
        link = "https://gamerant.com/gamer-grandma-animal-crossing-new-horizons-milestone/",
    ),
    GameNewsState(
        title = "Pokemon Fan Creates a Fun Water-Type Regional Variant for Diancie",
        date = "Sun, 14 May 2023 01:11:32 GMT",
        description = "A Pokemon fan creates a very fun and creative regional variant of the mythical Pokemon Diancie, which comes with an equally inspired Mega Evolution.",
        image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2023/05/pokemon-diancie-and-ocean-photo.jpg",
        link = "https://gamerant.com/pokemon-diancie-water-type-regional-variant-fan-art/",
    ),
    GameNewsState(
        title = "Artist Designs NES-Style Xbox Controllers",
        date = "Sun, 14 May 2023 01:11:32 GMT",
        description = "An artist online shares their incredible fan-made NES-style Xbox controllers, providing a look into what it could have looked like back in the 1980s.",
        image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2023/05/nes-controller-xbox.jpg",
        link = "https://gamerant.com/xbox-custom-controllers-nes/",
    ),
    GameNewsState(
        title = "God of War: Ragnarok's Thor Struts Proudly After Breaking the Game",
        date = "Sun, 14 May 2023 00:46:43 GMT",
        description = "Thor struts away from Kratos after punching an undying draugr off the screen in a hilarious viral God of War: Ragnarok video.",
        image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2023/05/god-of-war-thor-upper-body.jpg",
        link = "https://gamerant.com/god-of-war-ragnarok-thor-funny-strut-training-kill-video-clip/",
    ),
)

val listOfNewsDTO: List<GameNewsDTO> = mutableListOf(
    GameNewsDTO(
        title = "Gremlins: Secrets Of The Mogwai Trailer Hints At Gizmo’s Magical Origins",
        date = "Sun, 14 May 2023 02:00:53 +0000",
        description = LoremIpsum(words = 50).values.first(),
        image = "https://www.kotaku.com.au/wp-content/uploads/sites/3/2023/05/12/08933e4a622ddb89d721158f62ce8060.png?quality=80=1280,720",
        link = "https://www.kotaku.com.au/2023/05/gremlins-secrets-of-the-mogwai-trailer-hints-at-gizmos-magical-origins/",
    ),
    GameNewsDTO(
        title = "Gamer's Grandma Reaches Absurd Milestone in Animal Crossing: New Horizons",
        date = "Sun, 14 May 2023 01:20:18 GMT",
        description = "A gamer online shares their grandma's impressive achievement of reaching " +
            "an absurd Nook Miles milestone in Animal Crossing: New Horizons.",
        image = "https://asdf/static0.gamerantimages" +
            ".com/wordpress/wp-content/uploads/2023/05/animal-crossing-nook-miles-grandma.jpg",
        link = "https://gamerant.com/gamer-grandma-animal-crossing-new-horizons-milestone/",
    ),
    GameNewsDTO(
        title = "Pokemon Fan Creates a Fun Water-Type Regional Variant for Diancie",
        date = "Sun, 14 May 2023 01:11:32 GMT",
        description = "A Pokemon fan creates a very fun and creative regional variant of the mythical Pokemon Diancie, which comes with an equally inspired Mega Evolution.",
        image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2023/05/pokemon-diancie-and-ocean-photo.jpg",
        link = "https://gamerant.com/pokemon-diancie-water-type-regional-variant-fan-art/",
    ),
    GameNewsDTO(
        title = "Artist Designs NES-Style Xbox Controllers",
        date = "Sun, 14 May 2023 01:11:32 GMT",
        description = "An artist online shares their incredible fan-made NES-style Xbox controllers, providing a look into what it could have looked like back in the 1980s.",
        image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2023/05/nes-controller-xbox.jpg",
        link = "https://gamerant.com/xbox-custom-controllers-nes/",
    ),
    GameNewsDTO(
        title = "God of War: Ragnarok's Thor Struts Proudly After Breaking the Game",
        date = "Sun, 14 May 2023 00:46:43 GMT",
        description = "Thor struts away from Kratos after punching an undying draugr off the screen in a hilarious viral God of War: Ragnarok video.",
        image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2023/05/god-of-war-thor-upper-body.jpg",
        link = "https://gamerant.com/god-of-war-ragnarok-thor-funny-strut-training-kill-video-clip/",
    ),
)
