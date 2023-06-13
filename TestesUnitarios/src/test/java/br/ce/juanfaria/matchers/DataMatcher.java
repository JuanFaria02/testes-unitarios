package br.ce.juanfaria.matchers;

import br.ce.juanfaria.utils.DataUtils;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DataMatcher extends TypeSafeMatcher<Date> {
    private Integer qtdDias;
    public DataMatcher(Integer qtdDias) {
        this.qtdDias = qtdDias;
    }


    //Realiza comparação
    @Override
    protected boolean matchesSafely(Date item) {
        return DataUtils.isMesmaData(item, DataUtils.obterDataComDiferencaDias(qtdDias));
    }


    @Override
    public void describeTo(Description description) {

    }
}
