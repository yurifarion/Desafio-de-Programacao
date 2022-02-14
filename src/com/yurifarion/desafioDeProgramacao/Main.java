package com.yurifarion.desafioDeProgramacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Primeiramente preciso saber qual questão você quer testar
        System.out.print("Qual questão gostaria de testar ?(ex. 1, 2, 3)");
        Scanner sc0 = new Scanner(System.in);
        int questao = sc0.nextInt(); // Pegamos apenas inputs do tipo 'int'


        //QUESTAO 1
        if(questao <= 1){
            System.out.print("Entre com o número de andares");
            //Scanner é a classe responsável pelo input do usuário
            // Scanner scanner = new Scanner(System.in);
            Scanner sc1 = new Scanner(System.in);
            int andares = sc1.nextInt();
            ConstruirEscada(andares);
        }

        else if(questao == 2){
            System.out.print("Escreva a sua senha:");
            Scanner sc2 = new Scanner(System.in);
            String senha = sc2.nextLine();

            int faltantes = SenhaForte(senha);

            if(faltantes <= 0 )   System.out.print("Senha forte");
            else System.out.print(faltantes);
        }
        else if(questao >= 3){
            System.out.print("Escreva a palavra para o anagrama:");
            Scanner sc3 = new Scanner(System.in);
            String anam = sc3.nextLine();
            System.out.print(Anagrama(anam));
        }


    }
    //QUESTAO 1
    static void ConstruirEscada(int a){
        String line = "";
        for(int i = 0 ; i <= a ; ++i){
            line="";//criei uma linha de string vazia

            for(int j = 0 ; j < a ; ++j) {

                if(j < (a-i)){
                    line += " ";
                }
                else{
                    line += "*";
                }

            }
            System.out.print(line+'\n');//'\n' é usado para quebrar a linha
        }
    }
    //QUESTAO 2 - Essa função retorna o numero de caracteres que faltam para o tamanha mínimo
    static int SenhaForte(String senha){

        //numero atuais de caracteres
        int numeroAtual = senha.length();
        int minimo = 6;

        return minimo - numeroAtual;
    }


    //QUESTAO 3 - função principal da questão 3
    static int Anagrama(String palavra){

        //Lista que vai armazenar todos os substrings possíveis, ou seja, aqueles que tem o numero de caracteres iguais
        //ou menor que a metade do string mae
        ArrayList<String> todosSubstrings = new ArrayList<String>();


        //encher a lista
        for(int i = 0; i < palavra.length(); ++i){

            //((palavra.length()-1) - i) é o número de letras que ainda existem depois do index i
            for(int j = 1; j < ((palavra.length()+1) - i); ++j){
                    todosSubstrings.add(PegaPalavra(palavra, i, j));

            }
        }
        int contadorDeAnagramas = 0;

            //Vou contar quantos anagrama existem naquela lista
            for(int i = 0; i < todosSubstrings.size();++i) {

                for (int j = i; j < todosSubstrings.size() ; ++j) {

                    if (IssoEhAnagrama(todosSubstrings.get(i), todosSubstrings.get(j))) {

                        contadorDeAnagramas++;
                    }
                }

            }


        return contadorDeAnagramas;
    }

    //Função auxiliar para Questão 3- Essa função ela retorna uma string de strg que tem inicio no index e um comprimento de length
    //por exemplo PegaPalavra("Abacaxi",2,5) retorna "acaxi".
    //usaremos ela para preencher a nossa lista de todas as substrings possíveis
    static String PegaPalavra(String strg, int index, int length){
        String result = "";

        int count = 1;//Essa variavel conta quantas letras foram adicionadas na string principal
        for(int i = 0; i < strg.length(); ++i){

            if(i >= index  && count <= length){
                result += strg.charAt(i);
                count++;
            }
        }
        //teste

        return result;
    }
    //Função auxiliar para a Questão 3 - Checa se é um anagrama, se sim retorna true se não false
    static boolean IssoEhAnagrama(String strg1, String strg2) {

        if(strg1.length() != strg2.length()) return false;//Impossivel ser um anagrama se o numero de letras é diferent

        if(strg1 == strg2) return false; //não é anagrama se for a mesma palavra

        int length = strg1.length();//serve para ambas palavras
        char[] ch1 = new char[strg1.length()];
        char[] ch2 = new char[strg2.length()];

        //transformo as strings em char[], posso usar um for só, por que ambos devem ter o mesmo Length
        for(int i = 0; i < length;++i) {
            ch1[i] =  strg1.charAt(i);
            ch2[i] =  strg2.charAt(i);
        }

        //uso sort para ambos os Arrays
        Arrays.sort(ch1,0,length);
        Arrays.sort(ch2,0,length);

        //Checar agora realmente se é um anagrama
        for(int i = 0; i < length;++i){
            if(ch1[i] != ch2[i]){
                return false; // não é um anagrama
            }
        }

        //se ambos sobreviveram até aqui, então é um anagrama mesmo :)
        return true;
    }

}
