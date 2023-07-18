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

val listOfNewsByQueryDTO: MutableList<GameNewsDTO>? = mutableListOf(
    GameNewsDTO(
        title = "Mario Kart 8 Deluxe Fans Point Out Missing Feature From One of DLC Wave 5’s Tracks",
        date = "Thu, 13 Jul 2023 01:09:40 GMT",
        description = "Wave 5 of the Mario Kart 8 Deluxe Booster Course Pass is available, but some fans are noticing a strange omission from one track.",
        image = "https://static0.gamerantimages.com/wordpress/wp-content/uploads/2023/07/mario-kart-8-deluxe-sunset-wilds-missing-feature.jpg",
        link = "https://gamerant.com/mario-kart-8-sunset-wilds-missing-feature-sunset/",
    ),
    GameNewsDTO(
        title = "Mario + Rabbids Sparks Of Hope Is Only $20 At Amazon During Prime Day",
        date = "Wed, 12 Jul 2023 14:44:00 -0700",
        description = "If you haven't picked up Mario + Rabbids: Sparks of Hope, now's the time to do it. Amazon is selling the excellent Nintendo Switch exclusive for only $20 as part of its Prime Day 2023 sale. Though Sparks of Hope has seen many regular discounts since launching last October, the $20 price is the best yet.",
        image = "https://www.gamespot.com/a/uploads/screen_medium/1702/17023653/4070430-sparksofhope.jpg",
        link = "https://www.gamespot.com/articles/mario-rabbids-sparks-of-hope-is-only-20-at-amazon-during-prime-day/1100-6509631/?ftag=CAD-01-10abi2f",
    ),
    GameNewsDTO(
        title = "Mario Golf: Super Rush Is 50% Off For Prime Day",
        date = "Tue, 11 Jul 2023 10:18:00 -0700",
        description = "Mario Golf: Super Rush is on sale for $30 at Amazon as part of the retailer's Prime Day 2023 sale. Both the digital and physical versions of the arcade golf game are eligible for this deal. You don't have to be a Prime member to be eligible for this discount. Needless to say, this is the lowest price ever for Super Rush.",
        image = "https://www.gamespot.com/a/uploads/screen_medium/1595/15950357/4164126-3849883-superrush.jpeg",
        link = "https://www.gamespot.com/articles/mario-golf-super-rush-is-50-off-for-prime-day/1100-6493365/?ftag=CAD-01-10abi2f",
    ),
)
