## Major Update One -> 12/20/2023 - 05/02/2024
### Summary 
This sheet is to serve as a general update for the state of the game to list out accomplished tasks and those that still need to be completed. Also, I want to have a definitive paper trail showcasing progress at intervaled steps, so I can look back if needed.
## Goals
| Overall Goals                             | Status  | 
|-------------------------------------------|---------|
| Get used to library and frameworks        | Ongoing |
| Research/planning of game and its systems | Ongoing |

| Gameplay Goals                                        | Status                         |
|-------------------------------------------------------|--------------------------------|
| Player can walk around                                | Completed                      |
| Player can interact with objects (static and dynamic) | Completed (fine-tuning needed) |
| Camera (accurately) follows player                    | Completed                      |


## Significant changes/decisions
- Decided to go with Tiled as the 2D editor over Hyperlap2D

- Reasons for Tiled:
  1. Better UI
  2. Better documentation
  3. Support for whole tile sets
  4. No crashes/known bugs

## Testing
- For right now no unit testing was done, namely for the sake of time and wanting to get the groundwork established. Initially there weren't any systems warranting thorough testing; however, for the future I would like to test the following systems: 
  1. Tick class
  2. EntityHandler
- Note: manually testing was done throughout, especially when deciding whether to stick with Hyperlap2D when Tiled was discovered.

## Outcome
Overall the initial phase was slow at the beginning; however, once I decided to use Tiled and figured out the quirks of the Box2D library, development picked up pace. The only thing I would change from here and going on to the next phase is have more motivation. Once I discovered that Hyperlap2D was a hassle and I didn't think there was an alternative I lost a little hope. Therefore, as a note to future Sam, things will work out, have patience. 

## Future
Here is a list of items I want to implement in the near future.
1. The world itself (map)
2. Player animation
3. NPCs and animation
4. Implementation of tick system (will make game run smoother)
5. Multi-threading (make game run smoother and faster)
6. Other things a game needs to be fun… (combat, abilities, armour, weapons)

## Persons that contributed to the initial phase
1. Sam Bernau -> coding and game design
2. Zach Walensa (friend) -> Helpful in bouncing ideas off of and giving me suggestions when working with scala/game systems to implement

