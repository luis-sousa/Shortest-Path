# Caminho mais curto
Solu��o criada no ambito da disciplina de Estrutura de Dados do curso de Engenharia Inform�tica por Lu�s Sousa, 8090228@estg.ipp.pt.
A solu��o usa grafos com pesos para encontrar o melhor caminho para um m�dico que est� no meio do tr�nsito para chegar ao hospital.

Um m�dico precisa chegar ao bloco operat�rio de forma a poder fazer uma cirurgia de emerg�ncia o mais r�pido poss�vel. Para isso � necess�rio encontrar o caminho mais curto entre o ponto onde se encontra e o bloco operat�rio do hospital. 

Na Figura abaixo , temos a representa��o dos poss�veis trajectos que o m�dico pode seguir at� chegar ao hospital, hospital esse que se situa no v�rtice �N13� e a posi��o do m�dico representa-se pelo v�rtice �N1�. 
![alt text](https://raw.githubusercontent.com/luis-sousa/Shortest-Path/master/trajetos.png)


Na Tabela abaixo, temos a representa��o para cada tro�o do gr�fico: o comprimento do tro�o, a qualidade do pavimento, o tempo que o m�dico demora a percorrer o respectivo tro�o em dois momentos hor�rios diferentes, em hora normal e em hora de ponta.
Toda esta tabela est� representada nuum excel, usando a API Jexcelapi - http://jexcelapi.sourceforge.net. Neste caso foi criado um "mapa" para a tabela abaixo apresentada.

![alt text](https://raw.githubusercontent.com/luis-sousa/Shortest-Path/master/mapa.png)




# Ecr� da aplica��o 

Exemplo:
Caminho mais curto entre o vertice n1 (operador) e n13 (bloco operat�rio), em que o pavimento tenha pelo menos qualidade 3.

![alt text](https://raw.githubusercontent.com/luis-sousa/Shortest-Path/master/caminho.png)