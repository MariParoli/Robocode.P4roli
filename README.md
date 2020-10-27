# Robocode.P4roli
Código de robô criado para o evento Solutis Robot Arena, utilizando Robocode.

## Não seja pego pelo scan! ##
O robô **P4roli** é um robô que herda a classe *AdvancedRobot*. 
Originalmente foi pensado a partir do robô simples - **Tracker** de *Mathew Nelson*, com a implementação do *linear targeting*, o qual pressupõe que o robô escaneado continuará na mesma direção e velocidade. Para isso utilizei o cálculo da velocidade lateral do inimigo, na direção perpendicular ao meu robô.


## Ponto Forte ##
* Antes de qualquer movimento, o robô faz o escaneamento. Assim, não perde tempo e nem energia, pois está focado em encontrar seu oponente;
* A implementação do *linear targeting*, o qual fornece o direcionamento linear, pressupõe o movimento paralelo entre os robôs, executando a ação de atirar. Logo, isso aumenta o alcance do scan e, consequentemente, tem maiores chances de atingir o oponente.

## Ponto Fraco ##
* Por conta do poder máximo de fogo e sua movimentação, a qual ignora as paredes, em algumas partidas o robô fica desativado por falta de energia. Isso acontece em algumas rodadas quando compete com vários robôs, ou com o robô *Crazy*, o qual tem padrão de movimento disruptivo.
