# Splendor

Proposal: Creation of a functional game with the tools we have learned so far in the MindSwap course.
Date: from February 22 to February 28, 2022.
Group: Diogo Noronha, Luís Faria, Ricardo Paiva and Tiago Miranda.
Chosen game: Splendor, game of chip-collecting and card development.

Splendor

Game components:
- 7 ((W)hite) tokens
- 7 (bl(U)e) tokens 
- 7 ((G)reen) tokens 
- 7 ((R)ed) tokens 
- 7 (blac(K)) tokens
- 5 Go(L)d tokens (yellow)

- 90 Mine cards - with 3 different tiers (Tier 1, tier 2, tier3).
- 10 Noble tiles - Tier 4

Setup
- Shuffle each Mine and Noble card tier separately, and then place them in a column in the middle of the table in increasing order from bottom to top (tier1, tier2, tier3 and tier4).
- Then reveal 4 cards from each level.

The Mine Cards - tier 1, tier 2 and tier 3.
- To win points, the players must purchase Mine cards. These cards are visible in the middle of the table and may be purchased by all players during the game.
- The Mine in hand are the cards which the players reserve throughout the game. Mine in hand may only be purchased by the players holding them.

The Noble Tiles - tier 4.
- The noble cards are visible in the upper table. At the end of their turn, a player automatically receives the visit from a noble if that player has the amount of bonuses (and only bonuses) required, and they get the corresponding tile.
- A player cannot refuse a visit from a noble.
- Receiving a noble isn't considered to be an action.


Game Play
A random player is chosen to begin the game.
On their turn, a player must choose to perform only one of the following four actions:

How to play:

1 - Initialize Sever trough Main.
2 - Initialize Client

Then,
You will have an updated board each round where you choose several actions:

1)	Take 3 tokens of different colors or take 2 tokens of the same color.
This action is only possible if there are at least 4 tokens of the chosen color left when the player takes them 
- It is possible to perform this action though console input of:  GRAB + number of each token needed according the following format: GRAB 0W 0U 0G 0R 0B 0L

2)	Reserve 1 Mine card and take 1 Go(L)d token.
- It is possible to perform this action though console input of:  RESERVE + Position of the card on the board

3)	Purchase 1 face-up Mine card from the middle of the table or a previously reserved one.
- It is possible to perform this action though console input of: BUY 0W 0U 0G 0R 0B 0L

You can always get help by typing HELP to console

Note: You can only  Buy or Reserve a card if you have sufficient funds to it, any kind of fraudulent purchase will be blocked by our bulletproof code~.


Selecting Tokens
A player can never have more than 10 tokens at the end of their turn (including jokers). If this happens, they must return tokens until they only have 10 left. A player can return all or some of those they've just drawn. The tokens owned by a player must be visible by all players at all times.
Note: players may not take 2 tokens of the same color if there are less than 4 tokens available of that color.

Reserve a Mine card
To reserve a card, a player simply needs to take a face-up development from the middle of the table or (if you're feeling lucky) draw the first card from one of the three decks (tier 1; tier 2 ; tier 3) without showing it to the other players.

The reserved cards are kept in hand and cannot be discarded. Players may not have more than three reserved cards in hand, and the only way to get rid of a card is to buy it.

Reserving a card is also the only way to get a gold token (joker). If there is no gold left, you can still reserve a card, but you won't get any gold.

Buying a Mine card
To purchase a card, a player must spend the number of tokens indicated on the card. A joker token can replace any color. The spent tokens (including any jokers) are returned to the middle of the table.

A player may purchase one of the face-up development cards in the middle of the table or a card in his hand that was reserved on a previous turn.

Each player makes distinct rows with the acquired development cards by sorting them by color, and staggering them vertically so that their bonuses and prestige point values are visible.

The bonuses and prestige points granted by each card must be visible to all at all times.

Note: when a development card from the middle of the table is acquired or reserved, it must immediately be replaced by a card of the same level.

At all times during the game, there must be 4 face-up cards of each level (unless the deck in question is empty, in which case the empty spaces also remain empty).

The Bonuses
The bonuses a player has from MIne cards acquired on previous turns provide discounts on the purchase of new cards. Each bonus of a given color is equal to a token of that color.
Thus, if a player has 2 blue bonuses and wants to purchase a card which costs 2 blue tokens and 1 green token, the player must only spend 1 green token.
If a player has enough development cards (and therefore bonuses), they can even purchase a card without spending any tokens.

The Nobles
At the end of their turn, each player checks the noble tiles in order to determine if they're receiving a visit from one of them. A player can be visited if they have (at least) the quantity and type of bonuses indicated on the noble tile.
It is impossible to refuse the visit from a noble, which is not considered to be an action.
If a player has enough bonuses to be visited by more than one noble at the end of their turn, that player chooses the noble to be received.
The tile obtained is placed face-up in front of the player in question.


End of the Game
When a player reaches >= 15 prestige points, complete the current round so that each player has played the same number of turns.
The player who then has the highest number of prestige points is declared the winner (don't forget to count your nobles).

