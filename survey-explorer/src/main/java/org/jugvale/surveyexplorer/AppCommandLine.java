/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jugvale.surveyexplorer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Stream;

/**
 *
 * @author william
 */
public class AppCommandLine {
    /*
    
     identificador da entrevista,
     região onde foi realizada a entrevista,
     idade: qual a sua idade (em anos completos)?
     área,
     sexo,
     cor ou raça
     última série escolar que concluiu com aprovação,
     até que série o seu pai estudou,
     até que série a sua mãe estudou,
     renda total do chefe da família no último mês
     renda total de todos os moradores (parentes e agregados) no último mês,
     número de moradores no domicílio (parentes e agregados),
     "no último mês, alguma pessoa deste domicílio recebeu rendimentos do bolsa famíli",
     my world - acesso a alimentos de qualidade,
     my world - governo honesto e atuante,
     my world - apoio às pessoas que não podem trabalhar,
     my world - educação de qualidade,
     my world - melhoria nos transportes e estradas,
     "my world - proteção a florestas, rios e oceanos",
     my world - liberdades políticas,
     my world - combater as mudanças climáticas,
     my world - melhoria dos serviços de saúde,
     my world - acesso ao telefone e à internet,
     my world - eliminação do preconceito e da discriminação,
     my world - acesso à água potável e ao saneamento,
     my world - igualdade entre homens e mulheres,
     my world - acesso à energia em sua casa,
     my world - proteção contra o crime e a violência,
     my world - melhores oportunidades de trabalho,
     religião,
     mulheres que usam roupas que mostram o corpo merecem ser atacadas,
     as mulatas são mais fogosas do que as mulheres brancas,
     dá para entender que um homem que cresceu em uma família violenta agrida sua mul,
     os homens devem ser a cabeça do lar,
     casos de violência dentro de casa devem ser discutidos somente entre os membros ,
     "incomoda ver dois homens, ou duas mulheres, se beijando na boca em público",
     "se as mulheres soubessem como se comportar, haveria menos estupros",
     homem que bate na esposa tem que ir para a cadeia,
     é violência falar mentiras sobre uma mulher para os outros,
     toda mulher sonha em se casar,
     o que acontece com o casal em casa não interessa aos outros,
     "quando há violência, os casais devem se separar",
     casais de pessoas do mesmo sexo devem ter os mesmos direitos dos outros casais,
     dá pra entender que um homem rasgue ou quebre as coisas da mulher se ficou nervo,
     um homem pode xingar e gritar com sua própria mulher,
     é da natureza do homem ser violento,
     "em briga de marido e mulher, não se mete a colher",
     a roupa suja deve ser lavada em casa,
     uma mulher só se sente realizada quando tem filhos/as,
     "a mulher casada deve satisfazer o marido na cama, mesmo quando não tem vontade",
     "piada de preto é só brincadeira, não é racismo",
     um casal de dois homens vive um amor tão bonito quanto entre um homem e uma mulh,
     mulher que é agredida e continua com o parceiro gosta de apanhar,
     casamento de homem com homem ou de mulher com mulher deve ser proibido,
     a mulher que apanha em casa deve ficar quieta para não prejudicar os filhos,
     a questão da violência contra as mulheres recebe mais importância do que merece,
     "tem mulher que é pra casar, tem mulher que é pra cama"    
     */

    public static void main(String args[]) throws Exception {
        Map<String, Map<String, Integer>> results = SurveyData.load();
        results.keySet().stream().forEach(k -> {
            System.out.println(k);
            results.get(k).entrySet().forEach(e -> {
                System.out.printf("\t%s: %d\n", e.getKey(), e.getValue());
            });
        });
    }

}
