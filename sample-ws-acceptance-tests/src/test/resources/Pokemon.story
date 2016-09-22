Pokemon Story

Narrative:
In order to be the very best
As a Pokemon Trainer
I want to know about my Pokemon
					 
Scenario:  Get a pokemon by id
Given A Pokemon of species 'Bulbasaur' and level 5
When The user retrieves the Pokemon
Then The status code of the response is 200
And The Pokemon has a 'nickname' of 'Bulbasaur'
And The Pokemon has a 'primaryType' of 'GRASS'
And The Pokemon has a 'secondaryType' of 'POISON'


Scenario: Get a pokemon that doesn't exist
When The user retrieves the Pokemon with id '28430eca-1fb8-4eb9-a423-93303607521e'
Then The status code of the response is 404
