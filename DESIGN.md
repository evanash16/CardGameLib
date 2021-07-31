# Cards

## Value

An enum which can be converted to a string, or numerical card value.

```Java
enum Value {
    ACE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING
}
```

### Methods
* getNumericalValue() &rarr; int : returns the numerical value (ex. `Value.ACE.getNumericalValue()` &rarr; `1`)
* toString() &rarr; String : returns the string representation of the `Value` (ex. `Value.ACE.toString()` &rarr; `"A"`)

## Suit

An enum which can be converted to a string.

```Java
enum Suit {
    CLUBS("♣"),
    DIAMONDS("♦"),
    HEARTS("♥"),
    SPADES("♠")
}
```

### Methods
* toString() &rarr; String : returns the string representation of the `Suit` (ex. `Suit.CLUBS.toString()` &rarr; `♣`)

## Card

Represents a playing card. There are 52 possible cards (13 values, 4 suits).

### Attributes
* faceUp (boolean)
* value (Value)
* suit (Suit)

### Methods
* isFaceUp() &rarr; boolean : returns whether or not the card is face up or face down
* setFaceUp(boolean faceUp) : sets the face up state
* getValue() &rarr; Value : returns the `Value` of the card
* getSuit() &rarr; Suit : returns the `Suit` of the card
* getNumericalValue() &rarr; int : returns the numerical value of the card
* getCardIndex() &rarr; int : returns the index of the card (ex. `ACE_OF_CLUBS.getCardIndex()` &rarr; `0`)
* toString() &rarr; String : returns the string representation of the card (ex. `ACE_OF_SPADES.toString()` &rarr; `A♠`). If the card is facedown, this function returns `??`.
* hashCode() &rarr; int : returns a unique hash for a given card (ex. card index)
* equals(Object other) &rarr; boolean : returns `true` if other is a `Card`, and shares the same attributes

# Storage

## Hand

A class which stores cards for players.

### Attributes
* cards (List<Card>)
* present (Set<Cards>)

### Methods
* setFaceUp(boolean faceUp) : sets the face up state of all of the cards
* getCards() &rarr; List<Card> : returns the cards in the hand
* addCard(Card card) : adds the card to the hand
* addCards(List<Card> cards) : adds the cards to the hand
* moveCards(List<Card> cards) &rarr; Hand : moves the cards into a new hand, returning the new hand. If any of the cards are missing from the hand, an exception should be thrown.
* moveCards(List<Card> cards, Hand hand) : moves the cards into the specified hand. If any of the cards are missing from the hand, an exception should be thrown.
* removeCards() &rarr; List<Card> : removes the cards from the hand and returns the removed cards
* isPresent(Card card) &rarr; boolean : returns `true` if the card is present in the hand. Otherwise, returns `false`.
* score(Function<Hand, Score> scoringFunction) &rarr; Score : returns the score for the hand 
* score(Hand other, BiFunction<Hand, Hand, Score> scoringFunction) &rarr; Score : returns the score of the hand against another hand
* toString() &rarr; String : returns the string representation of the hand (ex. `BLACKJACK.toString()` &rarr; `A♠ K♠`).
* hashCode() &rarr; int : returns a unique hash for a given hand

## Deck

A class which stores cards for dealers.

### Attributes
* cards (List<Card>)
* dealt (Set<Card>)
* discarded (List<Card>)

### Methods
* shuffle() : recombines the discard pile, and shuffles the deck one time. If any cards are still in play, an exception should be thrown.
* shuffle(int n) : shuffles the deck n times. If any cards are still in play, an exception should be thrown.
* pick() &rarr; Card : picks on card and returns it. If there aren't enough cards remaining, an exception should be thrown.
* pick(int n) &rarr; List<Card> : picks n cards and returns them. If there aren't enough cards remaining, an exception should be thrown.
* discard(Card card) : discards a card (this constitutes moving the card from `dealt` into `discarded`). If the card hasn't been dealt, an exception should be thrown.
* discard(List<Card> cards) : discards a list of cards. If any of the cards haven't been dealt, an exception should be thrown.
* getRemainingCardCount() &rarr; int : returns the number of cards remaining.

# Evaluation

## Rule

An abstract class for evaluating whether or not a hand passes a local condition, or a global condition relative to another hand.

If a rule is only evaluated locally (no external context required), the "global" rule method should return `true`.
If a rule is only evaluated globally (external context required), the "local" rule method should return `true`.

### Methods
* passes(Hand hand) &rarr; boolean : returns `true` if a hand passes the rule. Otherwise, returns `false`.
* passes(Hand hand, Hand other) &rarr; boolean : returns `true` if a hand with another hand passes a rule.

## Score

An interface for representing the value of a given hand.

### Methods
* getNumericalValue() &rarr; int : a function which returns the numerical value of a given score.
* isGreaterThan(Score other) &rarr; boolean : a function which returns `true` if the current `Score` is greater than `other`. Otherwise, returns `false`.
* isLessThan(Score other) &rarr; boolean : a function which returns `true` if the current `Score` is less than `other`. Otherwise, returns `false`.
* isEqualTo(Score other) &rarr; boolean : a function which returns `true` if two scores are equivalent

# Games

## Player

An abstract class for representing a player in a game.

### Attributes
* hands (List<Hand>)
* currentHand (int)

### Methods
* addHand() &rarr; Hand : adds a new empty hand, returning the new hand
* addHand(Hand hand) : adds a predefined hand to the end
* getHand() &rarr; Hand : returns the current hand. If there are no hands, an exception should be thrown.
* getHands() &rarr; List<Hand> : returns all hands. If there are no hands, an exception should be thrown.
* removeHands() &rarr; List<Hand> : removes the hands and returns the removed hands. If there are no hands, an exception should be thrown.
* nextHand() &rarr; Hand : returns the next hand which becomes the current hand. If there are no additional hands, this returns `null`. If there are no hands, an exception should be thrown.

## Dealer

An abstract class for representing a dealer in a game.

### Attributes
* deck (Deck)

### Methods
* shuffle() : wraps `deck.shuffle()`. If there are cards in play, an exception should be thrown.
* shuffle(int n) : wraps `deck.shuffle(n)`. If there are cards in play, an exception should be thrown.
* deal(int n) &rarr; Hand : deals n cards into a new hand, returning the new hand. If there aren't enough cards left in the deck, an exception should be thrown.
* deal(int n, Hand hand) : deals n cards into a specified hand. If there aren't enough cards left in the deck, an exception should be thrown.
* collect(Hand hand) : removes the cards from the hand and discards them. If any of the cards haven't been dealt, an exception should be thrown.
* collect(List<Hand> hands) : removes the cards from the hands and discards them. If any of the cards in any of the hands haven't been dealt, an exception should be thrown.

## Game

An abstract class for representing a game.

### Attributes
* dealer (Dealer)
* players (List<Player>)

### Methods
* play() : deals the appropriate number of cards to all players, and makes moves for the players or requests user input
* score() : scores the hands of each player
* reset() : resets the game (ex. collects hands and shuffles the deck)
