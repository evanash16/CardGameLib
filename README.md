# CardGameLib

A generic library for making card games played with one or more decks of cards.

## Overview
* `cardgamelib.cards` - contains all classes which describe a playing card (`Card`, `Value`, `Suit`)
* `cardgamelib.evaluation` - contains interfaces and abstract classes for scoring and evaluating game rules against hands (`Score`, `Rule`)
* `cardgamelib.exceptions` - contains a set of runtime exceptions thrown in entirely avoidable circumstances (see Best Practices below)
* `cardgamelib.games` - contains abstract classes concerning card games and their participants (`Game`, `Dealer`, `Player`)
* `cardgamelib.storage` - contains classes which store cards (`Deck`, `Hand`)

## Best Practices

### Try to avoid calling `player.nextHand()` outside of a loop.

> If you can have more than one player hand (in Blackjack, for example), I recommend the following:
> ```java
>     ...
>     do {
>         // retrieve the current hand
>         Hand currentHand = player.getHand();    
>         
>         // do stuff with the hand
>     } while (player.nextHand() != null);
>     ...
> ```

### Try to avoid instantiating `Card` singletons

> Calling `new Card(...)` makes your code liable to discard a card that was never dealt, which would result in a `CardNotDealtException`.

### Make sure to call `deck.discard(...)` or `dealer.collect(...)` after play concludes

> Calling `deck.discard(...)` or `dealer.collect(...)` after play concludes ensures that whatever `Deck` operations happen afterwards (shuffling, especially) can succeed. If these aren't called, calling `deck.shuffle(...)` will result in a `CardsInPlayException`.

### Keep track of the number of cards in the deck with `deck.getRemainingCardCount()`

> Keeping track of the number of cards will prevent the deck from running out of cards (resulting in an `EmptyDeckException`). Even if you catch the `EmptyDeckException` (a `RuntimeException`...but still catchable), you will have half-dealt the table and won't be able to deal more cards until all cards are collected.

### Call `player.addHand()` on all players before dealing people in

> Calling `player.addHand()` after creating a player will ensure that `player.getHand()` and `player.nextHand()` succeed, whether cards have been dealt or not. If `player.addHand()` is not called before cards are dealt, you'll likely run into a `NoHandsException`.
