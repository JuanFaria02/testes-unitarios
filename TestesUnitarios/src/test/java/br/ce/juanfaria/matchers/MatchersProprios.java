package br.ce.juanfaria.matchers;

import br.ce.juanfaria.utils.DataUtils;

import java.util.Calendar;
import java.util.Date;

public class MatchersProprios {
    public static DiaSemanaMatcher caiEm(Integer diaSemana){
        return new DiaSemanaMatcher(diaSemana);
    }
    public static DiaSemanaMatcher caiNumaSegunda(){
        return new DiaSemanaMatcher(Calendar.MONDAY);
    }

    public static DataMatcher eHojeComDiferencaDias(Integer dia){
        return new DataMatcher(dia);
    }

    public static DataMatcher eHoje(){
        return new DataMatcher(0);
    }

}
