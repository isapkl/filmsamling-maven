# FURPS – vores filmsamling

|   | Krav                                                                                            | Opfyldt?    | Hvor |
|---|-------------------------------------------------------------------------------------------------|-------------|---|
| F | Man kan søge på en del af en titel                                                              | Ja          | US4, `MovieCollection.searchMovies` |
| U | Brugeren skal kunne vælge funktioner gennem en tydelig menu ved at indtaste det tilhørende tal. | Ja          | UserInterface, metoden startProgram()|
| R | Programmet går ikke ned ved bogstaver i årstal                                                  | Nej – del 6 | |
| P | Programmet skal vise menuen inden for 2 sekunder ved opstart med 1.000 film                     | Ikke testet | UserInterface, metoden startProgram()|
| S | Koden skal være opdelt i klasser med høj cohesion/ansvar, så den let kan rettes og testes       | Ja, delvist | UserInterface, Controller, MovieCollection og Movie|
